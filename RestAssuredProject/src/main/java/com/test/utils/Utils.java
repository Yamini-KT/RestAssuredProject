package com.test.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.test.constants.SourcePath;

public class Utils {
	
	public static String getConfigProperty(String Key) {

		Properties properties = new Properties();
		String filePath = SourcePath.CONFIG_PROPERTIES_PATH;

		FileInputStream inputfile = null;
		try {
			inputfile = new FileInputStream(filePath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String value = null;

		try {
			try {
				properties.load(inputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			value = properties.getProperty(Key);
//			System.out.println("Property we got from the file is::" + value);
		} finally {
			try {
				inputfile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

}
