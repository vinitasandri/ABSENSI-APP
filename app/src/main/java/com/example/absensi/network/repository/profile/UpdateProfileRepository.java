package com.example.absensi.network.repository.profile;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.profile.ResponseProfile;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileRepository {
    private ApiInterface apiInterface;

    public UpdateProfileRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<ResponseProfile> getResponseUpdateProfile(String id_pegawai,String no_telp,String date,String jk,String alamat){
        final MutableLiveData<ResponseProfile> responseProfileMutableLiveData = new MutableLiveData<>();
//        System.out.println(id_pegawai + " " + no_telp + " " + date + " " + jk + " " + alamat);
        Call<ResponseProfile> requestOrder = apiInterface.updateProfile(id_pegawai,no_telp,date,jk,alamat);
        requestOrder.enqueue(new Callback<ResponseProfile>() {
            @Override
            public void onResponse(Call<ResponseProfile> call, Response<ResponseProfile> response) {
                if(response.body() != null){
                    System.out.println(response.body());
                    responseProfileMutableLiveData.postValue(response.body());
                }else{
                    responseProfileMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseProfile> call, Throwable t) {
                System.out.println(t.getMessage());
                responseProfileMutableLiveData.postValue(null);
            }
        });
        return responseProfileMutableLiveData;
    }

}
