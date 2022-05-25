package com.example.absensi.model;

public class Users {
    private String id_user;
    private String email;
    private String password;
    private String role;
    private String phone;
    private String device_id;
    private String is_verified;
    private String full_name;
    private String jenis_kelamin;
    private String alamat;
    private String foto;
    private String gaji;
    private String id_jabatan;
    private String tgl_lahir;
    private String id_pegawai;
    private String nama_jabatan;
    private String check_in;
    private String check_out;
    private String nik;

    public Users(String id_user, String email, String password, String role, String phone, String device_id, String is_verified, String full_name, String jenis_kelamin, String alamat, String foto, String gaji, String id_jabatan, String tgl_lahir, String id_pegawai, String nama_jabatan, String check_in, String check_out, String nik) {
        this.id_user = id_user;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.device_id = device_id;
        this.is_verified = is_verified;
        this.full_name = full_name;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
        this.foto = foto;
        this.gaji = gaji;
        this.id_jabatan = id_jabatan;
        this.tgl_lahir = tgl_lahir;
        this.id_pegawai = id_pegawai;
        this.nama_jabatan = nama_jabatan;
        this.check_in = check_in;
        this.check_out = check_out;
        this.nik = nik;
    }

    public String getId_user() {
        return id_user;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public String getDevice_id() {
        return device_id;
    }

    public String getIs_verified() {
        return is_verified;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getFoto() {
        return foto;
    }

    public String getGaji() {
        return gaji;
    }

    public String getId_jabatan() {
        return id_jabatan;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public String getNama_jabatan() {
        return nama_jabatan;
    }

    public String getCheck_in() {
        return check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public String getNik() {
        return nik;
    }
}
