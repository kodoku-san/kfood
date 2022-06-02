
package com.kfoodsys.entity;


/**
 *
 * @author phuho
 */
public class HoaDon {
    private int IdHoaDon;
    private int IdUser;
    private int IdTable;
    private String trangThai;
    private String ngayTaoHD;
    
    public void HoaDon() {
        
    }

    public int getIdHoaDon() {
        return IdHoaDon;
    }

    public void setIdHoaDon(int IdHoaDon) {
        this.IdHoaDon = IdHoaDon;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public int getIdTable() {
        return IdTable;
    }

    public void setIdTable(int IdTable) {
        this.IdTable = IdTable;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayTaoHD() {
        return ngayTaoHD;
    }

    public void setNgayTaoHD(String ngayTaoHD) {
        this.ngayTaoHD = ngayTaoHD;
    }
    
    
}
