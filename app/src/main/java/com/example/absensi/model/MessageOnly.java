package com.example.absensi.model;

import com.google.gson.annotations.SerializedName;

public class MessageOnly {
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private Boolean status;

    public String getMessage() {
        return message;
    }

    public Boolean getStatus() {
        return status;
    }
}
