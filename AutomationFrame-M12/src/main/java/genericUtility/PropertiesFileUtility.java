package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 *this 
 */
public class PropertiesFileUtility {
	/**
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String toReadDataFromPropertyfile(String key) throws IOException {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
	
		return value;
	}


}
