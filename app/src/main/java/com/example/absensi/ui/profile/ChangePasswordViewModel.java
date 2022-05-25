package com.example.absensi.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.repository.profile.ChangePasswordRepository;

public class ChangePasswordViewModel extends ViewModel {
    private ChangePasswordRepository changePasswordRepository;

    public ChangePasswordViewModel(){
        changePasswordRepository = new ChangePasswordRepository();
    }

    public LiveData<MessageOnly> changePassword(String id_user,String old_password,String new_password,String confirm_password){
        return changePasswordRepository.changePassword(id_user,old_password,new_password,confirm_password);
    }
}
