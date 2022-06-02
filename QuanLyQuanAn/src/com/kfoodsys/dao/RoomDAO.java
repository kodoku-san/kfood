
package com.kfoodsys.dao;

import com.kfoodsys.entity.PhongAn;
import com.kfoodsys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuho
 */
public class RoomDAO {

    public void update(String tenPhong, int IdRoom) {
        String sql = "UPDATE Room SET TenRoom = ? WHERE Idroom = ?";
        XJdbc.update(sql,
                tenPhong,
                IdRoom);
    }
    
    public PhongAn selctByID(int id) {
        String sql = "SELECT * FROM ROOM WHERE IdRoom = ?";
        List<PhongAn> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public List<PhongAn> selectAllRoom() {
        String sql = "SELECT * FROM ROOM";
        return selectBySql(sql);
    }

    protected List<PhongAn> selectBySql(String sql, Object... args) {
        List<PhongAn> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    PhongAn phongAn = new PhongAn();
                    phongAn.setIdPhongAn(rs.getInt("IdRoom"));
                    phongAn.setTongBan(rs.getInt("TotalTable"));
                    phongAn.setTenPhong(rs.getString("TenRoom"));
                    list.add(phongAn);
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
    
    public String trangThaiBan(int id) {
        String sql = "SELECT TrangThai FROM BILL WHERE IdTable = ? order by DateBill DEsc";
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, id);
                while (rs.next()) {
                    return rs.getString(1);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return "";
    }
}
