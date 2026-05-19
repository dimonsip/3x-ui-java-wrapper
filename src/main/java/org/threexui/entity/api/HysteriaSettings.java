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
public class HysteriaSettings implements APIObject, APIRequestData {
    private List<HysteriaClientSettings> clients;
    private Integer version;
}
