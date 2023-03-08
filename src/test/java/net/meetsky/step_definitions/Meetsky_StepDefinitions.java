package net.meetsky.step_definitions;

import io.cucumber.java.en.*;
import net.meetsky.pages.BasePage;
import net.meetsky.pages.DashboardPage;
import net.meetsky.pages.FilePage;
import net.meetsky.pages.LoginPage;
import net.meetsky.utilities.BrowserUtils;
import net.meetsky.utilities.ConfigurationReader;
import net.meetsky.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Meetsky_StepDefinitions {

    LoginPage loginPage = new LoginPage();

    @Given("user on the login page")
    public void user_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("user use username {string} and passcode {string}")
    public void user_use_username_and_passcode(String username, String password) {
        loginPage.userNameInputBox.sendKeys(username);
        loginPage.passwordInputBox.sendKeys(password);
    }

    @When("user click the login button")
    public void user_click_the_login_button() {
        loginPage.loginButton.click();
    }
    @When("user enter invalid {string} and {string}")
    public void user_enter_invalid_and(String username, String password) {
        loginPage.userNameInputBox.sendKeys(username);
        loginPage.passwordInputBox.sendKeys(password);
    }

    @Then("verify {string} message should be displayed")
    public void verify_message_should_be_displayed(String message) {
        Assert.assertEquals(message,loginPage.wrongUsernameMessage.getText());
    }

    @Then("verify the user should be at the {string} page")
    public void verify_the_user_should_be_at_the_dashboard_page(String title) {
        BrowserUtils.verifyTitleContains(title);
    }

    @And("user logins with valid credentials")
    public void the_users_log_in_with_valid_credentials() {
        loginPage.login(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
    }
    DashboardPage dashboardPage = new DashboardPage();
    @Then("Verify the user see the following modules:")
    public void verify_the_user_see_the_following_modules( List<String> expectedModules) {

        List <String> actualModules = new ArrayList<>();
        for (WebElement each : dashboardPage.topModules) {
            actualModules.add(each.getAttribute("aria-label"));
        }
        Assert.assertEquals(expectedModules,actualModules);

    }

    //Saja US
    FilePage filePage = new FilePage();
    Actions actions = new Actions(Driver.getDriver());
    @Given("user on the dashboard page")
    public void user_on_the_dashboard_page() {
        Driver.getDriver().get("https://qa.meetsky.net");
        loginPage.userNameInputBox.sendKeys("User1");
        loginPage.passwordInputBox.sendKeys("Userpass123");
        loginPage.loginButton.click();

    }
    @When("the user clicks the {string} module")
    public void the_user_clicks_the_module(String File) {
      filePage.File.click();
    }
    @When("user clicks the add icon on the top")
    public void user_clicks_the_add_icon_on_the_top() {
        actions.moveToElement(filePage.AddIcon);
        filePage.AddIcon.click();
        BrowserUtils.sleep(3);

    }
    @When("user click \"new folder”")
    public void user_click_new_folder() {
        filePage.NewFolder.click();

    }
    @When("user write a folder name")
    public void user_write_a_folder_name() {
        filePage.inputNewFolder.sendKeys("Saja Folder");
        BrowserUtils.sleep(2);
    }
    @When("the user click submit icon")
    public void the_user_click_submit_icon() {
        filePage.submit.click();
        BrowserUtils.sleep(2);
    }
    @Then("Verify the folder is displayed on the page")
    public void verify_the_folder_is_displayed_on_the_page() {
        filePage.SelectFiles.isDisplayed();
        String expected = "Saja Folder";

       Assert.assertTrue(expected,filePage.SelectFiles.isDisplayed());
    }

}
