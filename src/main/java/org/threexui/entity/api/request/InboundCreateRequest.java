package org.threexui.entity.api.request;

import org.threexui.entity.api.Inbound;

public class InboundCreateRequest extends APIRequest {

    public InboundCreateRequest(String host, Inbound inbound) {
        super(String.format("%s/panel/api/inbounds/add", host), RequestMethod.POST, inbound);
    }
}
