package com.example.doandidong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doandidong.R;
import com.example.doandidong.model.LoaiSanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiSP_Adapter extends BaseAdapter {

    ArrayList<LoaiSanPham> arrayListLoaiSP;
    Context context;

    public LoaiSP_Adapter(ArrayList<LoaiSanPham> arrayListLoaiSP, Context context) {
        this.arrayListLoaiSP = arrayListLoaiSP;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListLoaiSP.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListLoaiSP.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txt_TenLoaiSP;
        ImageView Img_LoaiSP;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            convertView =inflater.inflate( R.layout.view_1_loaisanpham,null);

            viewHolder.txt_TenLoaiSP=(TextView) convertView.findViewById( R.id.txtView_LoaiSp );
            viewHolder.Img_LoaiSP=(ImageView) convertView.findViewById( R.id.ImageView_LoaiSP );
            convertView.setTag( viewHolder );
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        LoaiSanPham loaiSanPham=(LoaiSanPham) getItem( position );
        viewHolder.txt_TenLoaiSP.setText( loaiSanPham.getTenSP() );
        Picasso.get().load(loaiSanPham.getHinhAnhSP()).into(viewHolder.Img_LoaiSP);
        return convertView;
    }
}
