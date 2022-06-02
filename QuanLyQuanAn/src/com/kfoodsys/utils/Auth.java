package com.kfoodsys.utils;

import com.kfoodsys.entity.ConfigSys;
import com.kfoodsys.entity.NhanVien;

public class Auth {

    /**
     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */
    public static NhanVien user = null;
    
    public static boolean payment = false;
    public static ConfigSys cfs = new ConfigSys();
    public static String fileSys = "config\\SystemsConfig.DAT";
    
    public static void getSConfig() throws Exception {
        try {
            ObjectFile oof = new ObjectFile(fileSys);
            String datas = oof.readObject();
            String[] data = datas.split("\\|");
            if(data.length == 5) {
                cfs.setDriver(data[0].trim());
                cfs.setServer(data[1].trim());
                cfs.setDatabase(data[2].trim());
                cfs.setUsername(data[3].trim());
                cfs.setPassword(data[4].trim());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void clear() {
        Auth.user = null;
    }

    /**
     * Kiểm tra xem đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return Auth.user != null;
    }

    /**
     * Kiểm tra xem có phải là quản lý hay không
     */
    public static boolean isManager() {
        return Auth.isLogin() && user.getQuyen() == 0;
    }

    /**
     * Kiểm tra xem có phải là NV bếp hay không
     */
    public static boolean isNVBep() {
        return Auth.isLogin() && user.getQuyen() == 1;
    }

    /**
     * Kiểm tra xem có phải là NV Order hay không
     */
    public static boolean isNVOrder() {
        return Auth.isLogin() && user.getQuyen() == 2;
    }
}
