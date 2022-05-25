package com.example.absensi.ui.register;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.absensi.R;
import com.example.absensi.model.MessageOnly;
import com.example.absensi.model.register.ResponseRegisterDevice;
import com.example.absensi.ui.login.LoginActivity;
import com.example.absensi.utils.DialogClass;

public class RegisterDeviceActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_fullname,edt_email,edt_phone,edt_password,edt_nik,edt_nopegawai;
    Button btn_register,btn_cekdata;
    private android.app.AlertDialog alertDialog;
    private RegisterDeviceViewModel registerDeviceViewModel;
    private VerifyDeviceViewModel verifyDeviceViewModel;
    TextView tv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_device);
        edt_fullname = findViewById(R.id.edt_fullname);
        edt_email = findViewById(R.id.edt_email);
        edt_phone = findViewById(R.id.edt_number);
        btn_cekdata = findViewById(R.id.btn_cek);
        btn_register = findViewById(R.id.btn_register);
        edt_password = findViewById(R.id.edt_password);
        edt_nik = findViewById(R.id.edt_nik);
        tv_login = findViewById(R.id.tv_login);
        edt_nopegawai = findViewById(R.id.edt_nopegawai);
        registerDeviceViewModel = ViewModelProviders.of(this).get(RegisterDeviceViewModel.class);
        verifyDeviceViewModel = ViewModelProviders.of(this).get(VerifyDeviceViewModel.class);

        btn_register.setOnClickListener(this);
        btn_cekdata.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                register();

                break;

            case R.id.btn_cek:
                getData();
                break;

            case R.id.tv_login:
                startActivity(new Intent(RegisterDeviceActivity.this, LoginActivity.class));
                break;
        }
    }

    private void register() {
        String email = edt_email.getText().toString();
        String fullName = edt_fullname.getText().toString();
        String nik = edt_nik.getText().toString();
        String phone = edt_phone.getText().toString();

        if(!email.isEmpty() && !fullName.isEmpty() && !nik.isEmpty() && !phone.isEmpty()){
            String no_pegawai = edt_nopegawai.getText().toString();
            String password = edt_password.getText().toString();
            @SuppressLint("HardwareIds") String device_unique_id = Settings.Secure.getString(RegisterDeviceActivity.this.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            System.out.println(device_unique_id);
            verifyDeviceViewModel.verifyDevice(no_pegawai,device_unique_id,password).observe(this, new Observer<MessageOnly>() {
                @Override
                public void onChanged(MessageOnly messageOnly) {
                    if(messageOnly.getStatus()){
                        alertDialog.dismiss();
                        Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }else{
                        alertDialog.dismiss();
                        Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"Silahkan input no pegawai anda terlebih dahulu",Toast.LENGTH_SHORT).show();
            edt_nopegawai.setFocusable(true);
            edt_nopegawai.setFocusableInTouchMode(true);
        }
    }

    private void getData() {
        String no_pegawai = edt_nopegawai.getText().toString();
        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();
        registerDeviceViewModel.registerDevice(no_pegawai).observe(this, new Observer<ResponseRegisterDevice>() {
            @Override
            public void onChanged(ResponseRegisterDevice responseRegisterDevice) {
                if(responseRegisterDevice.getStatus()){
                    alertDialog.dismiss();
                    edt_email.setText(responseRegisterDevice.getDataUsers().getEmail());
                    edt_fullname.setText(responseRegisterDevice.getDataUsers().getNamaLengkap());
                    edt_nik.setText(responseRegisterDevice.getDataUsers().getNik());
                    edt_phone.setText(responseRegisterDevice.getDataUsers().getNoTelp());
                }else{
                    alertDialog.dismiss();
                    edt_email.setText("");
                    edt_fullname.setText("");
                    edt_nik.setText("");
                    edt_phone.setText("");
                    Toast.makeText(getApplicationContext(),responseRegisterDevice.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}