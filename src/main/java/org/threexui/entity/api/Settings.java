package org.threexui.entity.api;

import lombok.*;
import org.threexui.impl.APIObject;
import org.threexui.impl.APIRequestData;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Settings implements APIObject, APIRequestData {

    private List<ClientSettings> clients;
    private String decryption;
    private String[] fallbacks;
}