package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalProxySettings {
    private String forceTls;
    private String dest;
    private int port;
    private String remark;
}

