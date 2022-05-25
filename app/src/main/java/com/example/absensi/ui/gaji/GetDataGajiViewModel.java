package com.example.absensi.ui.gaji;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.gaji.DataGajiResponse;
import com.example.absensi.network.repository.gaji.GetDataGajiRepository;

public class GetDataGajiViewModel extends ViewModel {
    private GetDataGajiRepository getDataGajiRepository;

    public GetDataGajiViewModel(){
        getDataGajiRepository = new GetDataGajiRepository();
    }

    public LiveData<DataGajiResponse> getDataGaji(String no_pegawai){
        return  getDataGajiRepository.getDataGaji(no_pegawai);
    }
}
