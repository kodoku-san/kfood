
package com.kfoodsys.dao;

import com.kfoodsys.entity.KhoNguyenLieu;
import com.kfoodsys.entity.XuatKho;
import com.kfoodsys.utils.XDate;
import com.kfoodsys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author phuho
 */
public class XuatKhoDAO extends KFoodDAO<XuatKho, Integer>{

    @Override
    public void insert(XuatKho entity) {
        String sql = "INSERT INTO XUAT_KHO (IdKho, NgayXuat, SoLuong, IdUser) VALUES (?, ?, ?, ?)";
        XJdbc.update(sql,
                entity.getIdKho(),
                entity.getNgayXuat(),
                entity.getSoLuong(),
                entity.getIdUser());
    }

    @Override
    public void update(XuatKho entity) {
        String sql = "UPDATE XUAT_KHO SET IdKho = ?, NgayXuat = ?, SoLuong = ?, IdUser = ? WHERE IdXuatKho = ?";
        XJdbc.update(sql,
                entity.getIdKho(),
                entity.getNgayXuat(),
                entity.getSoLuong(),
                entity.getIdUser(),
                entity.getIdXuatKho());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM XUAT_KHO WHERE IdXuatKho = ?";
        XJdbc.update(sql, id);
    }

    @Override
    public XuatKho selectById(Integer id) {
        String sql = "SELECT * FROM XUAT_KHO WHERE IdXuatKho = ?";
        List<XuatKho> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<XuatKho> selectAll() {
        String sql = "SELECT * FROM XUAT_KHO";
        return this.selectBySql(sql);
    }

    public List<XuatKho> searchXuatKhoNL(String keyword) {
        String year = XDate.toString(new Date(), "yyyy");
        String month = XDate.toString(new Date(), "MM");
        String sql = "SELECT * FROM XUAT_KHO WHERE (IdKho like ? or IdUser like ?) and (Year(NgayXuat) = ? and Month(NgayXuat) = ?)";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", year, month);
    }

    @Override
    protected List<XuatKho> selectBySql(String sql, Object... args) {
        List<XuatKho> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    XuatKho xuatKho = new XuatKho();
                    xuatKho.setIdXuatKho(rs.getInt("IdXuatKho"));
                    xuatKho.setIdKho(rs.getString("IdKho"));
                    xuatKho.setNgayXuat(rs.getObject("NgayXuat"));
                    xuatKho.setSoLuong(rs.getDouble("SoLuong"));
                    xuatKho.setIdUser(rs.getInt("IdUser"));
                    list.add(xuatKho);
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
