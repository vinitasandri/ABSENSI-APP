package com.example.absensi.ui.surat.tidak_hadir;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.absensi.R;
import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.Constanta;
import com.example.absensi.session.SystemDataLocal;
import com.example.absensi.utils.DialogClass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class TambahSuratTidakHadirActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView tv_title;
    EditText edtNama,edtTanggal,edtAlasan;
    ImageView imageBukti;
    private AddSuratIzinViewModel addSuratIzinViewModel;
    private SystemDataLocal systemDataLocal;
    private final int REQUEST_PICK_PHOTO = 2;
    private final int REQUES_WRITE_PERMISION = 786;
    private String mediaPath;
    private String postPath;
    AlertDialog.Builder builder;
    View myview;
    private android.app.AlertDialog alertDialog;
    Button btnImage,btnSubmit;
    Spinner sp_alasan;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_surat_tidak_hadir);
        toolbar = findViewById(R.id.toolbar);
        tv_title = findViewById(R.id.title);
        edtNama = findViewById(R.id.edt_namapegawai);
        edtAlasan = findViewById(R.id.edt_alasan);
        edtTanggal = findViewById(R.id.edt_tanggal);
        imageBukti = findViewById(R.id.imageBukti);
        sp_alasan = findViewById(R.id.sp_alasan);
        systemDataLocal = new SystemDataLocal(this);
        addSuratIzinViewModel = ViewModelProviders.of(this).get(AddSuratIzinViewModel.class);
        setSupportActionBar(toolbar);

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
        tv_title.setText("Form Surat Pernyataan");
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        edtTanggal.setText(formattedDate);
        edtNama.setText(systemDataLocal.getLoginData().getFull_name());
        btnImage = findViewById(R.id.buttonImage);
        btnSubmit = findViewById(R.id.btn_submit);
        btnImage.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        sp_alasan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pilihan = sp_alasan.getSelectedItem().toString();
                if(pilihan.equals("Izin Lainnya")){
                    edtAlasan.setVisibility(View.VISIBLE);
                    btnImage.setVisibility(View.GONE);
                }else if(pilihan.equals("Sakit")){
                    edtAlasan.setVisibility(View.GONE);
                    btnImage.setVisibility(View.VISIBLE);
                }else{
                    edtAlasan.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == this.RESULT_OK){
            if(requestCode == REQUEST_PICK_PHOTO){
                if(data != null){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = this.getContentResolver().query(selectedImage,filePathColumn,null,null,null);
                    if(cursor != null){
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        mediaPath = cursor.getString(columnIndex);
                        imageBukti.setImageURI(data.getData());
                        cursor.close();
                        postPath = mediaPath;
                    }
                }
            }
        }
    }

    private void requestPermision() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUES_WRITE_PERMISION);
        }else{
            saveImageUpload();
        }
    }

    private void saveImageUpload() {
        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();
        if(mediaPath == null){

            Toast.makeText(getApplicationContext(),"Pilih Gambar terlebih dahulu ...", Toast.LENGTH_LONG).show();
            alertDialog.dismiss();

        }else{
            final File imageFile = new File(mediaPath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"),imageFile);
            final MultipartBody.Part partImage = MultipartBody.Part.createFormData("image",imageFile.getName(),requestBody);

//            String alasan = edtAlasan.getText().toString();
            String alasan = sp_alasan.getSelectedItem().toString();
            if(alasan.equals("-- Pilih Alasan --")){
                alasan = "";
            }else if(alasan.equals("Sakit")){
                alasan = alasan;
            }else if(alasan.equals("Izin Lainnya")){
                alasan = "Izin - "+edtAlasan.getText().toString();
            }
            String id_users = systemDataLocal.getLoginData().getId_pegawai();
            final RequestBody reqAlasan = RequestBody.create(MediaType.parse("text/plain"),alasan);
            final RequestBody reqIdUsers = RequestBody.create(MediaType.parse("text/plain"),id_users);
            addSuratIzinViewModel.addSuratIzin(partImage,reqAlasan,reqIdUsers).observe(this, new Observer<MessageOnly>() {
                @Override
                public void onChanged(MessageOnly messageOnly) {
                    if(messageOnly.getStatus()){
                        alertDialog.dismiss();
                        Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }else{
                        alertDialog.dismiss();
                        Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            saveImageUpload();
        }
    }

    private void insertIzin() {
        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();
        String alasan = alasan = "Izin - "+edtAlasan.getText().toString();
        String id_users = systemDataLocal.getLoginData().getId_pegawai();
        addSuratIzinViewModel.addSuratIzinLainnya(id_users,alasan).observe(this, new Observer<MessageOnly>() {
            @Override
            public void onChanged(MessageOnly messageOnly) {
                if(messageOnly.getStatus()){
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                    onBackPressed();
                }else{
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonImage:
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,REQUEST_PICK_PHOTO);
                break;

            case R.id.btn_submit:
                String alasan = sp_alasan.getSelectedItem().toString();
                if(alasan.equals("-- Pilih Alasan --")){
                    alasan = "";
                    Toast.makeText(getApplicationContext(),"Pilih Gambar terlebih dahulu ...", Toast.LENGTH_LONG).show();
                    alertDialog.dismiss();
                }else if(alasan.equals("Sakit")){
                    requestPermision();
                }else if(alasan.equals("Izin Lainnya")){
                    insertIzin();
                }

                break;
        }
    }
}