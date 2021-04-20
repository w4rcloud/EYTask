package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AccountCreationPageTest {

    private WebDriver driver;
    private AccountCreationPage page;

    @BeforeClass()
    public void setUp() {
        driver = new ChromeDriver();
        page = new AccountCreationPage(driver);
        goToAccCreationPage();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void goToAccCreationPage() {
        String signInUrl = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        driver.get(signInUrl);
        driver.manage().window().maximize();
        driver.findElement(By.id("email_create")).sendKeys(generateRandomEmailAddress());
        driver.findElement(By.id("email_create")).sendKeys(Keys.RETURN);
    }

    @AfterClass(description = "Home Page tests end")
    public void tearDown() {
        driver.quit();
    }

    private String generateRandomEmailAddress() {
        StringBuilder builder = new StringBuilder();
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'
                , 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        int charsNumber1 = new Random().nextInt(30) + 1;
        for (int i = 0; i < charsNumber1; i++) {
            builder.append(chars[new Random().nextInt(chars.length)]);
        }
        builder.append("@");
        int charsNumber2 = new Random().nextInt(20) + 1;
        for (int i = 0; i < charsNumber2; i++) {
            builder.append(chars[new Random().nextInt(chars.length)]);
        }
        builder.append(".");
        for (int i = 0; i < 3; i++) {
            builder.append(chars[new Random().nextInt(chars.length)]);
        }
        return builder.toString();
    }

    @Test(description = "Confirm account creation impossible if mandatory fields left without input data", priority = 1)
    public void accCreationPageTC1() {
        page.getEmailInputField().clear();
        page.setCountryToNone();
        page.clickOnRegisterButton();

        Assert.assertTrue(page.containsText("There are 10 errors"));
    }

    @Test(description = "Confirm 'Title' radio buttons work properly", priority = 2)
    public void accCreationPageTC2() {
        page.getMaleRadioButton().click();
        page.getFemaleRadioButton().click();

        Assert.assertTrue(!page.getMaleRadioButton().isSelected() && page.getFemaleRadioButton().isSelected());
    }

    @Test(description = "Confirm account creation impossible if 'First name' field populated with invalid characters"
            , priority = 3)
    public void accCreationPageTC3() {
        page.getFirstNameOnAddressInputField().sendKeys("R2D2");
        page.getFirstNameInputField().sendKeys("R2D2");
        page.clickOnRegisterButton();

        Assert.assertTrue(page.containsText("firstname is invalid"));
    }

    @Test(description = "Confirm account creation impossible if 'First name' field exceeds character limit (32)",
            priority = 4)
    public void accCreationPageTC4() {
        page.getFirstNameInputField().clear();
        page.getFirstNameOnAddressInputField().clear();

        page.getFirstNameOnAddressInputField().sendKeys("MikeMikeMikeMikeMikeMikeMikeMikeMike");
        page.getFirstNameInputField().sendKeys("MikeMikeMikeMikeMikeMikeMikeMikeMike");
        page.clickOnRegisterButton();

        Assert.assertTrue(page.containsText("firstname is too long. Maximum length: 32"));
    }

    @Test(description = "Confirm number of errors decreases by one if 'First name' field gets populated with valid " +
            "data", priority = 5)
    public void accCreationPageTC5() {
        page.getFirstNameInputField().clear();
        page.getFirstNameOnAddressInputField().clear();

        page.getFirstNameOnAddressInputField().sendKeys("Mike");
        page.getFirstNameInputField().sendKeys("Mike");
        page.clickOnRegisterButton();

        Assert.assertTrue(page.containsText("There are 9 errors"));
    }

    @Test(description = "Confirm account creation impossible if 'Last name' field gets populated with invalid " +
            "characters", priority = 6)
    public void accCreationPageTC6() {
        page.getLastNameInputField().sendKeys("C-3PO");
        page.getLastNameOnAddressInputField().sendKeys("C-3PO");
        page.clickOnRegisterButton();

        Assert.assertTrue(page.containsText("lastname is invalid"));
    }

    @Test(description = "Confirm account creation impossible if 'Last name' field exceeds character limit (32)",
            priority = 7)
    public void accCreationPageTC7() {
        page.getLastNameOnAddressInputField().clear();
        page.getLastNameInputField().clear();

        page.getLastNameOnAddressInputField().sendKeys("TysonTysonTysonTysonTysonTysonTyson");
        page.getLastNameInputField().sendKeys("TysonTysonTysonTysonTysonTysonTyson");
        page.clickOnRegisterButton();

        Assert.assertTrue(page.containsText("lastname is too long. Maximum length: 32"));
    }

    @Test(description = "Confirm number of errors decreases by one if 'Last name' field gets populated with valid " +
            "data", priority = 8)
    public void accCreationPageTC8() {
        page.getLastNameOnAddressInputField().clear();
        page.getLastNameInputField().clear();

        page.getLastNameOnAddressInputField().sendKeys("Tyson");
        page.getLastNameInputField().sendKeys("Tyson");
        page.clickOnRegisterButton();

        Assert.assertTrue(page.containsText("There are 8 errors"));
    }

    @Test(description = "Confirm account creation impossible if 'Email' field gets populated with invalid email " +
            "address", priority = 9)
    public void accCreationPageTC9() {
        page.getEmailInputField().sendKeys("a");
        page.clickOnRegisterButton();

        Assert.assertTrue(page.containsText("email is invalid"));
    }

    @Test(description = "Confirm account creation impossible if 'Email' field gets populated with email address from " +
            "an existing account", priority = 10)
    public void accCreationPageTC10() {
        page.getEmailInputField().clear();

        page.getEmailInputField().sendKeys("a@a.pl", Keys.RETURN);

        Assert.assertTrue(page.containsText("An account using this email address has already been registered"));
    }

    @Test(description = "Confirm number of errors decreases by one if 'Email' field gets populated with valid data",
            priority = 11)
    public void accCreationPageTC11() {
        page.getEmailInputField().clear();

        page.getEmailInputField().sendKeys(generateRandomEmailAddress(), Keys.RETURN);

        Assert.assertTrue(page.containsText("There are 7 errors"));
    }

    @Test(description = "Confirm 'Sign up for our newsletter!' & 'Receive special offers from our partners!' " +
            "checkboxes work properly - checking", priority = 12)
    public void accCreationPageTC12() {
        page.getNewsletterCheckbox().click();
        page.getSpecialOffersCheckbox().click();

        Assert.assertTrue(page.getNewsletterCheckbox().isSelected() && page.getSpecialOffersCheckbox().isSelected());
    }

    @Test(description = "Confirm 'Sign up for our newsletter!' & 'Receive special offers from our partners!' " +
            "checkboxes work properly - unchecking", priority = 13)
    public void accCreationPageTC13() {
        page.getNewsletterCheckbox().click();
        page.getSpecialOffersCheckbox().click();

        Assert.assertFalse(page.getNewsletterCheckbox().isSelected() && page.getSpecialOffersCheckbox().isSelected());
    }

    @Test(description = "Confirm account creation impossible if 'Company' field exceeds character limit (64)",
            priority = 14)
    public void accCreationPageTC14() {
        page.getCompanyNameInputField().sendKeys("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                Keys.RETURN);

        Assert.assertTrue(page.containsText("company is too long. Maximum length: 64"));
    }

    @Test(description = "Confirm account creation impossible if 'Address' field gets populated with invalid characters", priority = 15)
    public void accCreationPageTC15() {
        page.getAddressInputField().clear();

        page.getAddressInputField().sendKeys("!@#", Keys.RETURN);

        Assert.assertTrue(page.containsText("address1 is invalid"));
    }

    @Test(description = "Confirm account creation impossible if 'Address' field exceeds character limit (64)",
            priority = 16)
    public void accCreationPageTC16() {
        page.getAddressInputField().clear();

        page.getAddressInputField().sendKeys("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Keys.RETURN);

        Assert.assertTrue(page.containsText("address1 is too long. Maximum length: 128"));
    }

    @Test(description = "Confirm number of errors decreases by one if 'Address' field gets populated with valid data"
            , priority = 17)
    public void accCreationPageTC17() {
        page.getAddressInputField().clear();
        page.getCompanyNameInputField().clear();

        page.getAddressInputField().sendKeys("99 Main Street", Keys.RETURN);

        Assert.assertTrue(page.containsText("There are 6 errors"));
    }

    @Test(description = "Confirm account creation impossible if 'City' field gets populated with invalid characters",
            priority = 18)
    public void accCreationPageTC18() {
        page.getCityInputField().sendKeys("!@#", Keys.RETURN);

        Assert.assertTrue(page.containsText("city is invalid"));
    }

    @Test(description = "Confirm account creation impossible if 'City' field exceeds character limit (64)", priority
            = 19)
    public void accCreationPageTC19() {
        page.getCityInputField().clear();

        page.getCityInputField().sendKeys("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Keys.RETURN);

        Assert.assertTrue(page.containsText("city is too long. Maximum length: 64"));
    }

    @Test(description = "Confirm number of errors decreases by one if 'City' field gets populated with valid data",
            priority = 20)
    public void accCreationPageTC20() {
        page.getCityInputField().clear();

        page.getCityInputField().sendKeys("Warsaw", Keys.RETURN);

        Assert.assertTrue(page.containsText("There are 5 errors"));
    }

    @Test(description = "Confirm number of errors decreases by one if 'United States' gets selected from the 'Country' dropdown list", priority = 21)
    public void accCreationPageTC21() {
        page.setCountryToUSA();
        page.clickOnRegisterButton();

        Assert.assertTrue(page.containsText("There are 4 errors"));
    }

    @Test(description = "Confirm number of errors decreases by one if anything other than '-' gets selected from the 'State' dropdown list", priority = 22)
    public void accCreationPageTC22() {
        page.setState("Texas");
        page.clickOnRegisterButton();

        Assert.assertTrue(page.containsText("There are 3 errors"));
    }

    @Test(description = "Confirm account creation impossible if 'Zip/Postal Code' field gets populated with characters other than numbers or the length of the number is other than 5", priority = 23)
    public void accCreationPageTC23() {
        page.getPostCodeInputField().sendKeys("aaaaa", Keys.RETURN);

        Assert.assertTrue(page.containsText("The Zip/Postal code you've entered is invalid. It must follow this format: 00000"));
    }

    @Test(description = "Confirm number of errors decreases by one if proper 'Zip/Postal Code gets inputted", priority = 24)
    public void accCreationPageTC24() {
        page.getPostCodeInputField().clear();
        page.getPostCodeInputField().sendKeys("12345", Keys.RETURN);

        Assert.assertTrue(page.containsText("There are 2 errors"));
    }

    @Test(description = "Confirm account creation impossible if 'Additional information' field exceeds character limit (300)", priority = 25)
    public void accCreationPageTC25() {
        page.getAdditionalInformationTextarea().sendKeys("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        page.clickOnRegisterButton();

        Assert.assertTrue(page.containsText("other is too long. Maximum length: 300"));
    }

    @Test(description = "Confirm account creation impossible if 'Home phone' field populated with invalid characters", priority = 26)
    public void accCreationPageTC26() {
        page.getAdditionalInformationTextarea().clear();
        page.getPhoneInputField().sendKeys("!", Keys.RETURN);

        Assert.assertTrue(page.containsText("phone is invalid"));
    }

    @Test(description = "Confirm account creation impossible if 'Home phone' field exceeds character limit (32)", priority = 27)
    public void accCreationPageTC27() {
        page.getPhoneInputField().clear();
        page.getPhoneInputField().sendKeys("111111111111111111111111111111111", Keys.RETURN);

        Assert.assertTrue(page.containsText("phone is too long. Maximum length: 32"));
    }

    @Test(description = "Confirm account creation impossible if 'Mobile phone' field populated with invalid characters", priority = 28)
    public void accCreationPageTC28() {
        page.getMobilePhoneInputField().sendKeys("!", Keys.RETURN);

        Assert.assertTrue(page.containsText("phone_mobile is invalid"));
    }

    @Test(description = "Confirm account creation impossible if 'Mobile phone' field exceeds character limit (32)", priority = 29)
    public void accCreationPageTC29() {
        page.getMobilePhoneInputField().clear();
        page.getMobilePhoneInputField().sendKeys("111111111111111111111111111111111", Keys.RETURN);

        Assert.assertTrue(page.containsText("phone_mobile is too long. Maximum length: 32"));
    }

    @Test(description = "Confirm number of errors decreases by one if either or both 'Home phone' or 'Mobile phone' field gets populated with valid data", priority = 30)
    public void accCreationPageTC30() {
        page.getMobilePhoneInputField().clear();
        page.getPhoneInputField().clear();
        page.getMobilePhoneInputField().sendKeys("(48)123456789");
        page.getPhoneInputField().sendKeys("+48 123456789", Keys.RETURN);

        Assert.assertTrue(page.containsText("There is 1 error"));
    }

    @Test(description = "Confirm account creation impossible if 'Password' field gets populated with less than 5 and more than 32 characters", priority = 31)
    public void accCreationPageTC31() {
        page.getPasswordInputField().sendKeys("1", Keys.RETURN);

        Assert.assertTrue(page.containsText("passwd is invalid"));
    }

    @Test(description = "Confirm account has been created if  'Password' field gets populated with more than 4 and less than 33 characters", priority = 32)
    public void accCreationPageTC32() {

        String myAccountURL = "http://automationpractice.com/index.php?controller=my-account";

        page.getPasswordInputField().clear();
        page.getPasswordInputField().sendKeys("./0123456789:;<=>?@ABCDEFGHIJKLM", Keys.RETURN);

        Assert.assertEquals(driver.getCurrentUrl(), myAccountURL);
    }
}