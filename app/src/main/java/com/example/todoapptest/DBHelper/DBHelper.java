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
        listObject.setList_id(nextID);
        listObject.setList_contents(contents);
        listObject.setList_writeDate(writeDate);

        Log.d("INSERT DB",
                "[ ID : " + nextID + " , CONTENTS : " + contents + " , WRITE DATE : "+writeDate + " ]");

        realm.beginTransaction();
        realm.copyToRealm(listObject);
        realm.commitTransaction();
    }

    public ArrayList<ListViewItem> getList(){
        RealmResults<ListObject> lists = realm.where(ListObject.class).findAll().sort("list_id");

        ArrayList<ListViewItem> listItems = new ArrayList<>();

        for(int i=0; i<lists.size(); i++){
            listItems.add(new ListViewItem(
                    lists.get(i).getList_id(),
                    lists.get(i).getList_contents(),
                    lists.get(i).getList_writeDate()
            ));

            Log.d("GET LIST", "[ ID : "+lists.get(i).getList_id()
                    + " , CONTENTS : "+lists.get(i).getList_contents()
                    + " , WRITE DATE : "+lists.get(i).getList_writeDate() + " ]");
        }

        return listItems;
    }

    public void editList(int list_id, String contents) {
        RealmResults<ListObject> lists = realm.where(ListObject.class).equalTo("list_id", list_id).findAll();

        if (lists.isEmpty()) {
            return;
        }

        ListObject list = lists.get(0);


        Log.d("EDIT LIST", "[ ID : " + lists.get(0).getList_id()
                + " , CONTENTS : " + lists.get(0).getList_contents()
                + " , WRITE DATE : " + lists.get(0).getList_writeDate() + " ]");


        realm.beginTransaction();
        list.setList_contents(contents);
        realm.commitTransaction();
    }

    public void deleteList(final int list_id) {
        final RealmResults<ListObject> lists = realm.where(ListObject.class).equalTo("list_id", list_id).findAll();

        if (lists.isEmpty()) {
            return;
        }

        Log.d("DELETE LIST", "[ ID : " + lists.get(0).getList_id()
                + " , CONTENTS : " + lists.get(0).getList_contents()
                + " , WRITE DATE : " + lists.get(0).getList_writeDate() + " ]");

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                lists.deleteAllFromRealm();
            }
        });
    }
}
