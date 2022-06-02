
package com.kfoodsys.utils;

import com.kfoodsys.dao.NhanVienDAO;
import com.kfoodsys.entity.NhanVien;
import com.kfoodsys.ui.LoaddingJDialog;
import javax.swing.JDialog;

/**
 *
 * @author phuho
 */
public class XVerify {
    
    public static JDialog jDialog = new LoaddingJDialog(null, true);
    private static boolean QMK = false;
    private static String Mailer = null;
    private static String ExceptionMailer = "-1";
    public static NhanVien nhanVienVerify = null;
    private static NhanVienDAO daoNhanVienVerify = new NhanVienDAO();
    
    public static void VerifiedQMK() {
        QMK = true;
    }
    
    public static void NotVerifyQMK() {
        QMK = false;
        nhanVienVerify = null;
    }
    
    public static boolean GetVerifyQMK() {
        return QMK;
    }
    
    public static void WaitVerifyMailer() {
        Mailer = "-1";
    }
    
    public static void VerifiedMailer() {
        Mailer = "1";
    }
    
    public static void NotVerifyMailer(String e) {
        Mailer = "0";
        ExceptionMailer = e;
    }
    
    public static String GetVerifyMailer() {
        return Mailer;
    }
    
    public static String GetExceptionMailer() {
        return ExceptionMailer;
    }
    
    /**
     *  Xử lý các Verify
     * 
     * @param flag là các trường hợp cần thực thi xử lý. VD: flag == "QMK" thì sẽ tạo mới 
     * một Verify Code. Sau đó gửi đến mail người sử dụng, nhằm thực hiện việc check code quên mật khẩu
     * @param agrs là các phát sinh nếu có. VD: ("clearQMK", "new pass") thì sẽ 
     * xóa Verify Code, thay đổi pass người sử dụng thành tham số truyền vào...
     */
    public static void HandleVerify(String flag, Object...agrs) {
        if(flag == "QMK") {
            int code = (int) Math.floor(((Math.random() * 8999) + 1000));
            nhanVienVerify.setQuenMK(String.valueOf(code));
            daoNhanVienVerify.update(nhanVienVerify);
            String content = "Xin chào <span style='font-size: 14px; font-weight: bold'>" + nhanVienVerify.getHoTen() + "</span>,<br>";
            content += "Hướng dẫn xác nhận yêu cầu lấy lại tài khoản.<br>";
            content += "Bạn hãy nhập mã dưới đây vào ứng dụng để xác thực quyền sử dụng tài khoản này!<br>";
            content += "Mã xác nhận của bạn là: <span style='font-size: 18px; font-weight: bold'>" + code + "</span>";
            try {
                String title = "Mã xác thực tài khoản. Đổi mật khẩu KFood - Quản lý quán ăn nhanh chóng hiệu quả";
                XMail.sendMail(nhanVienVerify.getEmail(), content, title);
            } catch (Exception e) {
                MsgBox.alert(null, e.getMessage());
            }
        } else if(flag == "clearQMK" && nhanVienVerify != null) {
            nhanVienVerify.setQuenMK(null);
            if(agrs.length == 1 && QMK) {
                nhanVienVerify.setMatKhau(agrs[0].toString());
            }
            daoNhanVienVerify.update(nhanVienVerify);
            XVerify.NotVerifyQMK();
        }
    }
}
