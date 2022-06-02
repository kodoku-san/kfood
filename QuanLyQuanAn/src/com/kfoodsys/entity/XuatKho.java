
package com.kfoodsys.entity;

/**
 *
 * @author phuho
 */
public class XuatKho {
    private int IdXuatKho;
    private String IdKho;
    private Object NgayXuat;
    private double soLuong;
    private int IdUser;

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }
    
    public int getIdXuatKho() {
        return IdXuatKho;
    }

    public void setIdXuatKho(int IdXuatKho) {
        this.IdXuatKho = IdXuatKho;
    }

    public String getIdKho() {
        return IdKho;
    }

    public void setIdKho(String IdKho) {
        this.IdKho = IdKho;
    }

    public Object getNgayXuat() {
        return NgayXuat;
    }

    public void setNgayXuat(Object NgayXuat) {
        this.NgayXuat = NgayXuat;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }
    
    public XuatKho() {}
}
