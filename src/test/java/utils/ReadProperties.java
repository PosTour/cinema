package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    public static Properties propertiesRead() {
        final String PROPERTIES_FILE = "src/test/resources/testprocess.properties";
        File f = new File(PROPERTIES_FILE);
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(f));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
