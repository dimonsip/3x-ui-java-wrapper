package org.threexui.entity.api.request;

public class ResetInboundTrafficsRequest extends APIRequest {

    public ResetInboundTrafficsRequest(String host, long inboundId) {
        super(String.format("%s/panel/api/inbounds/resetAllClientTraffics/%s", host, inboundId), RequestMethod.POST);
    }
}
