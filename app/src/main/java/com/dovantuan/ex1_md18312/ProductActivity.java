package com.dovantuan.ex1_md18312;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dovantuan.ex1_md18312.Adapter.ProductAdapter;
import com.dovantuan.ex1_md18312.DAO.ProductDAO;
import com.dovantuan.ex1_md18312.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    EditText edt_name;
    EditText edt_Price;
    EditText edt_IdCat;
    Button btnAddProduct;
    ListView lvProductList;

    ProductAdapter productAdapter;
    List<ProductDTO> productList;
    ProductDAO productDAO;

    ProductDTO objCurrentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        edt_name = findViewById(R.id.etProductName);
        edt_Price = findViewById(R.id.etProductPrice);
        edt_IdCat = findViewById(R.id.etProductCategory);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        lvProductList = findViewById(R.id.lvProductList);

        productDAO = new ProductDAO(this);
        // tự động load danh sách khi vào activity
        productList = productDAO.getAll();
        productAdapter = new ProductAdapter(this, productList);

        lvProductList.setAdapter(productAdapter);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name.getText().toString().trim();
                String priceStr = edt_Price.getText().toString().trim();
                String categoryStr = edt_IdCat.getText().toString().trim();

                if (name.isEmpty() || priceStr.isEmpty() || categoryStr.isEmpty()) {
                    Toast.makeText(ProductActivity.this, "Bạn chưa nhập thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                int price = Integer.parseInt(priceStr);
                int categoryId = Integer.parseInt(categoryStr);

                ProductDTO product = new ProductDTO(0, name, price, categoryId);
                long id = productDAO.addRow(product);

//                if (id != -1) {
//                    product.setId((int) id);
//                    productList.add(product);
////                    productList.addAll(ProductDAO.getAll());
//                    productAdapter.notifyDataSetChanged();
//                    clearInputFields();
//                    Toast.makeText(ProductActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(ProductActivity.this, "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
//                }
//            }
                if (id > 0) {
                    productList.clear();
                    productList.addAll(productDAO.getAll());
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(ProductActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductActivity.this, "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvProductList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                objCurrentProduct = productList.get(i);
                // đưa dữ liệu lên edittext
                edt_name.setText( objCurrentProduct .getName());
                edt_Price.setText(objCurrentProduct .getPrice());
                edt_IdCat.setText(objCurrentProduct .getId_cat());

                return true;
            }
        });


    }
}
