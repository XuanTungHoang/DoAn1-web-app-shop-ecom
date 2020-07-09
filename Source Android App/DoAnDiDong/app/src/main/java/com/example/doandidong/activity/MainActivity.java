package com.example.doandidong.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
//import android.support.v7.widget.Toolbar;
//import android.widget.Toolbar;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doandidong.R;
import com.example.doandidong.adapter.LoaiSP_Adapter;
import com.example.doandidong.adapter.Sanpham_Adapter;
import com.example.doandidong.model.GioHang;
import com.example.doandidong.model.LoaiSanPham;
import com.example.doandidong.model.SanPham;
import com.example.doandidong.ultil.server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView_main;
    NavigationView navigationView;
    ListView listView_main;
    DrawerLayout drawerLayout;

    ArrayList<LoaiSanPham> mangLoaiSP;
    LoaiSP_Adapter loaiSP_adapter;

    ArrayList<SanPham> mangSanPham;
    Sanpham_Adapter sanpham_adapter;

    // Gio hang da duoc luu se van con du lieu khi quay ve main mua hang tiep
    // Tao mang ngoai toan cuc co the truy cap den tat ca acti khac
    public static ArrayList<GioHang>  arr_giohang;



    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Anhxa();
        Action_Bar();
        Action_ViewFlipper();
        GetDuLieuLoaiSP();
        GetDuLieuSanPhamMoiNhat();
        GetKindItemOnListView();
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

    private void GetKindItemOnListView() {
        listView_main.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent=new Intent( MainActivity.this,MainActivity.class );
                        startActivity( intent );
                        drawerLayout.closeDrawer( GravityCompat.START );
                        break;
                    case 1:
                        Intent intent_dienthoai=new Intent( MainActivity.this,DienthoaiActivity.class );
                        intent_dienthoai.putExtra( "id_sanpham",mangLoaiSP.get( position ).getId() );
                        drawerLayout.closeDrawer( GravityCompat.START );
                        startActivity( intent_dienthoai );
                        //System.out.println( mangLoaiSP.get( position ).getId() );
                        break;
                    case 2:
                        Intent intent_laptop=new Intent( MainActivity.this,LaptopActivity.class );
                        intent_laptop.putExtra( "id_sanpham",mangLoaiSP.get( position ).getId() );
                        drawerLayout.closeDrawer( GravityCompat.START );
                        startActivity( intent_laptop );
                        break;
                    case 3:
                        Intent intent_lienhe=new Intent( MainActivity.this,LienheActivity.class );
                        startActivity( intent_lienhe );
                        drawerLayout.closeDrawer( GravityCompat.START );
                        break;
                    case 4:
                        Intent intent_thongtin=new Intent( MainActivity.this,ThongtinActivity.class );
                        startActivity( intent_thongtin );
                        drawerLayout.closeDrawer( GravityCompat.START );
                        break;
                }
            }
        } );

    }

    private void GetDuLieuSanPhamMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue( getApplicationContext() );
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest( server.URL_spmoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    int id=0;
                    String tensanpham="";
                    Integer giasanpham=0;
                    String hinhanhsanpham="";
                    String motasanpham="";
                    int id_sanpham=0;
                    System.out.println( response );
                    // for tung san pham tra ve tu URL
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject=response.getJSONObject( i );

                            id=jsonObject.getInt( "id" );
                            tensanpham=jsonObject.getString( "tensanpham" );
                            giasanpham=jsonObject.getInt( "giasanpham" );
                            hinhanhsanpham=jsonObject.getString( "hinhanhsanphan" );
                            motasanpham=jsonObject.getString( "motasanpham" );
                            id_sanpham=jsonObject.getInt( "id_sanpham" );

                            // add san pham vao mang
                            mangSanPham.add( new SanPham( id,tensanpham,giasanpham,hinhanhsanpham,motasanpham,id_sanpham ) );

                            // xac nhan-cap nhat thay doi
                            sanpham_adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );

        // xac nhan request
        requestQueue.add( jsonArrayRequest );

    }

    private void GetDuLieuLoaiSP() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest( server.URL_loaiSP, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject=response.getJSONObject(i);
                            id=jsonObject.getInt( "id" );
                            tenloaisp=jsonObject.getString( "tenloaisanpham" );
                            hinhanhloaisp=jsonObject.getString( "hinhanhloaisanpham");
                            mangLoaiSP.add( new LoaiSanPham( id,tenloaisp,hinhanhloaisp ) );
                            loaiSP_adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    mangLoaiSP.add( 3,new LoaiSanPham( 0,"Liên hệ","https://cdn0.iconfinder.com/data/icons/constructivism-for-the-bank/64/constr_call_to_bank-128.png" ) );
                    mangLoaiSP.add( 4,new LoaiSanPham( 0,"Thông tin","https://cdn2.iconfinder.com/data/icons/circle-icons-1/64/news-128.png" ) );

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        requestQueue.add( jsonArrayRequest );
    }

    private void Action_ViewFlipper() {
        ArrayList<String> slider_image=new ArrayList<>(  );

        // Link image
        slider_image.add("https://i.ytimg.com/vi/6tt8P9igEGo/maxresdefault.jpg");
        slider_image.add("https://static2.yan.vn/YanNews/2167221/201904/do-khi-chat-cua-blackpink-trong-anh-chup-quang-cao-dien-thoai-08869e23.jpg");
        slider_image.add("https://cdn.tgdd.vn/Files/2018/12/05/1135864/iphone-vs-samsung_800x450.jpg");

        for(int i=0;i<slider_image.size();i++){
            ImageView imageView =new ImageView( getApplicationContext() );
            Picasso.get().load( slider_image.get(i) ).into( imageView );
            imageView.setScaleType( ImageView.ScaleType.FIT_XY );
            viewFlipper.addView( imageView );
        }
        viewFlipper.setFlipInterval( 5000 );
        viewFlipper.setAutoStart( true );
        Animation animation_in= AnimationUtils.loadAnimation( getApplicationContext(),R.anim.slide_in_right );
        Animation animation_out= AnimationUtils.loadAnimation( getApplicationContext(),R.anim.slide_out_right );
        viewFlipper.setInAnimation( animation_in );
        viewFlipper.setOutAnimation( animation_out );


    }

    private void Action_Bar() {
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer( GravityCompat.START );
            }
        } );
    }

    private void Anhxa() {
        toolbar= findViewById( R.id.Toolbar_main );
        viewFlipper= findViewById( R.id.ViewFlipper_main );
        recyclerView_main= findViewById( R.id.RecyclerView_main );
        navigationView= findViewById( R.id.Navigation_main );
        listView_main= findViewById( R.id.ListView_main );
        drawerLayout= findViewById( R.id.DrawerLayout_main );

        // Gan du lieu cho danh sach loai san pham
        mangLoaiSP=new ArrayList<>(  );
        mangLoaiSP.add( 0,new LoaiSanPham( 0,"Trang chủ","https://cdn3.iconfinder.com/data/icons/tango-icon-library/48/go-home-128.png"));
        loaiSP_adapter=new LoaiSP_Adapter(mangLoaiSP,getApplicationContext());
        listView_main.setAdapter( loaiSP_adapter );

        // Gan du lieu cho danh sach san pham
        mangSanPham=new ArrayList<>(  );
        sanpham_adapter=new Sanpham_Adapter( getApplicationContext(),mangSanPham );

        // san pham nam tren 2 cot recycler view
        recyclerView_main.setHasFixedSize( true );
        recyclerView_main.setLayoutManager( new GridLayoutManager( getApplicationContext(),2 ) );
        recyclerView_main.setAdapter( sanpham_adapter );

        // mang gio hang
        // Neu gio hang da co du lieu thi luu lai
        // Nguoc lai tao mang gio hang va cap phat vung nho
        if(arr_giohang!=null){

        }else {
            arr_giohang=new ArrayList<>(  );
        }
    }
}
