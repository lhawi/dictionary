package com.hawi.lukman.project_kamus_made_dicoding.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.hawi.lukman.project_kamus_made_dicoding.R;
import com.hawi.lukman.project_kamus_made_dicoding.model.KamusModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.hawi.lukman.project_kamus_made_dicoding.db.DatabaseContract.KamusColumns.FIELD_ARTI;
import static com.hawi.lukman.project_kamus_made_dicoding.db.DatabaseContract.KamusColumns.FIELD_KATA;
import static com.hawi.lukman.project_kamus_made_dicoding.db.DatabaseContract.TABLE_ENG_TO_IND;
import static com.hawi.lukman.project_kamus_made_dicoding.db.DatabaseContract.TABLE_IND_TO_ENG;

public class KamusHelper {
    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public KamusHelper(Context context){
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<KamusModel> getDataByName(String query, String lang_select){
        Cursor cursor;
        if (lang_select == "Eng"){
            cursor = database.query(TABLE_ENG_TO_IND, null, FIELD_KATA+" LIKE?", new String[]{query.trim()+"%"}, null, null, _ID + " ASC", null);

        }else{
            cursor = database.query(TABLE_IND_TO_ENG, null, FIELD_KATA+" LIKE?", new String[]{query.trim()+"%"}, null, null, _ID + " ASC", null);
        }
        cursor.moveToFirst();

        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount()>0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(FIELD_KATA)));
                kamusModel.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(FIELD_ARTI)));

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusModel> getAllData(String lang_select){
        Cursor cursor;

        if (lang_select == "Eng"){
            cursor = database.query(TABLE_ENG_TO_IND, null, null,null,null, null, _ID+ " ASC", null);
        }else{
            cursor = database.query(TABLE_IND_TO_ENG, null, null,null,null, null, _ID+ " ASC", null);
        }
        cursor.moveToFirst();

        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount()>0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(FIELD_KATA)));
                kamusModel.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(FIELD_ARTI)));

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(KamusModel kamusModel, String lang_select){

        String table = null;
        if (lang_select == "Eng"){
            table = TABLE_ENG_TO_IND;
        }else{
            table = TABLE_IND_TO_ENG;
        }

        ContentValues initialValues = new ContentValues();
        initialValues.put(FIELD_KATA, kamusModel.getKata());
        initialValues.put(FIELD_ARTI, kamusModel.getDeskripsi());
        return database.insert(table, null, initialValues);
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    public void insertTransaction(KamusModel kamusModel, String lang_select){
        String table = null;
        if (lang_select == "Eng"){
            table = TABLE_ENG_TO_IND;
        }else{
            table = TABLE_IND_TO_ENG;
        }
        String sql = "INSERT INTO "+table+" ("+FIELD_KATA+", "+FIELD_ARTI+") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusModel.getKata());
        stmt.bindString(2, kamusModel.getDeskripsi());
        stmt.execute();
        stmt.clearBindings();
    }


    public  int update (KamusModel kamusModel, String lang_select){
        String table = null;
        if (lang_select == "Eng"){
            table = TABLE_ENG_TO_IND;
        }else{
            table = TABLE_IND_TO_ENG;
        }
        ContentValues args = new ContentValues();
        args.put(FIELD_KATA, kamusModel.getKata());
        args.put(FIELD_ARTI, kamusModel.getDeskripsi());
        return database.update(table, args, _ID + "= '" + kamusModel.getId() + "'", null);
    }

    public int delete (int id, String lang_select){
        String table = null;
        if (lang_select == "Eng"){
            table = TABLE_ENG_TO_IND;
        }else{
            table = TABLE_IND_TO_ENG;
        }

        return database.delete(table, _ID + " = '"+id+"'", null);
    }
}
