
package com.kfoodsys.dao;

import com.kfoodsys.entity.ChiTietHoaDon;
import com.kfoodsys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuho
 */
public class ChiTietHDDAO extends KFoodDAO<ChiTietHoaDon, Integer>{

    @Override
    public void insert(ChiTietHoaDon chiTietHoaDon) {
        String sql = "INSERT INTO DETAIL_BILL (IdBill, IdProduct, GiaSP, SoLuong, GhiChu, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                chiTietHoaDon.getIdHoaDon(),
                chiTietHoaDon.getIdSanPham(),
                chiTietHoaDon.getGiaSP(),
                chiTietHoaDon.getSoLuong(),
                chiTietHoaDon.getGhiChu(),
                chiTietHoaDon.isTrangThai());
    }

    @Override
    public void update(ChiTietHoaDon chiTietHoaDon) {
        String sql = "UPDATE DETAIL_BILL SET IdBill = ?, IdProduct = ?, GiaSP = ?, SoLuong = ?, GhiChu = ?, TrangThai = ? WHERE IdDetailBill = ?";
        XJdbc.update(sql,
                chiTietHoaDon.getIdHoaDon(),
                chiTietHoaDon.getIdSanPham(),
                chiTietHoaDon.getGiaSP(),
                chiTietHoaDon.getSoLuong(),
                chiTietHoaDon.getGhiChu(),
                chiTietHoaDon.isTrangThai(),
                chiTietHoaDon.getIdHDChiTiet());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM DETAIL_BILL WHERE IdDetailBill=?";
        XJdbc.update(sql, id);
    }

    @Override
    public ChiTietHoaDon selectById(Integer id) {
        String sql = "SELECT * FROM DETAIL_BILL WHERE IdDetailBill=?";
        List<ChiTietHoaDon> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<ChiTietHoaDon> selectAll() {
        String sql = "SELECT * FROM DETAIL_BILL";
        return this.selectBySql(sql);
    }

    public List<ChiTietHoaDon> selectByHD(int idBill) {
        String sql = "SELECT * FROM DETAIL_BILL WHERE IdBill = ?";
        return this.selectBySql(sql, idBill);
    }

    @Override
    protected List<ChiTietHoaDon> selectBySql(String sql, Object... args) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setIdHDChiTiet(rs.getInt("IdDetailBill"));
                    chiTietHoaDon.setIdHoaDon(rs.getInt("IdBill"));
                    chiTietHoaDon.setIdSanPham(rs.getInt("IdProduct"));
                    chiTietHoaDon.setGiaSP(rs.getDouble("GiaSP"));
                    chiTietHoaDon.setSoLuong(rs.getInt("SoLuong"));
                    chiTietHoaDon.setGhiChu(rs.getString("GhiChu"));
                    chiTietHoaDon.setTrangThai(rs.getBoolean("TrangThai"));
                    list.add(chiTietHoaDon);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
}
