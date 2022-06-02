package com.kfoodsys.dao;

import com.kfoodsys.utils.Auth;
import com.kfoodsys.utils.XJdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuho
 */
public class ProceduDAO {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get danh sách bàn ăn của nhân viên đang phục vụ
    public List<Object[]> getNVOrderBanAn(Integer IdUser) {
        String sql = "{CALL sp_NVorder_BanAn (?)}";
        String[] cols = {"IdTable", "IdBill", "TenTable", "SoChoNgoi", "TrangThai", "IdRoom"};
        return this.getListOfArray(sql, cols, IdUser);
    }

    // search danh sách bàn ăn của nhân viên đang phục vụ
    public List<Object[]> searchNVOrderBanAn(Integer IdUser, String keyword) {
        String sql = "{CALL sp_NVorder_BanAn_Search (?, ?)}";
        String[] cols = {"IdTable", "IdBill", "TenTable", "SoChoNgoi", "TrangThai", "IdRoom"};
        return this.getListOfArray(sql, cols, IdUser, keyword);
    }

    // nhân viên bếp get danh sách order 
    public List<Object[]> getNVBepOrder(String keyword, int flag) {
        String sql;
        if (keyword == "") {
            sql = "{CALL sp_NVBep_Order}";
            String[] cols = {"IdUser", "IdTable", "IdBill", "IdRoom", "TenTable", "SoChoNgoi", "TrangThai", "TenRoom", "DateBill"};
            return this.getListOfArray(sql, cols);
        }
        sql = "{CALL sp_NVBep_Order (?, ?)}";
        String[] cols = {"IdUser", "IdTable", "IdBill", "IdRoom", "TenTable", "SoChoNgoi", "TrangThai", "TenRoom", "DateBill"};
        return this.getListOfArray(sql, cols, keyword, flag);
    }

    // nv bếp get chi tiết bàn ăn
    public List<Object[]> getNVbepChiTiet(Integer IdRoom, String tenBan) {
        String sql = "{CALL sp_NVbep_ChiTiet (?, ?)}";
        String[] cols = {"TenSanPham", "SoLuong", "DonVi", "GhiChu", "TrangThai", "IdBill", "IdProduct", "IdDetailBill", "GiaSP"};
        return this.getListOfArray(sql, cols, IdRoom, tenBan);
    }

    // doanh thu tháng
    public List<Object[]> getDoanhThuThang(Integer month) {
        String sql = "{CALL sp_DoanhThuThang (?)}";
        String[] cols = {"TongDoanhthu"};
        return this.getListOfArray(sql, cols, month);
    }

    // tong nhan vien
    public List<Object[]> getTongNV() {
        String sql = "Select Count(*) as 'TongSo' from USERS";
        String[] cols = {"TongSo"};
        return this.getListOfArray(sql, cols);
    }

    // tong doanh thu
    public List<Object[]> getTongDT() {
        String sql = "select sum(GiaSP*SoLuong) as 'TongDoanhthu' from BILL inner join DETAIL_BILL on BILL.IdBill = DETAIL_BILL.IdBill where Bill.TrangThai = 'close'";
        String[] cols = {"TongDoanhthu"};
        return this.getListOfArray(sql, cols);
    }

    // lich su ban 
    public List<Object[]> getLichSuBan(String s_Date, String e_Date) {
        String sql = "{CALL sp_LichSuBan (?, ?)}";
        String[] cols = {"IdBill", "TenTable", "TenRoom", "TongTien", "DateBill", "HoTen"};
        return this.getListOfArray(sql, cols, s_Date, e_Date);
    }

    // doanh thu 
    public List<Object[]> getDoanhThu(String s_Date, String e_Date) {
        String sql = "{CALL sp_ThongKeDoanhThu (?, ?)}";
        String[] cols = {"TenSanPham", "BatDau", "KetThuc", "TongSanPham", "TongDoanhthu"};
        return this.getListOfArray(sql, cols, s_Date, e_Date);
    }

    // Mon Ban Chay 
    public List<Object[]> getMonBanChay(int IdCaterogy) {
        String sql = "{CALL sp_MonBanChay (?)}";
        String[] cols = {"IdProduct", "TenSanPham", "GiaBan", "DonVi", "TongBanDuoc", "TongDT"};
        return this.getListOfArray(sql, cols, IdCaterogy);
    }

    // Top nhan vien 
    public List<Object[]> getTopNhanVien(int month) {
        String sql = "{CALL sp_TopNhanVien (?)}";
        String[] cols = {"IdUser", "HoTen", "GioiTinh", "TongDoanhThu"};
        return this.getListOfArray(sql, cols, month);
    }

    // Back up database 
    public void BackupDatabase(String databaseName, String type) {
        String sql = "exec sp_BackupDatabases '" + databaseName + "', '" + type + "', ''";
        XJdbc.update(sql);
    }
}
