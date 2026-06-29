package utils;

import config.DriverFactory;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenshotUtils {

    public static void attachScreenshot(String name) {
        attach(name);
    }

    @Attachment(value = "{name}", type = "image/png")
    private static byte[] attach(String name) {
        try {
            return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }
}