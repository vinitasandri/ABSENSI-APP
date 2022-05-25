package com.example.absensi.network.repository.laporan;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.laporan.DataLaporanResponse;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataLaporanRepository {
    private ApiInterface apiInterface;

    public GetDataLaporanRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<DataLaporanResponse> getDataLaporan(String id_usres){
        final MutableLiveData<DataLaporanResponse> mutableLiveData = new MutableLiveData<>();
        Call<DataLaporanResponse> requestOrder = apiInterface.getDataLaporan(id_usres);
        requestOrder.enqueue(new Callback<DataLaporanResponse>() {
            @Override
            public void onResponse(Call<DataLaporanResponse> call, Response<DataLaporanResponse> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataLaporanResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
