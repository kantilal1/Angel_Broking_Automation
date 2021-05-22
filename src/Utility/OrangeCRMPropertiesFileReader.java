package orange.crm.Utility;

import java.io.FileInputStream;
import java.util.Properties;

public class OrangeCRMPropertiesFileReader {

    public Properties getProperty() {
        FileInputStream inputStream = null;
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("Winium-Projects/Serav/testdata.properties"));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return properties;
    }

}
