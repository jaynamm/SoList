package com.example.todoapptest.Item;

import java.util.Date;

public class ListViewItem {
    private int id;
    private String contents;
    private Date writeDate;

    public ListViewItem() {

    }

    public ListViewItem(int id, String contents, Date writeDate) {
        this.id = id;
        this.contents = contents;
        this.writeDate = writeDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
