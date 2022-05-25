package com.example.absensi.ui.scanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.repository.absensi.AddAbsensiRepository;

public class AddAbsensiViewModel extends ViewModel {
    private AddAbsensiRepository addAbsensiRepository;

    public AddAbsensiViewModel(){
        addAbsensiRepository = new AddAbsensiRepository();
    }

    public LiveData<MessageOnly> addAbsensi(String id_pegawai,String time){
        return  addAbsensiRepository.addAbsensi(id_pegawai,time);
    }
}
