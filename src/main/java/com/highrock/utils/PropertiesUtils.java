package com.highrock.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
	public static Properties getProperties(){
		return getProperties("source.properties");
	}
	public static Properties getProperties(String properties){
		Properties pro=new Properties();
		try {
			pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(properties));
			return pro;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
