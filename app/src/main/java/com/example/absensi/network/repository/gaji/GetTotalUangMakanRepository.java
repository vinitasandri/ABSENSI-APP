package com.example.absensi.network.repository.gaji;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.gaji.TotalUangMakanResponse;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTotalUangMakanRepository {
    private ApiInterface apiInterface;

    public GetTotalUangMakanRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<TotalUangMakanResponse> getTotalUangMakan(String no_pegawai){
        final MutableLiveData<TotalUangMakanResponse> mutableLiveData = new MutableLiveData<>();
        Call<TotalUangMakanResponse> requestOrder = apiInterface.getTotalUangMakan(no_pegawai);
        requestOrder.enqueue(new Callback<TotalUangMakanResponse>() {
            @Override
            public void onResponse(Call<TotalUangMakanResponse> call, Response<TotalUangMakanResponse> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<TotalUangMakanResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return  mutableLiveData;
    }
}
