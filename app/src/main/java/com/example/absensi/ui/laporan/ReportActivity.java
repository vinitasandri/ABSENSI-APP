package com.example.absensi.ui.laporan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.absensi.R;
import com.example.absensi.adapter.LaporanAdapter;
import com.example.absensi.model.laporan.DataLaporan;
import com.example.absensi.model.laporan.DataLaporanResponse;
import com.example.absensi.session.SystemDataLocal;

import java.util.List;

public class ReportActivity extends AppCompatActivity {

    private RecyclerView rv_laporan;
    private SystemDataLocal systemDataLocal;
    private GetDataLaporanViewModel getDataLaporanViewModel;
    private Activity activity;
    private TextView tv_hadir,tv_tidakhadir,tv_izin,tv_harikerja,tv_harilibur;
    SwipeRefreshLayout swipeRefreshLayout;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        rv_laporan = findViewById(R.id.rv_laporan);
        systemDataLocal = new SystemDataLocal(this);
        getDataLaporanViewModel = ViewModelProviders.of(this).get(GetDataLaporanViewModel.class);
        tv_hadir = findViewById(R.id.tv_hadir);
        tv_tidakhadir = findViewById(R.id.tv_tidakhadir);
        tv_harikerja = findViewById(R.id.tv_harikerja);
        tv_harilibur = findViewById(R.id.tv_harilibur);
        tv_izin = findViewById(R.id.tv_izin);
        swipeRefreshLayout = findViewById(R.id.swipe);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tv_title = findViewById(R.id.title);
        tv_title.setText("Laporan Presensi");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                readDataLaporan();
            }
        },1000));
        readDataLaporan();
    }

    private void readDataLaporan() {
        String id_users = systemDataLocal.getLoginData().getId_pegawai();
        getDataLaporanViewModel.getDataLaporan(id_users).observe(this, new Observer<DataLaporanResponse>() {
            @Override
            public void onChanged(DataLaporanResponse dataLaporanResponse) {
                if(dataLaporanResponse.getStatus()){
                    if(dataLaporanResponse.getDataLaporan().size() > 0){
                        readData(dataLaporanResponse.getDataLaporan());
                        tv_hadir.setText(dataLaporanResponse.getHadir());
                        tv_tidakhadir.setText(dataLaporanResponse.getTidakHadir());
                        tv_izin.setText(dataLaporanResponse.getIzin());
                        tv_harikerja.setText(dataLaporanResponse.getJumlah_kerja());
                        tv_harilibur.setText(dataLaporanResponse.getJumlah_libur());

                    }
                }
            }
        });
    }

    private void readData(List<DataLaporan> dataLaporan) {
        LaporanAdapter laporanAdapter = new LaporanAdapter(this,dataLaporan);
        rv_laporan.setLayoutManager(new GridLayoutManager(this,2));
        rv_laporan.setAdapter(laporanAdapter);
        rv_laporan.setNestedScrollingEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        readDataLaporan();
    }

}