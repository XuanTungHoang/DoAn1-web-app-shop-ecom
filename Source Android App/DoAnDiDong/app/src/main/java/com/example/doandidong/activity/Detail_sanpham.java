package com.example.doandidong.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.doandidong.R;
import com.example.doandidong.model.GioHang;
import com.example.doandidong.model.SanPham;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Detail_sanpham extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imgView_chitietsp;
    TextView tensanpham, giasanpham,motasanpham;
    Spinner spinner;
    Button btn_themvaogiohang;

    int id=0;
    String tenspchitiet="";
    int giaspchitiet=0;
    String hinhspchitiet="";
    String motachitietsp="";
    int id_loaisp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail_sanpham );

        Anhxa();
        ActionToolBar();
        GetThongTinSP();
        DataSpinner();
        EventClickButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.card_menu,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cart_menu:
                Intent intent=new Intent( getApplicationContext(),GioHangActivity.class );
                startActivity( intent );
        }
        return true;
    }

    private void EventClickButton() {
        btn_themvaogiohang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.arr_giohang.size()>0){
                    // neu da co trong gio-> update so luong
                    int sl=Integer.parseInt( spinner.getSelectedItem().toString() );
                     boolean exists=false;

                    for(int i=0;i< MainActivity.arr_giohang.size();i++){
                        if(MainActivity.arr_giohang.get( i ).getId_sanpham()==id){
                            MainActivity.arr_giohang.get( i ).setSoluong_sp( MainActivity.arr_giohang.get( i ).getSoluong_sp()+sl );
                            if(MainActivity.arr_giohang.get( i ).getSoluong_sp()>=10){
                                MainActivity.arr_giohang.get( i ).setSoluong_sp( 10 );
                            }
                            MainActivity.arr_giohang.get( i ).setGiasanpham( giaspchitiet*MainActivity.arr_giohang.get( i ).getSoluong_sp() );
                            exists=true;
                        }
                    }
                    if(exists==false){   // k tim thay i nao trung
                        int soluong_sp=Integer.parseInt( spinner.getSelectedItem().toString() );
                        long Giamoi=soluong_sp*giaspchitiet;
                        MainActivity.arr_giohang.add( new GioHang( id,tenspchitiet,Giamoi,hinhspchitiet,soluong_sp ) );
                    }

                }else {
                    int soluong_sp=Integer.parseInt( spinner.getSelectedItem().toString() );
                    long Giamoi=soluong_sp*giaspchitiet;
                    MainActivity.arr_giohang.add( new GioHang( id,tenspchitiet,Giamoi,hinhspchitiet,soluong_sp ) );
                }

                Intent intent= new Intent( getApplicationContext(),GioHangActivity.class );
                startActivity( intent );

            }
        } );

    }

    private void DataSpinner() {
        Integer[] soluong=new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<>( this,android.R.layout.simple_dropdown_item_1line,soluong );
        spinner.setAdapter( arrayAdapter );
    }

    private void GetThongTinSP() {


        // nhan du lieu duoi dang object
        SanPham sanPham= (SanPham) getIntent().getSerializableExtra( "thongtinsanpham" );
        id=sanPham.getId();
        tenspchitiet=sanPham.getTensanpham();
        giaspchitiet=sanPham.getGiasanpham();
        hinhspchitiet=sanPham.getHinhanhsanpham();
        motachitietsp=sanPham.getMotasanpham();
        id_loaisp=sanPham.getId_sanpham();

        tensanpham.setText( tenspchitiet );
        DecimalFormat decimalFormat=new DecimalFormat( "###,###,###" );
        giasanpham.setText( "Giá: "+decimalFormat.format( giaspchitiet )+" Đ" );
        motasanpham.setText( motachitietsp );


        //doc hinh anh
        Picasso.get().load(hinhspchitiet).into(imgView_chitietsp);

    }

    private void ActionToolBar() {
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    private void Anhxa() {
        toolbar=(Toolbar) findViewById( R.id.Toolbar_chitietsp );
        imgView_chitietsp=(ImageView) findViewById( R.id.ImgView_chitietsp );
        tensanpham= (TextView) findViewById( R.id.txtView_tenspchitiet );
        giasanpham= (TextView) findViewById( R.id.txtView_giaspchitiet );
        motasanpham= (TextView) findViewById( R.id.txtView_chitietsp );
        spinner=(Spinner) findViewById( R.id.Spinner );
        btn_themvaogiohang=(Button) findViewById( R.id.btn_themvaogiohang );


    }
}
