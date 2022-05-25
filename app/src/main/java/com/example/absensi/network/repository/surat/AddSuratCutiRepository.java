package com.example.absensi.network.repository.surat;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSuratCutiRepository  {
    private ApiInterface apiInterface;

    public AddSuratCutiRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }

    public MutableLiveData<MessageOnly> addSuratCuti(String keterangan,String startDate,String endDate,String idUsers){
        final MutableLiveData<MessageOnly> mutableLiveData = new MutableLiveData<>();
        Call<MessageOnly> requestOrder = apiInterface.addSuratCuti(keterangan,startDate,endDate,idUsers);
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
