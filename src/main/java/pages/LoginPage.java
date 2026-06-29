package pages;

import core.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.WaitUtils;

public class LoginPage extends BasePage {

    private final By imgLogo = AppiumBy.xpath("//*[@resource-id='logoImage']");
    private final By txtEmail = AppiumBy.accessibilityId("emailField");
    private final By txtPassword = AppiumBy.accessibilityId("passwordField");
    private final By btnLogin = AppiumBy.androidUIAutomator("new UiSelector().text(\"Login\")");
    private final By txtInventory = AppiumBy.xpath("//android.widget.TextView[@text='Inventory']");

    public void login(String email, String password) {
        waitUntilLoaded();
        type(txtEmail, email);
        type(txtPassword, password);
        click(btnLogin);
    }

    public void verifySuccessLogin() {
        WaitUtils.visible(txtInventory);
    }

    public void validateAndroidAlert(String expectedTitle) {
        By alertTitle = AppiumBy.id("com.learnautomationapp:id/alertTitle");
        WebElement title = WaitUtils.visible(alertTitle);
        Assert.assertEquals(title.getText(), expectedTitle, "Unexpected Android alert title.");
    }

    public void waitUntilLoaded() {
        WaitUtils.visible(imgLogo);
        WaitUtils.visible(txtEmail);
        WaitUtils.visible(txtPassword);
    }

}