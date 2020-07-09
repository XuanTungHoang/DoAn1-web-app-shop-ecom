package com.example.doandidong.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doandidong.R;
import com.example.doandidong.activity.Detail_sanpham;
import com.example.doandidong.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Sanpham_Adapter extends RecyclerView.Adapter<Sanpham_Adapter.ItemHolder> {

    Context context;
    ArrayList<SanPham> arr_sanpham;

    public Sanpham_Adapter(Context context, ArrayList<SanPham> arr_sanpham) {
        this.context = context;
        this.arr_sanpham = arr_sanpham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // view cho san pham
        View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.view_1_sanpham,null );
        ItemHolder itemHolder=new ItemHolder( view );
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        SanPham sanPham= arr_sanpham.get( position );
        holder.txt_tensanpham.setText( sanPham.getTensanpham());

        // format price
        DecimalFormat decimalFormat= new DecimalFormat( "###,###,###" );
        holder.txt_giasanpham.setText( "Giá: " + decimalFormat.format( sanPham.getGiasanpham() )+ " Đ" );

        // load ảnh sản phẩm từ 1 đường dẫn về
        Picasso.get().load( sanPham.getHinhanhsanpham() ).into( holder.img_hinhsanpham );



    }

    @Override
    public int getItemCount() {
        return arr_sanpham.size() ;
    }

    public class ItemHolder extends RecyclerView.ViewHolder{


        public ImageView img_hinhsanpham;
        public TextView txt_tensanpham,txt_giasanpham;

        public ItemHolder(@NonNull View itemView) {
            super( itemView );

            img_hinhsanpham= (ImageView) itemView.findViewById( R.id.imgView_sanpham );
            txt_tensanpham=(TextView) itemView.findViewById( R.id.txtView_tensanpham );
            txt_giasanpham=(TextView) itemView.findViewById( R.id.txtView_giasanpham );


            // su kien click de hien thi man hinh detail san pham
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent( context, Detail_sanpham.class );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );

                    // gui dang object
                    intent.putExtra( "thongtinsanpham",arr_sanpham.get(getLayoutPosition()) );
                    //Toast.makeText( context,arr_sanpham.get(getLayoutPosition()).getTensanpham(),Toast.LENGTH_SHORT ).show();
                    context.startActivity( intent );

                }
            } );

        }
    }
}
