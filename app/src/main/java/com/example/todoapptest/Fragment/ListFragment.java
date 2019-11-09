package com.example.todoapptest.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapptest.Adapter.ListViewAdapter;
import com.example.todoapptest.DBHelper.DBHelper;
import com.example.todoapptest.Item.ListViewItem;
import com.example.todoapptest.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements ListViewAdapter.RecyclerViewClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String GET_DATE = "date";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_list, container, false);

        // 날짜 가져오기
        Date getDate = new Date(System.currentTimeMillis());

        SimpleDateFormat yearForm = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthForm = new SimpleDateFormat("MM");
        SimpleDateFormat dayForm = new SimpleDateFormat("dd");
        SimpleDateFormat dayOfWeekForm = new SimpleDateFormat("E");

        String curYear = yearForm.format(getDate);
        String curMonth = monthForm.format(getDate);
        String curDay = dayForm.format(getDate);
        String curDoW = dayOfWeekForm.format(getDate);

        String dateFormat = curYear + "-" + curMonth + "-" + curDay;

        // get listViewItem realm DB
        //ArrayList<ListViewItem> listViewItems = DBHelper.getInstance().getList();
        ArrayList<ListViewItem> listViewItems = DBHelper.getInstance().getListForDate(dateFormat);
        //ArrayList<ListViewItem> listViewItems = DBHelper.getInstance().getListForDate("2019-11-03");

        // recyclerView 생성 및 adapter 지정.
        final RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.list_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);

        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ListViewAdapter adapter = new ListViewAdapter(getContext(), listViewItems);

        // recyclerView 어댑터 연결
        recyclerView.setAdapter(adapter) ;

        // adapter listener set
        adapter.setOnClickedListener(this);

        TextView yearTextView = (TextView) layout.findViewById(R.id.year_textView);
        TextView monthTextView = (TextView) layout.findViewById(R.id.month_textView);
        TextView dayTextView = (TextView) layout.findViewById(R.id.day_textView);
        TextView dayofWeekTextView = (TextView) layout.findViewById(R.id.day_of_week_textView);

        Log.d("GET DATE", "get date : " + curYear + " / " + curMonth + " / " + curDay + " / " + curDoW);

        yearTextView.setText(curYear+"년");
        monthTextView.setText(curMonth+"월");
        dayTextView.setText(curDay+"일");
        dayofWeekTextView.setText(curDoW+"요일");

        // 할 일 추가 버튼
        final EditText inputText = (EditText) layout.findViewById(R.id.input_editText);

        ImageButton addButton = (ImageButton) layout.findViewById(R.id.input_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inputText 에 들어있는 문자 listview 에 입력
                String contents = inputText.getText().toString();
                Log.d("INSERT TEXT", ""+contents);

                if(contents == "" && contents.equals("") && contents == null) {
                    Toast.makeText(getContext(), "할 일을 입력해주세요 !", Toast.LENGTH_SHORT).show();
                } else {
                    adapter.addItem(contents);

                    Date date = new Date(System.currentTimeMillis());
                    SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss", Locale.ENGLISH);
                    String getWriteDate = date_format.format(date);

                    // realm DB list insert
                    DBHelper.getInstance().insertList(contents, getWriteDate);
                    // 리스트뷰 갱신
                    adapter.notifyDataSetChanged();
                    // 갱신 후 editText 초기화
                    inputText.setText(null);

                    onHideKeyboard(getContext(), inputText);
                }
            }
        });


        layout.setOnClickListener(v -> {
            onHideKeyboard(getContext(), inputText);
        });

        return layout;
    }

    // 입력창에서 밖을 클릭했을 때 키보드 사라지게 만들기
    public void onHideKeyboard(Context context, EditText editText) {
        InputMethodManager mInputMethodManager = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    // recyclerView ClickListener
    @Override
    public void onFavoriteClicked() {
        //Toast.makeText(getContext(), "즐겨찾기", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked() {

    }

    @Override
    public void onItemLongClicked() {

    }

    @Override
    public void onStatusClicked() {
        //Toast.makeText(getContext(), "상태 표시", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
