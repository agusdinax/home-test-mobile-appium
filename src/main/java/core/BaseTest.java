package core;

import config.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import listeners.AllureListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(AllureListener.class)
public class BaseTest {
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.createDriver();
        driver = DriverFactory.getDriver();

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}