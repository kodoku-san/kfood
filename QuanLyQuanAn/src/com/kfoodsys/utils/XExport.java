package com.kfoodsys.utils;

import com.kfoodsys.entity.ChiTietBanAn;
import com.kfoodsys.entity.DanhSachMon;
import com.kfoodsys.entity.XuatKho;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author phuho
 */
public class XExport {
    
    private static Locale localeVN = new Locale("vi", "VN");
    private static NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    private static String year = XDate.toString(new Date(), "yyyy");
    private static String month = XDate.toString(new Date(), "MM");
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    private static Cell cell;
    private static XSSFCellStyle style;
    private static XSSFCellStyle styleBorder;
    private static XSSFFont font;

    public static void billToExcel(String fileName, ArrayList<DanhSachMon> list, String nameNV, String tongTien, String tienKhachDua, String tienTraLai, int IDBill) throws Exception {
        try {    
            newExcel();
            int ml = 2;
            row = sheet.createRow(2);
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("Chi Tiết Hóa Đơn - KFood");
            cell.setCellStyle(style);
            
            row = sheet.createRow(4);
            sheet.addMergedRegion(new CellRangeAddress(4, 4, 0 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("____________________________________________");
            
            row = sheet.createRow(6);
            sheet.addMergedRegion(new CellRangeAddress(6, 6, 0 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("Mã số: HDKF" + IDBill);
            
            row = sheet.createRow(7);
            sheet.addMergedRegion(new CellRangeAddress(7, 7, 0 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("Ngày thanh toán: " + XDate.toString(new Date(), "HH:mm:ss dd/MM/yyyy"));
            
            row = sheet.createRow(8);
            sheet.addMergedRegion(new CellRangeAddress(8, 8, 0 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("Nhân viên: " + nameNV);
            
            row = sheet.createRow(9);
            sheet.addMergedRegion(new CellRangeAddress(9, 9, 0 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("____________________________________________");
            
            row = sheet.createRow(10);
            String[] topRows = {"SL", "Giá Bán", "Thành tiền"};
            int[] listCell = {0 + ml, 1 + ml, 3 + ml};
            int[] listMrg = {0 + ml, 2 + ml, 4 + ml};
            setDateToExcel(topRows, listCell, listMrg, 10, 0);
            
            int i = 0;
            int r = 0;
            for(DanhSachMon danhSachMon : list) {
                r = i + 14;
                row = sheet.createRow(r);
                XSSFRow rowName = sheet.createRow(r - 1);
                sheet.addMergedRegion(new CellRangeAddress(r - 1, r - 1, 0 + ml, 4 + ml));

                cell = rowName.createCell(0 + ml, CellType.STRING);
                cell.setCellValue(danhSachMon.getTenMon());
                Object[] listData = {
                    "(" + danhSachMon.getSoLuong() + ")",
                    currencyVN.format(danhSachMon.getGia()),
                    currencyVN.format(danhSachMon.getGia() * danhSachMon.getSoLuong())
                };
                setDateToExcel(listData, listCell, listMrg, r, 1);
                i = i + 4;
            }
            r = r + 1;
            row = sheet.createRow(++r);
            sheet.addMergedRegion(new CellRangeAddress(r, r, 0 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("____________________________________________");
            r = r + 1;
            row = sheet.createRow(++r);
            sheet.addMergedRegion(new CellRangeAddress(r, r + 1, 0 + ml, 1 + ml));
            sheet.addMergedRegion(new CellRangeAddress(r, r + 1, 3 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("Tổng tiền              :");
            cell = row.createCell(3 + ml, CellType.STRING);
            cell.setCellValue(tongTien);
            r = r + 1;
            row = sheet.createRow(++r);
            sheet.addMergedRegion(new CellRangeAddress(r, r + 1, 0 + ml, 1 + ml));
            sheet.addMergedRegion(new CellRangeAddress(r, r + 1, 3 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("Tiền khách đưa    :");
            cell = row.createCell(3 + ml, CellType.STRING);
            cell.setCellValue(tienKhachDua);
            r = r + 1;
            row = sheet.createRow(++r);
            sheet.addMergedRegion(new CellRangeAddress(r, r + 1, 0 + ml, 1 + ml));
            sheet.addMergedRegion(new CellRangeAddress(r, r + 1, 3 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("Tiền thừa              :");
            cell = row.createCell(3 + ml, CellType.STRING);
            cell.setCellValue(tienTraLai);
            r = r + 1;
            row = sheet.createRow(++r);
            sheet.addMergedRegion(new CellRangeAddress(r, r, 0 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("____________________________________________");
            
            row = sheet.createRow(r + 6);
            sheet.addMergedRegion(new CellRangeAddress(r + 6, r + 6, 0 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("____________________________________________");
            row = sheet.createRow(r + 8);
            sheet.addMergedRegion(new CellRangeAddress(r + 8, r + 8, 0 + ml, 4 + ml));
            cell = row.createCell(0 + ml, CellType.STRING);
            cell.setCellValue("Cảm ơn và hẹn gặp lại quý khách");
            cell.setCellStyle(style);
            
            String data = "169" + String.format("%06d", IDBill);
            XGenCode.createBarCode(data);
            InputStream my_banner_image = new FileInputStream("BarCode\\Bill-" + data + ".png");
            byte[] bytes = IOUtils.toByteArray(my_banner_image);
            int my_picture_id = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            my_banner_image.close();
            XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
            XSSFClientAnchor my_anchor = new XSSFClientAnchor();
            int rowNum = r + 2;
            my_anchor.setCol1(2); 
            my_anchor.setRow1(rowNum); 
            my_anchor.setCol2(7);
            my_anchor.setRow2(rowNum + 4);
            XSSFPicture my_picture = drawing.createPicture(my_anchor, my_picture_id);
            
            CellRangeAddress region = new CellRangeAddress(1, r + 9, ml, 4 + ml);
            RegionUtil.setBorderTop(BorderStyle.THICK, region, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THICK, region, sheet);
            RegionUtil.setBorderBottom(BorderStyle.THICK, region, sheet);
            RegionUtil.setBorderRight(BorderStyle.THICK, region, sheet);

            writeFile("bill\\" + fileName, workbook);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void historyToExcel(List<Object[]> list, String s_Date, String e_Date) throws Exception {
        String fileName = "historyKFood-" + XDate.toString(new Date(), "HHmmssddMMyyyy") + ".xlsx";
        try {    
            newExcel();
            row = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 5));
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Lịch sử bán - KFood");
            cell.setCellStyle(style);
            
            row = sheet.createRow(2);
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 5));
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Từ ngày: " + s_Date + "    Đến ngày: " + e_Date);
            
            row = sheet.createRow(4);
            String[] topRows = {"IdBill", "Tên bàn", "Tên phòng", "Tổng tiền", "Thời gian", "Nhân viên"};
            int[] listCell = {0, 1, 3, 5, 7, 10};
            int[] listMrg = {0, 2, 4, 6, 9, 11};
            setDateToExcel(topRows, listCell, listMrg, 4, 0);
            
            int i = 0;
            int r = 0;
            for(Object[] rObjects: list) {
                r = i + 6;
                row = sheet.createRow(r);
                Object[] listData = {
                    rObjects[0], 
                    rObjects[1], 
                    rObjects[2], 
                    rObjects[3], 
                    rObjects[4],
                    rObjects[5]
                };
                setDateToExcel(listData, listCell, listMrg, r, 1);
                i = i + 2;
            }

            writeFile("history\\" + fileName, workbook);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void khoToExcel(List<XuatKho> list) throws Exception {
        String fileName = "xuatKho-" + XDate.toString(new Date(), "HHmmssddMMyyyy") + ".xlsx";
        try {    
            newExcel();
            row = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
            cell = row.createCell(1, CellType.STRING);            
            cell.setCellValue("Phiếu xuất kho: tháng " + month + " năm " + year);
            cell.setCellStyle(style);
            
            row = sheet.createRow(4);
            String[] topRows = {"Id Kho", "Ngày Xuất", "Số Lượng", "ID Nhân viên"};
            int[] listCell = {0, 3, 6, 8};
            int[] listMrg = {2, 5, 7, 9};
            setDateToExcel(topRows, listCell, listMrg, 4, 0);
            
            int i = 0;
            int r = 0;
            for(XuatKho xuatKho: list) {
                r = i + 6;
                row = sheet.createRow(r);
                Object[] listData = {
                    xuatKho.getIdKho(),
                    XDate.toString((Date) xuatKho.getNgayXuat(), "HH:mm:ss dd-MM-yyyy"),
                    xuatKho.getSoLuong(),
                    xuatKho.getIdUser()
                };
                setDateToExcel(listData, listCell, listMrg, r, 1);
                i = i + 2;
            }

            writeFile("xuatkho\\" + fileName, workbook);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public static void doanhThuToExcel(List<Object[]> list, String s_Date, String e_Date, String tong) throws Exception {
        String fileName = "doanhThuKFood-" + XDate.toString(new Date(), "HHmmssddMMyyyy") + ".xlsx";
        try {    
            newExcel();
            row = sheet.createRow(0);
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 3, 7));
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Doanh thu bán hàng - KFood");
            cell.setCellStyle(style);
            
            row = sheet.createRow(2);
            sheet.addMergedRegion(new CellRangeAddress(2, 3, 3, 7));
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Từ ngày: " + s_Date + "  Đến ngày: " + e_Date);
            cell.setCellStyle(style);
            
            row = sheet.createRow(4);
            sheet.addMergedRegion(new CellRangeAddress(4, 5, 3, 7));
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(tong);
            cell.setCellStyle(style);
            
            row = sheet.createRow(6);
            String[] topRows = {"Tên Sản Phẩm", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Tổng Bán Được", "Doanh Thu"};
            int[] listCell = {0, 3, 5, 7, 9};
            int[] listMrg = {2, 4, 6, 8, 10};
            setDateToExcel(topRows, listCell, listMrg, 6, 0);
            
            int i = 0;
            int r = 0;
            for(Object[] rObjects: list) {
                r = i + 8;
                row = sheet.createRow(r);
                Object[] listData = {
                    rObjects[0], 
                    rObjects[1], 
                    rObjects[2], 
                    rObjects[3], 
                    rObjects[4]
                };
                setDateToExcel(listData, listCell, listMrg, r, 1);
                i = i + 2;
            }

            writeFile("thongke\\" + fileName, workbook);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public static void topSPToExcel(List<Object[]> list, String caterogy) throws Exception {
        String fileName = "topProduct-" + XDate.toString(new Date(), "HHmmssddMMyyyy") + ".xlsx";
        try {    
            newExcel();
            row = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Top sản phẩm bán chạy - KFood");
            cell.setCellStyle(style);
            
            row = sheet.createRow(2);
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Danh mục sản phẩm: " + caterogy);
            cell.setCellStyle(style);
            
            row = sheet.createRow(4);
            String[] topRows = {"ID", "Tên Sản Phẩm", "Giá Bán", "Đơn Vị", "Tổng Bán Được", "Tổng Doanh Thu"};
            int[] listCell = {0, 1, 4, 6, 8, 10};
            int[] listMrg = {0, 3, 5, 7, 9, 11};
            setDateToExcel(topRows, listCell, listMrg, 4, 0);
            
            int i = 0;
            int r = 0;
            for(Object[] rObjects: list) {
                r = i + 6;
                row = sheet.createRow(r);
                Object[] listData = {
                    rObjects[0], 
                    rObjects[1], 
                    rObjects[2], 
                    rObjects[3], 
                    rObjects[4], 
                    rObjects[5]
                };
                setDateToExcel(listData, listCell, listMrg, r, 1);
                i = i + 2;
            }

            writeFile("thongke\\" + fileName, workbook);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public static void topNVToExcel(List<Object[]> list, int t) throws Exception {
        String fileName = "topNhanVien-" + XDate.toString(new Date(), "HHmmssddMMyyyy") + ".xlsx";
        try {    
            newExcel();
            row = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Top Nhân Viên Tháng " + t);
            cell.setCellStyle(style);
            row = sheet.createRow(4);
            
            String[] topRows = {"ID", "Họ và Tên", "Giới Tính", "Tổng Doanh Thu"};
            int[] listCell = {0, 1, 4, 6};
            int[] listMrg = {0, 3, 5, 7};
            setDateToExcel(topRows, listCell, listMrg, 4, 0);
            
            int i = 0;
            int r = 0;
            for(Object[] rObjects: list) {
                r = i + 6;
                row = sheet.createRow(r);
                Object[] listData = {
                    rObjects[0], 
                    rObjects[1], 
                    rObjects[2], 
                    rObjects[3]
                };
                setDateToExcel(listData, listCell, listMrg, r, 1);
                i = i + 2;
            }

            writeFile("thongke\\" + fileName, workbook);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public static void hoaDonBepToExcel(List<ChiTietBanAn> list, String detail) throws Exception {
        String fileName = "ChiTietBanAn-" + XDate.toString(new Date(), "HHmmssddMMyyyy") + ".xlsx";
        try {    
            newExcel();
            
            row = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(detail);
            cell.setCellStyle(style);
            
            row = sheet.createRow(4);
            String[] topRows = {"Tên Món", "Số Lượng", "Đơn Vị", "Ghi Chú", "Trạng Thái"};
            int[] listCell = {0, 3, 5, 7, 9};
            int[] listMrg = {2, 4, 6, 8, 10};
            setDateToExcel(topRows, listCell, listMrg, 4, 0);
            
            int i = 0;
            int r = 0;
            for(ChiTietBanAn chiTietBanAn: list) {
                r = i + 6;
                row = sheet.createRow(r);
                Object[] listData = {
                    chiTietBanAn.getTenMon(), 
                    chiTietBanAn.getSoLuong(), 
                    chiTietBanAn.getDonVi(), 
                    chiTietBanAn.getGhiChu(),
                    ""
                };
                setDateToExcel(listData, listCell, listMrg, r, 1);
                i = i + 2;
            }

            writeFile("chitietbanan\\" + fileName, workbook);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public static void writeFile(String fileName, XSSFWorkbook workbook) throws Exception {
        File file = new File("report\\" + fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        openFile("report\\" + fileName);
    }
    
    public static void openFile(String file) throws Exception {
        try {
            Desktop.getDesktop().browse(new File(file).toURI());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void setDateToExcel(Object[] listData, int[] listCell, int[] listMrg, int r, int head) {
        for(int k = 0; k < listData.length; k++) { 
            CellRangeAddress region = new CellRangeAddress(r, r + 1, listCell[k], listMrg[k]);
            sheet.addMergedRegion(region);
            cell = row.createCell(listCell[k], CellType.STRING);
            
            String typeData = listData[k].getClass().getSimpleName();
            switch(typeData) {
                case "Date" -> cell.setCellValue((Date) listData[k]);
                case "String" -> cell.setCellValue((String) listData[k]);
                case "Double" -> cell.setCellValue((Double) listData[k]);
                case "Integer" -> cell.setCellValue((Integer) listData[k]);
                case "Boolean" -> cell.setCellValue((Boolean) listData[k]);
            }
            if(head == 0) {
                cell.setCellStyle(style);
            } else {                
                cell.setCellStyle(styleBorder);
            }
            RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
            RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
        }
    }
    
    public static void newExcel() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet();
        row = null;
        cell = null;
        
        styleBorder = workbook.createCellStyle();
        style = workbook.createCellStyle();
        font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        styleBorder.setAlignment(HorizontalAlignment.CENTER);
        styleBorder.setVerticalAlignment(VerticalAlignment.CENTER);
    }
}
