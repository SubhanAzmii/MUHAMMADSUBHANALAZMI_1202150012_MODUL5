package com.example.android.muhammadsubhanalazmi_1202150012_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;

/**
 * Created by Kizuna on 24-Mar-18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //Versi database
    private static final int DATABASE_VERSION = 1;
    //database name
    private static final String DATABASE_NAME = "ToDOList";
    //Table name
    private static final String TABLE_NAME = "List";

    //Table Coloumb
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_DESKRIPSI = "deskripsi";
    private static final String KEY_PRIORITY = "priority";




    //membuat construktor untuk databasehelper
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //implementasi untuk method databasehelper untuk membuat table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb = "CREATE TABLE " + TABLE_NAME + "("              //sql untuk create table
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAMA + " TEXT,"
                + KEY_DESKRIPSI + " TEXT, " +KEY_PRIORITY + " TEXT)";
        db.execSQL(tb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //melakukan drop tabel lama jika data suda ada
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //membuat tabel lagi
        onCreate(db);
    }

    //Crud Operation
    //add data
    public void tambahTodo(todo Todo){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAMA, Todo.getNama());           //get data value
        values.put(KEY_DESKRIPSI, Todo.getDeskripsi());
        values.put(KEY_PRIORITY, Todo.getPriority());

        db.insert(TABLE_NAME, null, values); //memasukkan ke database
        db.close(); //menutup database
    }

    //read semua data
    public LinkedList<todo> bacatodo(){
        LinkedList<todo> datalist = new LinkedList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //pengulangan semua data untuk di masukkan kedalam list
        if (cursor.moveToFirst()){
            do {
                todo ntodo = new todo();
                ntodo.setId(Integer.valueOf(cursor.getString(0))); //set value
                ntodo.setNama(cursor.getString(1));
                ntodo.setDeskripsi(cursor.getString(2));
                ntodo.setPriority(cursor.getString(3));
                datalist.add(ntodo); //menambahkan kedalam list
            }while (cursor.moveToNext());
        }
        return datalist;
    }

    //Delete data
    public Boolean hapusTodo(todo Todo){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ? ", new  String[] {String.valueOf(Todo.getId())});
        db.close();
        //return db.delete(TABLE_NAME, "id =" + Todo.getId(),null) > 0 ;
        return false;
    }
}
