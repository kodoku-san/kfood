package com.kfoodsys.utils;

import com.barcodelib.barcode.Linear;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.kfoodsys.entity.KhoNguyenLieu;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.util.EnumMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author phuho
 */
public class XGenCode {

    public static KhoNguyenLieu khoNguyenLieu = null;
    public static int IdBillByBC = -1;

    public static void createQRCode(String FileName, String content) throws Exception {
        try {
            String filePath = "QRCode\\" + FileName + ".png";
            Map<EncodeHintType, Object> hinMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hinMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix matrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, 250, 250, hinMap);
            MatrixToImageWriter.writeToPath(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), FileSystems.getDefault().getPath(filePath));
            Desktop.getDesktop().browse(new File(filePath).toURI());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static String read(String path) throws Exception {
        try {
            InputStream barcodeInputStream = new FileInputStream(path);
            BufferedImage barcBufferedImage = ImageIO.read(barcodeInputStream);

            LuminanceSource source = new BufferedImageLuminanceSource(barcBufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new QRCodeReader();
            Result result = reader.decode(bitmap);

            return result.getText();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public static void createBarCode(String data) throws Exception {
        try {
            Linear barCode = new Linear();
            barCode.setBarcodeHeight(110);
            barCode.setX(2.5f);
            barCode.setY(130f);
            barCode.setType(Linear.CODE128A);
            barCode.setData(data);
            barCode.renderBarcode("BarCode\\Bill-" + data + ".png");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
