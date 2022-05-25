package com.example.absensi.model.gaji;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataGajiResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data_gaji")
    @Expose
    private List<DataGaji> dataGaji = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DataGaji> getDataGaji() {
        return dataGaji;
    }

    public void setDataGaji(List<DataGaji> dataGaji) {
        this.dataGaji = dataGaji;
    }
}
