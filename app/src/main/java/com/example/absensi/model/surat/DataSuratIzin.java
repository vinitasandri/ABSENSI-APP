package com.example.absensi.model.surat;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSuratIzin implements Parcelable {
    @SerializedName("id_surat")
    @Expose
    private String idSurat;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("alasan")
    @Expose
    private String alasan;
    @SerializedName("bukti")
    @Expose
    private String bukti;
    @SerializedName("id_users")
    @Expose
    private String idUsers;
    @SerializedName("status_surat")
    @Expose
    private String statusSurat;
    @SerializedName("id_absensi")
    @Expose
    private String idAbsensi;
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
    @SerializedName("check_in")
    @Expose
    private String checkIn;
    @SerializedName("check_out")
    @Expose
    private String checkOut;
    @SerializedName("late")
    @Expose
    private String late;
    @SerializedName("work_time")
    @Expose
    private String workTime;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("is_late")
    @Expose
    private String isLate;
    @SerializedName("status")
    @Expose
    private String status;

    public String getIdSurat() {
        return idSurat;
    }

    public void setIdSurat(String idSurat) {
        this.idSurat = idSurat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getBukti() {
        return bukti;
    }

    public void setBukti(String bukti) {
        this.bukti = bukti;
    }

    public String getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(String idUsers) {
        this.idUsers = idUsers;
    }

    public String getStatusSurat() {
        return statusSurat;
    }

    public void setStatusSurat(String statusSurat) {
        this.statusSurat = statusSurat;
    }

    public String getIdAbsensi() {
        return idAbsensi;
    }

    public void setIdAbsensi(String idAbsensi) {
        this.idAbsensi = idAbsensi;
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

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsLate() {
        return isLate;
    }

    public void setIsLate(String isLate) {
        this.isLate = isLate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idSurat);
        dest.writeString(this.tanggal);
        dest.writeString(this.alasan);
        dest.writeString(this.bukti);
        dest.writeString(this.idUsers);
        dest.writeString(this.statusSurat);
        dest.writeString(this.idAbsensi);
        dest.writeString(this.noPegawai);
        dest.writeString(this.nik);
        dest.writeString(this.namaLengkap);
        dest.writeString(this.email);
        dest.writeString(this.noTelp);
        dest.writeString(this.password);
        dest.writeString(this.deviceId);
        dest.writeString(this.role);
        dest.writeString(this.isVerified);
        dest.writeString(this.checkIn);
        dest.writeString(this.checkOut);
        dest.writeString(this.late);
        dest.writeString(this.workTime);
        dest.writeString(this.date);
        dest.writeString(this.isLate);
        dest.writeString(this.status);
    }

    public DataSuratIzin() {
    }

    protected DataSuratIzin(Parcel in) {
        this.idSurat = in.readString();
        this.tanggal = in.readString();
        this.alasan = in.readString();
        this.bukti = in.readString();
        this.idUsers = in.readString();
        this.statusSurat = in.readString();
        this.idAbsensi = in.readString();
        this.noPegawai = in.readString();
        this.nik = in.readString();
        this.namaLengkap = in.readString();
        this.email = in.readString();
        this.noTelp = in.readString();
        this.password = in.readString();
        this.deviceId = in.readString();
        this.role = in.readString();
        this.isVerified = in.readString();
        this.checkIn = in.readString();
        this.checkOut = in.readString();
        this.late = in.readString();
        this.workTime = in.readString();
        this.date = in.readString();
        this.isLate = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<DataSuratIzin> CREATOR = new Parcelable.Creator<DataSuratIzin>() {
        @Override
        public DataSuratIzin createFromParcel(Parcel source) {
            return new DataSuratIzin(source);
        }

        @Override
        public DataSuratIzin[] newArray(int size) {
            return new DataSuratIzin[size];
        }
    };
}
