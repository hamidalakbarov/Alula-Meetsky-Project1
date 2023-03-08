package net.meetsky.step_definitions;

import io.cucumber.java.en.*;
import net.meetsky.pages.FilesPage;
import net.meetsky.pages.DashboardPage;
import net.meetsky.pages.LoginPage;
import net.meetsky.utilities.BrowserUtils;
import net.meetsky.utilities.ConfigurationReader;
import net.meetsky.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        loginPage.login(username,password);

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
        Assert.assertEquals(message, loginPage.wrongUsernameMessage.getText());
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
    public void verify_the_user_see_the_following_modules(List<String> expectedModules) {

        List<String> actualModules = new ArrayList<>();
        for (WebElement each : dashboardPage.topModules) {
            actualModules.add(each.getAttribute("aria-label"));
        }
        Assert.assertEquals(expectedModules, actualModules);

    }

    @Given("user is on the {string} page")
    public void user_is_on_the_files_page(String pageName) {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
        filesPage.clickDashboardModules(pageName);
    }

    @When("user clicks on the three dots icon next to the file with the favorite icon")
    public void user_clicks_on_the_three_dots_icon_next_to_the_file_with_the_favorite_icon() {

        if (filesPage.favouriteIcon.isDisplayed()) {
            filesPage.get3dotsMenuOf(1).click();

        }
    }
    @When("user clicks {string} option from file 3dotsMenu")
    public void user_clicks_option(String string) {
        if (string.equals("Download")){
            Driver.getDriver().findElement(By.xpath("(//span[.='"+string+"'])[2]")).click();
        }else {
            Driver.getDriver().findElement(By.xpath("//span[.='" + string + "']")).click();
        }
    }

    @Then("user shouldn't see the removed file or folder among favorites")
    public void user_shouldn_t_see_the_removed_file_folder_among_favorites() {
        List<String> allFilesAndFoldersInFavourites = new ArrayList<>();
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
        List<String> allFilesAndFoldersInFavourites = new ArrayList<>();
        for (WebElement each : filesPage.allFilesAndFolders_WEforNames_InFavorites) {
            allFilesAndFoldersInFavourites.add(each.getText());
        }
        Assert.assertTrue(allFilesAndFoldersInFavourites.contains(lastFileName));
    }

    @Given("user on the dashboard page")
    public void user_on_the_dashboard_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
    }

    @When("the user clicks the {string} module")
    public void the_user_clicks_the_module(String module) {
        dashboardPage.clickDashboardModules(module);
    }

    @When("user choose a folder from the page")
    public void user_choose_a_folder_from_the_page() {
        filesPage.firstFolder.click();
    }

    @When("user clicks the add icon on the top")
    public void user_clicks_the_add_icon_on_the_top() {
        filesPage.addIcon.click();
    }

    @When("the user uploads a file with the upload file option")
    public void the_user_uploads_a_file_with_the_upload_file_option() {
        // Using JS to click
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", filesPage.uploadFileButton);
        filesPage.uploadFileForMac(ConfigurationReader.getProperty("filePathUS_09"));
    }

    @Then("Verify the file is displayed on the page")
    public void verify_the_file_is_displayed_on_the_page() {
        Assert.assertTrue(filesPage.elementIsDisplayed(ConfigurationReader.getProperty("filePathUS_09")));
    }
    @Then("verify the page title is {string}")
    public void verify_the_page_title_is(String contacts) {
    BrowserUtils.verifyTitleContains(contacts);
    }
    @Given("User is on the home page")
    public void userIsOnTheHomePage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
    }

    @When("user clicks {string} option from top app menu")
    public void userClicksOptionFromTopAppMenu(String moduleName) {
        dashboardPage.clickDashboardModules(moduleName);
    }
    @And("user clicks Comments option")
    public void userClicksCommentsOption() {
        filesPage.commentBtn.click();
    }


    @And("user writs {string} inside the comment input box")
    public void userWritsInsideTheCommentInputBox(String theComment) {
        filesPage.commentInputBox.sendKeys(theComment);
    }

    @And("user clicks submit button to post it")
    public void userClicksSubmitButtonToPostIt() {
        filesPage.submitArrow.click();
    }

    @Then("user should see {string} displayed in the comment section")
    public void userShouldSeeDisplayedInTheCommentSection(String theComment) {
        Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//div[normalize-space()='"+theComment+"']")).isDisplayed());
    }


    @Given("user is on the home page")
    public void user_is_on_the_home_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
    }

    @When("user clicks the {string} module")
    public void user_clicks_the_module(String fileModule) {
        dashboardPage.clickDashboardModules(fileModule);
    }

    @When("user clicks action-icon from any file on the page")
    public void user_clicks_action_icon_from_any_file_on_the_page() {
        filesPage.threeDots.click();
    }

    @When("user chooses the {string} file or folder option")
    public void user_chooses_the_option(String action) {
        filesPage.clickActionIcons(action);
    }

    @When("user clicks the {string} sub-module on the left side")
    public void user_clicks_the_sub_module_on_the_left_side(String subModule) {
        filesPage.clickOnSubModules(subModule);
    }

    @Then("Verify the deleted file is displayed on the Deleted Files page")
    public void verify_the_deleted_file_is_displayed_on_the_deleted_files_page() {

        String deletedFileName = filesPage.firstFile.getAttribute("title");

        List<String> listOfDeletedFiles = BrowserUtils.listOfWE_to_ListOfString(filesPage.allDeletedFilesFoldersList, "title");

        Assert.assertTrue(listOfDeletedFiles.contains(deletedFileName));
    }

}
