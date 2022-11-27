package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileOpeartions {

	Properties prop=new Properties();
	
	public PropertyFileOpeartions(String filePath) throws IOException {
		File file = new File(filePath);
		FileInputStream inputStream= new FileInputStream(file);
		prop.load(inputStream);
	}
	
	public String getKeyValue(String key) {
		return prop.getProperty(key);
	}
	
}
