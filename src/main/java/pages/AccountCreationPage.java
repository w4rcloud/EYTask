package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AccountCreationPage {

    private WebDriver driver;

    @FindBy(id = "id_gender1")
    private WebElement maleRadioButton;

    @FindBy(id = "id_gender2")
    private WebElement femaleRadioButton;

    @FindBy(id = "customer_firstname")
    private WebElement firstNameInputField;

    @FindBy(id = "customer_lastname")
    private WebElement lastNameInputField;

    @FindBy(id = "firstname")
    private WebElement firstNameOnAddressInputField;

    @FindBy(id = "lastname")
    private WebElement lastNameOnAddressInputField;

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(id = "passwd")
    private WebElement passwordInputField;

    @FindBy(id = "days")
    private WebElement dayOfBirthDropdown;

    @FindBy(id = "months")
    private WebElement monthOfBirthDropdown;

    @FindBy(id = "years")
    private WebElement yearOfBirthDropdown;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement specialOffersCheckbox;

    @FindBy(id = "company")
    private WebElement companyNameInputField;

    @FindBy(id = "address1")
    private WebElement addressInputField;

    @FindBy(id = "address2")
    private WebElement addressLine2InputField;

    @FindBy(id = "city")
    private WebElement cityInputField;

    @FindBy(id = "id_state")
    private WebElement stateDropdownList;

    @FindBy(id = "postcode")
    private WebElement postCodeInputField;

    @FindBy(xpath = "//*[@id=\"id_country\"]")
    private WebElement countryDropdownList;

    @FindBy(id = "other")
    private WebElement additionalInformationTextarea;

    @FindBy(id = "phone")
    private WebElement phoneInputField;

    @FindBy(id = "phone_mobile")
    private WebElement mobilePhoneInputField;

    @FindBy(id = "alias")
    private WebElement addressAliasInputField;

    @FindBy(id = "submitAccount")
    private WebElement registerButton;

    public AccountCreationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnRegisterButton() {
        registerButton.click();
    }

    public void selectDateOfBirth(int day, String month, int year) {
        Select selectDay = new Select(dayOfBirthDropdown);
        selectDay.selectByVisibleText(String.valueOf(day));
        Select selectMonth = new Select(monthOfBirthDropdown);
        selectMonth.selectByVisibleText(month);
        Select selectYear = new Select(yearOfBirthDropdown);
        selectYear.selectByVisibleText(String.valueOf(year));
    }

    public void setCountryToNone() {
        Select select = new Select(countryDropdownList);
        select.selectByVisibleText("-");
    }

    public void setCountryToUSA() {
        Select select = new Select(countryDropdownList);
        select.selectByVisibleText("United States");
    }

    public void setState(String state) {
        Select select = new Select(stateDropdownList);
        select.selectByVisibleText(state);
    }

    public boolean containsText(String text) {
        String bodyText = driver.findElement(By.tagName("body")).getText().toLowerCase();
        return bodyText.contains(text.toLowerCase());
    }



    public WebElement getFirstNameOnAddressInputField() {
        return firstNameOnAddressInputField;
    }

    public WebElement getLastNameOnAddressInputField() {
        return lastNameOnAddressInputField;
    }

    public WebElement getEmailInputField() {
        return emailInputField;
    }

    public WebElement getPasswordInputField() {
        return passwordInputField;
    }

    public WebElement getDayOfBirthDropdown() {
        return dayOfBirthDropdown;
    }

    public WebElement getMonthOfBirthDropdown() {
        return monthOfBirthDropdown;
    }

    public WebElement getYearOfBirthDropdown() {
        return yearOfBirthDropdown;
    }

    public WebElement getNewsletterCheckbox() {
        return newsletterCheckbox;
    }

    public WebElement getSpecialOffersCheckbox() {
        return specialOffersCheckbox;
    }

    public WebElement getCompanyNameInputField() {
        return companyNameInputField;
    }

    public WebElement getAddressInputField() {
        return addressInputField;
    }

    public WebElement getAddressLine2InputField() {
        return addressLine2InputField;
    }

    public WebElement getCityInputField() {
        return cityInputField;
    }

    public WebElement getCountryDropdownList() {
        return countryDropdownList;
    }

    public WebElement getAdditionalInformationTextarea() {
        return additionalInformationTextarea;
    }

    public WebElement getPhoneInputField() {
        return phoneInputField;
    }

    public WebElement getMobilePhoneInputField() {
        return mobilePhoneInputField;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }

    public WebElement getMaleRadioButton() {
        return maleRadioButton;
    }

    public WebElement getFemaleRadioButton() {
        return femaleRadioButton;
    }

    public WebElement getAddressAliasInputField() {
        return addressAliasInputField;
    }

    public WebElement getFirstNameInputField() {
        return firstNameInputField;
    }

    public WebElement getLastNameInputField() {
        return lastNameInputField;
    }

    public WebElement getPostCodeInputField() {
        return postCodeInputField;
    }
}
