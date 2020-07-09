package com.example.doandidong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doandidong.R;
import com.example.doandidong.activity.GioHangActivity;
import com.example.doandidong.activity.MainActivity;
import com.example.doandidong.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class GioHang_Adapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arr_giohang;

    public GioHang_Adapter(Context context, ArrayList<GioHang> arr_giohang) {
        this.context = context;
        this.arr_giohang = arr_giohang;
    }

    @Override
    public int getCount() {
        return arr_giohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arr_giohang.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txt_tengiohang,txt_giagiohang;
        public ImageView img_giohang;
        public Button btn_minus,btn_values,btn_plus;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(viewHolder==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            convertView=inflater.inflate( R.layout.view_1_spgiohang,null );

            viewHolder.txt_tengiohang=(TextView) convertView.findViewById( R.id.txtView_tengiohang );
            viewHolder.txt_giagiohang=(TextView) convertView.findViewById( R.id.txtView_giagiohang );
            viewHolder.img_giohang=(ImageView) convertView.findViewById( R.id.img_giohang );
            viewHolder.btn_minus=(Button) convertView.findViewById( R.id.btn_minus );
            viewHolder.btn_values=(Button) convertView.findViewById( R.id.btn_values );
            viewHolder.btn_plus=(Button) convertView.findViewById( R.id.btn_plus );
            convertView.setTag( viewHolder );
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        GioHang gioHang=(GioHang) getItem( position );
        viewHolder.txt_tengiohang.setText( gioHang.getTensanpham() );
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txt_giagiohang.setText( decimalFormat.format( gioHang.getGiasanpham() )+" Đ" );
        Picasso.get().load( gioHang.getHinhanhsanpham() ).into( viewHolder.img_giohang );
        viewHolder.btn_values.setText( gioHang.getSoluong_sp()+"" );

        // xu li button them bot so luong
        int sl=Integer.parseInt( viewHolder.btn_values.getText().toString() );
        if(sl>=10){
            viewHolder.btn_plus.setVisibility( View.INVISIBLE );
            viewHolder.btn_minus.setVisibility( View.VISIBLE );
        }else if(sl<=1){
            viewHolder.btn_minus.setVisibility( View.INVISIBLE );
        }else if(sl>1){
             viewHolder.btn_minus.setVisibility( View.VISIBLE );
             viewHolder.btn_plus.setVisibility( View.VISIBLE );
        }

        final ViewHolder finalViewHolder = viewHolder;
        finalViewHolder.btn_plus.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl_moi=Integer.parseInt( finalViewHolder.btn_values.getText().toString() )+1;
                int sl_hientai= MainActivity.arr_giohang.get( position ).getSoluong_sp();
                long giasp_hientai=MainActivity.arr_giohang.get( position ).getGiasanpham();
                MainActivity.arr_giohang.get( position ).setSoluong_sp( sl_moi );
                long tongtien_moi_1sp=(giasp_hientai*sl_moi)/sl_hientai;
                MainActivity.arr_giohang.get( position ).setGiasanpham( tongtien_moi_1sp );

                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                finalViewHolder.txt_giagiohang.setText( decimalFormat.format( tongtien_moi_1sp)+" Đ" );
                GioHangActivity.PutDataToView();

                if(sl_moi>9){
                    finalViewHolder.btn_plus.setVisibility( View.INVISIBLE );
                    finalViewHolder.btn_minus.setVisibility( View.VISIBLE );
                    finalViewHolder.btn_values.setText( String.valueOf( sl_moi ) );
                }else {
                    finalViewHolder.btn_minus.setVisibility( View.VISIBLE );
                    finalViewHolder.btn_plus.setVisibility( View.VISIBLE );
                    finalViewHolder.btn_values.setText( String.valueOf( sl_moi ) );
                }
            }
        } );

        finalViewHolder.btn_minus.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl_moi=Integer.parseInt( finalViewHolder.btn_values.getText().toString() )-1;
                int sl_hientai= MainActivity.arr_giohang.get( position ).getSoluong_sp();
                long giasp_hientai=MainActivity.arr_giohang.get( position ).getGiasanpham();
                MainActivity.arr_giohang.get( position ).setSoluong_sp( sl_moi );
                long tongtien_moi_1sp=(giasp_hientai*sl_moi)/sl_hientai;
                MainActivity.arr_giohang.get( position ).setGiasanpham( tongtien_moi_1sp );

                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                finalViewHolder.txt_giagiohang.setText( decimalFormat.format( tongtien_moi_1sp)+" Đ" );
                GioHangActivity.PutDataToView();

                if(sl_moi<2){
                    finalViewHolder.btn_plus.setVisibility( View.VISIBLE );
                    finalViewHolder.btn_minus.setVisibility( View.INVISIBLE );
                    finalViewHolder.btn_values.setText( String.valueOf( sl_moi ) );
                }else {
                    finalViewHolder.btn_minus.setVisibility( View.VISIBLE );
                    finalViewHolder.btn_plus.setVisibility( View.VISIBLE );
                    finalViewHolder.btn_values.setText( String.valueOf( sl_moi ) );
                }
            }
        } );

        return convertView;
    }
}
