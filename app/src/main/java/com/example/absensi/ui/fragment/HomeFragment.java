package com.example.absensi.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.absensi.R;
import com.example.absensi.model.absensi.AbsensiResponse;
import com.example.absensi.model.absensi.DataAbsensi;
import com.example.absensi.model.absensi.DataPercentResponse;
import com.example.absensi.session.SystemDataLocal;
import com.example.absensi.ui.home.GetAbsensiHomeViewModel;
import com.example.absensi.ui.home.GetDataPercentViewModel;
import com.example.absensi.utils.ProgressBarAnimation;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    SystemDataLocal systemDataLocal;
    TextView name,tv_date,tv_checkin,tv_checkout,tv_late,tv_worktime,tv_percent,tv_status;
    Context context;
    ProgressBar  progressBar;
    private GetAbsensiHomeViewModel getAbsensiHomeViewModel;
    private GetDataPercentViewModel getDataPercentViewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        systemDataLocal = new SystemDataLocal(context);
        String full_name = systemDataLocal.getLoginData().getFull_name();
        String[] split = full_name.split(" ");
        name = view.findViewById(R.id.name);
        tv_checkin = view.findViewById(R.id.tv_checkin);
        tv_checkout = view.findViewById(R.id.tv_checkout);
        tv_late = view.findViewById(R.id.tv_late);
        tv_worktime = view.findViewById(R.id.tv_worktime);
        tv_date = view.findViewById(R.id.tv_date);
        tv_percent = view.findViewById(R.id.tv_percent);
        tv_status = view.findViewById(R.id.tv_status);
        getDataPercentViewModel = ViewModelProviders.of(this).get(GetDataPercentViewModel.class);
//        LocalDateTime ldt = LocalDateTime.now();
//        Locale id = new Locale("in","ID");
//        String dateNow = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", id).format(ldt);
        name.setText(split[0]);

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        progressBar = view.findViewById(R.id.progressbar);



        tv_date.setText(formattedDate);
        getAbsensiHomeViewModel = ViewModelProviders.of(this).get(GetAbsensiHomeViewModel.class);
        loadDataAbsensi();
        loadDataPercent();
    }

    private void loadDataPercent() {
        String no_pegawai = systemDataLocal.getLoginData().getId_pegawai();
        getDataPercentViewModel.getDataPercent(no_pegawai).observe(this, new Observer<DataPercentResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(DataPercentResponse dataPercentResponse) {
                if(dataPercentResponse.getStatus()){
                    int percent = Integer.parseInt(dataPercentResponse.getPercent());
                    ProgressBarAnimation anim = new ProgressBarAnimation(progressBar,0,percent);
                    anim.setDuration(1000);
                    tv_percent.setText(percent + "%");
                    progressBar.startAnimation(anim);
                }
            }
        });
    }

    private void loadDataAbsensi() {
        String no_pegawai = systemDataLocal.getLoginData().getId_pegawai();

        getAbsensiHomeViewModel.getAbsensiHome(no_pegawai).observe(this, new Observer<AbsensiResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(AbsensiResponse absensiResponse) {
                if(absensiResponse.getStatus()){
                    if(absensiResponse.getDataAbsensi() != null){
                        tv_checkin.setText(absensiResponse.getDataAbsensi().getCheckIn());
                        tv_checkout.setText(absensiResponse.getDataAbsensi().getCheckOut());
                        tv_late.setText(absensiResponse.getDataAbsensi().getLate());
                        tv_worktime.setText(absensiResponse.getDataAbsensi().getWorkTime());
                        String status = absensiResponse.getDataAbsensi().getStatus();
                        if(status.equals("Tidak")){
                            tv_status.setText("Tidak Hadir");
                        }else{
                            tv_status.setText(status);
                        }
                    }else{
                        tv_checkin.setText("00:00:00");
                        tv_checkout.setText("00:00:00");
                        tv_late.setText("00:00:00");
                        tv_worktime.setText("00:00:00");
                    }

                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDataAbsensi();
        loadDataPercent();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }


}