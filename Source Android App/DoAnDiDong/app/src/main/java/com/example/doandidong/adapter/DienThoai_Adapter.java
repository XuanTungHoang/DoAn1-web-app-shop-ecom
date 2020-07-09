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

public class DienThoai_Adapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> array_dienthoai;

    public DienThoai_Adapter(Context context, ArrayList<SanPham> array_dienthoai) {
        this.context = context;
        this.array_dienthoai = array_dienthoai;
    }

    @Override
    public int getCount() {
        return array_dienthoai.size();
    }

    @Override
    public Object getItem(int position) {
        return array_dienthoai.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txt_tendienthoai;
        public TextView txt_giadienthoai;
        public TextView txt_motadienthoai;
        public ImageView img_dienthoai;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(viewHolder==null){
             viewHolder=new ViewHolder();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            convertView=inflater.inflate( R.layout.view_1_dienthoai,null );
            viewHolder.txt_tendienthoai=(TextView) convertView.findViewById( R.id.txtView_tendienthoai );
            viewHolder.txt_giadienthoai=(TextView) convertView.findViewById( R.id.txtView_giadienthoai );
            viewHolder.txt_motadienthoai=(TextView) convertView.findViewById( R.id.txtView_motadienthoai );
            viewHolder.img_dienthoai=(ImageView) convertView.findViewById( R.id.ImgView_dienthoai );
            convertView.setTag( viewHolder );
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        SanPham sanPham=(SanPham) getItem( position );
        viewHolder.txt_tendienthoai.setText( sanPham.getTensanpham() );

        DecimalFormat decimalFormat= new DecimalFormat( "###,###,###" );
        viewHolder.txt_giadienthoai.setText( "Giá: " + decimalFormat.format( sanPham.getGiasanpham() )+ "VNĐ" );

        // Full 2 dong tren view
        viewHolder.txt_motadienthoai.setMaxLines( 2 );
        // Het 2 dong hien dau 3 cham
        viewHolder.txt_motadienthoai.setEllipsize( TextUtils.TruncateAt.END );
        viewHolder.txt_motadienthoai.setText( sanPham.getMotasanpham() );

        // Load img dien thoai
        Picasso.get().load( sanPham.getHinhanhsanpham() ).into( viewHolder.img_dienthoai );
        return convertView;

    }
}
