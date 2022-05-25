package com.example.absensi.ui.surat.cuti.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.absensi.R;
import com.example.absensi.model.MessageOnly;
import com.example.absensi.model.surat.DataCuti;
import com.example.absensi.session.SystemDataLocal;
import com.example.absensi.ui.surat.cuti.CancelCutiViewModel;
import com.example.absensi.utils.DialogClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class DetailCutiActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_startDate,edt_endDate,edt_keterangan,edt_namapegawai;
    private SystemDataLocal systemDataLocal;
    private DataCuti dataCuti;
    Button btnCancel;
    AlertDialog.Builder builder;
    View myview;
    private CancelCutiViewModel cancelCutiViewModel;
    private android.app.AlertDialog alertDialog;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cuti);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tv_title = findViewById(R.id.title);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tv_title.setText("Detail Surat Cuti");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        edt_startDate = findViewById(R.id.edt_startDate);
        edt_endDate = findViewById(R.id.edt_endDate);
        edt_keterangan = findViewById(R.id.edtKeterangan);
        edt_keterangan = findViewById(R.id.edtKeterangan);
        edt_namapegawai = findViewById(R.id.edt_namapegawai);
        btnCancel = findViewById(R.id.btn_cancel);
        systemDataLocal = new SystemDataLocal(this);
        edt_namapegawai.setText(systemDataLocal.getLoginData().getFull_name());
        dataCuti = getIntent().getParcelableExtra("data");
        if(dataCuti != null){

            edt_keterangan.setText(dataCuti.getKeterangan());
            if(dataCuti.getStatus().equals("0")){
                btnCancel.setVisibility(View.VISIBLE);
            }else{
                btnCancel.setVisibility(View.GONE);
            }
            String date1 = dataCuti.getDariTanggal();
            String date2 = dataCuti.getSampaiTanggal();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            try {
               String  startDate = format2.format(Objects.requireNonNull(format1.parse(date1)));
               String  endDate = format2.format(Objects.requireNonNull(format1.parse(date2)));
               edt_startDate.setText(startDate);
               edt_endDate.setText(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        btnCancel.setOnClickListener(this);
        cancelCutiViewModel = ViewModelProviders.of(this).get(CancelCutiViewModel.class);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_cancel){
            dialogCancel();
        }
    }

    private void dialogCancel() {
        builder = new AlertDialog.Builder(this);
        myview = getLayoutInflater().inflate(R.layout.container_cancel,null,false);
        builder.setView(myview);
        builder.setTitle("Form Pembatalan Surat Cuti");
        builder.setCancelable(true);
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Batalkan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelCuti();
            }
        });

        builder.show();
    }

    private void cancelCuti() {
        String id_cuti = dataCuti.getIdCuti();
        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();
        cancelCutiViewModel.cancelCuti(id_cuti).observe(this, new Observer<MessageOnly>() {
            @Override
            public void onChanged(MessageOnly messageOnly) {
                if(messageOnly.getStatus()){
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
    }
}