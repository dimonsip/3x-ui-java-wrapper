package org.threexui.entity.api.request;

public class NewX25519CertRequest extends APIRequest {

    public NewX25519CertRequest(String host) {
        super(String.format("%s/server/getNewX25519Cert", host), RequestMethod.POST);
    }
}
