package com.example.todoapptest.Model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ListObject extends RealmObject {
    @PrimaryKey
    private int list_id;
    private String contents;
    private Date writeDate;

    public ListObject(){

    }

    public ListObject(int list_id) {
        this.list_id = list_id;
    }

    public ListObject(int list_id, String contents) {
        this.list_id = list_id;
        this.contents = contents;
    }

    public ListObject(int list_id, String contents, Date writeDate) {
        this.list_id = list_id;
        this.contents = contents;
        this.writeDate = writeDate;
    }

    public int getlist_id() {
        return list_id;
    }

    public void setlist_id(int list_id) {
        this.list_id = list_id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }
}
