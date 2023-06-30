package com.dovantuan.ex1_md18312;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dovantuan.ex1_md18312.DAO.CatDAO;
import com.dovantuan.ex1_md18312.DTO.CatDTO;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    TextView tv_id;
    EditText edt_name;
    Button btn_add;
    ListView lv_cat;
    CatDAO catDAO;
    List<CatDTO> ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        tv_id = findViewById(R.id.tv_id);
        edt_name = findViewById(R.id.tv_name);
        btn_add = findViewById(R.id.btn_add);
        lv_cat = findViewById(R.id.lv_cat);
        catDAO= new CatDAO(this);

        ds = catDAO.getAll();
        ArrayAdapter<CatDTO> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ds);
        lv_cat.setAdapter(adapter);

        //hoàn thành hiển thị dữ liệu
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name.getText().toString();
                CatDTO objCat = new CatDTO();
                objCat.setName(name);

                long id = catDAO.AddRow(objCat);
                if (id>0){
                    Toast.makeText(CategoryActivity.this, "Thêm thành công!!", Toast.LENGTH_SHORT).show();
                    ds.clear();
                    ds.addAll(catDAO.getAll());
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(CategoryActivity.this, "Không thêm được!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}