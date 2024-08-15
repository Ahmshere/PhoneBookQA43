package helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesWriterXML {
    public static void main(String[] args) {
        PropertiesWriterXML properties = new PropertiesWriterXML();
        properties.setProperty("mypass","MyPassword123!", false,"src/main/resources/data.xml");
    }
    Properties properties = new Properties();

    public void setProperty(String key, String value, boolean clearFile, String path){
        if(!clearFile){
            try(FileInputStream fileInputStream = new FileInputStream(path)){
                properties.loadFromXML(fileInputStream);
            }catch (IOException e){e.printStackTrace();}
        }

        properties.setProperty(key, value);
        try(FileOutputStream fileOutputStream = new FileOutputStream(path)){
            properties.storeToXML(fileOutputStream, null);

        }
        catch (IOException e){e.printStackTrace();}
    }

}
