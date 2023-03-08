package net.meetsky.pages;

import net.meetsky.utilities.BrowserUtils;
import net.meetsky.utilities.ConfigurationReader;
import net.meetsky.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://qa.meetsky.net/index.php/login?clear=1
public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "user")
    public WebElement userNameInputBox;

    @FindBy(id = "password")
    public WebElement passwordInputBox;

    @FindBy(id = "submit-form")
    public WebElement loginButton;

    @FindBy(xpath = "//p[contains(@class, \"warning\")]")
    public WebElement wrongUsernameMessage;

    public void login() {
        userNameInputBox.sendKeys(ConfigurationReader.getProperty("username"));
        passwordInputBox.sendKeys(ConfigurationReader.getProperty("password"));
        loginButton.click();
    }

    public void login(String username, String password) {
        userNameInputBox.sendKeys(username);
        passwordInputBox.sendKeys(password);

    }
    
}