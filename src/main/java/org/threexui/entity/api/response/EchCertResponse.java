package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.threexui.entity.api.EchCert;
import org.threexui.impl.APIObject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EchCertResponse extends AbstractResponse implements APIObject {

    @SerializedName("obj")
    private EchCert obj;
}
