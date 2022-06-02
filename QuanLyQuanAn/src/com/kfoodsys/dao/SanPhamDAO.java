package com.kfoodsys.dao;

import com.kfoodsys.entity.SanPham;
import com.kfoodsys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuho
 */
public class SanPhamDAO extends KFoodDAO<SanPham, Integer> {

    @Override
    public void insert(SanPham sanPham) {
        String sql = "INSERT INTO Product (TenSanPham, GiaBan, DonVi, Hinh, MoTa, IdCategory) VALUES (?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                sanPham.getTenSanPham(),
                sanPham.getGiaBan(),
                sanPham.getDonVi(),
                sanPham.getHinh(),
                sanPham.getMoTa(),
                sanPham.getIdDanhMuc());
    }

    @Override
    public void update(SanPham sanPham) {
        String sql = "UPDATE Product SET TenSanPham = ?, GiaBan = ?, DonVi= ?, Hinh = ?, MoTa = ?, IdCategory = ? WHERE IdProduct = ?";
        XJdbc.update(sql,
                sanPham.getTenSanPham(),
                sanPham.getGiaBan(),
                sanPham.getDonVi(),
                sanPham.getHinh(),
                sanPham.getMoTa(),
                sanPham.getIdDanhMuc(),
                sanPham.getIdSanPham());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Product WHERE IdProduct = ?";
        XJdbc.update(sql, id);
    }

    @Override
    public SanPham selectById(Integer id) {
        String sql = "SELECT * FROM Product WHERE IdProduct = ?";
        List<SanPham> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public List<SanPham> selectDanhMuc(Integer idCategory) {
        String sql = "SELECT * FROM Product WHERE IdCategory = ?";
        return this.selectBySql(sql, idCategory);
    }
    
    public List<SanPham> searchSanPham(String keyword) {
        String sql = "SELECT * FROM Product WHERE TenSanPham like ? OR MoTa like ? OR GiaBan like ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }

    @Override
    public List<SanPham> selectAll() {
        String sql = "SELECT * FROM Product";
        return this.selectBySql(sql);
    }

    @Override
    protected List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    SanPham sanPham = new SanPham();
                    sanPham.setTenSanPham(rs.getString("TenSanPham"));
                    sanPham.setGiaBan(rs.getDouble("GiaBan"));
                    sanPham.setDonVi(rs.getString("DonVi"));
                    sanPham.setHinh(rs.getString("Hinh"));
                    sanPham.setMoTa(rs.getString("MoTa"));
                    sanPham.setIdDanhMuc(rs.getInt("IdCategory"));
                    sanPham.setIdSanPham(rs.getInt("IdProduct"));
                    list.add(sanPham);
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
