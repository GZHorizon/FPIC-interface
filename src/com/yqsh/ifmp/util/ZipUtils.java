package com.yqsh.ifmp.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ZipUtils {
	/**
	 * 使用base64进行解压数据
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static byte[] getBase64Decoder(String str) throws IOException{
		BASE64Decoder decoder = new BASE64Decoder();
    	return decoder.decodeBuffer(str);
	}
	/**
	 * 使用base64进行压缩数据
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String getBase64Encoder(byte[] endata){
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(endata);
	}
	
	public static void main(String[] args) throws Exception {
		String dd = getBase64Encoder("http://192.168.1.1: 8088:AMS/connector/cardLost.action?token='123456789'&cardNo='0003315'".getBytes("UTF-8")).toUpperCase();
		System.out.println(dd);
	}
}
