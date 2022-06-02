
package com.kfoodsys.entity;

/**
 *
 * @author phuho
 */
public class DanhSachMon {
    private int IdMon;
    private String tenMon;
    private int soLuong;
    private double Gia;
    private String trangThai;
    private String ghiChu;

    public int getIdMon() {
        return IdMon;
    }

    public void setIdMon(int IdMon) {
        this.IdMon = IdMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    public DanhSachMon() {}
}
