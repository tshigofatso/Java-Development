package com.games.cyko.storelocator.models;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.games.cyko.storelocator.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Cyko on 9/30/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "StoreLocator.db";
    public static final String STORE_TABLE_NAME = "store";
    public static final String STOER_COLUMN_ID = "id";
    public static final String STORE_NAME = "store_name";
    public static final String STORE_AREA = "store_area";
    private HashMap hp;



    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table store " +
                        "(id integer primary key, store_name text, store_area text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS store");
        onCreate(db);
    }

    private static final int icons = R.drawable.ic_location_on_white_36dp;

    public List<StoreItems> getListData()
    {
        List<StoreItems> store_list = new ArrayList<StoreItems>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from store", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            StoreItems item = new StoreItems();
            item.setImageResId(icons);
            item.setStoreName(res.getString(res.getColumnIndex(STORE_NAME)));
            item.setStoreArea(res.getString(res.getColumnIndex(STORE_AREA)));
            item.setAd_hoc_id(res.getInt(res.getColumnIndex(STOER_COLUMN_ID)));
            store_list.add(item);


            res.moveToNext();
        }
        return store_list;
    }



    public Integer deleteStore (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("store",
                "id = ?",
                new String[] { Integer.toString(id) });
    }

    public void deleteAllStores(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("store", null,null);
    }

    public StoreItems getRandomListItem (String custom_title)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("store_name", custom_title);
        contentValues.put("store_area", custom_title);
        db.insert("store", null, contentValues);

        StoreItems item = new StoreItems();
        item.setStoreName(custom_title.toString());
        item.setStoreArea(custom_title.toString());
        item.setImageResId(icons);

        return item;
    }




}
