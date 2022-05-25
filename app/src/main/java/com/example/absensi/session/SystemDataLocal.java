package com.example.absensi.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.absensi.model.Users;
import com.example.absensi.model.login.UsersLogin;

public class SystemDataLocal {

    private SharedPreferences sharedPreferences;
    private Context context;
    private static final String KEY_USER = "User";
    private static final String KEY_ADDR = "address";
    private UsersLogin usersLogin;

    public SystemDataLocal(Context context,UsersLogin usersLogin){
        this.context = context;
        this.usersLogin = usersLogin;
    }

    public SystemDataLocal(Context context){
        this.context = context;
    }

    public void setSessionLogin(){
        sharedPreferences = context.getSharedPreferences(KEY_USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",usersLogin.getEmail());
        editor.putString("full_name",usersLogin.getNamaLengkap());
        editor.putString("phone",usersLogin.getNoTelp());
        editor.putString("device_id",usersLogin.getDeviceId());
        editor.putString("id_users",usersLogin.getIdUsers());
        editor.putString("is_verified",usersLogin.getIsVerified());
        editor.putString("role",usersLogin.getRole());
        editor.putString("password",usersLogin.getPassword());
        editor.putString("alamat",usersLogin.getAlamat());
        editor.putString("foto",usersLogin.getFoto());
        editor.putString("jenis_kelamin",usersLogin.getJenisKelamin());
        editor.putString("id_pegawai",usersLogin.getNoPegawai());
        editor.putString("id_jabatan",usersLogin.getIdJabatan());
        editor.putString("nama_jabatan",usersLogin.getNamaJabatan());
        editor.putString("gaji",usersLogin.getGaji());
        editor.putString("tgl_lahir",usersLogin.getTglLahir());
        editor.putString("nik",usersLogin.getNik());
        editor.putString("check_in",usersLogin.getCheckIn());
        editor.putString("check_out",usersLogin.getCheckOut());
        editor.putBoolean("login",true);
        editor.apply();
    }

    public void edtAllSessionLogin(String email,String full_name,String phone,String device_id,String id_users,String is_verified,String role,String password,String alamat,String foto,String jenis_kelamin,String no_pegawai,String id_jabatan,String nama_jabatan,String gaji,String tgl_lahir,String nik,String check_in,String check_out){
        sharedPreferences = context.getSharedPreferences(KEY_USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("full_name",full_name);
        editor.putString("phone",phone);
        editor.putString("device_id",device_id);
        editor.putString("id_users",id_users);
        editor.putString("is_verified",is_verified);
        editor.putString("role",role);
        editor.putString("password",password);
        editor.putString("alamat",alamat);
        editor.putString("foto",foto);
        editor.putString("tgl_lahir",tgl_lahir);
        editor.putString("jenis_kelamin",jenis_kelamin);
        editor.putString("id_pegawai",no_pegawai);
        editor.putString("nama_jabatan",nama_jabatan);
        editor.putString("id_jabatan",id_jabatan);
        editor.putString("nik",nik);
        editor.putString("gaji",gaji);
        editor.putString("check_in",check_in);
        editor.putString("check_out",check_out);
        editor.putBoolean("login",true);
        editor.apply();

    }

    public Users getLoginData(){
        sharedPreferences = context.getSharedPreferences(KEY_USER,Context.MODE_PRIVATE);
        String id_users = sharedPreferences.getString("username","");
        String full_name = sharedPreferences.getString("full_name","");
        String phone = sharedPreferences.getString("phone","");
        String role = sharedPreferences.getString("role","");
        String is_verified = sharedPreferences.getString("is_verified","");
        String email = sharedPreferences.getString("email","");
        String password = sharedPreferences.getString("password","");
        String device_id = sharedPreferences.getString("device_id","");
        String alamat = sharedPreferences.getString("alamat","");
        String foto = sharedPreferences.getString("foto","");
        String tgl_lahir = sharedPreferences.getString("tgl_lahir","");
        String jenis_kelamin = sharedPreferences.getString("jenis_kelamin","");
        String id_pegawai = sharedPreferences.getString("id_pegawai","");
        String nama_jabatan = sharedPreferences.getString("nama_jabatan","");
        String gaji = sharedPreferences.getString("gaji","");
        String id_jabatan = sharedPreferences.getString("id_jabatan","");
        String check_in = sharedPreferences.getString("check_in","");
        String check_out = sharedPreferences.getString("check_out","");
        String nik = sharedPreferences.getString("nik","");
        return new Users(id_users,email,password,role,phone,device_id,is_verified,full_name,jenis_kelamin,alamat,foto,gaji,id_jabatan,tgl_lahir,id_pegawai,nama_jabatan,check_in,check_out,nik);
    }

    public boolean getCheckLogin(){
        sharedPreferences = context.getSharedPreferences(KEY_USER,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login",false);
    }

    public void destroySessionLogin(){
        sharedPreferences = context.getSharedPreferences(KEY_USER,Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }
}
