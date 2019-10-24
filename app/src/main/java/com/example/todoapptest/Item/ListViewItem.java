package com.example.todoapptest.Item;

import android.graphics.drawable.Drawable;

import java.util.Date;

public class ListViewItem {
    private int list_id;
    private String list_contents;
    private Drawable list_favorite;
    private String list_status;
    private Date list_date;

    public ListViewItem(){

    }

    public ListViewItem(int list_id, String list_contents, Date list_date) {
        this.list_id = list_id;
        this.list_contents = list_contents;
        this.list_date = list_date;
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

    public Date getList_date() {
        return list_date;
    }

    public void setList_date(Date list_date) {
        this.list_date = list_date;
    }
}
