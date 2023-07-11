package com.dovantuan.ex1_md18312.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dovantuan.ex1_md18312.DTO.CatDTO;
import com.dovantuan.ex1_md18312.R;

import java.util.List;

public class CatAdapter extends BaseAdapter {

    List<CatDTO> listCat;
    Context context;

    public CatAdapter(List<CatDTO> listCat, Context context) {
        this.listCat = listCat;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listCat.size();
    }

    @Override
    public Object getItem(int position) {
        return listCat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listCat.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        if(convertView == null){
            row = View.inflate(context, R.layout.row_cat, null);
        } else
            row = convertView;
            CatDTO objCat = listCat.get(position);

            TextView tv_id = row.findViewById(R.id.txtID);
            TextView tv_name = row.findViewById(R.id.txtName);

            tv_id.setText(objCat.getId()+"");
            tv_name.setText(objCat.getName());

        return row;
    }
}
