package com.example.todoapptest.Model;

        import androidx.lifecycle.MutableLiveData;

public class CalLiveData<T> extends MutableLiveData<T> {

    public CalLiveData() {

    }

    public CalLiveData(T value) {
        setValue(value);
    }
}