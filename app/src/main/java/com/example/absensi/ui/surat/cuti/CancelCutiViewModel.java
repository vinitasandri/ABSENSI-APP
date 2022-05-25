package com.example.absensi.ui.surat.cuti;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.repository.surat.CancelCutiRepository;

public class CancelCutiViewModel extends ViewModel {
    private CancelCutiRepository cancelCutiRepository;

    public CancelCutiViewModel(){
        cancelCutiRepository = new CancelCutiRepository();
    }

    public LiveData<MessageOnly> cancelCuti(String id_cuti){
       return cancelCutiRepository.cancelCuti(id_cuti);
    }
}
