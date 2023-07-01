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
    private EditText etProductName;
    private EditText etProductPrice;
    private EditText etProductCategory;
    private Button btnAddProduct;
    private ListView lvProductList;

    private ProductAdapter productAdapter;
    private List<ProductDTO> productList;
    private ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        etProductName = findViewById(R.id.etProductName);
        etProductPrice = findViewById(R.id.etProductPrice);
        etProductCategory = findViewById(R.id.etProductCategory);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        lvProductList = findViewById(R.id.lvProductList);

        productDAO = new ProductDAO(this);
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        lvProductList.setAdapter(productAdapter);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etProductName.getText().toString().trim();
                String priceStr = etProductPrice.getText().toString().trim();
                String categoryStr = etProductCategory.getText().toString().trim();

                if (name.isEmpty() || priceStr.isEmpty() || categoryStr.isEmpty()) {
                    Toast.makeText(ProductActivity.this, "Bạn chưa nhập thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                int price = Integer.parseInt(priceStr);
                int categoryId = Integer.parseInt(categoryStr);

                ProductDTO product = new ProductDTO(0, name, price, categoryId);
                long result = productDAO.addRow(product);

                if (result != -1) {
                    product.setId((int) result);
                    productList.add(product);
                    productAdapter.notifyDataSetChanged();
                    clearInputFields();
                    Toast.makeText(ProductActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductActivity.this, "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductDTO selectedProduct = productList.get(position);
                Toast.makeText(ProductActivity.this, "Chọn sản phẩm: " + selectedProduct.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        loadProductList();
    }

    private void loadProductList() {
        productList.clear();
        productList.addAll(productDAO.getAll());
        productAdapter.notifyDataSetChanged();
    }

    private void clearInputFields() {
        etProductName.setText("");
        etProductPrice.setText("");
        etProductCategory.setText("");
    }
}
