package org.threexui.entity.api.request;

public class DeleteInboundRequest extends APIRequest {
    public DeleteInboundRequest(String host, int inboundId) {
        super(String.format("%s/panel/api/inbounds/del/%s", host, inboundId), RequestMethod.POST);
    }
}
