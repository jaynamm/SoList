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

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> implements EditCustomDialog.CustomDialogListener {
    private final ArrayList<ListViewItem> mDataList;
    private Context mContext;
    private EditCustomDialog dialog;

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

        final int list_id = items.getId();
        final String list_contents = items.getContents();
        final Date list_date = items.getWriteDate();

        // customDialog create and set Listener
        dialog = new EditCustomDialog(mContext);
        dialog.setCustomDialogListener(this);

        holder.contents.setText(list_contents);

        if (mListener != null) {
            final int pos = position;
            holder.itemView.setOnClickListener(v -> {
                // 수정하는 dialog 띄우기
                dialog.callFunction(pos, items);
            });
            holder.itemView.setOnLongClickListener(v -> {
                deleteItem(pos, list_id);
                return true;
            });
            holder.status.setOnClickListener(v -> mListener.onStatusClicked());
            /*
            holder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFavoriteClicked();
                }
            });

             */
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
        mItem.setContents(contents);
        mDataList.add(mItem);
    }

    // 길게 눌렀을 때 삭제하는 Dialog 뜨고 '예' 누르면 삭제하기
    public void deleteItem(final int position, final int id){
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("삭제하시겠습니까?");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDataList.remove(position);
                notifyDataSetChanged();
                DBHelper.getInstance().deleteList(id);
                Toast.makeText(mContext, "리스트가 삭제되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("아니오", null);
        builder.show();
    }

    // customDialog 에서 Listener 가져와서 수정하기
    @Override
    public void onPositiveClicked(int position, ListViewItem item) {
        mDataList.set(position, item);
        notifyDataSetChanged();
        DBHelper.getInstance().editList(item.getId(), item.getContents());
        Toast.makeText(mContext, "리스트가 수정되었습니다", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeClicked() {
        Toast.makeText(mContext, "취소되었습니다", Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //ImageView favorite;
        TextView contents;
        Button status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //favorite = itemView.findViewById(R.id.list_favorite_image);
            contents = itemView.findViewById(R.id.list_contents_text);
            status = itemView.findViewById(R.id.list_status_button);
        }
    }
}
