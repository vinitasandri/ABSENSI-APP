package com.example.absensi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.R;
import com.example.absensi.model.laporan.DataLaporan;
import com.example.absensi.ui.laporan.DetailLaporanActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.ViewHolder> {

    private Context context;
    private List<DataLaporan> list;

    public LaporanAdapter(Context context, List<DataLaporan> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_laporan,parent,false);
        return  new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataLaporan dataLaporan = list.get(position);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat("EEEE");
        Locale id = new Locale("in","ID");
        String date = dataLaporan.getDate();
        String[] parts = date.split("-");
        holder.tv_tgl.setText(parts[2]);
        try {
            String hari = format2.format(Objects.requireNonNull(format1.parse(date)));
            System.out.println(hari);
            holder.tv_hari.setText(hari);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.cardLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context, DetailLaporanActivity.class);
                detail.putExtra("data",dataLaporan);
                context.startActivity(detail);
            }
        });
        String status = dataLaporan.getStatus();
        if(status.equals("Libur")){
            holder.cardColor.setCardBackgroundColor(Color.RED);
//            holder.circleImageView6.setImageResource(R.color.red);
        }else if(status.equals("Libur Nasional")){
            holder.cardColor.setCardBackgroundColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_hari,tv_tgl;
        CardView cardLaporan,cardColor;
        CircleImageView circleImageView6;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_hari = itemView.findViewById(R.id.tv_hari);
            tv_tgl = itemView.findViewById(R.id.tv_tgl);
            cardLaporan = itemView.findViewById(R.id.cardLaporan);
            cardColor = itemView.findViewById(R.id.cardColor);
            circleImageView6 = itemView.findViewById(R.id.circleImageView6);
        }
    }
}
