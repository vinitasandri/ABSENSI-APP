package com.example.absensi.ui.surat.cuti;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.repository.surat.AddSuratCutiRepository;
import com.example.absensi.ui.surat.tidak_hadir.AddSuratIzinViewModel;

public class AddSuratCutiViewModel extends ViewModel {
    private AddSuratCutiRepository addSuratCutiRepository;

    public AddSuratCutiViewModel(){
        addSuratCutiRepository = new AddSuratCutiRepository();
    }

    public LiveData<MessageOnly> addSuratCuti(String keterangan,String startDate,String endDate,String idUsers){
        return addSuratCutiRepository.addSuratCuti(keterangan,startDate,endDate,idUsers);
    }
}
