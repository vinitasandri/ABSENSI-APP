package com.example.absensi.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.repository.profile.ChangePictureRepository;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ChangePictureViewModel extends ViewModel {
    private ChangePictureRepository changePictureRepository;

    public ChangePictureViewModel(){
        changePictureRepository = new ChangePictureRepository();
    }

    public LiveData<MessageOnly> updateProfileImage(MultipartBody.Part image, RequestBody id_users){
        return changePictureRepository.UpdateProfileImage(image,id_users);
    }
}
