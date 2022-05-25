package com.example.absensi.ui.profile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.absensi.R;
import com.example.absensi.model.MessageOnly;
import com.example.absensi.session.SystemDataLocal;
import com.example.absensi.ui.profile.ChangePasswordViewModel;
import com.example.absensi.utils.DialogClass;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_update;
    EditText edt_oldPassword,edt_newPassword,edt_confirmPassword;
    private SystemDataLocal systemDataLocal;
    private ChangePasswordViewModel changePasswordViewModel;
    private android.app.AlertDialog alertDialog;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView tv_title = findViewById(R.id.title);

        setSupportActionBar(toolbar);
        btn_update = findViewById(R.id.btn_update);
        edt_oldPassword = findViewById(R.id.edt_oldPassword);
        edt_newPassword = findViewById(R.id.edt_newPassword);
        edt_confirmPassword = findViewById(R.id.edt_confirmPassword);

        systemDataLocal = new SystemDataLocal(this);
        changePasswordViewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel.class);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tv_title.setText("Form Ubah Password");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_update){
            changePassword();
        }
    }

    private void changePassword() {
        String oldPassword = edt_oldPassword.getText().toString();
        String newPassword = edt_newPassword.getText().toString();
        String confirmPassword = edt_confirmPassword.getText().toString();
        String id_users = systemDataLocal.getLoginData().getId_pegawai();
        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();

        changePasswordViewModel.changePassword(id_users,oldPassword,newPassword,confirmPassword).observe(this, new Observer<MessageOnly>() {
            @Override
            public void onChanged(MessageOnly messageOnly) {
                if(messageOnly.getStatus()){
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                    onBackPressed();
                }else{
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}