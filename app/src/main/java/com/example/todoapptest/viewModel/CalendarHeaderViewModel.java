package com.example.todoapptest.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.todoapptest.Model.CalLiveData;

public class CalendarHeaderViewModel extends ViewModel {
    public CalLiveData<Long> mHeaderDate = new CalLiveData<>();

    public void setHeaderDate(long headerDate) {
        this.mHeaderDate.setValue(headerDate);
    }
}