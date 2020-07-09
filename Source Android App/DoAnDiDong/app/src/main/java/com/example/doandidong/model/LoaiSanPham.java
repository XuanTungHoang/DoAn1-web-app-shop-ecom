package com.example.doandidong.model;

public class LoaiSanPham {
    public int id;
    public String tenSP;
    public String hinhAnhSP;

    public LoaiSanPham(int id, String tenSP, String hinhAnhSP) {
        this.id = id;
        this.tenSP = tenSP;
        this.hinhAnhSP = hinhAnhSP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHinhAnhSP() {
        return hinhAnhSP;
    }

    public void setHinhAnhSP(String hinhAnhSP) {
        this.hinhAnhSP = hinhAnhSP;
    }
}
