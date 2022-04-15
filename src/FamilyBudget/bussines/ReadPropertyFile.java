package FamilyBudget.bussines;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

	public static String getProp(String property) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("res\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("cant read file");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop.getProperty(property);
		
	}

}
