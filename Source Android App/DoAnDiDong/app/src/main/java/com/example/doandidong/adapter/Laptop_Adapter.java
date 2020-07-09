package com.example.doandidong.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doandidong.R;
import com.example.doandidong.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Laptop_Adapter extends BaseAdapter {

    Context context;
    ArrayList<SanPham> array_laptop;

    public Laptop_Adapter(Context context, ArrayList<SanPham> array_laptop) {
        this.context = context;
        this.array_laptop = array_laptop;
    }

    @Override
    public int getCount() {
        return array_laptop.size();
    }

    @Override
    public Object getItem(int position) {
        return array_laptop.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{

        // run len lan dau null -> bat co nhung thuoc tinh nay
        // run lan sau co san chi can gan du lieu -> nhanh hon
        public TextView txt_tenlaptop;
        public TextView txt_gialaptop;
        public TextView txt_motalaptop;
        public ImageView img_laptop;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(viewHolder==null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            convertView=inflater.inflate( R.layout.view_1_laptop,null );
            viewHolder.txt_tenlaptop=(TextView) convertView.findViewById( R.id.txtView_tenlaptop );
            viewHolder.txt_gialaptop=(TextView) convertView.findViewById( R.id.txtView_gialaptop );
            viewHolder.txt_motalaptop=(TextView) convertView.findViewById( R.id.txtView_motalaptop );
            viewHolder.img_laptop=(ImageView) convertView.findViewById( R.id.ImgView_laptop );
            convertView.setTag( viewHolder );
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        SanPham sanPham=(SanPham) getItem( position );
        viewHolder.txt_tenlaptop.setText( sanPham.getTensanpham() );

        // Dinh dang gia san pham
        DecimalFormat decimalFormat= new DecimalFormat( "###,###,###" );
        viewHolder.txt_gialaptop.setText( "Giá: " + decimalFormat.format( sanPham.getGiasanpham() )+ "VNĐ" );

        // Full 2 dong tren view
        viewHolder.txt_motalaptop.setMaxLines( 2 );

        // Het 2 dong hien dau 3 cham
        viewHolder.txt_motalaptop.setEllipsize( TextUtils.TruncateAt.END );
        viewHolder.txt_motalaptop.setText( sanPham.getMotasanpham() );

        // Load img dien thoai
        Picasso.get().load( sanPham.getHinhanhsanpham() ).into( viewHolder.img_laptop );
        return convertView;

    }
}
