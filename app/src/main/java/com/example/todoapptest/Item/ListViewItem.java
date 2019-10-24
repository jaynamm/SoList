package com.example.todoapptest.Item;

import android.graphics.drawable.Drawable;

import java.util.Date;

public class ListViewItem {
    private String list_contents;
    private Drawable list_favorite;
    private String list_status;
    private Date list_date;

    public ListViewItem(){

    }

    public ListViewItem(String list_contents, Date list_date) {
        this.list_contents = list_contents;
        this.list_date = list_date;
    }

    public ListViewItem(String list_contents, Drawable list_favorite, String list_status, Date list_date) {
        this.list_contents = list_contents;
        this.list_favorite = list_favorite;
        this.list_status = list_status;
        this.list_date = list_date;
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
