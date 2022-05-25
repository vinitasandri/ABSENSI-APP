package com.example.absensi.model.absensi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPercentResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("percent")
    @Expose
    private String percent;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
