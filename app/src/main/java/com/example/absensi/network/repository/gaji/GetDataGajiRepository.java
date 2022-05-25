package com.example.absensi.network.repository.gaji;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.gaji.DataGajiResponse;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataGajiRepository {
    private ApiInterface apiInterface;

    public GetDataGajiRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<DataGajiResponse> getDataGaji(String no_pegawai){
        final MutableLiveData<DataGajiResponse> mutableLiveData = new MutableLiveData<>();
        Call<DataGajiResponse> requestOrder = apiInterface.getDataGaji(no_pegawai);
        requestOrder.enqueue(new Callback<DataGajiResponse>() {
            @Override
            public void onResponse(Call<DataGajiResponse> call, Response<DataGajiResponse> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataGajiResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return  mutableLiveData;
    }
}
