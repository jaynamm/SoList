package com.example.todoapptest.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.todoapptest.Adapter.ListViewAdapter;
import com.example.todoapptest.Item.ListViewItem;
import com.example.todoapptest.R;

import java.util.ArrayList;

public class EditCustomDialog {

    private Context context;

    public EditCustomDialog(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction(final ArrayList<ListViewItem> mListData, final int position, final ListViewItem item) {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dialog = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dialog.setContentView(R.layout.edit_dialog);

        // 커스텀 다이얼로그를 노출한다.
        dialog.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final EditText contents = (EditText) dialog.findViewById(R.id.edit_contents);
        final Button changeButton = (Button) dialog.findViewById(R.id.edit_change_button);
        final Button cancelButton = (Button) dialog.findViewById(R.id.edit_cancel_button);

        contents.setText(item.getList_contents());

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setList_contents(contents.getText().toString());
                mListData.set(position, item);
                // 커스텀 다이얼로그를 종료한다.
                dialog.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 종료한다.
                dialog.dismiss();
            }
        });
    }

}
