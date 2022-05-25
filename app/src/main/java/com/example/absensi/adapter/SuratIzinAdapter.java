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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.R;
import com.example.absensi.model.surat.DataSuratIzin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SuratIzinAdapter extends RecyclerView.Adapter<SuratIzinAdapter.ViewHolder> {

    private Context context;
    private List<DataSuratIzin> listData;
    private OnItemClickCallBack onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallBack onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public SuratIzinAdapter(Context context, List<DataSuratIzin> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_suratizin,parent,false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataSuratIzin dataSuratIzin = listData.get(position);
        holder.tv_alasan.setText(dataSuratIzin.getAlasan());
        String status = dataSuratIzin.getStatusSurat();
        if(status.equals("0")){
            holder.tv_status.setText("Belum dikonfirmasi");
        }else{
            holder.tv_status.setText("Sudah dikonfirmasi");
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat("EEEE");
        Locale id = new Locale("in","ID");
        String date = dataSuratIzin.getTanggal();
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
                onItemClickCallback.onItemClicked(dataSuratIzin);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_hari,tv_tgl,tv_status,tv_alasan;
        CardView cardLaporan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_hari = itemView.findViewById(R.id.tv_hari);
            tv_tgl = itemView.findViewById(R.id.tv_tanggal);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_alasan = itemView.findViewById(R.id.tv_alasan);
            cardLaporan = itemView.findViewById(R.id.cardLaporan);
        }
    }
    public interface OnItemClickCallBack{
        void onItemClicked(DataSuratIzin dataSuratIzin);
    }
}
