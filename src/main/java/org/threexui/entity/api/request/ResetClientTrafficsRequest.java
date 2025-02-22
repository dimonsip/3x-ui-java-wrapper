package org.threexui.entity.api.request;

public class ResetClientTrafficsRequest extends APIRequest {

    public ResetClientTrafficsRequest(String host, long inboundId, String clientId) {
        super(String.format("%s/panel/api/inbounds/%s/resetClientTraffic/%s", host, inboundId, clientId), APIRequest.RequestMethod.POST);
    }
}
