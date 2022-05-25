package com.example.absensi.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsersLogin {

    @SerializedName("id_users")
    @Expose
    private String idUsers;
    @SerializedName("no_pegawai")
    @Expose
    private String noPegawai;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("no_telp")
    @Expose
    private String noTelp;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("is_verified")
    @Expose
    private String isVerified;
    @SerializedName("users_id")
    @Expose
    private String usersId;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("id_jabatan")
    @Expose
    private String idJabatan;
    @SerializedName("check_in")
    @Expose
    private String checkIn;
    @SerializedName("check_out")
    @Expose
    private String checkOut;
    @SerializedName("nama_jabatan")
    @Expose
    private String namaJabatan;
    @SerializedName("gaji")
    @Expose
    private String gaji;

    public String getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(String idUsers) {
        this.idUsers = idUsers;
    }

    public String getNoPegawai() {
        return noPegawai;
    }

    public void setNoPegawai(String noPegawai) {
        this.noPegawai = noPegawai;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(String idJabatan) {
        this.idJabatan = idJabatan;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public String getGaji() {
        return gaji;
    }

    public UsersLogin(String idUsers, String noPegawai, String nik, String namaLengkap, String email, String noTelp, String password, String deviceId, String role, String isVerified, String usersId, String jenisKelamin, String alamat, String foto, String tglLahir, String idJabatan, String checkIn, String checkOut, String namaJabatan, String gaji) {
        this.idUsers = idUsers;
        this.noPegawai = noPegawai;
        this.nik = nik;
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.noTelp = noTelp;
        this.password = password;
        this.deviceId = deviceId;
        this.role = role;
        this.isVerified = isVerified;
        this.usersId = usersId;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.foto = foto;
        this.tglLahir = tglLahir;
        this.idJabatan = idJabatan;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.namaJabatan = namaJabatan;
        this.gaji = gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }
}
