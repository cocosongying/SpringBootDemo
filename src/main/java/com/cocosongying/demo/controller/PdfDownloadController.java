package com.cocosongying.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class PdfDownloadController {
	
	@GetMapping(value="/download")
	public void downloadPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
	    response.setHeader("Expires", "0");
	    response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	    response.setHeader("Pragma", "public");
	    // 控制使用浏览器预览还是直接下载
//	    response.setHeader("Content-Disposition", "attachment; filename=Hello.pdf");

		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		document.add(new Paragraph("Hello World !"));
		document.close();
		writer.close();
	}
}
