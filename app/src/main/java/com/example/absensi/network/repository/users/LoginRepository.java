package com.example.absensi.network.repository.users;

import androidx.lifecycle.MutableLiveData;

import com.example.absensi.model.login.ResponseLogin;
import com.example.absensi.network.api.ApiClient;
import com.example.absensi.network.api.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private ApiInterface apiInterface;

    public LoginRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<ResponseLogin> getResponseLogin(String no_pegawai,String password,String device_id){
        final MutableLiveData<ResponseLogin> responseLoginMutableLiveData = new MutableLiveData<>();
        Call<ResponseLogin> requestOrder = apiInterface.loginUser(no_pegawai,password,device_id);
        requestOrder.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.body() != null){
                    responseLoginMutableLiveData.postValue(response.body());
                }else{
                    responseLoginMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                System.out.println(t.getMessage());
                responseLoginMutableLiveData.postValue(null);
            }
        });
        return responseLoginMutableLiveData;
    }
}
