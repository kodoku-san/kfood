
package com.kfoodsys.entity;

import java.util.Date;

/**
 *
 * @author phuho
 */
public class KhoNguyenLieu {
    private String IdKho;
    private String TenNL;
    private String TenDanhMuc;
    private Object ngayNhap;
    private double soLuong;
    private String donVi;

    public String getIdKho() {
        return IdKho;
    }

    public void setIdKho(String IdKho) {
        this.IdKho = IdKho;
    }

    public String getTenNL() {
        return TenNL;
    }

    public void setTenNL(String TenNL) {
        this.TenNL = TenNL;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String TenDanhMuc) {
        this.TenDanhMuc = TenDanhMuc;
    }

    public Object getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Object ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }
    
    public KhoNguyenLieu() {}
}
