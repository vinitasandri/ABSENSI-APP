package com.example.absensi.ui.surat.cuti;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.surat.CutiResponse;
import com.example.absensi.network.repository.surat.GetDataCutiRepository;

public class GetDataCutiViewModel extends ViewModel {
    private GetDataCutiRepository getDataCutiRepository;

    public GetDataCutiViewModel(){
        getDataCutiRepository = new GetDataCutiRepository();
    }

    public LiveData<CutiResponse> getDataCuti(String idUsers){
        return getDataCutiRepository.getDataCuti(idUsers);
    }
}
