package com.example.todoapptest.Model;

import android.graphics.drawable.Drawable;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ListObject extends RealmObject {
    @PrimaryKey
    private int list_id;
    private String list_contents;
    private String list_status;
    private Date list_writeDate;

    public ListObject() {

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
