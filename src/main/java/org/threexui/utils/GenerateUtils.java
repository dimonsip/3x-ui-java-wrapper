package org.threexui.utils;

import org.threexui.entity.api.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class GenerateUtils {

    public static List<String> randomShortIdGenerator() {
        int count = 8;

        int minLength = 1;
        int maxLength = 8;

        return generateRandomShortIds(count, minLength, maxLength);
    }

    public static String vlessStringGenerate(String host, Inbound inbound, Client client) {
        String uuid, flow;
        if (client == null) { //if creating client with inbound with ClientSettings
            Settings settings = inbound.getSettings();
            ClientSettings clientSettings = settings.getClients().get(0);
            uuid = clientSettings.getId();
            flow = clientSettings.getFlow().getValue();
        } else {
            uuid = client.getId();
            flow = client.getFlow();
        }
        StreamSettings streamSettings = inbound.getStreamSettings();
        long port = inbound.getPort();
        String type = streamSettings.getNetwork();
        String security = streamSettings.getSecurity();
        String publicKey = streamSettings.getRealitySettings().getSettings().getPublicKey();
        String fingerprint = streamSettings.getBrowser();
        String sni = streamSettings.getSni();
        String shortId = streamSettings.getFirstSid();
        String spiderX = streamSettings.getRealitySettings().getSettings().getSpiderX();
        String comment = inbound.getRemark();

        String encodedSpiderX = URLEncoder.encode(spiderX, StandardCharsets.UTF_8);
        String encodedComment = URLEncoder.encode(comment, StandardCharsets.UTF_8).replace("+", "%20");

        return String.format(
                "vless://%s@%s:%d/?type=%s&security=%s&pbk=%s&fp=%s&sni=%s&sid=%s&spx=%s&flow=%s#%s",
                uuid, host, port, type, security, publicKey, fingerprint, sni, shortId, encodedSpiderX, flow,
                encodedComment
        );
    }

    public static List<String> generateRandomShortIds(int count, int minLength, int maxLength) {
        List<String> shortIds = new ArrayList<>();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < count; i++) {
            int length = secureRandom.nextInt((maxLength - minLength + 1)) + minLength;
            shortIds.add(generateShortId(length));
        }

        return shortIds;
    }

    public static String generateShortId(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[length];
        secureRandom.nextBytes(randomBytes);

        StringBuilder hexString = new StringBuilder();
        for (byte b : randomBytes) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }
}
