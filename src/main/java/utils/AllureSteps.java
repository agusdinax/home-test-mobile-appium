package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;

public final class AllureSteps {
    
    //ejecuta una acción y siempre adjunta 1 screenshot de después de la acción
    public static void run(String name, RemoteWebDriver driver, Runnable action) {
        Objects.requireNonNull(action, "action");
        Allure.step(name, () -> {
            try {
                action.run();
            } finally {
                //toma screen si es error o siempre que pueda despues de realizar el paso
                AllureAttachments.screenshotIfPossible(driver, "Después - " + name);
            }
        });
    }
}