package com.example.absensi.ui.surat.tidak_hadir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.surat.SuratIzinResponse;
import com.example.absensi.network.repository.surat.GetDataSuratIzinRepository;

public class GetDataSuratIzinViewModel extends ViewModel {
    private GetDataSuratIzinRepository getDataSuratIzinRepository;

    public GetDataSuratIzinViewModel(){
        getDataSuratIzinRepository = new GetDataSuratIzinRepository();
    }

    public LiveData<SuratIzinResponse> getDataSuratIzin(String id_users){
        return getDataSuratIzinRepository.getDataSuratIzin(id_users);
    }

}
