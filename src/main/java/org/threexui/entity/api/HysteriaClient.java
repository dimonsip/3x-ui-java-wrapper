package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.threexui.impl.APIObject;
import org.threexui.impl.APIRequestData;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HysteriaClient extends Client implements APIObject, APIRequestData {

    private String auth;
    private Long created_at;
    private Long updated_at;
}
