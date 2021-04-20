package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SignInPageTest {

    private WebDriver driver;
    private SignInPage page;
    private AccountCreationPage accCreationPage;

    @DataProvider
    public static Object[][] getInvalidEmailAddresses() {
        return new Object[][]{
                {"."},
                {"1"},
                {"a"}
        };
    }

    @DataProvider
    public static Object[][] getInvalidUserCredentials() {
        return new Object[][]{
                {".", "1"},
                {"1", "1"},
                {"a", "1"}
        };
    }

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new SignInPage(driver);
        driver.get(page.getURL());
    }

    // below method created because first few 'Create An Account' clicks with invalid input in 'Email address' do not
    // make the error message appear
    @BeforeGroups("Email verification for Create An Account")
    public void inputInvalidEmailFor1stTime() {
        for (int i = 0; i < 3; i++) {
            page.getNewAccEmailInput().clear();
            page.getNewAccEmailInput().sendKeys("!");
            driver.findElement(By.id("SubmitCreate")).click();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test(dataProvider = "getInvalidEmailAddresses",
            description = "Confirm account creation impossible if 'Email address' field gets populated with " +
                    "invalid email address",
            groups = {"Email verification for Create New Account"},
            priority = 1)
    public void signInPageTC1(String email) {
        page.getNewAccEmailInput().clear();
        page.getNewAccEmailInput().sendKeys(email);
        driver.findElement(By.id("SubmitCreate")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(page.containsText("Invalid email address"));
    }

    @Test(dataProvider = "getInvalidUserCredentials",
            description = "Confirm signing in impossible if 'Email address' & 'Password' fields get populated with " +
                    "invalid input",
            priority = 2)
    public void signInPageTC2(String uName, String pass) {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(uName);

        driver.findElement(By.id("passwd")).clear();
        driver.findElement(By.id("passwd")).sendKeys(pass);

        driver.findElement(By.id("SubmitLogin")).click();

        Assert.assertTrue(page.containsText("Invalid email address"));
    }

    @Test(description = "Confirm signing in possible when existing user's data is provided in 'Email address' & " +
            "'Password' fields",
            priority = 3)
    public void signInPageTC3() {
        String myAccountURL = "http://automationpractice.com/index.php?controller=my-account";

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("t3st@test.test");

        driver.findElement(By.id("passwd")).clear();
        driver.findElement(By.id("passwd")).sendKeys("testtest123");

        driver.findElement(By.id("SubmitLogin")).click();

        Assert.assertEquals(driver.getCurrentUrl(), myAccountURL);
    }
}