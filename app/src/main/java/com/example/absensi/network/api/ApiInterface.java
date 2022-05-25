package com.example.absensi.network.api;


import com.example.absensi.model.MessageOnly;
import com.example.absensi.model.absensi.AbsensiResponse;
import com.example.absensi.model.absensi.DataPercentResponse;
import com.example.absensi.model.gaji.DataGajiResponse;
import com.example.absensi.model.gaji.TotalUangMakanResponse;
import com.example.absensi.model.laporan.DataLaporanResponse;
import com.example.absensi.model.login.ResponseLogin;
import com.example.absensi.model.profile.ResponseProfile;
import com.example.absensi.model.qr.QrInfoResponse;
import com.example.absensi.model.register.ResponseRegister;
import com.example.absensi.model.register.ResponseRegisterDevice;
import com.example.absensi.model.surat.CutiResponse;
import com.example.absensi.model.surat.SuratIzinResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("users/register")
    Call<ResponseRegister>  registerUser(@Field("full_name") String full_name,
                                         @Field("email") String email,
                                         @Field("phone") String phone,
                                         @Field("password") String password,
                                         @Field("imei") String imei,
                                         @Field("nik")String nik);

    @FormUrlEncoded
    @POST("users/login")
    Call<ResponseLogin> loginUser(@Field("no_pegawai") String no_pegawai,
                                  @Field("password") String password,
                                  @Field("device_id") String device_id);

    @FormUrlEncoded
    @POST("users/updateProfil")
    Call<ResponseProfile> updateProfile(@Field("id_pegawai") String id_pegawai,
                                        @Field("no_telp") String no_telp,
                                        @Field("date") String date,
                                        @Field("jk") String jk,
                                        @Field("alamat") String alamat);

    @Multipart
    @POST("profile/updateImageProfile")
    Call<MessageOnly> updateImageProfile(@Part MultipartBody.Part image,
                                         @Part("id_users")RequestBody id_users);

    @FormUrlEncoded
    @POST("absensi/addAbsensi")
    Call<MessageOnly> addAbsensi(@Field("no_pegawai") String id_pegawai,
                                 @Field("time") String time);

    @FormUrlEncoded
    @POST("absensi/getDataAbsensiByIdUsers")
    Call<AbsensiResponse> getAbsensiHome(@Field("id_users") String id_users);

    @FormUrlEncoded
    @POST("absensi/getLaporanKehadiran")
    Call<DataLaporanResponse> getDataLaporan(@Field("id_users") String id_users);

    @FormUrlEncoded
    @POST("absensi/getPercentAbsensi")
    Call<DataPercentResponse> getDataPercent(@Field("no_pegawai") String no_pegawai);

    @FormUrlEncoded
    @POST("absensi/getTotalUangMakan")
    Call<TotalUangMakanResponse> getTotalUangMakan(@Field("no_pegawai")String no_pegawai);

    @FormUrlEncoded
    @POST("absensi/getGajiUangMakan")
    Call<DataGajiResponse> getDataGaji(@Field("no_pegawai")String no_pegawai);

    @Multipart
    @POST("surat/addSuratIzin")
    Call<MessageOnly> addSuratIzin(@Part MultipartBody.Part image,
                                   @Part("alasan")RequestBody alasan,
                                   @Part("id_users")RequestBody id_users);

    @FormUrlEncoded
    @POST("surat/addSuratIzinLainnya")
    Call<MessageOnly> addSuratIzinLainnya(@Field("id_users")String id_users,
                                          @Field("alasan")String alasan);

    @FormUrlEncoded
    @POST("surat/getDataSuratIzin")
    Call<SuratIzinResponse> getDataSuratIzin(@Field("id_users") String id_users);

    @FormUrlEncoded
    @POST("users/changePassword")
    Call<MessageOnly> changePassword(@Field("id_users")String id_users,
                                     @Field("old_password")String old_password,
                                     @Field("new_password")String new_password,
                                     @Field("confirm_password")String confirm_password);

    @FormUrlEncoded
    @POST("users/changeEmail")
    Call<MessageOnly> changeEmail(@Field("id_users")String id_users,
                                  @Field("old_email")String old_email,
                                  @Field("new_email")String new_email,
                                  @Field("confirm_email")String confirm_email);

    @FormUrlEncoded
    @POST("surat/addSuratCuti")
    Call<MessageOnly> addSuratCuti(@Field("keterangan")String keterangan,
                                   @Field("start_date")String startDate,
                                   @Field("end_date")String endDate,
                                   @Field("id_users")String idUsers);

    @FormUrlEncoded
    @POST("surat/getDataCuti")
    Call<CutiResponse> getDataCuti(@Field("id_users")String idUsers);

    @FormUrlEncoded
    @POST("surat/cancelCuti")
    Call<MessageOnly> cancelCuti(@Field("id_cuti")String id_cuti);

    @FormUrlEncoded
    @POST("users/changeDevice")
    Call<MessageOnly> changeDevice(@Field("noPegawai")String noPegawai,
                                   @Field("merk")String merk,
                                   @Field("password")String password,
                                   @Field("confirmPassword")String confirmPassword,
                                   @Field("deviceId")String deviceId);

    @FormUrlEncoded
    @POST("users/getDataPegawai")
    Call<ResponseRegisterDevice> registerDevice(@Field("no_pegawai")String no_pegawai);

    @FormUrlEncoded
    @POST("users/registerDevice")
    Call<MessageOnly> registerPerangkat(@Field("no_pegawai")String no_pegawai,
                                        @Field("device_id")String device_id,
                                        @Field("password")String password);

    @FormUrlEncoded
    @POST("qr/getInfoStart")
    Call<QrInfoResponse> getInfoQr(@Field("no_pegawai")String no_pegawai);
}
