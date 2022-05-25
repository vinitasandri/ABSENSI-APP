package com.example.absensi.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.profile.ResponseProfile;
import com.example.absensi.network.repository.profile.UpdateProfileRepository;

public class UpdateProfileViewModel extends ViewModel {

    private UpdateProfileRepository updateProfileRepository;

    public  UpdateProfileViewModel(){
        updateProfileRepository = new UpdateProfileRepository();
    }

    public LiveData<ResponseProfile> getProfileResponse(String id_pegawai,String no_telp,String date,String jk,String alamat){
        return updateProfileRepository.getResponseUpdateProfile(id_pegawai,no_telp,date,jk,alamat);
    }
}
