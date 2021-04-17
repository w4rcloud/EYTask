import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AccountCreationPage;

public class Main {


    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        AccountCreationPage page = new AccountCreationPage(driver);

        String signInUrl = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        driver.get(signInUrl);
        driver.findElement(By.id("email_create")).sendKeys("abcde@abcde.pl");
        driver.findElement(By.id("email_create")).sendKeys(Keys.RETURN);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        page.getEmailInputField().clear();
        page.setCountryToNone();
        page.clickOnRegisterButton();

        System.out.println(driver.findElement(By.tagName("body")).getText());
    }


}
