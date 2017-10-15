package com.study.util.barcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * Created on 2017-10-15
 *
 * @author liuzhaoyuan
 */
public class BarcodeUtils {

    private static final int width = 300;
    private static final int height = 300;
    private static final String format = "png";

    private BarcodeUtils() {
    }

    /**
     * 生成 二维码
     *
     * @return
     */
    public static boolean createQR(String content, OutputStream outputStream) {
        // 二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 边距
        hints.put(EncodeHintType.MARGIN, 2);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            try {
                MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读取 二维码
     * @param inputStream
     * @return
     */
    public static String readQR(InputStream inputStream) {
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        BufferedImage image;
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

        try {
            Result result = multiFormatReader.decode(bitmap, hints);
            return result.getText();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
