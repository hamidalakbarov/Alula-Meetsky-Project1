package net.meetsky.step_definitions;

import io.cucumber.java.en.*;
import net.meetsky.pages.FilesPage;
import net.meetsky.pages.BasePage;
import net.meetsky.pages.DashboardPage;
import net.meetsky.pages.LoginPage;
import net.meetsky.utilities.BrowserUtils;
import net.meetsky.utilities.ConfigurationReader;
import net.meetsky.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Meetsky_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    FilesPage filesPage = new FilesPage();

    String lastFileName;
    String removedFileName;

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
        loginPage.login();
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
    @Given("user is on the {string} page")
    public void user_is_on_the_files_page(String pageName) {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
        filesPage.clickDashboardModules(pageName);
    }
    @When("user clicks on the three dots icon next to the file with the favorite icon")
    public void user_clicks_on_the_three_dots_icon_next_to_the_file_with_the_favorite_icon() {

        if (filesPage.favouriteIcon.isDisplayed()){
            filesPage.get3dotsMenuOf(1).click();

        }
    }
    @When("user clicks {string} option")
    public void user_clicks_option(String string) {

        Driver.getDriver().findElement(By.xpath("//span[.='"+string+"']")).click();
    }

    @Then("user shouldn't see the removed file or folder among favorites")
    public void user_shouldn_t_see_the_removed_file_folder_among_favorites() {
        List<String> allFilesAndFoldersInFavourites=new ArrayList<>();
        for (WebElement each : filesPage.allFilesAndFolders_WEforNames_InFavorites) {
            allFilesAndFoldersInFavourites.add(each.getText());
        }
        Assert.assertTrue(!allFilesAndFoldersInFavourites.contains(removedFileName));
    }

    @When("user clicks on the three dots icon next to any file or folder.")
    public void user_clicks_on_the_three_dots_icon_next_to_the_file_or_folder() {
        List<WebElement> allFilesAndFoldersRowsElements = filesPage.allFilesAndFoldersRowsElements;
        int numberOfFiles = allFilesAndFoldersRowsElements.size();

        lastFileName = filesPage.getFileNameOf(numberOfFiles);
        WebElement lastFile3dotsMenu = filesPage.get3dotsMenuOf(numberOfFiles);
        Actions action = new Actions(Driver.getDriver());
        action.moveToElement(lastFile3dotsMenu).click().perform();

    }

    @When("user clicks on the Favorites option from the top left menu")
    public void user_clicks_on_the_favorites_option_from_the_top_left_menu() {
        filesPage.favouritesPageButton.click();
    }
    @Then("user should see the added file in the favourites list")
    public void user_should_see_the_added_file_in_the_favourites_list() {
        List<String> allFilesAndFoldersInFavourites=new ArrayList<>();
        for (WebElement each : filesPage.allFilesAndFolders_WEforNames_InFavorites) {
            allFilesAndFoldersInFavourites.add(each.getText());
        }
        Assert.assertTrue(allFilesAndFoldersInFavourites.contains(lastFileName));
    }

}
