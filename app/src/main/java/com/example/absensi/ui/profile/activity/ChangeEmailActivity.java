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
import com.example.absensi.ui.profile.ChangeEmailViewModel;
import com.example.absensi.utils.DialogClass;

public class ChangeEmailActivity extends AppCompatActivity implements View.OnClickListener {
    
    EditText edt_oldEmail,edt_newEmail,edt_confirmEmail;
    Button btn_update;
    private SystemDataLocal systemDataLocal;
    private ChangeEmailViewModel changeEmailViewModel;
    private android.app.AlertDialog alertDialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView tv_title = findViewById(R.id.title);
        setSupportActionBar(toolbar);
        
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tv_title.setText("Form Ubah Email");
        edt_confirmEmail = findViewById(R.id.edt_confirmEmail);
        edt_newEmail = findViewById(R.id.edt_newEmail);
        edt_oldEmail = findViewById(R.id.edt_oldEmail);
        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        systemDataLocal = new SystemDataLocal(this);
        changeEmailViewModel = ViewModelProviders.of(this).get(ChangeEmailViewModel.class);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_update){
            changeEmail();
        }
    }

    private void changeEmail() {
        String oldEmail = edt_oldEmail.getText().toString();
        final String newEmail = edt_newEmail.getText().toString();
        String confirmEmail = edt_confirmEmail.getText().toString();
        String id_users = systemDataLocal.getLoginData().getId_pegawai();
        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();

        changeEmailViewModel.changeEmail(id_users,oldEmail,newEmail,confirmEmail).observe(this, new Observer<MessageOnly>() {
            @Override
            public void onChanged(MessageOnly messageOnly) {
                if(messageOnly.getStatus()){
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                    systemDataLocal.edtAllSessionLogin(newEmail,
                            systemDataLocal.getLoginData().getFull_name(),
                            systemDataLocal.getLoginData().getPhone(),
                            systemDataLocal.getLoginData().getDevice_id(),
                            systemDataLocal.getLoginData().getId_user(),
                            systemDataLocal.getLoginData().getIs_verified(),
                            systemDataLocal.getLoginData().getRole(),
                            systemDataLocal.getLoginData().getPassword(),
                            systemDataLocal.getLoginData().getAlamat(),
                            systemDataLocal.getLoginData().getFoto(),
                            systemDataLocal.getLoginData().getJenis_kelamin(),
                            systemDataLocal.getLoginData().getId_pegawai(),
                            systemDataLocal.getLoginData().getId_jabatan(),
                            systemDataLocal.getLoginData().getNama_jabatan(),
                            systemDataLocal.getLoginData().getGaji(),
                            systemDataLocal.getLoginData().getTgl_lahir(),
                            systemDataLocal.getLoginData().getNik(),
                            systemDataLocal.getLoginData().getCheck_in(),
                            systemDataLocal.getLoginData().getCheck_out()
                    );
                    onBackPressed();
                }else{
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}