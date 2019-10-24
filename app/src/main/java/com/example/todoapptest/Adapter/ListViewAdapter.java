package com.example.todoapptest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapptest.Item.ListViewItem;
import com.example.todoapptest.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {
    private final ArrayList<ListViewItem> mDatalist;

    // listener interface 생성
    public interface RecyclerViewClickListener {
        void onFavoriteClicked();
        void onItemClicked();
        void onItemLongClicked();
        void onStatusClicked();
    }

    private RecyclerViewClickListener mListener;

    public void setOnClickedListener(RecyclerViewClickListener listener){
        mListener = listener;
    }

    public ListViewAdapter(ArrayList<ListViewItem> listitems){
        mDatalist = listitems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListViewItem items = mDatalist.get(position);
        //holder.favorite.setImageDrawable(items.getList_favorite());

        holder.contents.setText(items.getList_contents());
        //holder.status.setText(items.getList_status());

        if (mListener != null) {
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked();
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    mListener.onItemLongClicked();
                    return true;
                }
            });
            holder.status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onStatusClicked();
                }
            });
            holder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFavoriteClicked();
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    public void addItem(String contents) {
        ListViewItem mItem = new ListViewItem();
        // 아이템 setting

        //mItem.setList_id();
        mItem.setList_contents(contents);
        // mItems에 추가한다.
        mDatalist.add(mItem);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView favorite;
        TextView contents;
        Button status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favorite = itemView.findViewById(R.id.list_favorite_image);
            contents = itemView.findViewById(R.id.list_contents_text);
            status = itemView.findViewById(R.id.list_status_button);
        }
    }
}
