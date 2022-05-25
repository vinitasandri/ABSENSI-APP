package com.example.absensi.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.absensi.R;
import com.example.absensi.adapter.LaporanAdapter;
import com.example.absensi.model.laporan.DataLaporanResponse;
import com.example.absensi.session.SystemDataLocal;
import com.example.absensi.ui.gaji.DataGajiActivity;
import com.example.absensi.ui.laporan.GetDataLaporanViewModel;
import com.example.absensi.ui.laporan.ReportActivity;

public class ReportFragment extends Fragment implements View.OnClickListener {


    private CardView cardLaporan,cardGaji;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        cardGaji = view.findViewById(R.id.cardGaji);
        cardLaporan = view.findViewById(R.id.cardLaporan);
        cardLaporan.setOnClickListener(this);
        cardGaji.setOnClickListener(this);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.cardLaporan){
            startActivity(new Intent(getContext(), ReportActivity.class));
        }else if(view.getId() == R.id.cardGaji){
            startActivity(new Intent(getContext(), DataGajiActivity.class));
        }
    }
}