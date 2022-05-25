package com.example.absensi.network.repository.users;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.register.ResponseRegisterDevice;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDeviceRepository {

    private ApiInterface apiInterface;

    public RegisterDeviceRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<ResponseRegisterDevice> registerDevice(String no_pegawai){
        final MutableLiveData<ResponseRegisterDevice> mutableLiveData = new MutableLiveData<>();
        Call<ResponseRegisterDevice> requestOrder = apiInterface.registerDevice(no_pegawai);
        requestOrder.enqueue(new Callback<ResponseRegisterDevice>() {
            @Override
            public void onResponse(Call<ResponseRegisterDevice> call, Response<ResponseRegisterDevice> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseRegisterDevice> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
