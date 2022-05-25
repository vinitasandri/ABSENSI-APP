package com.example.absensi.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.absensi.R;
import com.example.absensi.model.MessageOnly;
import com.example.absensi.model.Users;
import com.example.absensi.network.Constanta;
import com.example.absensi.session.SystemDataLocal;
import com.example.absensi.ui.home.HomeActivity;
import com.example.absensi.ui.profile.ChangePictureViewModel;
import com.example.absensi.ui.profile.activity.UpdateProfileActivity;
import com.example.absensi.utils.DialogClass;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class ProfileFragment extends Fragment implements View.OnClickListener {



    private SystemDataLocal systemDataLocal;
    private Context context;
    TextView tv_name,tv_email,tv_phone,tv_date,tv_jk,tv_addres,tv_id,tv_jabatan;
    Button btn_update;
    String id_pegawai,no_telp,tgl_lahir,jk,alamat,id_users;
    Users users;
    String date2;
    private final int REQUEST_PICK_PHOTO = 2;
    private final int REQUES_WRITE_PERMISION = 786;
    private String mediaPath;
    private String postPath;
    ImageView profile_image,imageGallery;
    AlertDialog.Builder builder;
    View myview;
    private android.app.AlertDialog alertDialog;
    private ChangePictureViewModel changePictureViewModel;
    HomeActivity activity = (HomeActivity) getContext();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        systemDataLocal = new SystemDataLocal(context);
        tv_name = view.findViewById(R.id.tv_name);
        tv_date = view.findViewById(R.id.tv_date);
        tv_email = view.findViewById(R.id.tv_email);
        tv_phone = view.findViewById(R.id.tv_phone);
        tv_jk = view.findViewById(R.id.tv_jk);
        tv_id = view.findViewById(R.id.tv_id);
        tv_jabatan = view.findViewById(R.id.tv_jabatan);
        tv_addres = view.findViewById(R.id.tv_alamat);
        btn_update = view.findViewById(R.id.button_update);
        profile_image = view.findViewById(R.id.profile_image);
        users = systemDataLocal.getLoginData();

        String date = users.getTgl_lahir();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MMMM-yyyy");
        try {
            date2 = format2.format(Objects.requireNonNull(format1.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv_date.setText(date2);
        tv_name.setText(users.getFull_name());
        tv_email.setText(users.getEmail());
        tv_phone.setText(users.getPhone());
        tv_jabatan.setText(users.getNama_jabatan());

        tv_jk.setText(users.getJenis_kelamin());
        tv_id.setText(users.getId_pegawai());

        id_pegawai = users.getId_pegawai();
        no_telp = users.getPhone();
        tgl_lahir = users.getTgl_lahir();
        jk = users.getJenis_kelamin();
        alamat = users.getAlamat();

        if(users.getAlamat().equals("")){
            String alamat = "-";
            tv_addres.setText(alamat);
        }else{
            tv_addres.setText(users.getAlamat());
        }

        if(users.getJenis_kelamin().equals("")){
            String jk = "-";
            tv_jk.setText(jk);
        }
        btn_update.setOnClickListener(this);
        profile_image.setOnClickListener(this);
        changePictureViewModel = ViewModelProviders.of(this).get(ChangePictureViewModel.class);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_update:
                Intent move = new Intent(getActivity(),UpdateProfileActivity.class);
                context.startActivity(move);
                break;

            case R.id.profile_image:
                displayDialog();
                break;
        }
    }
    private void displayDialog(){
        builder = new AlertDialog.Builder(getContext());
        myview = getLayoutInflater().inflate(R.layout.dialog_upload,null,false);
        builder.setView(myview);
        Button btn_gallery = myview.findViewById(R.id.btn_gallery);
        imageGallery = myview.findViewById(R.id.imageGallery);
        Button btn_submit_file = myview.findViewById(R.id.btn_submit_file);
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,REQUEST_PICK_PHOTO);
            }
        });
        btn_submit_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermision();
            }
        });
        builder.show();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == activity.RESULT_OK){
            if(requestCode == REQUEST_PICK_PHOTO){
                if(data != null){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContext().getContentResolver().query(selectedImage,filePathColumn,null,null,null);
                    if(cursor != null){
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        mediaPath = cursor.getString(columnIndex);
                        imageGallery.setImageURI(data.getData());
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
        alertDialog = DialogClass.dialog(getContext(),v).create();
        alertDialog.show();
        if(mediaPath == null){
            System.out.println(id_pegawai);
            Toast.makeText(getContext(),"Pilih Gambar terlebih dahulu ...", Toast.LENGTH_LONG).show();
            alertDialog.dismiss();
        }else{
            final File imageFile = new File(mediaPath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"),imageFile);
            final MultipartBody.Part partImage = MultipartBody.Part.createFormData("image",imageFile.getName(),requestBody);
            changePictureViewModel.updateProfileImage(partImage,RequestBody.create(MediaType.parse("text/plain"),id_pegawai)).observe(this, new Observer<MessageOnly>() {
                @Override
                public void onChanged(MessageOnly messageOnly) {
                    if(messageOnly.getStatus()){
                        systemDataLocal.edtAllSessionLogin(systemDataLocal.getLoginData().getEmail(),
                                                            systemDataLocal.getLoginData().getFull_name(),
                                                            systemDataLocal.getLoginData().getPhone(),
                                                            systemDataLocal.getLoginData().getDevice_id(),
                                                            systemDataLocal.getLoginData().getId_user(),
                                                            systemDataLocal.getLoginData().getIs_verified(),
                                                            systemDataLocal.getLoginData().getRole(),
                                                            systemDataLocal.getLoginData().getPassword(),
                                                            systemDataLocal.getLoginData().getAlamat(),
                                                            imageFile.getName(),
                                                            systemDataLocal.getLoginData().getJenis_kelamin(),
                                                            systemDataLocal.getLoginData().getId_pegawai(),
                                                            systemDataLocal.getLoginData().getId_jabatan(),
                                                            systemDataLocal.getLoginData().getNama_jabatan(),
                                                            systemDataLocal.getLoginData().getGaji(),
                                                            systemDataLocal.getLoginData().getTgl_lahir(),
                                                            systemDataLocal.getLoginData().getNik(),
                                                            systemDataLocal.getLoginData().getCheck_in(),
                                                            systemDataLocal.getLoginData().getCheck_out());
                        Toast.makeText(getContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                        Glide.with(getContext()).load(Constanta.BASE_URL_IMG_PROFILE + systemDataLocal.getLoginData().getFoto()).into(profile_image);
                    }else{
                        Toast.makeText(getContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
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

    @Override
    public void onStart() {
        super.onStart();
    }

    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        systemDataLocal = new SystemDataLocal(context);
        users = systemDataLocal.getLoginData();
        String date = users.getTgl_lahir();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat("dd-MMMM-yyyy");
        try {
            date2 = format2.format(Objects.requireNonNull(format1.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv_name.setText(users.getFull_name());
        tv_email.setText(users.getEmail());
        tv_phone.setText(users.getPhone());
        tv_date.setText(date2);
        tv_jk.setText(users.getJenis_kelamin());
        tv_id.setText(users.getId_pegawai());
        tv_addres.setText(users.getAlamat());

        if(!systemDataLocal.getLoginData().getFoto().equals("")){
            Glide.with(getContext()).load(Constanta.BASE_URL_IMG_PROFILE + systemDataLocal.getLoginData().getFoto()).into(profile_image);
        }
    }
}