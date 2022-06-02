
package com.kfoodsys.entity;

/**
 *
 * @author phuho
 */
public class ChiTietBanAn extends ChiTietHoaDon {
    private String tenMon;
    private String donVi;

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }
    
    public ChiTietBanAn() {}
}
