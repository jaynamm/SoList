package com.example.todoapptest.DBHelper;

import android.util.Log;

import com.example.todoapptest.Item.ListViewItem;
import com.example.todoapptest.Model.ListObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DBHelper {
    private static DBHelper instance = new DBHelper();

    public static DBHelper getInstance() {
        return instance;
    }

    Realm realm;

    public DBHelper(){
        realm = Realm.getDefaultInstance();
    }

    public Realm getRealmInstance() {
        return realm;
    }

    // 새롭게 추가하는 부분
    public void insertList(String contents, Date writeDate) {
        ListObject listObject = new ListObject();

        Number curID = realm.where(ListObject.class).max("list_id");

        int nextID;
        if(curID == null) {
            nextID = 1;
        } else {
            nextID = curID.intValue() + 1;
        }

        Log.d("CUR ID", ""+curID);
        Log.d("NEXT ID", ""+nextID);

        listObject.setList_id(nextID);
        listObject.setContents(contents);
        listObject.setWriteDate(writeDate);

        Log.d("INSERT LIST", ""+listObject);

        realm.beginTransaction();
        realm.copyToRealm(listObject);
        realm.commitTransaction();
    }

    public ArrayList<ListViewItem> getList(){
        RealmResults<ListObject> lists = realm.where(ListObject.class).findAll();
        Log.d(TAG, "list : " + lists + "\n");
        ArrayList<ListViewItem> listItems = new ArrayList<>();

        for(int i=0; i<lists.size(); i++){
            listItems.add(new ListViewItem(
                    lists.get(i).getList_id(),
                    lists.get(i).getContents(),
                    lists.get(i).getWriteDate()
            ));
        }

        return listItems;
    }

    public void editList(int list_id, String contents, Date writeDate) {
        RealmResults<ListObject> lists = realm.where(ListObject.class).equalTo("list_id", list_id).findAll();

        if (lists.isEmpty()) {
            return;
        }

        ListObject list = lists.get(0);

        realm.beginTransaction();
        list.setContents(contents);
        list.setWriteDate(writeDate);
        realm.commitTransaction();
    }

    public void deleteList(final int list_id) {
        RealmResults<ListObject> lists = realm.where(ListObject.class).equalTo("list_id", list_id).findAll();
        RealmResults<ListObject> lists_result = realm.where(ListObject.class).findAll();

        if (lists.isEmpty()) {
            return;
        }

        realm.beginTransaction();
        lists.deleteAllFromRealm();
        realm.commitTransaction();
    }
}
