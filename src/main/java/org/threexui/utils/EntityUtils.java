package org.threexui.utils;

import org.threexui.entity.api.*;
import org.threexui.entity.enums.FlowEnum;
import org.threexui.impl.APIRequestData;
import org.threexui.utils.data.InboundData;

import java.util.Collections;
import java.util.UUID;

public class EntityUtils {

    public static Inbound createDefaultVlessInbound(
            String id,
            String email,
            Long totalBytes,
            Integer limitIP,
            String remark,
            X25519Cert x25519Cert
    ) {
        String randomUUID = UUID.randomUUID().toString();
        if (id == null) {
            id = randomUUID;
        }
        if (email == null) {
            email = randomUUID;
        }
        if (totalBytes == null) {
            totalBytes = 0L;
        }
        if (limitIP == null) {
            limitIP = 0;
        }
        if (remark == null) {
            remark = "My Vless key";
        }
        if (x25519Cert == null) {
            throw new IllegalArgumentException("Generate and fill X25519Cert");
        }

        Inbound inbound = new Inbound();
        Settings settings = new Settings();

        ClientSettings clientSettings = new ClientSettings();
        clientSettings.setId(id);
        clientSettings.setEmail(email);
        clientSettings.setFlow(FlowEnum.XLTS_RPRX_VISION.getValue());
        clientSettings.setEnable(true);
        clientSettings.setTotalGB(totalBytes);
        clientSettings.setLimitIp(limitIP);

        settings.setClients(Collections.singletonList(clientSettings));
        settings.setDecryption("none");
        settings.setFallbacks(new String[0]);

        StreamSettings streamSettings = APIRequestData.fromJson(InboundData.defaultStreamSettings, StreamSettings.class);
        streamSettings.getRealitySettings().getSettings().setPublicKey(x25519Cert.getPublicKey());
        streamSettings.getRealitySettings().setPrivateKey(x25519Cert.getPrivateKey());
        streamSettings.getRealitySettings().setShortIds(GenerateUtils.randomShortIdGenerator());

        inbound.setPort(443);
        inbound.setEnable(true);
        inbound.setProtocol("vless");
        inbound.setRemark(remark);
        inbound.setSettings(settings.toJson());
        inbound.setStreamSettings(streamSettings.toJson());

        return inbound;
    }
}
