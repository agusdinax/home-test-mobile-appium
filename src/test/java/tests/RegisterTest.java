package tests;

import core.BaseTest;
import data.RegisterDataLoader;
import data.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;
import pages.RegisterPage;
import utils.AllureSteps;

@Epic("Mobile Automation")
@Feature("Registration")
@Owner("Agustina Di Natale")
@Tag("Register")

public class RegisterTest extends BaseTest {

    @Story("Scenario 4 - User Registration")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new user can successfully register by submitting the registration form")
    @Test(groups = {"regression"}, description = "TC_REG_001 - Register new user successfully")
    public void TC_REG_001_RegisterNewUserSuccessfully() {
        RegisterPage register = new RegisterPage();
        User user = RegisterDataLoader.getUser("newUser");
        AllureSteps.run("Open Register screen", driver, register::validateRegisterScreen);
        AllureSteps.run("Complete account information", driver, () -> register.fillAccountInformation(user));
        AllureSteps.run("Continue to Personal Information", driver, register::clickContinue);
        AllureSteps.run("Verify Personal Information screen", driver, register::validatePersonalInformationScreen);
        AllureSteps.run("Complete personal information", driver, () -> register.fillPersonalInformation(user));
        AllureSteps.run("Submit registration", driver, register::clickSignup);
        AllureSteps.run("Verify registration success", driver, register::validateRegistrationSuccess);
    }
}