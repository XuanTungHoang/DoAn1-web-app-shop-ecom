package com.example.doandidong.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doandidong.R;
import com.example.doandidong.adapter.DienThoai_Adapter;
import com.example.doandidong.model.SanPham;
import com.example.doandidong.ultil.server;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienthoaiActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar_dt;
    ListView listView_dt;
    DienThoai_Adapter dienThoai_adapter;
    ArrayList<SanPham> mangdienthoai;

    int id_dt=0;
    int page=1;

    View footerview;
    boolean isLoading=false;
    boolean endData=false;

    myHandler myHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dienthoai );
        //System.out.println( "10000" );
        Anhxa();
        GetIdLoaiSP();
        //System.out.println( id_dt +"         abcccccccc" );
        ActionToolBar();
        GetDaTa(page);
        LoadMoreData();
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


    //Ham vuot hien thi them du lieu
    private void LoadMoreData() {
        // chuyen man hinh detail
        listView_dt.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent( getApplicationContext(),Detail_sanpham.class );
                intent.putExtra( "thongtinsanpham",mangdienthoai.get( position ) );
                startActivity( intent );
            }
        } );



        listView_dt.setOnScrollListener( new AbsListView.OnScrollListener() {

            // Vuot listview
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount==totalItemCount&& totalItemCount!=0 && isLoading==false &&endData==false){
                    // isLoading de khi vuot se hien loading va lay du lieu --> k cho vuot tiep khi dang load
                    isLoading=true;
                    ThreadData thread=new ThreadData();
                    thread.start();
                }
            }
        } );
    }

    private void GetDaTa(int Page) {

        RequestQueue requestQueue= Volley.newRequestQueue( getApplicationContext() );
        String duongdan= server.URL_dienthoai+ String.valueOf( Page );
        // tham so gom: phuong thuc, duong dan, action,bao loi
        StringRequest stringRequest=new StringRequest( Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                String tendienthoai="";
                int giadienthoai=0;
                String Hinhanhdienthoai="";
                String motadienthoai="";
                int id_loaisanpham=0;
                if(response!=null && response.length()!=2){
                    listView_dt.removeFooterView( footerview );
                    try {
                        JSONArray jsonArray=new JSONArray( response );

                        // doc tung phan tu trong mang
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject( i );
                            id=jsonObject.getInt( "id" );
                            tendienthoai=jsonObject.getString( "tensanpham" );
                            giadienthoai=jsonObject.getInt( "giasanpham" );
                            Hinhanhdienthoai=jsonObject.getString( "hinhanhsanphan" );
                            motadienthoai=jsonObject.getString( "motasanpham" );
                            id_loaisanpham=jsonObject.getInt( "id_sanpham" );
                            mangdienthoai.add( new SanPham( id,tendienthoai,giadienthoai,Hinhanhdienthoai,motadienthoai,id_loaisanpham ) );
                            dienThoai_adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                        // het du lieu de load
                    endData=true;
                    listView_dt.removeFooterView( footerview );
                    Toast.makeText( getApplicationContext(),"Đã hết sản phẩm",Toast.LENGTH_SHORT ).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // hash map gui len 1 key de webservice nhan biet
                HashMap<String,String> param = new HashMap<String,String>();
                param.put( "id_sanpham",String.valueOf( id_dt ));
                return param;
            }
        };

        //
        requestQueue.add( stringRequest );
    }

    private void ActionToolBar() {
         setSupportActionBar( toolbar_dt );
         getSupportActionBar().setDisplayHomeAsUpEnabled( true );
         toolbar_dt.setNavigationOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         } );
    }

    private void GetIdLoaiSP() {
        id_dt=getIntent().getIntExtra("id_sanpham",-1);
        //System.out.println( id_dt );
        Log.d( "Giatriloaisanpham", id_dt + "" );
    }

    private void Anhxa() {
        toolbar_dt=(Toolbar) findViewById( R.id.Toolbar_dienthoai );
        listView_dt=(ListView) findViewById( R.id.ListView_dienthoai );
        mangdienthoai=new ArrayList<>(  );
        dienThoai_adapter=new DienThoai_Adapter( getApplicationContext(),mangdienthoai );
        listView_dt.setAdapter( dienThoai_adapter );

        // View progress bar
        LayoutInflater inflater=(LayoutInflater) getSystemService( LAYOUT_INFLATER_SERVICE );
        footerview=inflater.inflate( R.layout.progressbar,null );

        myHandler= new myHandler();
    }

    public class myHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    listView_dt.addFooterView( footerview );
                    break;
                case 1:
                    GetDaTa( ++page );
                    isLoading=false;
                    break;
            }
            super.handleMessage( msg );
        }
    }

    public class ThreadData extends Thread{
        @Override
        public void run() {
            myHandler.sendEmptyMessage( 0 );
            try {
                Thread.sleep( 3000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // get message la 1 len cho Handler chay case 1
            Message message=myHandler.obtainMessage(1);
            myHandler.sendMessage( message );
            super.run();
        }
    }
}
