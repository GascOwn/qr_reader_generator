package com.qrReader;

import java.io.*;

import java.io.IOException;
import java.util.Map;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;

public class QrCode {


    public static String readQR(String path, String charset) throws IOException, NotFoundException {
            BinaryBitmap binaryBitmap
                    = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(
                            ImageIO.read(
                                    new FileInputStream(path)))));

            Result result
                    = new MultiFormatReader().decode(binaryBitmap);


            return result.getText();
        }

    public static void createQR(String data, String path, String charset,  int height, int width) throws WriterException, IOException
    {

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                new File(path));
    }
}
