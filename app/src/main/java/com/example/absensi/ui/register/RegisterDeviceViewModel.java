package com.example.absensi.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.register.ResponseRegisterDevice;
import com.example.absensi.network.repository.users.RegisterDeviceRepository;

public class RegisterDeviceViewModel extends ViewModel {
    private RegisterDeviceRepository registerDeviceRepository;

    public RegisterDeviceViewModel(){
        registerDeviceRepository = new RegisterDeviceRepository();
    }

    public LiveData<ResponseRegisterDevice> registerDevice(String no_pegawai){
        return registerDeviceRepository.registerDevice(no_pegawai);
    }
}
