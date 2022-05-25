package com.example.absensi.ui.surat.cuti.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.absensi.R;
import com.example.absensi.adapter.SuratCutiAdapter;
import com.example.absensi.model.surat.CutiResponse;
import com.example.absensi.session.SystemDataLocal;
import com.example.absensi.ui.surat.cuti.GetDataCutiViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SuratCutiActivity extends AppCompatActivity {

    FloatingActionButton floatAdd;
    TextView tvSisaCuti,tvCuti,tv_notif_kosong;
    RecyclerView rv_surat;
    private SystemDataLocal systemDataLocal;
    private GetDataCutiViewModel getDataCutiViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_cuti);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tv_title = findViewById(R.id.title);
        tv_title.setText("Daftar Surat Cuti");


        floatAdd = findViewById(R.id.floatAdd);
        tvSisaCuti = findViewById(R.id.tvSisaCuti);
        tv_notif_kosong = findViewById(R.id.tv_notif_kosong);
        tvCuti = findViewById(R.id.tvCuti);
        rv_surat = findViewById(R.id.rv_surat);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuratCutiActivity.this, AddCutiActivity.class));
            }
        });

        systemDataLocal = new SystemDataLocal(this);
        getDataCutiViewModel = ViewModelProviders.of(this).get(GetDataCutiViewModel.class);
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

        loadData();
    }

    private void loadData() {
        String idUsers = systemDataLocal.getLoginData().getId_pegawai();
        getDataCutiViewModel.getDataCuti(idUsers).observe(this, new Observer<CutiResponse>() {
            @Override
            public void onChanged(CutiResponse cutiResponse) {
                if(cutiResponse.getStatus()){
                    if(cutiResponse.getDataCuti().size() > 0){
                        SuratCutiAdapter suratCutiAdapter = new SuratCutiAdapter(getApplicationContext(),cutiResponse.getDataCuti());
                        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
                        rv_surat.setAdapter(suratCutiAdapter);
                        rv_surat.setLayoutManager(lm);
                        rv_surat.setVisibility(View.VISIBLE);
                        tv_notif_kosong.setVisibility(View.GONE);
                    }else{
                        rv_surat.setVisibility(View.GONE);
                        tv_notif_kosong.setVisibility(View.VISIBLE);
                    }

                    tvSisaCuti.setText(String.valueOf(cutiResponse.getSisaCuti()));
                    tvCuti.setText(cutiResponse.getJumlahCuti());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}