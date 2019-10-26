package com.example.todoapptest.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapptest.DBHelper.DBHelper;
import com.example.todoapptest.Dialog.EditCustomDialog;
import com.example.todoapptest.Item.ListViewItem;
import com.example.todoapptest.R;

import java.util.ArrayList;
import java.util.Date;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {
    private final ArrayList<ListViewItem> mDataList;
    private Context mContext;

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

    public ListViewAdapter(Context context, ArrayList<ListViewItem> mList){
        mContext = context;
        mDataList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ListViewItem items = mDataList.get(position);

        final int list_id = items.getList_id();
        final String list_contents = items.getList_contents();
        final Date list_date = items.getList_writeDate();

        holder.contents.setText(list_contents);

        if (mListener != null) {
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditCustomDialog dialog = new EditCustomDialog(mContext);
                    dialog.callFunction(mDataList, pos, items);
                    notifyDataSetChanged();
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    //mListener.onItemLongClicked(pos, list_id);
                    android.app.AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("삭제하시겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mDataList.remove(pos);
                            //notifyItemRemoved(pos);
                            notifyDataSetChanged();
                            DBHelper.getInstance().deleteList(list_id);
                            Toast.makeText(mContext, "리스트가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("아니오", null);
                    builder.show();
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
        return mDataList.size();
    }

    public void addItem(String contents) {
        ListViewItem mItem = new ListViewItem();

        mItem.setList_contents(contents);

        mDataList.add(mItem);
    }

    public void editItem(int position, ListViewItem item){
        mDataList.set(position, item);
        notifyDataSetChanged();
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
