package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.threexui.impl.APIObject;
import org.threexui.impl.APIRequestData;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HysteriaSettings extends ClientSettings implements APIObject, APIRequestData {
    private List<HysteriaClient> clients;
    private Integer version;
}
