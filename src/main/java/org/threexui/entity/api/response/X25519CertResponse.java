package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.entity.api.X25519Cert;
import org.threexui.impl.APIObject;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class X25519CertResponse implements APIObject {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private X25519Cert obj;
}
