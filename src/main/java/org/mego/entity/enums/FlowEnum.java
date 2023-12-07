package org.mego.entity.enums;

import org.jetbrains.annotations.Nullable;

public enum FlowEnum {

    XLTS_RPRX_VISION("xtls-rprx-vision"),
    XLTS_RPRX_VISION_UPD_443("xtls-rprx-vision-udp443"),
    NONE("");

    private final String value;

    FlowEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Nullable
    public static FlowEnum find(String value) {
        FlowEnum[] values = FlowEnum.values();
        for (FlowEnum version : values) {
            String localValue = version.getValue();
            if (localValue.equals(value)) {
                return version;
            }
        }
        return null;
    }
}
