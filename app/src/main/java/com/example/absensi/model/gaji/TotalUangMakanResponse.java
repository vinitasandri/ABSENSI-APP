package com.example.absensi.model.gaji;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalUangMakanResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("total")
    @Expose
    private String total;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
