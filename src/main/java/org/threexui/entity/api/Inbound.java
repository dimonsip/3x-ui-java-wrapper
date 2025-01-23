package org.threexui.entity.api;

import com.google.gson.Gson;
import lombok.*;
import org.threexui.impl.APIObject;
import org.threexui.impl.APIRequestData;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inbound implements APIObject, APIRequestData {

    private List<ClientTraffics> clientStats;
    private long down;
    private String settings;
    private long up;
    private boolean enable;
    private long expiryTime;
    private long id;
    private String listen;
    private long port;
    private String streamSettings;
    private String protocol;
    private String remark;
    private String tag;
    private String sniffing;
    private long total;

    public Settings getSettings() {
        Gson gson = new Gson();
        return gson.fromJson(settings, Settings.class);
    }

    public StreamSettings getStreamSettings() {
        Gson gson = new Gson();
        return gson.fromJson(streamSettings, StreamSettings.class);
    }

    public Sniffing getSniffingSettings() {
        Gson gson = new Gson();
        return gson.fromJson(sniffing, Sniffing.class);
    }
}