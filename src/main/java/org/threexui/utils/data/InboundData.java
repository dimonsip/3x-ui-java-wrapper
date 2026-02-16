package org.threexui.utils.data;

public class InboundData {

    public static final String BASE_STREAM_SETTINGS = "{\n" +
            "  \"security\": \"\",\n" +
            "  \"externalProxy\": [],\n" +
            "  \"realitySettings\": {\n" +
            "    \"show\": false,\n" +
            "    \"xver\": 0,\n" +
            "    \"dest\": \"vkvideo.ru:443\",\n" +
            "    \"serverNames\": [\"vkvideo.ru\"],\n" +
            "    \"privateKey\": \"\",\n" +
            "    \"minClient\": \"\",\n" +
            "    \"maxClient\": \"\",\n" +
            "    \"maxTimediff\": 0,\n" +
            "    \"shortIds\": [],\n" +
            "    \"settings\": {\n" +
            "      \"publicKey\": \"\",\n" +
            "      \"fingerprint\": \"chrome\",\n" +
            "      \"serverName\": \"\",\n" +
            "      \"spiderX\": \"/\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    public static final String defaultSniffing = "{\n" +
            "  \"enabled\": true,\n" +
            "  \"destOverride\": [\n" +
            "    \"http\",\n" +
            "    \"tls\",\n" +
            "    \"quic\",\n" +
            "    \"fakedns\"\n" +
            "  ],\n" +
            "  \"metadataOnly\": false,\n" +
            "  \"routeOnly\": false\n" +
            "}";
}
