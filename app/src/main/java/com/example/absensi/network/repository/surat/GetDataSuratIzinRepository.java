package com.example.absensi.network.repository.surat;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.surat.SuratIzinResponse;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataSuratIzinRepository {
    private ApiInterface apiInterface;

    public GetDataSuratIzinRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<SuratIzinResponse> getDataSuratIzin(String id_users){
        final MutableLiveData<SuratIzinResponse> mutableLiveData = new MutableLiveData<>();
        Call<SuratIzinResponse> requestOrder = apiInterface.getDataSuratIzin(id_users);
        requestOrder.enqueue(new Callback<SuratIzinResponse>() {
            @Override
            public void onResponse(Call<SuratIzinResponse> call, Response<SuratIzinResponse> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<SuratIzinResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return  mutableLiveData;
    }
}
