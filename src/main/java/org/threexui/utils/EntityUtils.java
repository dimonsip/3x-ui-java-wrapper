package org.threexui.utils;

import org.threexui.entity.api.*;
import org.threexui.entity.enums.FlowEnum;
import org.threexui.entity.enums.StreamNetwork;
import org.threexui.utils.data.InboundData;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

public class EntityUtils {

    private static Inbound createInbound(
            String protocol,
            String settings,
            StreamSettings streamSettings,
            String remark,
            Long port
    ) {
        Inbound inbound = new Inbound();
        inbound.setSniffing(InboundData.defaultSniffing);
        inbound.setPort(Objects.requireNonNullElse(port, 443L));
        inbound.setEnable(true);
        inbound.setProtocol(protocol);
        inbound.setRemark(remark);
        inbound.setSettings(settings);
        inbound.setStreamSettings(streamSettings.toJson());
        return inbound;
    }

    private static ClientSettings createBaseClient(
            String email,
            Long totalBytes,
            Integer limitIP
    ) {
        String randomUUID = UUID.randomUUID().toString();
        if (email == null) {
            email = randomUUID;
        }
        if (totalBytes == null) {
            totalBytes = 0L;
        }
        if (limitIP == null) {
            limitIP = 0;
        }
        ClientSettings client = new ClientSettings();
        client.setEmail(email);
        client.setEnable(true);
        client.setTotalGB(totalBytes);
        client.setLimitIp(limitIP);
        return client;
    }

    private static ClientSettings createVlessClient(
            String id,
            String email,
            Long totalBytes,
            Integer limitIP,
            StreamNetwork streamNetwork
    ) {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        ClientSettings client =
                createBaseClient(email, totalBytes, limitIP);
        client.setId(id);
        client.setSubId(UUID.randomUUID().toString());
        if (streamNetwork == StreamNetwork.TCP) {
            client.setFlow(
                    FlowEnum.XLTS_RPRX_VISION.getValue()
            );
        }
        return client;
    }

    public static HysteriaClient createHysteriaClient(
            String auth,
            String email,
            String subId,
            Long totalBytes,
            Integer limitIP,
            Long expiryTime
    ) {

        String random = UUID.randomUUID().toString();

        long now = System.currentTimeMillis();

        if (auth == null) {
            auth = UUID.randomUUID().toString();
        }

        if (email == null) {
            email = random;
        }

        if (subId == null) {
            subId = random;
        }

        if (totalBytes == null) {
            totalBytes = 0L;
        }

        if (limitIP == null) {
            limitIP = 0;
        }
        HysteriaClient client =
                new HysteriaClient();
        client.setId(UUID.randomUUID().toString());
        client.setAuth(auth);
        client.setComment("");
        client.setCreated_at(now);
        client.setEmail(email);
        client.setEnable(true);
        client.setExpiryTime(expiryTime);
        client.setLimitIp(limitIP);
        client.setReset(0);
        client.setSubId(subId);
        client.setTgId("");
        client.setTotalGB(totalBytes);
        client.setUpdated_at(now);

        return client;
    }

    public static Inbound createDefaultVlessInbound(
            String id,
            String email,
            Long totalBytes,
            Integer limitIP,
            String remark,
            X25519Cert x25519Cert,
            String sni,
            Long port,
            StreamNetwork streamNetwork
    ) {
        if (remark == null) {
            remark = "My VLESS key";
        }
        if (x25519Cert == null) {
            throw new IllegalArgumentException("Generate and fill X25519Cert");
        }
        if (streamNetwork == null) {
            streamNetwork = StreamNetwork.TCP;
        }
        ClientSettings client = createVlessClient(id, email, totalBytes, limitIP, streamNetwork);
        Settings settings = new Settings();
        settings.setClients(Collections.singletonList(client));
        settings.setDecryption("none");
        settings.setFallbacks(new String[0]);
        StreamSettings streamSettings = StreamSettingsFactory.createStreamSettings(streamNetwork);
        streamSettings.getRealitySettings().getSettings().setPublicKey(x25519Cert.getPublicKey());
        streamSettings.getRealitySettings().setPrivateKey(x25519Cert.getPrivateKey());
        streamSettings.getRealitySettings().setShortIds(GenerateUtils.randomShortIdGenerator());
        if (sni != null) {
            streamSettings.getRealitySettings().setDest(sni + ":443");
            streamSettings.getRealitySettings().setServerNames(Collections.singletonList(sni));
        }
        return createInbound("vless", settings.toJson(), streamSettings, remark, port);
    }

    public static Inbound createDefaultHysteriaInbound(
            String auth,
            String email,
            String subId,
            Long totalBytes,
            Integer limitIP,
            String remark,
            EchCert echCert,
            String domain,
            String certPath,
            String keyPath,
            Long port
    ) {
        if (remark == null) {
            remark = "My Hysteria2 key";
        }
        if (certPath == null) {
            certPath = "/root/cert/" + domain + "/fullchain.pem";
        }
        if (keyPath == null) {
            keyPath = "/root/cert/" + domain + "/privkey.pem";
        }
        HysteriaClient client = createHysteriaClient(auth, email, subId, totalBytes, limitIP, 0L);
        HysteriaSettings settings = new HysteriaSettings();
        settings.setClients(Collections.singletonList(client));
        settings.setVersion(2);

        StreamSettings streamSettings = StreamSettingsFactory.createStreamSettings(StreamNetwork.HYSTERIA);
        streamSettings.setTlsSettings(new StreamSettings.TlsSettings(domain,
                Collections.singletonList(
                    new org.threexui.entity.api.StreamSettings.Certificate(
                            certPath,
                            keyPath
                    )
                ),
                Collections.singletonList("h3"),
                echCert.getEchServerKeys(),
                new org.threexui.entity.api.StreamSettings.TlsConfigSettings(
                        "ios",
                        echCert.getEchConfigList()
                )));

        return createInbound(StreamNetwork.HYSTERIA.toString().toLowerCase(), settings.toJson(), streamSettings, remark, port);
    }
}
