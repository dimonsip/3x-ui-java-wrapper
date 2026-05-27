package org.threexui.entity.api;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.threexui.impl.APIRequestData;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StreamSettings implements APIRequestData {

    private String network;
    private String security;
    private List<ExternalProxySettings> externalProxy;
    private RealitySettings realitySettings;
    private TcpSettings tcpSettings;
    private GrpcSettings grpcSettings;
    private KcpSettings kcpSettings;
    private WsSettings wsSettings;
    private HttpUpgradeSettings httpUpgradeSettings;
    private XhttpSettings xhttpSettings;
    private HysteriaSettings hysteriaSettings;
    private TlsSettings tlsSettings;

    public String getFirstSid() {
        return getRealitySettings().getShortIds().get(0);
    }

    public String getBrowser() {
        return getRealitySettings().getSettings().getFingerprint();
    }

    public String getSni() {
        return getRealitySettings().getServerNames().get(0);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class RealitySettings implements APIRequestData {

        private boolean show;
        private int xver;
        private String dest;
        private List<String> serverNames;
        private String privateKey;
        private String minClient;
        private String maxClient;
        private int maxTimediff;
        private List<String> shortIds;
        private Settings settings;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class TcpSettings implements APIRequestData {

        @SerializedName("acceptProxyProtocol")
        private boolean acceptProxyProtocol;
        private Header header;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GrpcSettings implements APIRequestData {
        private String serviceName;
        private String authority;
        private boolean multiMode;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KcpSettings implements APIRequestData {
        private int mtu = 1350;
        private int tti = 50;
        private int uplinkCapacity = 5;
        private int downlinkCapacity = 20;
        private boolean congestion = false;
        private int readBufferSize = 2;
        private int writeBufferSize = 2;
        private Header header;

        public KcpSettings(Header header) {
            this.header = header;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WsSettings implements APIRequestData {
        private String path;
        private Map<String, String> headers;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HttpUpgradeSettings implements APIRequestData {
        private String path;
        private String host;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class XhttpSettings implements APIRequestData {
        private String path = "/";
        private String mode = "auto";
        private String host = "";
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Header implements APIRequestData {
        private String type;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Settings implements APIRequestData {

        private String publicKey;
        private String fingerprint;
        private String serverName;
        private String spiderX;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class HysteriaSettings {
        private int version;
        private String auth;
        private int udpIdleTimeout;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Certificate {
        private String certificateFile;
        private String keyFile;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TlsSettings {
        private String serverName;
        private List<Certificate> certificates;
        private List<String> alpn;
        private String echServerKeys;
        @SerializedName("settings")
        private TlsConfigSettings settings;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class TlsConfigSettings implements APIRequestData {
        private String fingerprint;
        private String echConfigList;
    }

}