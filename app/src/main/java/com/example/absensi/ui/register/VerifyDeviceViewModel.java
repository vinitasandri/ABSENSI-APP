package com.example.absensi.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.repository.users.VerifyDeviceRepository;

public class VerifyDeviceViewModel extends ViewModel {
    private VerifyDeviceRepository verifyDeviceRepository;

    public VerifyDeviceViewModel(){
        verifyDeviceRepository = new VerifyDeviceRepository();
    }

    public LiveData<MessageOnly> verifyDevice(String no_pegawai,String device_id,String password){
        return verifyDeviceRepository.verifyDevice(no_pegawai,device_id,password);
    }
}
