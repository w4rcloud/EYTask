package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

    private WebDriver driver;
    private final String URL = "http://automationpractice.com/index.php?controller=authentication&back=identity";

    @FindBy(id = "email_create")
    private WebElement newAccEmailInput;

    @FindBy(id = "authentication")
    private WebElement pageBody;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getNewAccEmailInput() {
        return newAccEmailInput;
    }

    public boolean containsText(String text) {
        String bodyText = pageBody.getText().toLowerCase();
        return bodyText.contains(text.toLowerCase());
    }

    public String getURL() {
        return URL;
    }

    public WebElement getPageBody() {
        return pageBody;
    }
}
