package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	public static String getKey(Object object) {
		String trKey = "";
		InputStream inputStream=object.getClass().getClassLoader().getResourceAsStream("key.properties");
		Properties properties=new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trKey = properties.getProperty("secretKey");
		return trKey;
	}
}
