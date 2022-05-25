package com.example.absensi.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.register.ResponseRegister;
import com.example.absensi.network.repository.users.RegisterRepository;

public class RegisterViewModel extends ViewModel {

    private RegisterRepository registerRepository;

    public RegisterViewModel() {
        registerRepository = new RegisterRepository();
    }

    public LiveData<ResponseRegister> getRegisterResponse(String fullname,String email,String phone,String password,String imei,String nik){
        return registerRepository.getResponseRegister(fullname,email,password,phone,imei,nik);
    }

}
