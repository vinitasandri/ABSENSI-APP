package com.example.absensi.model.surat;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCuti implements Parcelable {
    @SerializedName("id_cuti")
    @Expose
    private String idCuti;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("dari_tanggal")
    @Expose
    private String dariTanggal;
    @SerializedName("sampai_tanggal")
    @Expose
    private String sampaiTanggal;
    @SerializedName("jumlah_hari")
    @Expose
    private String jumlahHari;
    @SerializedName("status")
    @Expose
    private String status;
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

    public String getIdCuti() {
        return idCuti;
    }

    public void setIdCuti(String idCuti) {
        this.idCuti = idCuti;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getDariTanggal() {
        return dariTanggal;
    }

    public void setDariTanggal(String dariTanggal) {
        this.dariTanggal = dariTanggal;
    }

    public String getSampaiTanggal() {
        return sampaiTanggal;
    }

    public void setSampaiTanggal(String sampaiTanggal) {
        this.sampaiTanggal = sampaiTanggal;
    }

    public String getJumlahHari() {
        return jumlahHari;
    }

    public void setJumlahHari(String jumlahHari) {
        this.jumlahHari = jumlahHari;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idCuti);
        dest.writeString(this.keterangan);
        dest.writeString(this.dariTanggal);
        dest.writeString(this.sampaiTanggal);
        dest.writeString(this.jumlahHari);
        dest.writeString(this.status);
        dest.writeString(this.idUsers);
        dest.writeString(this.noPegawai);
        dest.writeString(this.nik);
        dest.writeString(this.namaLengkap);
        dest.writeString(this.email);
        dest.writeString(this.noTelp);
        dest.writeString(this.password);
        dest.writeString(this.deviceId);
        dest.writeString(this.role);
        dest.writeString(this.isVerified);
    }

    public DataCuti() {
    }

    protected DataCuti(Parcel in) {
        this.idCuti = in.readString();
        this.keterangan = in.readString();
        this.dariTanggal = in.readString();
        this.sampaiTanggal = in.readString();
        this.jumlahHari = in.readString();
        this.status = in.readString();
        this.idUsers = in.readString();
        this.noPegawai = in.readString();
        this.nik = in.readString();
        this.namaLengkap = in.readString();
        this.email = in.readString();
        this.noTelp = in.readString();
        this.password = in.readString();
        this.deviceId = in.readString();
        this.role = in.readString();
        this.isVerified = in.readString();
    }

    public static final Parcelable.Creator<DataCuti> CREATOR = new Parcelable.Creator<DataCuti>() {
        @Override
        public DataCuti createFromParcel(Parcel source) {
            return new DataCuti(source);
        }

        @Override
        public DataCuti[] newArray(int size) {
            return new DataCuti[size];
        }
    };
}
