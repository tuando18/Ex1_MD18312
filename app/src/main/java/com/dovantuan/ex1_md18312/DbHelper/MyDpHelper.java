package com.dovantuan.ex1_md18312.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDpHelper extends SQLiteOpenHelper {

    static String DP_NAME = "quanli_banhang";
    static int DP_VERSION = 1;

    public MyDpHelper(Context context){
        super(context, DP_NAME, null, DP_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_tb_cat="CREATE TABLE tb_cat ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE NOT NULL );";
        db.execSQL(sql_tb_cat);

        String sql_tb_product="CREATE TABLE tb_product ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, price INTEGER DEFAULT (0) NOT NULL,id_cat INTEGER NOT NULL CONSTRAINT fk_pro_cat REFERENCES tb_cat (id) );";
        db.execSQL(sql_tb_product);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE tb_cat");
        db.execSQL("DROP TABLE tb_product");
        onCreate(db);

    }
}
