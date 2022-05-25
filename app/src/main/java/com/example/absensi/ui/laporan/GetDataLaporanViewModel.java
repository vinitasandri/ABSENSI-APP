package com.example.absensi.ui.laporan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.laporan.DataLaporan;
import com.example.absensi.model.laporan.DataLaporanResponse;
import com.example.absensi.network.repository.laporan.GetDataLaporanRepository;
import com.example.absensi.ui.home.GetAbsensiHomeViewModel;

public class GetDataLaporanViewModel extends ViewModel {
    private GetDataLaporanRepository getDataLaporanRepository;

    public GetDataLaporanViewModel(){
        getDataLaporanRepository = new GetDataLaporanRepository();
    }

    public LiveData<DataLaporanResponse> getDataLaporan(String id_users){
       return getDataLaporanRepository.getDataLaporan(id_users);
    }
}
