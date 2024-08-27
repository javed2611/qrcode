package com.tcs.qrcode_generation.controller;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.tcs.qrcode_generation.service.QRCodeService;

@RestController
public class QRCodeController {
	
	 @Autowired
	 private QRCodeService qrCodeService;

	    @GetMapping("/generateQRCode")
	    public ResponseEntity<byte[]> generateQRCode(@RequestParam String text) {
	        try {
	            byte[] qrCodeImage = qrCodeService.generateQRCodeImage(text, 400, 400);
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.IMAGE_PNG);
	            return ResponseEntity.ok().headers(headers).body(qrCodeImage);
	        } catch (WriterException | IOException e) {
	            return ResponseEntity.status(500).body(null);
	        }
	    }

}
