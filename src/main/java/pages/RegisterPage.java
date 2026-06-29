package pages;

import core.BasePage;
import data.User;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.WaitUtils;

public class RegisterPage extends BasePage {
    private final By btnRegister = AppiumBy.accessibilityId("registerButton");
    private final By txtRegister = AppiumBy.xpath("//android.view.View[@text='Register']");
    private final By txtEmail = AppiumBy.accessibilityId("emailField");
    private final By txtFirstName = AppiumBy.accessibilityId("firstNameField");
    private final By txtLastName = AppiumBy.accessibilityId("lastNameField");
    private final By txtPassword = AppiumBy.accessibilityId("passwordField");
    private final By btnContinue = AppiumBy.accessibilityId("Continue");
    private final By txtPersonalInformation = AppiumBy.xpath("//android.view.View[@text='Personal Information']");
    private final By txtAddress = AppiumBy.accessibilityId("addressInput");
    private final By txtCity = AppiumBy.accessibilityId("cityInput");
    private final By txtZip = AppiumBy.accessibilityId("zipInput");
    private final By btnBirthDate = AppiumBy.accessibilityId("openDatePicker");
    private final By chkTerms = AppiumBy.accessibilityId("termConditions");
    private final By btnSignup = AppiumBy.accessibilityId("Signup!");
    private final By txtSuccess = AppiumBy.xpath("//*[contains(@text,'Your user has been created.')]");
    private final By btnConfirmDate = AppiumBy.xpath("//android.widget.Button[@text='Confirm']");

    public void validateRegisterScreen() {
        click(btnRegister);
        WaitUtils.visible(txtRegister);
        validateText(txtRegister, "Register");
    }

    public void validatePersonalInformationScreen() {
        WaitUtils.visible(txtPersonalInformation);
        validateText(txtPersonalInformation, "Personal Information");
    }

    public void validateRegistrationSuccess() {
        WaitUtils.visible(txtSuccess);
        Assert.assertTrue(isDisplayed(txtSuccess), "Registration success screen is not displayed.");
    }

    public void fillAccountInformation(User user) {
        fill(txtEmail, user.getEmail());
        fill(txtFirstName, user.getFirstName());
        fill(txtLastName, user.getLastName());
        fill(txtPassword, user.getPassword());
    }

    public void clickContinue() {
        click(btnContinue);
    }

    public void fillPersonalInformation(User user) {
        fill(txtAddress, user.getAddress());
        fill(txtCity, user.getCity());
        fill(txtZip, user.getZip());
        selectBirthDate(user.getBirthDate());
        if (user.isAcceptTerms()) {
            acceptTerms();
        }
    }

    public void selectBirthDate(String birthDate) {
        click(btnBirthDate);
        WaitUtils.visible(btnConfirmDate);
        click(btnConfirmDate);
    }

    public void acceptTerms() {
        click(chkTerms);
    }

    public void clickSignup() {
        click(btnSignup);
    }
}