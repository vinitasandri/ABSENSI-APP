package com.example.absensi.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.absensi.R;
import com.example.absensi.model.register.ResponseRegister;
import com.example.absensi.utils.DialogClass;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_login,info_ktp;
    Button btn_register,btn_cekdata;
    EditText edt_fullname,edt_email,edt_phone,edt_password,edt_nik;
    private RegisterViewModel registerViewModel;
    private android.app.AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tv_login = findViewById(R.id.tv_login);
        btn_register = findViewById(R.id.btn_register);
        btn_cekdata = findViewById(R.id.btn_cek);
        edt_fullname = findViewById(R.id.edt_fullname);
        edt_email = findViewById(R.id.edt_email);
        edt_phone = findViewById(R.id.edt_number);
        edt_password = findViewById(R.id.edt_password);
        edt_nik = findViewById(R.id.edt_nik);
        info_ktp = findViewById(R.id.info_ktp);
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        edt_nik.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    info_ktp.setVisibility(View.VISIBLE);
                    int digit_ktp = edt_nik.getText().toString().length();
                    if(digit_ktp  >= 16){
                        info_ktp.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                register();
                break;

            case R.id.tv_login:
                onBackPressed();
                break;
        }
    }

    private void register(){
        @SuppressLint("HardwareIds") String device_unique_id = Settings.Secure.getString(RegisterActivity.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        String full_name = edt_fullname.getText().toString();
        String email = edt_email.getText().toString();
        String phone = edt_phone.getText().toString();
        String password = edt_password.getText().toString();
        String nik = edt_nik.getText().toString();
        System.out.println(nik);

        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();

        if(!full_name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !password.isEmpty()){
            registerViewModel.getRegisterResponse(full_name,email,phone,password,device_unique_id,nik).observe(this, new Observer<ResponseRegister>() {
                @Override
                public void onChanged(ResponseRegister responseRegister) {
                    if(responseRegister != null){
                        if(responseRegister.getStatus()){
                            alertDialog.dismiss();
                            Toast.makeText(RegisterActivity.this,responseRegister.getMessage(),Toast.LENGTH_LONG).show();
                            edt_fullname.setText("");
                            edt_email.setText("");
                            edt_password.setText("");
                            edt_phone.setText("");
                            edt_nik.setText("");
                        }else{
                            alertDialog.dismiss();
                            Toast.makeText(RegisterActivity.this,responseRegister.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }

                }
            });

        }else{
            alertDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Data tidak boleh kosong !" ,Toast.LENGTH_LONG).show();
        }
    }
}
