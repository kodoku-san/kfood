
package com.kfoodsys.dao;

import com.kfoodsys.entity.BanAn;
import com.kfoodsys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuho
 */
public class BanAnDAO extends KFoodDAO<BanAn, Integer>{

    @Override
    public void insert(BanAn entity) {
        //dosomething 
    }

    @Override
    public void update(BanAn banAn) {
        String sql = "UPDATE Table_room SET TenTable = ?, SoChoNgoi= ?, GhiChu = ? WHERE IdTable = ?";
        XJdbc.update(sql,
                banAn.getTenBan(),
                banAn.getSoChoNgoi(),
                banAn.getGhiChu(),
                banAn.getIdBan());
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public BanAn selectById(Integer id) {
        String sql = "Select * from Table_room where IdTable = ?";
        List<BanAn> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<BanAn> selectAll() {
        String sql = "SELECT * FROM Table_room";
        return this.selectBySql(sql);
    }
    
    public List<BanAn> selectByRoom(int room) {
        String sql = "SELECT * FROM Table_room WHERE IdRoom = ?";
        return selectBySql(sql, room);
    }

    @Override
    protected List<BanAn> selectBySql(String sql, Object... args) {
        List<BanAn> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    BanAn banAn = new BanAn();
                    banAn.setTenBan(rs.getString("TenTable"));
                    banAn.setIdRoom(rs.getInt("IdRoom"));
                    banAn.setSoChoNgoi(rs.getInt("SoChoNgoi"));
                    banAn.setGhiChu(rs.getString("GhiChu"));
                    banAn.setIdBan(rs.getInt("IdTable"));
                    list.add(banAn);
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
