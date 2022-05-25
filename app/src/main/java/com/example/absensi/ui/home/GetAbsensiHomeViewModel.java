package com.example.absensi.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.absensi.AbsensiResponse;
import com.example.absensi.network.repository.absensi.GetAbsensiHomeRepository;

public class GetAbsensiHomeViewModel extends ViewModel {
    private GetAbsensiHomeRepository getAbsensiHomeRepository;

    public GetAbsensiHomeViewModel(){
        getAbsensiHomeRepository = new GetAbsensiHomeRepository();
    }

    public LiveData<AbsensiResponse> getAbsensiHome(String id_users){
        return  getAbsensiHomeRepository.getAbsensiHome(id_users);
    }
}
