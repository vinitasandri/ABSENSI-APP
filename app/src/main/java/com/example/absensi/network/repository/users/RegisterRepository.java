package com.example.absensi.network.repository.users;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.register.ResponseRegister;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {
    private ApiInterface apiInterface;

    public RegisterRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<ResponseRegister> getResponseRegister(String fullname,String email,String password,String phone,String imei,String nik){
        System.out.println(nik);
        final MutableLiveData<ResponseRegister> responseRegisterMutableLiveData = new MutableLiveData<>();
        Call<ResponseRegister> requesOrder = apiInterface.registerUser(fullname,email,phone,password,imei,nik);
        requesOrder.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if(response.body() != null){
                    System.out.println(response.body());
                    responseRegisterMutableLiveData.postValue(response.body());
                }else{
                    responseRegisterMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                System.out.println(t.getMessage());
                responseRegisterMutableLiveData.postValue(null);
            }
        });
        return responseRegisterMutableLiveData;
    }
}
