package com.ai_education.utils;

import com.ai_education.utils.currencyUtils.HttpUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class currencyUtil {
	    // webapi 接口地址
		private static final String URL = "http://tupapi.xfyun.cn/v1/currency";
		// 应用ID

		// 图片名称
		private static final String IMAGE_NAME = "img.jpg";
		// 图片url
		//private static final String IMAGE_URL = " ";
		
		// 图片地址
		private static final String PATH = "文件路径";



		public static String getmessage(MultipartFile multipartFile,String APPID,String API_KEY) throws IOException {

			Map<String, String> header = buildHttpHeader(APPID,API_KEY);
			byte[] imageByteArray = multipartFile.getBytes();
			String result = HttpUtil.doPost1(URL, header, imageByteArray);
			return result;
		}
		/**
		 * 组装http请求头
		 */
		private static Map<String, String> buildHttpHeader(String APPID,String API_KEY) throws UnsupportedEncodingException {
			String curTime = System.currentTimeMillis() / 1000L + "";
			String param = "{\"image_name\":\"" + IMAGE_NAME + "\"}";
			String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
			String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
			Map<String, String> header = new HashMap<String, String>();
			header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			header.put("X-Param", paramBase64);
			header.put("X-CurTime", curTime);
			header.put("X-CheckSum", checkSum);
			header.put("X-Appid", APPID);
			return header;
		}
}