package com.study.crawler.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtils {
	public static String getMsg(String url) {
		String sb="";
		InputStream inputStream = openConnection(url);
		if (inputStream != null) {
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				isr = new InputStreamReader(inputStream,"utf-8");
				br = new BufferedReader(isr);
				String line;
				while ((line = br.readLine()) != null) {
					sb+=line;
					sb+="\n";
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null) {
						br.close();
					}
					if (isr != null) {
						isr.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		} else {
			return null;
		}
		return sb;

	}

	public static void getImg(String url, String path) {
		InputStream is = openConnection(url);
		if (is != null) {
			String[] split = path.split("/");
			String name = split[split.length - 1];
			String realPath = path.substring(0, path.length() - name.length()-1);
			File file = new File(realPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			BufferedInputStream bis = new BufferedInputStream(is);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(new File(path));
				int length = 0;
				byte[] buff = new byte[1024];
				int count=0;
				while ((length = bis.read(buff))>0) {
					fos.write(buff, 0, length);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fos != null) {
						fos.close();
					}
					if (bis != null) {
						bis.close();
					}
					if (is != null) {
						is.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	public static InputStream openConnection(String url) {
		try {
			if(StringUtils.isBlank(url)) {
				throw new RuntimeException("url为空!");
			}else {
				URL realUrl=new URL(url);
				URLConnection connection = realUrl.openConnection();
				connection.connect();
				InputStream is = connection.getInputStream();
				return is;
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String regexString(String target,String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(target);
		if(matcher.find()) {
			return matcher.group(1);
		}else {
			return null;
		}
	}
}
