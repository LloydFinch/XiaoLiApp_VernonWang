package com.xiaoliapp.app.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by VennUser on 2015/8/14.
 */
//Post请求工具类
public final class HttpPostUtils {
	private HttpPostUtils() {

	}

	public static String sendByPost(HashMap<String, String> params, String path, String encode) throws
			IOException {
		String result = null;

		//构造请求参数
		StringBuilder sb = new StringBuilder();
		for (HashMap.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey())
					.append("=")
					.append(URLEncoder.encode(entry.getValue(), encode))
					.append("&");
		}
		sb.deleteCharAt(sb.length() - 1);

		//准备请求
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setConnectTimeout(10000);
		connection.setDoInput(true);
		connection.setDoOutput(true);

		//配置请求信息
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		byte[] data = sb.toString().getBytes();
		connection.setRequestProperty("Content-Length", String.valueOf(data.length));

		//写入请求信息
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(data, 0, data.length);
		outputStream.close();

		//发起请求并返回相应数据
		if (connection.getResponseCode() == 200) {
			InputStream inputStream = connection.getInputStream();
			result = makeString(inputStream, encode);
		}
		return result;
	}

	private static String makeString(InputStream in, String encode) throws IOException {
		int length = 0;
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		while ((length = in.read(buffer)) != -1) {
			arrayOutputStream.write(buffer, 0, length);
		}
		return new String(arrayOutputStream.toByteArray(), encode);
	}
}
