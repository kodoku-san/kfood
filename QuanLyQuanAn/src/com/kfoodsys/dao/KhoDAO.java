package com.kfoodsys.dao;

import com.kfoodsys.entity.KhoNguyenLieu;
import com.kfoodsys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuho
 */
public class KhoDAO extends KFoodDAO<KhoNguyenLieu, String> {

    @Override
    public void insert(KhoNguyenLieu khoNguyenLieu) {
        String sql = "INSERT INTO KHO (IdKho, TenNguyenLieu, TenDanhMuc, NgayNhap, SoLuong, DonVi) VALUES (?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                khoNguyenLieu.getIdKho(),
                khoNguyenLieu.getTenNL(),
                khoNguyenLieu.getTenDanhMuc(),
                khoNguyenLieu.getNgayNhap(),
                khoNguyenLieu.getSoLuong(),
                khoNguyenLieu.getDonVi());
    }

    @Override
    public void update(KhoNguyenLieu khoNguyenLieu) {
        String sql = "UPDATE KHO SET TenNguyenLieu = ?, TenDanhMuc = ?, NgayNhap = ?, SoLuong = ?, DonVi = ? WHERE IdKho = ?";
        XJdbc.update(sql,
                khoNguyenLieu.getTenNL(),
                khoNguyenLieu.getTenDanhMuc(),
                khoNguyenLieu.getNgayNhap(),
                khoNguyenLieu.getSoLuong(),
                khoNguyenLieu.getDonVi(),
                khoNguyenLieu.getIdKho());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM KHO WHERE IdKho = ?";
        XJdbc.update(sql, id);
    }

    @Override
    public KhoNguyenLieu selectById(String id) {
        String sql = "SELECT * FROM KHO WHERE IdKho = ?";
        List<KhoNguyenLieu> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<KhoNguyenLieu> selectAll() {
        String sql = "SELECT * FROM Kho";
        return this.selectBySql(sql);
    }

    public List<KhoNguyenLieu> searchKhoNguyenLieu(String keyword) {
        String sql = "SELECT * FROM Kho WHERE IdKho like ? or TenNguyenLieu like ? or TenDanhMuc like ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }

    public List<KhoNguyenLieu> selectDanhMucNL(String danhMuc) {
        String sql = "SELECT * FROM Kho WHERE TenDanhMuc = ?";
        return this.selectBySql(sql, danhMuc);
    }

    @Override
    protected List<KhoNguyenLieu> selectBySql(String sql, Object... args) {
        List<KhoNguyenLieu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    KhoNguyenLieu khoNguyenLieu = new KhoNguyenLieu();
                    khoNguyenLieu.setIdKho(rs.getString("IdKho"));
                    khoNguyenLieu.setTenNL(rs.getString("TenNguyenLieu"));
                    khoNguyenLieu.setTenDanhMuc(rs.getString("TenDanhMuc"));
                    khoNguyenLieu.setNgayNhap(rs.getObject("NgayNhap"));
                    khoNguyenLieu.setSoLuong(rs.getDouble("SoLuong"));
                    khoNguyenLieu.setDonVi(rs.getString("DonVi"));
                    list.add(khoNguyenLieu);
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
