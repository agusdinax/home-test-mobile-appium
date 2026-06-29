package pages;

import core.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.WaitUtils;

public class CatalogPage extends BasePage {

    private final By txtTitle = AppiumBy.accessibilityId("title");
    private final By scrollView = AppiumBy.accessibilityId("itemsList");
    private final By txtItemDetails = AppiumBy.xpath("//*[contains(@text,'Item Details')]");
    private final By txtAuthor = AppiumBy.xpath("//android.widget.TextView[@resource-id='author']");
    private final By txtDate = AppiumBy.xpath("//android.widget.TextView[@resource-id='date']");
    private final By imgArtwork = AppiumBy.xpath("//android.widget.ImageView[@resource-id='image']");
    private final By txtDescription = AppiumBy.xpath("//android.widget.TextView[@resource-id='textDescription']");

    //gets the xpath from a specific artwork
    private By artwork(String name) {
        return AppiumBy.xpath("//android.widget.TextView[@resource-id='itemName' and @text='" + name + "']");
    }

    //scroll to the artwork searching
    public void scrollToArtwork(String artworkName) {
        driver.findElement(scrollView).findElement(
                AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().description(\"itemsList\"))" +
                        ".scrollIntoView(new UiSelector().text(\"" + artworkName + "\"));"
                )
        );
    }

    public void openArtwork(String artworkName) {
        click(artwork(artworkName));
    }

    //validate artwork details
    public void validateArtworkDetail(String expectedTitle) {
        WaitUtils.visible(txtItemDetails);
        Assert.assertEquals(getText(txtTitle), expectedTitle, "Unexpected artwork title.");
        Assert.assertTrue(isDisplayed(imgArtwork), "Artwork image is not displayed.");
        Assert.assertFalse(getText(txtAuthor).isBlank(), "Artwork author is empty.");
        Assert.assertFalse(getText(txtDate).isBlank(), "Artwork date is empty.");
        Assert.assertFalse(getText(txtDescription).isBlank(), "Artwork description is empty.");
    }
}