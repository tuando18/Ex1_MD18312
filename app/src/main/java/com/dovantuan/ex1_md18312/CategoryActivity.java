package com.dovantuan.ex1_md18312;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dovantuan.ex1_md18312.Adapter.CatAdapter;
import com.dovantuan.ex1_md18312.DAO.CatDAO;
import com.dovantuan.ex1_md18312.DTO.CatDTO;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    TextView tv_id;
    EditText edt_name;
    Button btn_add,btn_update,btn_delete;
    ListView lv_cat;
    CatDAO catDAO;
    CatDTO objCurrenCat;
    List<CatDTO> ds;
    CatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        tv_id = findViewById(R.id.tv_id);
        edt_name = findViewById(R.id.tv_name);
        btn_add = findViewById(R.id.btn_add);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        lv_cat = findViewById(R.id.lv_cat);
        catDAO= new CatDAO(this);

        ds = catDAO.getAll();
//        ArrayAdapter<CatDTO> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ds);
        adapter = new CatAdapter(ds, this);
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

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objCurrenCat.setName(edt_name.getText().toString());
                int kq = catDAO.UpdateRow(objCurrenCat);
                if (kq>0){
                    Toast.makeText(CategoryActivity.this, "Sửa thành công!!", Toast.LENGTH_SHORT).show();
                    ds.clear();
                    ds.addAll(catDAO.getAll());
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(CategoryActivity.this, "Không sửa được!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objCurrenCat.setName(edt_name.getText().toString());
                int kq = catDAO.DeleteRow(objCurrenCat);
                if (kq>0){
                    Toast.makeText(CategoryActivity.this, "Xóa thành công!!", Toast.LENGTH_SHORT).show();
                    ds.clear();
                    ds.addAll(catDAO.getAll());
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(CategoryActivity.this, "Không xóa được!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lv_cat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                objCurrenCat = ds.get(position);
                edt_name.setText(objCurrenCat.getName());

                return false;
            }
        });

    }
}