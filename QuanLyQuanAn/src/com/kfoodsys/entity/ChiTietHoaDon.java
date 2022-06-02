
package com.kfoodsys.entity;

/**
 *
 * @author phuho
 */
public class ChiTietHoaDon {
    private int IdHDChiTiet;
    private int IdHoaDon;
    private int IdSanPham;
    private double giaSP;
    private int soLuong;
    private boolean trangThai;
    private String ghiChu;
    
    public void ChiTietHoaDon() {
        
    }

    public double getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(double giaSP) {
        this.giaSP = giaSP;
    }
    
    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    public int getIdHDChiTiet() {
        return IdHDChiTiet;
    }

    public void setIdHDChiTiet(int IdHDChiTiet) {
        this.IdHDChiTiet = IdHDChiTiet;
    }

    public int getIdHoaDon() {
        return IdHoaDon;
    }

    public void setIdHoaDon(int IdHoaDon) {
        this.IdHoaDon = IdHoaDon;
    }

    public int getIdSanPham() {
        return IdSanPham;
    }

    public void setIdSanPham(int IdSanPham) {
        this.IdSanPham = IdSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
