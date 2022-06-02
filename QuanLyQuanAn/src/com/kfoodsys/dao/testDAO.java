/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kfoodsys.dao;

import com.barcodelib.barcode.Linear;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.kfoodsys.entity.BanAn;
import com.kfoodsys.entity.ChiTietHoaDon;
import com.kfoodsys.entity.HoaDon;
import com.kfoodsys.entity.PhongAn;
import com.kfoodsys.entity.SanPham;
import com.kfoodsys.utils.XDate;
import com.kfoodsys.utils.XGenCode;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.kfoodsys.entity.ConfigSys;
import com.kfoodsys.utils.ObjectFile;
import java.io.File;
import java.security.CodeSource;

/**
 *
 * @author phuho
 */
public class testDAO {
    public static String setContentHtml(String content) {
        String template =   
                "<div style=\"background-color: #e37c4117; padding: 15px;font-family: alata,sans-serif;\">\n" +
                "    <div style=\"margin: auto; background-color: #ffffff; max-width: 500px; padding: 10px; border-top: 6px solid #e37c41; border-radius: 5px;\">\n" +
                "        <div>\n" +
                "            <div style=\"width: 100%;text-align: center;\">\n" +
                "                <img src=\"cid:image-logo\" width=\"120\" height= \"120\" alt=\"logo\"/>\n" +
                "                <span style=\"display: block; font-size: 20px; font-weight: bold; color: #ff0561; text-align: center; border: 3px solid; border-radius: 10px;\">KFOOD</span>\n" +
                "            </div>\n" +
                "        </div><br>\n" +
                "        <p style=\"line-height: 22px;\">\n" +
                            content +
                "            \n" +
                "        </p><br>\n" +
                "        <hr style=\"border-top: 1px solid;\" />\n" +
                "        <div style=\"font-style: italic;\">\n" +
                "            <span>Đây là mail tự động của hệ thống. Vui lòng không phản hồi mail này!</span>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>";
        
        return template;
    }
    public static void main(String[] args) {
//        try {
//            ObjectFile oof = new ObjectFile("SystemConfig.DAT");
//            ConfigSys config = new ConfigSys("com.microsoft.sqlserver.jdbc.SQLServerDriver", "localhost", "DA1_QLQA", "sa", "123456");
//            Object[] objects = {config};
//            oof.writeObject(objects);
//        } catch (Exception e) {
//            System.out.println(e.getMessage() + 2);
//        }
        
        
    
//        ProceduDAO proceduDAO = new ProceduDAO();
//        proceduDAO.BackupDatabase("DA1_QLQA", "F");
//        System.out.println(System.getProperty("user.dir"));
        try {
//            XGenCode.createBarCode("160920021");
        } catch (Exception e) {
        }
        System.exit(0);
        System.out.println((3>=3 && 3<=3));
        try {
            XGenCode.createBarCode("160027");
        } catch (Exception e) {
        }
        
        

        
        
        String a = "khonguyenlieuKFood16092002-Thit Bo-Thit Bo--Kg-1";
        if(a.contains("khonguyenlieuKFood16092002-")) {
            System.out.println("Day la ma hoa kho nguyen lieu");
        }
        try {
            
            InputStream barcodeInputStream = new FileInputStream("E:\\phudeptrai.png");
            BufferedImage barcBufferedImage = ImageIO.read(barcodeInputStream);
            
            LuminanceSource source = new BufferedImageLuminanceSource(barcBufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new MultiFormatReader();
            Result reslut = reader.decode(bitmap);
            
            System.out.println(reslut.getText());
            
            
            
            
        } catch (Exception e) {
        }
        LocalDate lastDayOfMonth = LocalDate.parse("2021-02-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"))
               .with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDayOfMonth);
        String content = "Xin chào <span style='font-size: 14px; font-weight: bold'>Hồ Hoàng Phú</span>,<br>";
        content += "Hướng dẫn xác nhận yêu cầu lấy lại tài khoản.<br>";
        content += "Bạn hãy nhập mã dưới đây vào ứng dụng để xác thực quyền sử dụng tài khoản này!<br>";
        content += "Mã xác nhận của bạn là: <span style='font-size: 18px; font-weight: bold'>1609</span>";
        System.out.println(setContentHtml(content));
        System.exit(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(169000);
        
        
        System.out.println(str1);
        
        String newString = str1.replace(" ₫", "").replace(".", "");
        double gia = Double.parseDouble(newString);
        System.out.println(gia);
        
        System.exit(0);
//        SanPhamDAO dao = new SanPhamDAO();
        
//        List<SanPham> list = dao.selectAll();
//        for (SanPham sanPham : list) {
//            System.out.println(sanPham.getTenSanPham());
//        }
//        SanPham sanPham1 = new SanPham();
//        sanPham1.setTenSanPham("Test DAO san pham");
//        sanPham1.setDonVi("Test DAO san pham");
//        sanPham1.setHinh("Test DAO san pham");
//        sanPham1.setGiaBan(169000);
//        sanPham1.setMoTa("Test DAO san pham");
//        sanPham1.setIdDanhMuc(1);
//        
////        dao.insert(sanPham1);
////        sanPham1 = dao.selectById(23);
////        sanPham1.setTenSanPham("Test DAO Update");
////        sanPham1.setGiaBan(0);
////        dao.update(sanPham1);
//        BanAnDAO dao = new BanAnDAO();
//        List<PhongAn> list = new RoomDAO().selectAllRoom();
//        for(PhongAn phongAn: list) {
//            System.out.println(phongAn.getTenPhong());
//            System.out.println(phongAn.getTongBan());
//        }

//        RoomDAO dao = new RoomDAO();
//        System.out.println(dao.trangThaiBan(27));
        HoaDonDAO dao = new HoaDonDAO();
        HoaDon hoaDon = dao.selectBillNew(5, 27);
        System.out.println(hoaDon.getIdHoaDon());
        for(HoaDon hoaDon1 : dao.selectAll()) {
            System.out.println(hoaDon1.getNgayTaoHD());
        }
              
//        ChiTietHDDAO dao = new ChiTietHDDAO();
////        for(ChiTietHoaDon chiTietHoaDon : dao.selectAll()) {
////            System.out.println(chiTietHoaDon.getIdSanPham());
////        }
//        for(ChiTietHoaDon chiTietHoaDon : dao.selectByHD(1)) {
//            System.out.println(chiTietHoaDon.getIdSanPham());
//        }
//        System.out.println(XDate.toString(XDate.addDays(new Date(), 0), "hh:mm dd/mm/yyyy"));
//        System.out.println(XDate.toString(new Date(), "hh:mm dd/mm/yyyy"));
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        System.out.println(sdf.format(new Date()));
//        ProceduDAO dao = new ProceduDAO();
//        List<Object[]> list = dao.getNVOrderBanAn(5);
//        for (Object[] row : list) {
//            System.out.println(row[0]);
//        }
    }
}
