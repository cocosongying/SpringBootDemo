package com.cocosongying.demo.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class BarcodeUtil {

	public static void getCode(String msg, OutputStream output) throws IOException {
		int dpi = 150;
		double moduleWidth = UnitConv.in2mm(1.0f / dpi);
		String format = "image/png";
		
		BitmapCanvasProvider canvas = new BitmapCanvasProvider(output, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
		Code39Bean bean = new Code39Bean();
		bean.setModuleWidth(moduleWidth);
	    bean.setWideFactor(3);
	    bean.doQuietZone(false);
	    bean.generateBarcode(canvas, msg);
	    canvas.finish();
	}
}
