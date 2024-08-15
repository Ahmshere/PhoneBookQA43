package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReaderXML {

    public static void main(String[] args) {
        System.out.println( getProperties("mykey","src/main/resources/data.xml"));
    }
    public static  String getProperties(String key, String path){
        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream(path)){
            properties.loadFromXML(fis);
            return properties.getProperty(key);
        }catch (IOException e){e.printStackTrace();
        return null;}
    }
}
