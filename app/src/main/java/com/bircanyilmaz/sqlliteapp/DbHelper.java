package com.bircanyilmaz.sqlliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context,"Userdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Userdetails(name TEXT primary key,surname TEXT,TC TEXT,telNo TEXT,dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE İF EXISTS Userdetails");
    }
    public Boolean insertUserData(String name,String surname,String TC,String telNo,String dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("surname",surname);
        contentValues.put("TC",TC);
        contentValues.put("telNo",telNo);
        contentValues.put("dob",dob);
        long result = DB.insert("Userdetails",null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Boolean updateUserData(String name,String surname,String TC,String telNo,String dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("surname",surname);
        contentValues.put("TC",TC);
        contentValues.put("telNo",telNo);
        contentValues.put("dob",dob);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?",new String[]{name} );
        if(cursor.getCount()>0){
            long result = DB.update("Userdetails",contentValues,"name=?",new String[]{name});
            if(result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }
    public Boolean deleteUserData(String name){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?",new String[]{name} );
        if(cursor.getCount()>0){
            long result = DB.delete("Userdetails","name=?",new String[]{name});
            if(result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Userdetails",null );
        return cursor;
    }
}
