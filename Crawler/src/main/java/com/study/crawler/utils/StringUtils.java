package com.study.crawler.utils;

public class StringUtils {

	public static boolean isBlank(String str) {
		if(str==null||str.equals("")) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean isNotBlank(String str) {
		if(str==null||str.equals("")) {
			return false;
		}else {
			return true;
		}
	}
}
