package com.example.absensi.ui.surat.tidak_hadir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.absensi.R;
import com.example.absensi.adapter.SuratIzinAdapter;
import com.example.absensi.model.surat.DataSuratIzin;
import com.example.absensi.model.surat.SuratIzinResponse;
import com.example.absensi.network.Constanta;
import com.example.absensi.session.SystemDataLocal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SuratTidakHadirActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tv_title,tv_notif_kosong;
    private FloatingActionButton floatAdd;
    private GetDataSuratIzinViewModel getDataSuratIzinViewModel;
    private SystemDataLocal systemDataLocal;
    RecyclerView rv_surat;
    AlertDialog.Builder builder;
    View myview,dialogView;
    ImageView imageGallery;
    Button btn_cancel;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_tidak_hadir);
        toolbar = findViewById(R.id.toolbar);
        tv_title = findViewById(R.id.title);
        tv_notif_kosong = findViewById(R.id.tv_notif_kosong);
        rv_surat = findViewById(R.id.rv_surat);
        floatAdd = findViewById(R.id.floatAdd);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuratTidakHadirActivity.this,TambahSuratTidakHadirActivity.class));
            }
        });
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        systemDataLocal = new SystemDataLocal(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_title.setText("Daftar Surat Tidak Hadir");
        getDataSuratIzinViewModel = ViewModelProviders.of(this).get(GetDataSuratIzinViewModel.class);
        loadData();
    }

    private void loadData() {
        String id_users = systemDataLocal.getLoginData().getId_pegawai();
        getDataSuratIzinViewModel.getDataSuratIzin(id_users).observe(this, new Observer<SuratIzinResponse>() {
            @Override
            public void onChanged(SuratIzinResponse suratIzinResponse) {
                if(suratIzinResponse.getStatus()){
                    if(suratIzinResponse.getDataSuratizin().size() > 0){
                        tv_notif_kosong.setVisibility(View.GONE);
                        rv_surat.setVisibility(View.VISIBLE);
                        readData(suratIzinResponse.getDataSuratizin());


                    }else{
                        rv_surat.setVisibility(View.GONE);
                        tv_notif_kosong.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void readData(List<DataSuratIzin> dataSuratizin) {
        SuratIzinAdapter suratIzinAdapter = new SuratIzinAdapter(this,dataSuratizin);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv_surat.setLayoutManager(lm);
        rv_surat.setAdapter(suratIzinAdapter);
        suratIzinAdapter.setOnItemClickCallback(new SuratIzinAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(DataSuratIzin dataSuratIzin) {
                displayDialog(dataSuratIzin);
            }
        });
    }

    private void displayDialog(DataSuratIzin dataSuratIzin) {
        builder = new AlertDialog.Builder(this);
        myview = getLayoutInflater().inflate(R.layout.dialog_izin,null,false);
        builder.setView(myview);
        btn_cancel = myview.findViewById(R.id.btn_cancel);
        imageGallery = myview.findViewById(R.id.imageGallery);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cancel
            }
        });
        if(dataSuratIzin.getStatusSurat().equals("0")){
            btn_cancel.setVisibility(View.GONE);
//            btn_cancel.setVisibility(View.VISIBLE);
        }else{
            btn_cancel.setVisibility(View.GONE);
        }
        Glide.with(this).load(Constanta.BASE_URL_IMG_IZIN + dataSuratIzin.getBukti()).into(imageGallery);
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}