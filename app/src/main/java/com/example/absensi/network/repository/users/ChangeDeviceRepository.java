package com.example.absensi.network.repository.users;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeDeviceRepository {
    private ApiInterface apiInterface;

    public ChangeDeviceRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<MessageOnly> changeDevice(String noPegawai,String merk,String password,String confirmPassword,String deviceId){
        final MutableLiveData<MessageOnly> mutableLiveData = new MutableLiveData<>();
        Call<MessageOnly> requestOrder = apiInterface.changeDevice(noPegawai,merk,password,confirmPassword,deviceId);
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
