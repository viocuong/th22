package com.example.nguyenvancuong_thuchanh2_bai2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.nguyenvancuong_thuchanh2_bai2.model.Quyengop;

import java.util.ArrayList;
import java.util.List;
public class SqlOpenHelp extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuyengopTH.db";
    private static final int DATABASE_VERSION =1;
    public SqlOpenHelp(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Quyengop("+
                "id integer primary key autoincrement,"+
                "name text," +
                "thanhpho integer,"+
                "date text,"+
                "sotien real"+
                ")";
        db.execSQL(sql);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public long add(Quyengop m){
        ContentValues v = new ContentValues();
        v.put("name",m.getName());
        v.put("thanhpho",m.getThanhpho());
        v.put("date",m.getDate());
        v.put("sotien",m.getSotien());
        return getWritableDatabase().insert("Quyengop",null,v);
    }
    public int delete(int id){
        String whereClause = "id = ?";
        String[] args = {""+id};
        return getWritableDatabase().delete("Quyengop",whereClause,args);
    }
    public int update(Quyengop m){
        String whereClause = "id= ?";
        String[] args = {""+m.getId()};
        ContentValues v = new ContentValues();
        v.put("sotien",m.getSotien());
        v.put("name",m.getName());
        v.put("thanhpho",m.getThanhpho());
        v.put("date",m.getDate());
        return getWritableDatabase().update("Quyengop",v,whereClause,args);
    }
    public List<Quyengop> getAll(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("Quyengop", null, null, null, null, null, null);
        List<Quyengop> ans = new ArrayList<>();
        while(c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String thanhpho = c.getString(2);
            String date = c.getString(3);
            double sotien = c.getInt(4);
            ans.add(new Quyengop(id, name,date,thanhpho,sotien));
        }
        return ans;
    }
    public Quyengop get(int id){
        String sql = "select * from Quyengop where id = ?";
        String[] args = {""+id};
        Cursor c = getReadableDatabase().rawQuery(sql,args);
        if(c.moveToNext()){
            String name = c.getString(1);
            String thanhpho = c.getString(2);
            String date = c.getString(3);
            double sotien = c.getInt(4);
            return new Quyengop(id, name,date,thanhpho,sotien);
        }
        return null;
    }
    public List<Quyengop> search(String key){
        String sql = "select * from Quyengop where name like ?";
        Cursor c= getReadableDatabase().rawQuery(sql,new String[]{"%"+key+"%"});
        List<Quyengop> ans = new ArrayList<>();
        while(c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String thanhpho = c.getString(2);
            String date = c.getString(3);
            double sotien = c.getInt(4);
            ans.add(new Quyengop(id, name,date,thanhpho,sotien));
        }
        return ans;
    }
}
