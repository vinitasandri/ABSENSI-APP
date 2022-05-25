package com.example.absensi.ui.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.absensi.R;
import com.example.absensi.model.login.ResponseLogin;
import com.example.absensi.session.SystemDataLocal;
import com.example.absensi.ui.device.ChangeDeviceActivity;
import com.example.absensi.ui.home.HomeActivity;
import com.example.absensi.ui.register.RegisterActivity;
import com.example.absensi.ui.register.RegisterDeviceActivity;
import com.example.absensi.utils.DialogClass;
import com.example.absensi.utils.SwitchAcitivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Observer<ResponseLogin> {


    TextView tv_sign,tv_change;
    private SystemDataLocal systemDataLocal;
    private LoginViewModel loginViewModel;
    private android.app.AlertDialog alertDialog;
    EditText edtNoPegawai,edtPassword;
    ImageButton btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_sign = findViewById(R.id.tv_sign);
        tv_change = findViewById(R.id.tv_change);
        edtNoPegawai = findViewById(R.id.edt_nopegawai);
        edtPassword = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        systemDataLocal = new SystemDataLocal(this);
        if(systemDataLocal.getCheckLogin()){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }

        tv_sign.setOnClickListener(this);
        tv_change.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sign:
                Intent moveToRegis = new Intent(LoginActivity.this, RegisterDeviceActivity.class);
                startActivity(moveToRegis);
                break;

            case R.id.btn_login:
                login();
                break;

            case R.id.tv_change:
                startActivity(new Intent(LoginActivity.this, ChangeDeviceActivity.class));
                break;
        }
    }

    private void login(){
        @SuppressLint("HardwareIds") String device_unique_id = Settings.Secure.getString(LoginActivity.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        String no_pegawai = edtNoPegawai.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();
        loginViewModel.setLogin(no_pegawai,password,device_unique_id);
        loginViewModel.getUserEntityLiveData().observe(this,this);
    }

    @SuppressLint("ShowToast")
    @Override
    public void onChanged(ResponseLogin responseLogin) {
        if(responseLogin != null){
            if(responseLogin.getStatus()){
                alertDialog.dismiss();
                systemDataLocal = new SystemDataLocal(this,responseLogin.getUsersLogin());
                systemDataLocal.setSessionLogin();
                alertDialog.dismiss();
                SwitchAcitivity.mainSwitch(this,HomeActivity.class);
                finish();
            }else{
                alertDialog.dismiss();
                System.out.println(responseLogin.getMessage());
                Toast.makeText(LoginActivity.this,responseLogin.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        systemDataLocal = new SystemDataLocal(this);
        if(systemDataLocal.getCheckLogin()){
            SwitchAcitivity.mainSwitch(this,HomeActivity.class);
            finish();
        }
    }
}
