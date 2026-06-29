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
import pages.CatalogPage;
import pages.LoginPage;
import utils.AllureSteps;

@Epic("Mobile Automation")
@Feature("Catalog")
@Owner("Agustina Di Natale")
@Tag("Catalog")
public class CatalogTest extends BaseTest {

    @Story("Scenario 3 - Catalog Exploration")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can browse the catalog and locate a specific element and validate it")
    @Test(groups = {"smoke", "regression"}, description = "TC_CAT_001 - Locate artwork in catalog")
    public void TC_CAT_001_LocateArtworkInCatalog() {
        LoginPage login = new LoginPage();
        CatalogPage catalog = new CatalogPage();
        User user = LoginDataLoader.getUser("validUser");
        String artwork = "Twilight Glow";
        AllureSteps.run("Login using valid credentials", driver, () -> login.login(user.getEmail(), user.getPassword()));
        AllureSteps.run("Validate Inventory screen", driver, login::verifySuccessLogin);
        AllureSteps.run("Scroll to artwork", driver, () -> catalog.scrollToArtwork(artwork));
        AllureSteps.run("Open artwork detail", driver, () -> catalog.openArtwork(artwork));
        AllureSteps.run("Validate artwork detail", driver, () -> catalog.validateArtworkDetail(artwork));
    }
}