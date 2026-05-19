package org.threexui.entity.api.request;

public class NewEchCertRequest extends APIRequest {

    public NewEchCertRequest(String host) {
        super(String.format("%s/panel/api/server/getNewEchCert", host), RequestMethod.POST);
    }
}
