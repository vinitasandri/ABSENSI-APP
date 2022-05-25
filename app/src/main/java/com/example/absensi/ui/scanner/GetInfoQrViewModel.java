package com.example.absensi.ui.scanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.qr.QrInfoResponse;
import com.example.absensi.network.repository.GetInfoQrRepository;

public class GetInfoQrViewModel extends ViewModel {
    private GetInfoQrRepository getInfoQrRepository;

    public GetInfoQrViewModel(){
        getInfoQrRepository = new GetInfoQrRepository();
    }

    public LiveData<QrInfoResponse> getInfoQr(String no_pegawai){
        return getInfoQrRepository.getInfoQr(no_pegawai);
    }
}
