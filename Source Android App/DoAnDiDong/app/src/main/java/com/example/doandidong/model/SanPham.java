package com.example.doandidong.model;

import java.io.Serializable;

public class SanPham implements Serializable {

    public int id;
    public String tensanpham;
    public Integer giasanpham;
    public String hinhanhsanpham;
    public String motasanpham;
    public Integer id_sanpham;

    public SanPham(int id, String tensanpham, Integer giasanpham, String hinhanhsanpham, String motasanpham, Integer id_sanpham) {
        this.id = id;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.hinhanhsanpham = hinhanhsanpham;
        this.motasanpham = motasanpham;
        this.id_sanpham = id_sanpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public Integer getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(Integer giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public String getMotasanpham() {
        return motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        this.motasanpham = motasanpham;
    }

    public Integer getId_sanpham() {
        return id_sanpham;
    }

    public void setId_sanpham(Integer id_sanpham) {
        this.id_sanpham = id_sanpham;
    }
}
