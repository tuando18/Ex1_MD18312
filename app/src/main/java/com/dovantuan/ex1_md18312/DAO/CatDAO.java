package com.dovantuan.ex1_md18312.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dovantuan.ex1_md18312.DTO.CatDTO;
import com.dovantuan.ex1_md18312.DbHelper.MyDpHelper;

import java.util.ArrayList;
import java.util.List;

public class CatDAO {
    MyDpHelper myDpHelper;
    SQLiteDatabase db;

    public CatDAO(Context context){
        myDpHelper = new MyDpHelper(context);
        db = myDpHelper.getWritableDatabase();
    }

    public long AddRow (CatDTO objCat){
        ContentValues values = new ContentValues();
        values.put("name", objCat.getName());
        return db.insert("tb_cat", null,values);
    }

    public int UpdateRow (CatDTO objCat){
        ContentValues values = new ContentValues();
        values.put("name", objCat.getName());
        //điều kiện sửa
        String[] dieukien = new String[]{ String.valueOf(objCat.getId())};
        return db.update("tb_cat", values, "id=?", dieukien);
    }

    public int DeleteRow (CatDTO objCat){
        //điều kiện sửa
        String[] dieukien = new String[]{ String.valueOf(objCat.getId())};
        return db.delete("tb_cat", "id=?", dieukien);
    }

    public List<CatDTO> getAll(){
        List<CatDTO>  listCat = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tb_cat", null);
        if (c !=null && c.getCount() >0 ){
            c.moveToFirst();
            do {//thứ tự cột: 0=id; 1=name
                int id_cat = c.getInt(0);
                String name = c.getString(1);
                //tạo đối tượng DTO
                CatDTO objCat = new CatDTO();
                objCat.setId(id_cat);
                objCat.setName(name);

                listCat.add(objCat);

            } while (c.moveToNext() );
        }

        return  listCat;
    }

}
