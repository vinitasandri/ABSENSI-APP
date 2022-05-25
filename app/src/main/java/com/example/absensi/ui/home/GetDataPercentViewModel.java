package com.example.absensi.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.absensi.DataPercentResponse;
import com.example.absensi.network.repository.absensi.GetDataPercentRepository;
import com.example.absensi.ui.laporan.GetDataLaporanViewModel;

public class GetDataPercentViewModel extends ViewModel {
    private GetDataPercentRepository getDataPercentRepository;

    public GetDataPercentViewModel(){
        getDataPercentRepository = new GetDataPercentRepository();
    }

    public LiveData<DataPercentResponse> getDataPercent(String no_pegawai){
        return getDataPercentRepository.getDataPercent(no_pegawai);
    }
}
