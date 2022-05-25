package com.example.absensi.network.repository.absensi;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.absensi.AbsensiResponse;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAbsensiHomeRepository {
    private ApiInterface apiInterface;

    public GetAbsensiHomeRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<AbsensiResponse> getAbsensiHome(String id_users){
        final MutableLiveData<AbsensiResponse> mutableLiveData = new MutableLiveData<>();
        Call<AbsensiResponse> requestOrder = apiInterface.getAbsensiHome(id_users);
        requestOrder.enqueue(new Callback<AbsensiResponse>() {
            @Override
            public void onResponse(Call<AbsensiResponse> call, Response<AbsensiResponse> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else {
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AbsensiResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
