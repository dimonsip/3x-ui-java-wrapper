package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.threexui.impl.APIRequestData;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sniffing implements APIRequestData {

    private boolean enabled;
    private List<String> destOverride;
    private boolean metadataOnly;
    private boolean routeOnly;
}
