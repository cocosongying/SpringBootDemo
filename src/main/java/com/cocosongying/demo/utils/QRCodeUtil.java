package com.cocosongying.demo.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QRCodeUtil {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	
	public static void getCode(String msg, OutputStream output) throws WriterException, IOException {
		int width = 300;
		int height = 300;
		String format = "png";
		
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		
		BitMatrix bm = new MultiFormatWriter().encode(msg, BarcodeFormat.QR_CODE, width, height);
		BufferedImage image = toImage(bm);
		ImageIO.write(image, format, output);
	}
	
	private static BufferedImage toImage(BitMatrix bm) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				image.setRGB(x, y, bm.get(x, y) ? BLACK : WHITE);
			}
		}
		
		return image;
	}
}
