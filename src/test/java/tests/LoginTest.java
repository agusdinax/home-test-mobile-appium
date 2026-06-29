package tests;

import core.BaseTest;
import data.LoginDataLoader;
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
import pages.LoginPage;
import utils.AllureSteps;

@Epic("Mobile Automation")
@Feature("Authentication")
@Owner("Agustina Di Natale")
@Tag("Login")
public class LoginTest extends BaseTest {

    @Story("Scenario 1 - User Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a registered user can successfully authenticate with valid credentials")
    @Test(groups = {"smoke", "regression"}, description = "TC_AUTH_001 - Login succesfully")
    public void TC_AUTH_001_LoginWithValidCredentials() {
        LoginPage login = new LoginPage();
        User user = LoginDataLoader.getUser("validUser");
        AllureSteps.run("Login using valid credentials", driver,
                () -> login.login(user.getEmail(), user.getPassword()));
        AllureSteps.run("Validate successful login", driver, login::verifySuccessLogin);
    }

    @Story("Scenario 2 - Input Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that authentication is blocked when both login fields are empty")
    @Test(groups = {"regression"}, description = "TC_AUTH_002 - Login with empty credentials")
    public void TC_AUTH_002_LoginWithEmptyCredentials() {
        LoginPage login = new LoginPage();
        User user = LoginDataLoader.getUser("emptyUser");
        AllureSteps.run("Attempt login with empty credentials", driver,
                () -> login.login(user.getEmail(), user.getPassword()));
        AllureSteps.run("Verify empty popup  is displayed", driver,
                () -> login.validateAndroidAlert("Please complete both fields"));
    }

    @Story("Scenario 2 - Input Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that authentication fails when invalid email is provided")
    @Test(groups = {"regression"}, description = "TC_AUTH_003 - Login with invalid email")
    public void TC_AUTH_003_LoginWithInvalidEmail() {
        LoginPage login = new LoginPage();
        User user = LoginDataLoader.getUser("invalidEmail");
        AllureSteps.run("Login using an invalid email", driver,
                () -> login.login(user.getEmail(), user.getPassword()));
        AllureSteps.run("Verify popup email error message  is displayed", driver,
                () -> login.validateAndroidAlert("Invalid user or password"));
    }

    @Story("Scenario 2 - Input Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that authentication fails when  invalid password is provided.")
    @Test(groups = {"regression"}, description = "TC_AUTH_004 - Login with invalid password")
    public void TC_AUTH_004_LoginWithInvalidPassword() {
        LoginPage login = new LoginPage();
        User user = LoginDataLoader.getUser("invalidPassword");
        AllureSteps.run("Login using an invalid password", driver,
                () -> login.login(user.getEmail(), user.getPassword()));
        AllureSteps.run("Verify popup password error message is displayed", driver,
                () -> login.validateAndroidAlert("Invalid user or password"));
    }

    @Story("Scenario 2 - Input Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that authentication fails when both email and password are invalid")
    @Test(groups = {"regression"}, description = "TC_AUTH_005 - Login with invalid credentials")
    public void TC_AUTH_005_LoginWithInvalidCredentials() {
        LoginPage login = new LoginPage();
        User user = LoginDataLoader.getUser("invalidCredentials");
        AllureSteps.run("Login using invalid credentials", driver,
                () -> login.login(user.getEmail(), user.getPassword()));
        AllureSteps.run("Verify popup password error message is displayed", driver,
                () -> login.validateAndroidAlert("Invalid user or password"));
    }
}