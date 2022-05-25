package com.example.absensi.ui.scanner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.absensi.R;
import com.example.absensi.model.MessageOnly;
import com.example.absensi.session.SystemDataLocal;
import com.example.absensi.ui.home.HomeActivity;
import com.example.absensi.utils.DialogClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AbsensiActivity extends AppCompatActivity implements View.OnClickListener {

    private SystemDataLocal systemDataLocal;
    TextView tv_idpegawai, tv_namapegawai,tv_date,tv_jam,tv_title,tv_start;
    Toolbar toolbar;
    Calendar calendar;
    Button btn_submit;
    private AddAbsensiViewModel addAbsensiViewModel;
    private android.app.AlertDialog alertDialog;
    String timeNow;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);
        systemDataLocal = new SystemDataLocal(this);
        tv_idpegawai = findViewById(R.id.tv_id_pegawai);
        tv_namapegawai = findViewById(R.id.tv_nama_pegawai);
        tv_title = findViewById(R.id.title);
        toolbar = findViewById(R.id.toolbar);
        tv_date = findViewById(R.id.tv_tanggal);
        tv_start = findViewById(R.id.textView15);
        tv_jam = findViewById(R.id.tv_jam);
        btn_submit = findViewById(R.id.btn_submit);
        String is_start = getIntent().getStringExtra("is_start");
        addAbsensiViewModel = ViewModelProviders.of(this).get(AddAbsensiViewModel.class);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_title.setText("Detail Absensi");
        tv_namapegawai.setText(systemDataLocal.getLoginData().getFull_name());
        tv_idpegawai.setText(systemDataLocal.getLoginData().getId_pegawai());
        LocalDateTime ldt = LocalDateTime.now();
        String dateNow = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        Date dateTime = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm:ss");
        timeNow = newFormat.format(dateTime);
        tv_jam.setText(timeNow);
        try {
           String date2 = format2.format(Objects.requireNonNull(format1.parse(dateNow)));
            tv_date.setText(date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(is_start.equals("0")){
            tv_start.setText("Jam Masuk :");
        }else{
            tv_start.setText("Jam Pulang :");
        }

        btn_submit.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_submit){
            addAbsensi();
        }
    }

    private void addAbsensi() {
        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();;
        addAbsensiViewModel.addAbsensi(systemDataLocal.getLoginData().getId_pegawai(),timeNow).observe(this, new Observer<MessageOnly>() {
            @Override
            public void onChanged(MessageOnly messageOnly) {
                if(messageOnly.getStatus()){
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AbsensiActivity.this, HomeActivity.class));
                    finish();
                }
            }
        });
    }
}