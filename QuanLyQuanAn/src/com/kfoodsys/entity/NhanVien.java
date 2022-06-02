/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kfoodsys.entity;

/**
 *
 * @author phuho
 */
public class NhanVien {
    private int maNV;
    private String hoTen;
    private String taiKhoan;
    private String matKhau;
    private String Email;
    private String diaChi;
    private String Hinh;
    private boolean gioiTinh;
    private int quyenNV;
    private String QuenMK;
    
    public NhanVien() {
        
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }
    
    public String getQuenMK() {
        return QuenMK;
    }

    public void setQuenMK(String QuenMK) {
        this.QuenMK = QuenMK;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getQuyen() {
        return quyenNV;
    }

    public void setQuyenNV(int quyenNV) {
        this.quyenNV = quyenNV;
    }
    
    
}
