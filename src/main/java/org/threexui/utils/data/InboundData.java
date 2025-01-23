package org.threexui.utils.data;

public class InboundData {

    public static final String defaultStreamSettings = "{\n" +
            "    \"network\": \"tcp\",\n" +
            "    \"security\": \"reality\",\n" +
            "    \"externalProxy\": [],\n" +
            "    \"realitySettings\": {\n" +
            "        \"show\": false,\n" +
            "        \"xver\": 0,\n" +
            "        \"dest\": \"vkvideo.ru:443\",\n" +
            "        \"serverNames\": [\n" +
            "            \"vkvideo.ru\"\n" +
            "        ],\n" +
            "        \"privateKey\": \"\",\n" +
            "        \"minClient\": \"\",\n" +
            "        \"maxClient\": \"\",\n" +
            "        \"maxTimediff\": 0,\n" +
            "        \"shortIds\": [],\n" +
            "        \"settings\": {\n" +
            "            \"publicKey\": \"\",\n" +
            "            \"fingerprint\": \"chrome\",\n" +
            "            \"serverName\": \"\",\n" +
            "            \"spiderX\": \"/\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"tcpSettings\": {\n" +
            "        \"acceptProxyProtocol\": false,\n" +
            "        \"header\": {\n" +
            "            \"type\": \"none\"\n" +
            "        }\n" +
            "    }\n" +
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
