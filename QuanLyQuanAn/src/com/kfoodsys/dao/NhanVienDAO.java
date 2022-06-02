/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kfoodsys.dao;

import com.kfoodsys.entity.NhanVien;
import com.kfoodsys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuho
 */
public class NhanVienDAO extends KFoodDAO<NhanVien, Integer>{

    @Override
    public void insert(NhanVien nhanVien) {
        String sql = "INSERT INTO Users (HoTen, TaiKhoan, MatKhau, Email, Diachi, Hinh, GioiTinh, Quyen, QuenMK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                nhanVien.getHoTen(),
                nhanVien.getTaiKhoan(),
                nhanVien.getMatKhau(),
                nhanVien.getEmail(),
                nhanVien.getDiaChi(),
                nhanVien.getHinh(),
                nhanVien.getGioiTinh(),
                nhanVien.getQuyen(),
                nhanVien.getQuenMK());
    }

    @Override
    public void update(NhanVien nhanVien) {
        String sql = "UPDATE Users SET HoTen = ?, TaiKhoan = ?, MatKhau = ?, Email = ?, Diachi = ?, Hinh = ?, GioiTinh = ?, Quyen = ?, QuenMK = ? WHERE IdUser = ?";
        XJdbc.update(sql,
                nhanVien.getHoTen(),
                nhanVien.getTaiKhoan(),
                nhanVien.getMatKhau(),
                nhanVien.getEmail(),
                nhanVien.getDiaChi(),
                nhanVien.getHinh(),
                nhanVien.getGioiTinh(),
                nhanVien.getQuyen(),
                nhanVien.getQuenMK(),
                nhanVien.getMaNV());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Users WHERE IdUser=?";
        XJdbc.update(sql, id);
    }

    @Override
    public NhanVien selectById(Integer id) {
        String sql = "SELECT * FROM Users WHERE IdUser=?";
        List<NhanVien> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public NhanVien selectByTaiKhoan(String taiKhoan) {
        String sql = "SELECT * FROM Users WHERE TaiKhoan=?";
        List<NhanVien> list = this.selectBySql(sql, taiKhoan);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public List<NhanVien> searchNhanVien(String keyword) {
        String sql = "SELECT * FROM Users WHERE HoTen like ? OR TaiKhoan like ? OR Email like ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }

    @Override
    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM Users";
        return this.selectBySql(sql);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    NhanVien entity = new NhanVien();
                    entity.setMaNV(rs.getInt("IdUser"));
                    entity.setTaiKhoan(rs.getString("TaiKhoan"));
                    entity.setMatKhau(rs.getString("MatKhau"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setDiaChi(rs.getString("DiaChi"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setQuyenNV(rs.getInt("Quyen"));
                    entity.setQuenMK(rs.getString("QuenMK"));
                    list.add(entity);
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
