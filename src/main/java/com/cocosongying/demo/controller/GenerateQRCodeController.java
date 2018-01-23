package com.cocosongying.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cocosongying.demo.utils.BarcodeUtil;
import com.cocosongying.demo.utils.QRCodeUtil;

@RestController
public class GenerateQRCodeController {

	private final static Logger logger = LoggerFactory.getLogger(GenerateQRCodeController.class);
	
	@RequestMapping(value="/qrcode")
	public void getQRCode(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "msg") String msg) throws Exception {
		logger.info(msg);
		msg = new String(msg.getBytes("UTF-8"), "ISO-8859-1");
		QRCodeUtil.getCode(msg, response.getOutputStream());
	}
	
	@RequestMapping(value="/barcode")
	public void getBarCode(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "msg") String msg) throws Exception {
		logger.info(msg);
		msg = new String(msg.getBytes("UTF-8"), "ISO-8859-1");
		BarcodeUtil.getCode(msg, response.getOutputStream());
	}
}
