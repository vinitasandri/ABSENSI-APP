package com.example.absensi.ui.device;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.repository.users.ChangeDeviceRepository;

public class ChangeDeviceViewModel extends ViewModel {
    private ChangeDeviceRepository changeDeviceRepository;

    public ChangeDeviceViewModel(){
        changeDeviceRepository = new ChangeDeviceRepository();
    }

    public LiveData<MessageOnly> changeDevice(String noPegawai,String merk,String password,String confirmPassword,String deviceId){
        return changeDeviceRepository.changeDevice(noPegawai,merk,password,confirmPassword,deviceId);
    }
}
