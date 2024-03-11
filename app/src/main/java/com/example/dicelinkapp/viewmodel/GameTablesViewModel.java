package com.example.dicelinkapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameTablesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GameTablesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gametables fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}