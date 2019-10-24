package com.example.todoapptest.Model;

import java.util.Date;

import io.realm.RealmObject;

public class ListObject extends RealmObject {
    private int seq;
    private String contents;
    private Date writeDate;

    public ListObject(){

    }

    public ListObject(int seq) {
        this.seq = seq;
    }

    public ListObject(int seq, String contents) {
        this.seq = seq;
        this.contents = contents;
    }

    public ListObject(int seq, String contents, Date writeDate) {
        this.seq = seq;
        this.contents = contents;
        this.writeDate = writeDate;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
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
