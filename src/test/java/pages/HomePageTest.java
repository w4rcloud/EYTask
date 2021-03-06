package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageTest {

    private HomePage page;
    private WebDriver driver;

    @BeforeClass(description = "Home Page tests start")
    public void setUp() {
        driver = new ChromeDriver();
        page = new HomePage(driver);
        driver.get(page.getURL());
        driver.manage().window().maximize();
    }

    @AfterClass(description = "Home Page tests end")
    public void tearDown() {
        driver.quit();
    }

    @Test(description = "Confirm Home Page contains 'Sign In' button")
    public void homePageTC1() {
        String signIn = page.getSignInButton().getText();
        assertTrue(signIn.equalsIgnoreCase("Sign In"));
    }

    @Test(description = "Confirm 'Sign In' button redirects to Log In Page")
    public void homePageTC2() {
        String authenticationPageUrl = "http://automationpractice.com/index" +
                ".php?controller=authentication&back=my-account";
        page.clickOnSignInButton();
        assertEquals(authenticationPageUrl, driver.getCurrentUrl());
    }
}