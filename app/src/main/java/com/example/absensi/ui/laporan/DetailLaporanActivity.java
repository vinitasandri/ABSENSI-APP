package com.example.absensi.ui.laporan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.absensi.R;
import com.example.absensi.model.laporan.DataLaporan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

public class DetailLaporanActivity extends AppCompatActivity {

    TextView tv_title;
    private DataLaporan dataLaporan;
    TextView name,tv_date,tv_checkin,tv_checkout,tv_late,tv_worktime,tv_percent,tv_status,tv_keterangan;
    CardView cardKeterangan;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laporan);
        dataLaporan = getIntent().getParcelableExtra("data");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv_title = findViewById(R.id.title);
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
        tv_title.setText("Detail Laporan");
        tv_checkin = findViewById(R.id.tv_checkin);
        tv_checkout = findViewById(R.id.tv_checkout);
        tv_late = findViewById(R.id.tv_late);
        tv_worktime = findViewById(R.id.tv_worktime);
        tv_date = findViewById(R.id.tv_date);
        tv_status = findViewById(R.id.tv_status);
        tv_keterangan = findViewById(R.id.keterangan_libur);
        cardKeterangan = findViewById(R.id.cardKeterangan);

        tv_checkin.setText(dataLaporan.getCheckIn());
        tv_checkout.setText(dataLaporan.getCheckOut());
        tv_late.setText(dataLaporan.getLate());
        tv_worktime.setText(dataLaporan.getWorkTime());
        String status = dataLaporan.getStatus();
        if(status.equals("Tidak")){
            tv_status.setText("Tidak Hadir");
        }else{
            tv_status.setText(status);
        }
        if(status.equals("Libur Nasional")){
            cardKeterangan.setVisibility(View.VISIBLE);
            tv_keterangan.setText(dataLaporan.getKeterangan());
        }else{
            cardKeterangan.setVisibility(View.GONE);
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        Locale id = new Locale("in","ID");
        String date = dataLaporan.getDate();
        String[] parts = date.split("-");
        try {
            String hari = format2.format(Objects.requireNonNull(format1.parse(date)));
            tv_date.setText(hari);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}