package com.example.absensi.ui.device;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.absensi.R;
import com.example.absensi.model.MessageOnly;
import com.example.absensi.ui.login.LoginActivity;
import com.example.absensi.utils.DialogClass;

public class ChangeDeviceActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_login;
    EditText edt_nopegawai,edt_password,edt_confirmPassword;
    ImageButton btn_submit;
    private android.app.AlertDialog alertDialog;
    private ChangeDeviceViewModel changeDeviceViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_device);
        tv_login = findViewById(R.id.tv_login);
        edt_nopegawai = findViewById(R.id.edt_nopegawai);
        edt_password = findViewById(R.id.edt_password);
        edt_confirmPassword = findViewById(R.id.edt_confirmPassword);
        btn_submit = findViewById(R.id.btn_submit);
        tv_login.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        changeDeviceViewModel = ViewModelProviders.of(this).get(ChangeDeviceViewModel.class);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_login){
            startActivity(new Intent(ChangeDeviceActivity.this, LoginActivity.class));
        }else if(v.getId() == R.id.btn_submit){
            changeDevice();
        }
    }

    private void changeDevice() {
        String noPegawai = edt_nopegawai.getText().toString();
        String password = edt_password.getText().toString();
        String confirmPassword = edt_confirmPassword.getText().toString();
        @SuppressLint("HardwareIds") String device_unique_id = Settings.Secure.getString(ChangeDeviceActivity.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        String merk = Build.MANUFACTURER;

        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();
        changeDeviceViewModel.changeDevice(noPegawai,merk,password,confirmPassword,device_unique_id).observe(this, new Observer<MessageOnly>() {
            @Override
            public void onChanged(MessageOnly messageOnly) {
                if(messageOnly.getStatus()){
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                    edt_confirmPassword.setText("");
                    edt_nopegawai.setText("");
                    edt_password.setText("");
                }else{
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}