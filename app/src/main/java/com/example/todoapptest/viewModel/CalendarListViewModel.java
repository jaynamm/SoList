package com.example.todoapptest.viewModel;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.lifecycle.ViewModel;

import com.example.todoapptest.Model.CalLiveData;
import com.example.todoapptest.util.DateFormat;
import com.example.todoapptest.util.Keys;

public class CalendarListViewModel extends ViewModel {
    private long mCurrentTime;
    private String TAG = "CalendarListViewModel";
    public CalLiveData<String> mTitle = new CalLiveData<>();
    public CalLiveData<ArrayList<Object>> mCalendarList = new CalLiveData<>(new ArrayList<>());

    public int mCenterPosition;

    public void setTitle(int position) {
        try {
            Object item = mCalendarList.getValue().get(position);
            if (item instanceof Long) {
                setTitle((Long) item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTitle(long time) {
        mCurrentTime = time;
        mTitle.setValue(DateFormat.getDate(time, DateFormat.CALENDAR_HEADER_FORMAT));
    }
    
    public void initCalendarList() {
        Log.d(TAG, "initCalendarList() START");

        GregorianCalendar cal = new GregorianCalendar(); // 현재 날짜 가져오기
        setCalendarList(cal);

        Log.d(TAG, "initCalendarList() END");
    }

    public void setCalendarList(GregorianCalendar cal) {
        Log.d(TAG, "setCalendarList START");

        setTitle(cal.getTimeInMillis());

        ArrayList<Object> calendarList = new ArrayList<>();

        for (int i = -100; i < 100; i++) {
            try {
                // 현재 날짜를 가져온다. 연도, 월
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0);
                if (i == 0) {
                    mCenterPosition = calendarList.size();
                    Log.d("size", ""+calendarList.size());
                }

                //Log.d("get time in millis", ""+ calendar.getTimeInMillis());

                calendarList.add(calendar.getTimeInMillis());

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; //해당 월에 시작하는 요일 -1 을 하면 빈칸을 구할 수 있다.
                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월에 마지막 요일

                for (int j = 0; j < dayOfWeek; j++) {
                    calendarList.add(Keys.EMPTY); // 비어있는 일자 타입
                }
                for (int j = 1; j <= max; j++) {
                    calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j)); // 일자 타입
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mCalendarList.setValue(calendarList);

        Log.d(TAG, "setCalendarList END");
    }

}