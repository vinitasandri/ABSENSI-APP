package com.example.absensi.model.surat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CutiResponse {
    @SerializedName("data_cuti")
    @Expose
    private List<DataCuti> dataCuti = null;
    @SerializedName("sisa_cuti")
    @Expose
    private Integer sisaCuti;
    @SerializedName("jumlah_cuti")
    @Expose
    private String jumlahCuti;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<DataCuti> getDataCuti() {
        return dataCuti;
    }

    public void setDataCuti(List<DataCuti> dataCuti) {
        this.dataCuti = dataCuti;
    }

    public Integer getSisaCuti() {
        return sisaCuti;
    }

    public void setSisaCuti(Integer sisaCuti) {
        this.sisaCuti = sisaCuti;
    }

    public String getJumlahCuti() {
        return jumlahCuti;
    }

    public void setJumlahCuti(String jumlahCuti) {
        this.jumlahCuti = jumlahCuti;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
