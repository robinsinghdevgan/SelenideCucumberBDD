package org.robinsinghdevgan.setup;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class BaseTestSetup {

    private Properties prop = null;
    private String propertiesFileName = null;
    private WebDriver driver = null;

    public Properties getPropertiesObject() {
        return prop;
    }

    public String getPropertiesFileName() {
        return propertiesFileName;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setProperties(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
        ArtifactLocations.setPropertyFileName(propertiesFileName);
        try (InputStream fis = new FileInputStream(ArtifactLocations.getPropertyFilePath())) {
            prop = new Properties();
            prop.load(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}