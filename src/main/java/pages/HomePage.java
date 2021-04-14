package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Sign in')]")
    private WebElement signInButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

}
