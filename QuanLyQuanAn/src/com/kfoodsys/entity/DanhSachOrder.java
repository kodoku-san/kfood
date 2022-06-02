
package com.kfoodsys.entity;

/**
 *
 * @author phuho
 */
public class DanhSachOrder extends HoaDon{
    private String TenBan;
    private int SoChoNgoi;
    private String TenRoom;
    private int IdRoom;

    public int getIdRoom() {
        return IdRoom;
    }

    public void setIdRoom(int IdRoom) {
        this.IdRoom = IdRoom;
    }
    
    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String TenBan) {
        this.TenBan = TenBan;
    }

    public int getSoChoNgoi() {
        return SoChoNgoi;
    }

    public void setSoChoNgoi(int SoChoNgoi) {
        this.SoChoNgoi = SoChoNgoi;
    }

    public String getTenRoom() {
        return TenRoom;
    }

    public void setTenRoom(String TenRoom) {
        this.TenRoom = TenRoom;
    }
    
    
    
    public DanhSachOrder() {}
}
