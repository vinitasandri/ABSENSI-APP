package com.example.absensi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.R;
import com.example.absensi.model.surat.DataCuti;
import com.example.absensi.ui.surat.cuti.activity.DetailCutiActivity;

import java.util.List;

public class SuratCutiAdapter extends RecyclerView.Adapter<SuratCutiAdapter.ViewHolder> {

    private Context context;
    private List<DataCuti> listData;

    public SuratCutiAdapter(Context context, List<DataCuti> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_suratcuti,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataCuti dataCuti = listData.get(position);
        holder.tv_number.setText(dataCuti.getJumlahHari());
        holder.tv_start.setText(dataCuti.getDariTanggal());
        holder.tv_end.setText(dataCuti.getSampaiTanggal());
        String status = dataCuti.getStatus();
        if(status.equals("0")){
            holder.tv_status_warning.setVisibility(View.VISIBLE);
            holder.tv_status_primary.setVisibility(View.GONE);
            holder.tv_status_danger.setVisibility(View.GONE);
        }else if(status.equals("1")){
            holder.tv_status_warning.setVisibility(View.GONE);
            holder.tv_status_primary.setVisibility(View.GONE);
            holder.tv_status_danger.setVisibility(View.VISIBLE);
        }else if(status.equals("2")){
            holder.tv_status_warning.setVisibility(View.GONE);
            holder.tv_status_primary.setVisibility(View.VISIBLE);
            holder.tv_status_danger.setVisibility(View.GONE);
        }
        holder.cardCuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context, DetailCutiActivity.class);
                detail.putExtra("data",dataCuti);
                detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number,tv_start,tv_end,tv_status_primary,tv_status_warning,tv_status_danger;
        CardView cardCuti;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_start = itemView.findViewById(R.id.tv_mulai);
            tv_end = itemView.findViewById(R.id.tv_selesai);
            tv_status_primary = itemView.findViewById(R.id.tv_status_primary);
            tv_status_warning = itemView.findViewById(R.id.tv_status_warning);
            tv_status_danger = itemView.findViewById(R.id.tv_status_danger);
            cardCuti = itemView.findViewById(R.id.cardCuti);
        }
    }
}
