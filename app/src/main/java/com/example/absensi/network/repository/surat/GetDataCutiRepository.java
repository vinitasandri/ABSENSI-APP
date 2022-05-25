package com.example.absensi.network.repository.surat;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.surat.CutiResponse;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataCutiRepository {
    private ApiInterface apiInterface;

    public GetDataCutiRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<CutiResponse> getDataCuti(String idUsers){
        final MutableLiveData<CutiResponse> mutableLiveData= new MutableLiveData<>();
        Call<CutiResponse> requestOrder = apiInterface.getDataCuti(idUsers);
        requestOrder.enqueue(new Callback<CutiResponse>() {
            @Override
            public void onResponse(Call<CutiResponse> call, Response<CutiResponse> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<CutiResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
