package com.example.absensi.model.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRegisterDevice {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data_users")
    @Expose
    private DataUsersDevice dataUsers;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public DataUsersDevice getDataUsers() {
        return dataUsers;
    }

    public void setDataUsers(DataUsersDevice dataUsers) {
        this.dataUsers = dataUsers;
    }
}
