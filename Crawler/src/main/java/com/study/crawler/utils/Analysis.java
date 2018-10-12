package com.study.crawler.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Analysis {
	public static void analysisData(String url,String path) {
		String msg = HttpUtils.getMsg(url);
		try {
			FileUtils.write(new File(path+"/index.html"), msg);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] split = msg.split(" ");
		for (String string : split) {
			String regexString = HttpUtils.regexString(string, "src=\"(.+?)\"");
			if(regexString!=null) {
				System.out.println(regexString);
				if(regexString.endsWith(".png")||regexString.endsWith(".jpg")||regexString.endsWith(".gif")) {
					 HttpUtils.getImg(url+regexString, path+regexString);
				}else if(regexString.endsWith(".js")||regexString.endsWith(".css")) {
					try {
						FileUtils.write(new File(path+regexString), regexString);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
