package com.example.todoapptest.Item;

import android.graphics.drawable.Drawable;

import java.util.Date;

public class ListViewItem {
    private int list_id;
    private Drawable list_favorite;
    private String list_contents;
    private String list_status;
    private Date list_writeDate;

    public ListViewItem(){

    }

    public ListViewItem(int list_id, String list_contents, Date list_writeDate) {
        this.list_id = list_id;
        this.list_contents = list_contents;
        this.list_writeDate = list_writeDate;
    }

    public int getList_id() {
        return list_id;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
    }

    public String getList_contents() {
        return list_contents;
    }

    public void setList_contents(String list_contents) {
        this.list_contents = list_contents;
    }

    public Drawable getList_favorite() {
        return list_favorite;
    }

    public void setList_favorite(Drawable list_favorite) {
        this.list_favorite = list_favorite;
    }

    public String getList_status() {
        return list_status;
    }

    public void setList_status(String list_status) {
        this.list_status = list_status;
    }

    public Date getList_writeDate() {
        return list_writeDate;
    }

    public void setList_writeDate(Date list_writeDate) {
        this.list_writeDate = list_writeDate;
    }
}
