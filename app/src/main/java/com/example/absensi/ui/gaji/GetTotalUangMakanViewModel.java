package com.example.absensi.ui.gaji;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.gaji.TotalUangMakanResponse;
import com.example.absensi.network.repository.gaji.GetTotalUangMakanRepository;

public class GetTotalUangMakanViewModel extends ViewModel {
    private GetTotalUangMakanRepository getTotalUangMakanRepository;

    public GetTotalUangMakanViewModel(){
        getTotalUangMakanRepository = new GetTotalUangMakanRepository();
    }

    public LiveData<TotalUangMakanResponse> getTotalUangMakan(String no_pegawai){
        return getTotalUangMakanRepository.getTotalUangMakan(no_pegawai);
    }
}
