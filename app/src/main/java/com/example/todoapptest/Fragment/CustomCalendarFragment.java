package com.example.todoapptest.Fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapptest.DBHelper.DBHelper;
import com.example.todoapptest.EventDecorator;
import com.example.todoapptest.Item.ListViewItem;
import com.example.todoapptest.OneDayDecorator;
import com.example.todoapptest.R;
import com.example.todoapptest.SaturdayDecorator;
import com.example.todoapptest.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CustomCalendarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CustomCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomCalendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CustomCalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomCalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomCalendarFragment newInstance(String param1, String param2) {
        CustomCalendarFragment fragment = new CustomCalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    MaterialCalendarView materialCalendarView;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_custom_calendar, container, false);

        materialCalendarView = layout.findViewById(R.id.calendarView);
        TextView listTextView = (TextView) layout.findViewById(R.id.listTextView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(1999, 0, 1))
                .setMaximumDate(CalendarDay.from(2999, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        materialCalendarView.setOnDateChangedListener((materialCalendarView1, date, b) -> {
            int Year = date.getYear();
            int Month = date.getMonth() + 1;
            int Day = date.getDay();

            Log.i("Year test", Year + "");
            Log.i("Month test", Month + "");
            Log.i("Day test", Day + "");

            String shot_Day = Year + "-" + Month + "-" + Day;

            Log.i("shot_Day test", shot_Day + "");

            // 선택 초기화
            //materialCalendarView.clearSelection();

            Toast.makeText(getContext(), shot_Day , Toast.LENGTH_SHORT).show();

            ArrayList<ListViewItem> allData = DBHelper.getInstance().getListForDate(shot_Day);

            listTextView.setText(null);
            String col = String.format("%-5s %-20s %-30s \n", "ID", "CONTENTS", "DATE");
            listTextView.append(col);
            for(int i=0; i<allData.size(); i++) {
                String data = String.format("%-5d %-20s %-30s \n",
                        allData.get(i).getId(),
                        allData.get(i).getContents(),
                        allData.get(i).getWriteDate());
                listTextView.append(data);
            }
        });
       /* 데코 추가하기 */
       materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
               oneDayDecorator);


        String[] result = {"2017,03,18","2017,04,18","2017,05,18","2017,06,18"};

        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());



        return layout;
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -2);
            ArrayList<CalendarDay> dates = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                CalendarDay day = CalendarDay.from(calendar);
                dates.add(day);
                calendar.add(Calendar.DATE, 5);
            }

            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isDetached()) {
                return;
            }

            materialCalendarView.addDecorator(new EventDecorator(Color.BLACK, calendarDays, getContext()));
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            activity = (Activity) context;
        }
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
