package org.threexui.entity.api;

import org.threexui.entity.enums.StreamNetwork;
import org.threexui.impl.APIRequestData;
import org.threexui.utils.data.InboundData;

public final class StreamSettingsFactory {

    private StreamSettingsFactory() {
    }

    public static StreamSettings createStreamSettings(StreamNetwork network) {

        StreamSettings ss = APIRequestData.fromJson(
                InboundData.BASE_STREAM_SETTINGS,
                StreamSettings.class
        );

        switch (network) {
            case TCP: applyTcp(ss); break;
            case GRPC: applyGrpc(ss); break;
            case XHTTP: applyXhttp(ss); break;
            case HYSTERIA: applyHysteria(ss); break;
//            case HTTP_UPGRADE: applyHttpUpgrade(ss); break;
//            case MKCP: applyKcp(ss); break;
//            case WS: applyWs(ss); break;
            default:
                throw new IllegalStateException("Unknown network: " + network);
        }

        return ss;
    }

    private static void applyTcp(StreamSettings ss) {
        ss.setNetwork("tcp");
        ss.setSecurity("reality");
        ss.setTcpSettings(new StreamSettings.TcpSettings(
                false,
                new StreamSettings.Header("none")
        ));
        clearExcept(ss, "tcp");
    }

    private static void applyGrpc(StreamSettings ss) {
        ss.setNetwork("grpc");
        //ss.setSecurity("reality");
        ss.setGrpcSettings(new StreamSettings.GrpcSettings(
                "",
                "",
                false
        ));
        clearExcept(ss, "grpc");
    }

    private static void applyKcp(StreamSettings ss) {
        ss.setNetwork("kcp");
        ss.setKcpSettings(new StreamSettings.KcpSettings(null));
        clearExcept(ss, "kcp");
    }

    private static void applyWs(StreamSettings ss) {
        ss.setNetwork("ws");
        ss.setWsSettings(new StreamSettings.WsSettings("/", null));
        clearExcept(ss, "ws");
    }

    private static void applyHttpUpgrade(StreamSettings ss) {
        ss.setNetwork("httpupgrade");
        ss.setHttpUpgradeSettings(new StreamSettings.HttpUpgradeSettings("/", ""));
        clearExcept(ss, "httpupgrade");
    }

    private static void applyXhttp(StreamSettings ss) {
        ss.setNetwork("xhttp");
        ss.setSecurity("reality");
        ss.setXhttpSettings(new StreamSettings.XhttpSettings());
        clearExcept(ss, "xhttp");
    }

    private static void applyHysteria(StreamSettings ss) {
        ss.setNetwork("hysteria");
        ss.setSecurity("tls");
        ss.setRealitySettings(null);
        ss.setHysteriaSettings(new StreamSettings.HysteriaSettings(2, "", 60));
        clearExcept(ss, "hysteria");
    }

    private static void clearExcept(StreamSettings ss, String protocol) {
        if (!protocol.equals("tcp")) ss.setTcpSettings(null);
        if (!protocol.equals("kcp")) ss.setKcpSettings(null);
        if (!protocol.equals("ws")) ss.setWsSettings(null);
        if (!protocol.equals("grpc")) ss.setGrpcSettings(null);
        if (!protocol.equals("httpupgrade")) ss.setHttpUpgradeSettings(null);
        if (!protocol.equals("xhttp")) ss.setXhttpSettings(null);
        if (!protocol.equals("hysteria")) ss.setHysteriaSettings(null);
    }
}
