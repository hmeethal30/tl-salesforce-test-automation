package com.salesforce.test.config;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesFile {
	static Properties prop = new Properties();
	static String strProjectPath = System.getProperty("user.dir");

	public static void main(String[] args) throws IOException {
		getProperties();
		setProperties();
	}

	public static void getProperties() throws IOException {
		try {
			InputStream input = new FileInputStream(strProjectPath + "/src/test/java/com/salesforce/test/config/config.properties");
			prop.load(input);
			String browser = prop.getProperty("browser");
			System.out.println(browser);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}
	
	public static void setProperties() throws IOException {
		OutputStream output = new FileOutputStream(strProjectPath + "/src/test/java/com/salesforce/test/config/config.properties");
		prop.setProperty("browserr", "chrome");
		prop.store(output, null);
	}
}
