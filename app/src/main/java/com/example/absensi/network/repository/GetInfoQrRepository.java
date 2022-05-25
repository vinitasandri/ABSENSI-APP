package com.example.absensi.network.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.qr.QrInfoResponse;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetInfoQrRepository {
    private ApiInterface apiInterface ;

    public GetInfoQrRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<QrInfoResponse> getInfoQr(String no_pegawai){
        final MutableLiveData<QrInfoResponse> mutableLiveData = new MutableLiveData<>();
        Call<QrInfoResponse> requestOrder = apiInterface.getInfoQr(no_pegawai);
        requestOrder.enqueue(new Callback<QrInfoResponse>() {
            @Override
            public void onResponse(Call<QrInfoResponse> call, Response<QrInfoResponse> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<QrInfoResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
