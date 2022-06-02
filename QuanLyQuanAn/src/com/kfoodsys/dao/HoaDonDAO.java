
package com.kfoodsys.dao;

import com.kfoodsys.entity.HoaDon;
import com.kfoodsys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuho
 */
public class HoaDonDAO extends KFoodDAO<HoaDon, Integer>{

    @Override
    public void insert(HoaDon hoaDon) {
        String sql = "INSERT INTO Bill (IdUser, IdTable, TrangThai, DateBill) VALUES (?, ?, ?, ?)";
        XJdbc.update(sql,
                hoaDon.getIdUser(),
                hoaDon.getIdTable(),
                hoaDon.getTrangThai(),
                hoaDon.getNgayTaoHD());
    }

    @Override
    public void update(HoaDon hoaDon) {
        String sql = "UPDATE Bill SET IdUser = ?, IdTable = ?, TrangThai = ? WHERE IdBill = ?";
        XJdbc.update(sql,
                hoaDon.getIdUser(),
                hoaDon.getIdTable(),
                hoaDon.getTrangThai(),
                hoaDon.getIdHoaDon());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Bill WHERE IdBill=?";
        XJdbc.update(sql, id);
    }

    @Override
    public HoaDon selectById(Integer id) {
        String sql = "SELECT * FROM Bill WHERE IdBill=?";
        List<HoaDon> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public HoaDon selectBillNew(Integer idUser, Integer idTable) {
        String sql = "SELECT * FROM Bill WHERE IdUser=? and IdTable=? and TrangThai not like '%close%' and TrangThai not like '%update%'";
        List<HoaDon> list = this.selectBySql(sql, idUser, idTable);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<HoaDon> selectAll() {
        String sql = "SELECT * FROM Bill";
        return this.selectBySql(sql);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setIdTable(rs.getInt("IdTable"));
                    hoaDon.setIdHoaDon(rs.getInt("IdBill"));
                    hoaDon.setIdUser(rs.getInt("IdUser"));
                    hoaDon.setTrangThai(rs.getString("TrangThai"));
                    hoaDon.setNgayTaoHD(rs.getString("DateBill"));
                    list.add(hoaDon);
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
