package Utility;

import java.io.FileInputStream; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * @author AVINASH D
 * This Is Used For fetchdatafrom property file and write back data to property file
 */
public class PropertyFileUtility {

	/**
	 * This method is used for fetch data from property file
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String fetchdatafrompropfile(String key) throws IOException {
		
		//fetch data from property file
		FileInputStream fis=new FileInputStream("./src/test/resources/commandata.properties");
		Properties pop= new Properties();
		pop.load(fis);
		String value = pop.getProperty(key);
		return value;
	}
	
	/**
	 * This method is used for write back data to property file
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public void writebackdatatopropfile(String key, String value) throws IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/commandata.properties");
		Properties pop= new Properties();
		pop.load(fis);
		pop.put(key, value);
		FileOutputStream fos=new FileOutputStream("./src/test/resources/commandata.properties");
		pop.store(fos, "Updated");	
		
	}


}
