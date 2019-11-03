package com.example.todoapptest.Item;

import java.util.Date;

public class ListViewItem {
    private int id;
    private String contents;
    private String writeDate;

    public ListViewItem() {

    }

    public ListViewItem(int id, String contents, String writeDate) {
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

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }
}
