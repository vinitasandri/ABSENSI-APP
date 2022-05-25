package com.example.absensi.model.register;

import com.google.gson.annotations.SerializedName;

public class ResponseRegister {
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public ResponseRegister(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public boolean getStatus() {
        return status;
    }
}
