package com.example.absensi.network.repository.absensi;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.absensi.DataPercentResponse;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataPercentRepository {

    private ApiInterface apiInterface;

    public GetDataPercentRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<DataPercentResponse> getDataPercent(String no_pegawai){
        final MutableLiveData<DataPercentResponse> mutableLiveData = new MutableLiveData<>();
        Call<DataPercentResponse> requestOrder = apiInterface.getDataPercent(no_pegawai);
        requestOrder.enqueue(new Callback<DataPercentResponse>() {
            @Override
            public void onResponse(Call<DataPercentResponse> call, Response<DataPercentResponse> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataPercentResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
