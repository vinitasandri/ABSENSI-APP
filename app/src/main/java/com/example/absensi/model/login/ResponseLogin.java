package com.example.absensi.model.login;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("message")
    private String message ;

    @SerializedName("status")
    private Boolean status;

    @SerializedName("user_data")
    private UsersLogin usersLogin;

    public ResponseLogin(String message, Boolean status, UsersLogin usersLogin) {
        this.message = message;
        this.status = status;
        this.usersLogin = usersLogin;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getStatus() {
        return status;
    }

    public UsersLogin getUsersLogin() {
        return usersLogin;
    }
}
