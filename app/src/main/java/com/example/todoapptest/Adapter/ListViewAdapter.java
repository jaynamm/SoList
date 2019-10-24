package com.example.todoapptest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapptest.Item.ListViewItem;
import com.example.todoapptest.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> items = new ArrayList<ListViewItem>() {};

    public ListViewAdapter(){ }

    public ListViewAdapter(ArrayList<ListViewItem> listViewItems) {
        this.items = listViewItems;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_item, parent, false);
        }

        ImageView favoorite = (ImageView) convertView.findViewById(R.id.list_favorite_image);
        TextView contents = (TextView) convertView.findViewById(R.id.list_contents_text);
        Button status = (Button) convertView.findViewById(R.id.list_status_button);

        ListViewItem listViewItem = (ListViewItem) getItem(position);

        favoorite.setImageDrawable(listViewItem.getList_favorite());
        contents.setText(listViewItem.getList_contents());
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "position : " + position, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public void addItem(String contents) {
        ListViewItem mItem = new ListViewItem();

        // 아이템 setting
        //mItem.setList_favorite(favorite);
        mItem.setList_contents(contents);
        //mItem.setList_status("0");
        System.out.println("check");
        // mItems에 추가한다.
        items.add(mItem);
    }
}
