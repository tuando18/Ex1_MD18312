package com.dovantuan.ex1_md18312.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dovantuan.ex1_md18312.DTO.ProductDTO;
import com.dovantuan.ex1_md18312.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<ProductDTO> productList;

    public ProductAdapter(Context context, List<ProductDTO> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
            holder = new ViewHolder();
            holder.txtName = convertView.findViewById(R.id.txtName);
            holder.txtPrice = convertView.findViewById(R.id.txtPrice);
            holder.txtCategoryId = convertView.findViewById(R.id.txtCategoryId);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ProductDTO product = productList.get(position);
        holder.txtName.setText(product.getName());
        holder.txtPrice.setText(String.valueOf(product.getPrice()));
        holder.txtCategoryId.setText(String.valueOf(product.getId_cat()));

        return convertView;
    }

    private static class ViewHolder {
        TextView txtName;
        TextView txtPrice;
        TextView txtCategoryId;
    }
}
