package com.example.absensi.ui.surat.tidak_hadir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.repository.surat.AddSuratIzinRepsitory;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddSuratIzinViewModel extends ViewModel {
    private AddSuratIzinRepsitory addSuratIzinRepsitory;

    public  AddSuratIzinViewModel(){
        addSuratIzinRepsitory = new AddSuratIzinRepsitory();
    }

    public LiveData<MessageOnly> addSuratIzin(MultipartBody.Part image, RequestBody alasan,RequestBody id_users){
        return addSuratIzinRepsitory.addSuratIzin(image,alasan,id_users);
    }

    public LiveData<MessageOnly> addSuratIzinLainnya(String idUsers,String alasan){
        return addSuratIzinRepsitory.addSuratIzinLainnya(idUsers,alasan);
    }
}
