package com.example.doandidong.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doandidong.R;
import com.example.doandidong.ultil.server;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.doandidong.activity.GioHangActivity.tongtien;
import static com.example.doandidong.activity.GioHangActivity.txt_tongtien;


public class ThongTinThanhToan_Activity extends AppCompatActivity {

    EditText edt_tenkhachhang,edt_sdt,edt_diachi;
    Button btn_xacnhan,btn_quaylai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_thong_tin_thanh_toan_ );

        Anhxa();

        btn_quaylai.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        EventButton();
    }

    private void EventButton() {
        btn_xacnhan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten=edt_tenkhachhang.getText().toString().trim();
                final String sdt=edt_sdt.getText().toString().trim();
                final String diachi=edt_diachi.getText().toString().trim();
                final String sum= tongtien+"";
                //tongtien.tostring();
               // final double sum=Double.parseDouble( tmp_sum );
                if(ten.length()>0&&sdt.length()>0&&diachi.length()>0){
                    RequestQueue requestQueue= Volley.newRequestQueue( getApplicationContext() );
                    StringRequest stringRequest= new StringRequest( Request.Method.POST, server.URL_donhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang",madonhang);
                            if(Integer.parseInt( madonhang )>0){
                                RequestQueue requestQueue1=Volley.newRequestQueue( getApplicationContext() );
                                StringRequest stringRequest1=new StringRequest( Request.Method.POST, server.URL_chitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // insert chi tiet don hang thanh cong
                                        if(response.length()>2){
                                            MainActivity.arr_giohang.clear();
                                            //Toast.makeText( getApplicationContext(),"Thêm dữ liệu thành công !",Toast.LENGTH_SHORT ).show();
                                            Intent intent=new Intent( getApplicationContext(),MainActivity.class );
                                            startActivity( intent );
                                            Toast.makeText( getApplicationContext(),"Đặt hàng thành công !",Toast.LENGTH_SHORT ).show();
                                        }else {
                                            Toast.makeText( getApplicationContext(),"Dữ liệu của bạn bị lỗi !",Toast.LENGTH_SHORT ).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                } ){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray=new JSONArray(  );
                                        for(int i=0;i<MainActivity.arr_giohang.size();i++){
                                            JSONObject jsonObject=new JSONObject(  );
                                            try {
                                                jsonObject.put( "madonhang",Integer.parseInt( madonhang ) );
                                                jsonObject.put( "masanpham",MainActivity.arr_giohang.get( i ).getId_sanpham() );
                                                jsonObject.put( "tensanpham",MainActivity.arr_giohang.get( i ).getTensanpham() );
                                                jsonObject.put( "giasanpham",MainActivity.arr_giohang.get( i ).getGiasanpham() );
                                                jsonObject.put( "soluongsanpham",MainActivity.arr_giohang.get( i ).getSoluong_sp() );
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            // put cac phan tu trong gio hang vao Mang Json
                                            jsonArray.put( jsonObject );
                                        }

                                        HashMap<String,String> hashMap1= new HashMap<String,String>(  );
                                        hashMap1.put( "json",jsonArray.toString(  ) );
                                        System.out.println( hashMap1 );
                                        return hashMap1;
                                    }
                                };

                                requestQueue1.add( stringRequest1 );
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    } ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String,String>(  );
                            hashMap.put( "tenkhachhang",ten );
                            hashMap.put( "sodienthoai",sdt );
                            hashMap.put( "diachi",diachi );
                            hashMap.put( "tongtien",sum );
                            System.out.println( hashMap );
                            return hashMap;
                        }
                    };

                    requestQueue.add(stringRequest);

                }else {
                    Toast.makeText( getApplicationContext(),"Kiểm tra lại thông tin !",Toast.LENGTH_SHORT ).show();
                }
            }
        } );

    }

    private void Anhxa() {
        edt_tenkhachhang=(EditText) findViewById( R.id.editText_tenkhachhang );
        edt_sdt=(EditText) findViewById( R.id.editText_sdtkhachhang );
        edt_diachi=(EditText) findViewById( R.id.editText_diachikhachang );
        btn_xacnhan=(Button) findViewById( R.id.btn_xacnhanmua );
        btn_quaylai=(Button) findViewById( R.id.btn_quaylaigiohang );
    }
}
