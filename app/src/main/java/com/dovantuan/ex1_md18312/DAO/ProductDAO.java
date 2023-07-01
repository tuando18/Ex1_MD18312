package com.dovantuan.ex1_md18312.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dovantuan.ex1_md18312.DTO.CatDTO;
import com.dovantuan.ex1_md18312.DTO.ProductDTO;
import com.dovantuan.ex1_md18312.DbHelper.MyDpHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    MyDpHelper myDpHelper;
    SQLiteDatabase db;

    public ProductDAO(Context context) {
        myDpHelper = new MyDpHelper(context);
        db = myDpHelper.getWritableDatabase();
    }

    public long addRow(ProductDTO product) {
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("id_cat", product.getId_cat());
        return db.insert("tb_product", null, values);
    }

    public int updateRow(ProductDTO product) {
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("id_cat", product.getId_cat());
        String[] condition = new String[]{String.valueOf(product.getId())};
        return db.update("tb_product", values, "id=?", condition);
    }

    public int deleteRow(ProductDTO product) {
        String[] condition = new String[]{String.valueOf(product.getId())};
        return db.delete("tb_product", "id=?", condition);
    }

    public List<ProductDTO> getAll() {
        List<ProductDTO> productList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_product", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int price = cursor.getInt(2);
                int id_cat = cursor.getInt(3);

                ProductDTO product = new ProductDTO(id, name, price, id_cat);
                productList.add(product);

//                ProductDTO objProduct = new ProductDTO();
//                objProduct.setId(id_cat);
//                objProduct.setName(name);
//                objProduct.setPrice(price);
//                objProduct.setId_cat(id_cat);
//
//                productList.add(objProduct);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return productList;
    }
}
