package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileOperations {
	Properties prop = new Properties();
	
	public PropertyFileOperations(String filePath) throws IOException {
		File file = new File(filePath);
		FileInputStream fileInputStream = new FileInputStream(file);
		prop.load(fileInputStream);
	}
	
	public String getPropValue(String key) {
		return prop.getProperty(key);
		
	}
}
