package com.example.absensi.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.absensi.model.MessageOnly;
import com.example.absensi.network.repository.profile.ChangeEmailRepository;

public class ChangeEmailViewModel extends ViewModel {
    private ChangeEmailRepository changeEmailRepository;

    public ChangeEmailViewModel(){
        changeEmailRepository = new ChangeEmailRepository();
    }

    public LiveData<MessageOnly> changeEmail(String id_users,String old_email,String new_email,String confirm_email){
        return  changeEmailRepository.changeEmail(id_users,old_email,new_email,confirm_email);
    }
}
