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

    public void insertList(String contents, String writeDate) {
        ListObject listObject = new ListObject();

        Number curID = realm.where(ListObject.class).max("id");

        // 수동으로 auto increase 해주기
        int nextID;
        if(curID == null) {
            nextID = 1;
        } else {
            nextID = curID.intValue() + 1;
        }

        Log.d("CUR ID", ""+curID);
        Log.d("NEXT ID", ""+nextID);

        // Object 객체 안에 데이터 입력하기
        listObject.setId(nextID);
        listObject.setContents(contents);
        listObject.setWriteDate(writeDate);

        Log.d("INSERT DB", "[ ID : " + nextID + " , CONTENTS : " + contents + " , WRITE DATE : "+writeDate + " ]");

        realm.beginTransaction();
        realm.copyToRealm(listObject);
        realm.commitTransaction();
    }

    public ArrayList<ListViewItem> getList(){
        RealmResults<ListObject> lists = realm.where(ListObject.class).findAll().sort("id");

        ArrayList<ListViewItem> listItems = new ArrayList<>();

        for(int i=0; i<lists.size(); i++){
            listItems.add(new ListViewItem(
                    lists.get(i).getId(),
                    lists.get(i).getContents(),
                    lists.get(i).getWriteDate()
            ));

            Log.d("GET LIST", "[ ID : "+lists.get(i).getId()
                    + " , CONTENTS : "+lists.get(i).getContents()
                    + " , WRITE DATE : "+lists.get(i).getWriteDate() + " ]");
        }

        return listItems;
    }

    public ArrayList<ListViewItem> getListForDate(String writeDate){
        RealmResults<ListObject> lists = realm.where(ListObject.class).equalTo("writeDate", writeDate).findAll().sort("id");

        ArrayList<ListViewItem> listItems = new ArrayList<>();

        for(int i=0; i<lists.size(); i++){
            listItems.add(new ListViewItem(
                    lists.get(i).getId(),
                    lists.get(i).getContents(),
                    lists.get(i).getWriteDate()
            ));

            Log.d("GET LIST FOR DATE", "[ ID : "+lists.get(i).getId()
                    + " , CONTENTS : "+lists.get(i).getContents()
                    + " , WRITE DATE : "+lists.get(i).getWriteDate() + " ]");
        }

        return listItems;
    }

    public void editList(int list_id, String contents) {
        RealmResults<ListObject> lists = realm.where(ListObject.class).equalTo("id", list_id).findAll();

        if (lists.isEmpty()) {
            return;
        }

        ListObject list = lists.get(0);

        Log.d("EDIT LIST", "[ ID : " + lists.get(0).getId()
                + " , CONTENTS : " + lists.get(0).getContents()
                + " , WRITE DATE : " + lists.get(0).getWriteDate() + " ]");


        realm.beginTransaction();
        list.setContents(contents);
        realm.commitTransaction();
    }

    public void deleteList(final int list_id) {
        final RealmResults<ListObject> lists = realm.where(ListObject.class).equalTo("id", list_id).findAll();

        if (lists.isEmpty()) {
            return;
        }

        Log.d("DELETE LIST", "[ ID : " + lists.get(0).getId()
                + " , CONTENTS : " + lists.get(0).getContents()
                + " , WRITE DATE : " + lists.get(0).getWriteDate() + " ]");

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                lists.deleteAllFromRealm();
            }
        });
    }
}
