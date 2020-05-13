package com.xiangxue.googleproject.status;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PersonalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PersonalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("个人中心");
    }

    public LiveData<String> getText() {
        return mText;
    }
}