package com.example.doandidong.ultil;

public class server {
    public static String localhost="172.17.27.194:85";
    public static String URL_loaiSP="http://"+localhost+"/get_loaisanpham.php";
    public static String URL_spmoinhat="http://"+localhost+"/getspmoinhat.php";
    public static String URL_dienthoai="http://"+localhost+"/getsanpham.php?page="; // get san pham dua tren id_loaisp
    public static String URL_donhang="http://"+localhost+"/thongtinkhachhang.php"; // post thong tin khach hang
    public static String URL_chitietdonhang="http://"+localhost+"/chitietdonhang.php"; // post json len sever
}

