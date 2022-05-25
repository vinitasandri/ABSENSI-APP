package com.example.absensi.model.surat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SuratIzinResponse {
    @SerializedName("data_suratizin")
    @Expose
    private List<DataSuratIzin> dataSuratizin = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<DataSuratIzin> getDataSuratizin() {
        return dataSuratizin;
    }

    public void setDataSuratizin(List<DataSuratIzin> dataSuratizin) {
        this.dataSuratizin = dataSuratizin;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
