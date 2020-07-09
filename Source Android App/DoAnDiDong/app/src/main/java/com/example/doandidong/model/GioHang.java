package com.example.doandidong.model;

public class GioHang {

    public int id_sanpham;
    public String tensanpham;
    public long giasanpham;
    public String hinhanhsanpham;
    public int soluong_sp;

    public GioHang(int id_sanpham, String tensanpham, long giasanpham, String hinhanhsanpham, int soluong_sp) {
        this.id_sanpham = id_sanpham;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.hinhanhsanpham = hinhanhsanpham;
        this.soluong_sp = soluong_sp;
    }

    public int getId_sanpham() {
        return id_sanpham;
    }

    public void setId_sanpham(int id_sanpham) {
        this.id_sanpham = id_sanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public long getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(long giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public int getSoluong_sp() {
        return soluong_sp;
    }

    public void setSoluong_sp(int soluong_sp) {
        this.soluong_sp = soluong_sp;
    }
}
