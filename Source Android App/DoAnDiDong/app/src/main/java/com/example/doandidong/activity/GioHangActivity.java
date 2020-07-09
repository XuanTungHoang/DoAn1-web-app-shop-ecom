package com.example.doandidong.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.doandidong.R;
import com.example.doandidong.adapter.GioHang_Adapter;
import com.example.doandidong.model.GioHang;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {

    ListView lsv_giohang;
    TextView txt_thongbao;
    static TextView txt_tongtien;
    Button btn_thanhtoan,btn_tieptucmua;
    Toolbar toolbar_giohang;
    GioHang_Adapter gioHang_adapter;
    static long tongtien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gio_hang );

        Anhxa();
        ActionToolBar();
        KiemTraGioHang();
        PutDataToView();
        ClickOnItemListView();
        EventButton_ThanhToan();

    }

    private void EventButton_ThanhToan() {
        btn_tieptucmua.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( getApplicationContext(),MainActivity.class );
                startActivity( intent );
            }
        } );


        btn_thanhtoan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Gio hang co sp moi chuyen man hinh thanh toan
                if(MainActivity.arr_giohang.size()>0){
                    Intent intent=new Intent(getApplicationContext(),ThongTinThanhToan_Activity.class);
                    startActivity( intent );

                }else {
                    Toast.makeText( getApplicationContext(),"Giỏ hàng của bạn đang trống !",Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

    private void ClickOnItemListView() {
        lsv_giohang.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder( GioHangActivity.this );
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage( "Bạn có muốn xóa sản phẩm ?" );
                builder.setPositiveButton( "Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.arr_giohang.size()<=0){
                            txt_thongbao.setVisibility( View.VISIBLE );
                        }else {
                            MainActivity.arr_giohang.remove( position );
                            gioHang_adapter.notifyDataSetChanged();
                            PutDataToView();

                            // neu het phan tu
                            if(MainActivity.arr_giohang.size()<=0){
                                txt_thongbao.setVisibility( View.VISIBLE );
                            }else {
                                txt_thongbao.setVisibility( View.INVISIBLE );
                                gioHang_adapter.notifyDataSetChanged();
                                PutDataToView();

                            }
                        }
                    }
                } );

                builder.setNegativeButton( "Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gioHang_adapter.notifyDataSetChanged();
                        PutDataToView();
                    }
                } );

                builder.show();

                return true;
            }
        } );
    }

    public static void PutDataToView() {
        tongtien=0;
        for(int i=0;i<MainActivity.arr_giohang.size();i++){
            tongtien+=MainActivity.arr_giohang.get( i ).getGiasanpham();
        }
        DecimalFormat decimalFormat=new DecimalFormat( "###,###,###" );
        txt_tongtien.setText( decimalFormat.format( tongtien )+" Đ" );
    }

    private void KiemTraGioHang() {
        if(MainActivity.arr_giohang.size()<=0){
            gioHang_adapter.notifyDataSetChanged();
            txt_thongbao.setVisibility( View.VISIBLE );
            lsv_giohang.setVisibility( View.INVISIBLE );
        }else {
            gioHang_adapter.notifyDataSetChanged();
            txt_thongbao.setVisibility( View.INVISIBLE );
            lsv_giohang.setVisibility( View.VISIBLE );
        }
    }

    private void ActionToolBar() {
        setSupportActionBar( toolbar_giohang );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        toolbar_giohang.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    private void Anhxa() {
        lsv_giohang=(ListView) findViewById( R.id.lsv_giohang );
        txt_thongbao=(TextView) findViewById( R.id.txtView_thongbao );
        txt_tongtien=(TextView) findViewById( R.id.txt_tongtien );
        btn_thanhtoan=(Button) findViewById( R.id.btn_thanhtoan );
        btn_tieptucmua=(Button) findViewById( R.id.btn_tieptucmua );
        toolbar_giohang=(Toolbar) findViewById( R.id.Toolbar_giohang );
        gioHang_adapter=new GioHang_Adapter( GioHangActivity.this,MainActivity.arr_giohang );
        lsv_giohang.setAdapter( gioHang_adapter );


    }
}
