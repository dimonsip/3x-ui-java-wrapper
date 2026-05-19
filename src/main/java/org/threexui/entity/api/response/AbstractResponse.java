package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;

public abstract class AbstractResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;
}
