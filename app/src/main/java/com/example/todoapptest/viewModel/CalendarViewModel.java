package com.example.todoapptest.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.todoapptest.Model.CalLiveData;

import java.util.Calendar;

public class CalendarViewModel extends ViewModel {
    public CalLiveData<Calendar> mCalendar = new CalLiveData<>();

    public void setCalendar(Calendar calendar) {
        this.mCalendar.setValue(calendar);
    }


}