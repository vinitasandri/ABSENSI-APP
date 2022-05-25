package com.example.absensi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.R;
import com.example.absensi.model.gaji.DataGaji;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class GajiAdapter extends RecyclerView.Adapter<GajiAdapter.ViewHolder> {

    private Context context;
    private List<DataGaji> listData;

    public GajiAdapter(Context context, List<DataGaji> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.container_gaji,parent,false);
        return new ViewHolder(v);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataGaji dataGaji = listData.get(position);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat("EEEE");
        Locale id = new Locale("in","ID");
        String date = dataGaji.getTanggal();
        String[] parts = date.split("-");
        holder.tv_tanggal.setText(parts[2]);
        try {
            String hari = format2.format(Objects.requireNonNull(format1.parse(date)));
            System.out.println(hari);
            holder.tv_hari.setText(hari);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tv_jamkerja.setText(dataGaji.getWorkTime());
        int gaji = Integer.parseInt(dataGaji.getNominal());
        holder.tv_uangmakan.setText("Rp " + String.format("%,d",gaji));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_hari,tv_tanggal,tv_jamkerja,tv_uangmakan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_hari = itemView.findViewById(R.id.tv_hari);
            tv_tanggal = itemView.findViewById(R.id.tv_tanggal);
            tv_jamkerja = itemView.findViewById(R.id.tv_jamkerja);
            tv_uangmakan = itemView.findViewById(R.id.tv_uangmakan);
        }
    }
}
