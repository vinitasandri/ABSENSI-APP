package com.example.absensi.network.repository.users;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyDeviceRepository {
    private ApiInterface apiInterface;

    public VerifyDeviceRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<MessageOnly> verifyDevice(String no_pegawai,String device_id,String password){
        final MutableLiveData<MessageOnly> mutableLiveData = new MutableLiveData<>();
        Call<MessageOnly> requestOrder = apiInterface.registerPerangkat(no_pegawai,device_id,password);
        requestOrder.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(Call<MessageOnly> call, Response<MessageOnly> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<MessageOnly> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
