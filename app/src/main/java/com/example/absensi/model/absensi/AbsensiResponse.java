package com.example.absensi.model.absensi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AbsensiResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data_absensi")
    @Expose
    private DataAbsensi dataAbsensi;



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

    public DataAbsensi getDataAbsensi() {
        return dataAbsensi;
    }

    public void setDataAbsensi(DataAbsensi dataAbsensi) {
        this.dataAbsensi = dataAbsensi;
    }

}
