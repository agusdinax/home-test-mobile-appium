package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//CLASES PARA QUE SAQUE FOTO A CADA PASO DEL TEST
public final class AllureAttachments {
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss.SSS");

    public static void screenshotIfPossible(RemoteWebDriver driver, String name) {
        try {
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(safeName(name) + " [" + TS.format(LocalDateTime.now()) + "]",
                    "image/png", new ByteArrayInputStream(png), ".png");
        } catch (WebDriverException e) {
            if (isFlagSecure(e)) {
                //si no se puede con flagsecure, se imprime el page source
                Allure.addAttachment(safeName(name) + " (omitada por FLAG_SECURE)", "text/plain",
                        new ByteArrayInputStream(("Screenshot omitida por FLAG_SECURE.\n" +
                                "Mensaje: " + e.getMessage()).getBytes(StandardCharsets.UTF_8)), ".txt");
                attachPageSource(driver, "PageSource por FLAG_SECURE");
            } else {
                Allure.addAttachment(safeName(name) + " (no se pudo capturar screenshot)", "text/plain",
                        new ByteArrayInputStream(("No se pudo capturar screenshot.\n" +
                                "Mensaje: " + e.getMessage()).getBytes(StandardCharsets.UTF_8)), ".txt");
                attachPageSource(driver, "PageSource (fallback)");
            }
        } catch (Throwable t) {
            //no romper si no se puede adjuntar algo
            Allure.addAttachment(safeName(name) + " (error inesperado al adjuntar)", "text/plain",
                    new ByteArrayInputStream(("Error: " + t).getBytes(StandardCharsets.UTF_8)), ".txt");
        }
    }

    public static void attachPageSource(RemoteWebDriver driver, String name) {
        try {
            String xml = driver.getPageSource();
            Allure.addAttachment(safeName(name), "text/xml", new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), ".xml");
        } catch (Throwable t) {
            Allure.addAttachment(safeName(name) + " (no se pudo obtener page-source)", "text/plain",
                    new ByteArrayInputStream(("Error: " + t).getBytes(StandardCharsets.UTF_8)), ".txt");
        }
    }

    //si la pantalla es flagSecure
    private static boolean isFlagSecure(WebDriverException e) {
        String msg = String.valueOf(e.getMessage()).toLowerCase();
        return msg.contains("flag_secure")
                || msg.contains("permission denied for window type")
                || msg.contains("screenshot has been blocked")
                || msg.contains("screenshots disabled")
                || msg.contains("not allowed to capture");
    }

    private static String safeName(String s) {
        return (s == null || s.isBlank()) ? "adjunto" : s.replaceAll("[\\r\\n\\t]+", " ").trim();
    }
}