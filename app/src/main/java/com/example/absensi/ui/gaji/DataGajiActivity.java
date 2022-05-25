package com.example.absensi.ui.gaji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.absensi.R;
import com.example.absensi.adapter.GajiAdapter;
import com.example.absensi.model.gaji.DataGaji;
import com.example.absensi.model.gaji.DataGajiResponse;
import com.example.absensi.model.gaji.TotalUangMakanResponse;
import com.example.absensi.session.SystemDataLocal;

import java.util.List;

public class DataGajiActivity extends AppCompatActivity {

    private SystemDataLocal systemDataLocal;
    private GetTotalUangMakanViewModel getTotalUangMakanViewModel;
    private GetDataGajiViewModel getDataGajiViewModel;
    RecyclerView rv_datagaji;
    Toolbar toolbar;
    TextView tv_title,tv_totaluang;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_gaji);
        toolbar = findViewById(R.id.toolbar);
        tv_title = findViewById(R.id.title);
        tv_totaluang = findViewById(R.id.tv_totaluang);
        rv_datagaji = findViewById(R.id.rv_datagaji);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tv_title.setText("Data Gaji Uang Makan");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        systemDataLocal = new SystemDataLocal(this);
        getDataGajiViewModel = ViewModelProviders.of(this).get(GetDataGajiViewModel.class);
        getTotalUangMakanViewModel = ViewModelProviders.of(this).get(GetTotalUangMakanViewModel.class);
        loadTotalGaji();
        loadDataGaji();
    }

    private void loadDataGaji() {
        String no_pegawai = systemDataLocal.getLoginData().getId_pegawai();
        getDataGajiViewModel.getDataGaji(no_pegawai).observe(this, new Observer<DataGajiResponse>() {
            @Override
            public void onChanged(DataGajiResponse dataGajiResponse) {
                if(dataGajiResponse.getStatus()){
                    if(dataGajiResponse.getDataGaji().size() > 0){
                        readData(dataGajiResponse.getDataGaji());
                    }else{
                        rv_datagaji.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void readData(List<DataGaji> dataGaji) {
        GajiAdapter gajiAdapter = new GajiAdapter(this,dataGaji);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv_datagaji.setAdapter(gajiAdapter);
        rv_datagaji.setLayoutManager(lm);
    }

    private void loadTotalGaji() {
        String no_pegawai = systemDataLocal.getLoginData().getId_pegawai();
        getTotalUangMakanViewModel.getTotalUangMakan(no_pegawai).observe(this, new Observer<TotalUangMakanResponse>() {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onChanged(TotalUangMakanResponse totalUangMakanResponse) {
                if(totalUangMakanResponse.getStatus()){
                    if(totalUangMakanResponse.getTotal() == null){
                        int total = 0;
                        tv_totaluang.setText("Rp " + String.format("%,d",total));
                    }else{
                        int total = Integer.parseInt(totalUangMakanResponse.getTotal());
                        tv_totaluang.setText("Rp " + String.format("%,d",total));
                    }

                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataGaji();
        loadTotalGaji();
    }
}