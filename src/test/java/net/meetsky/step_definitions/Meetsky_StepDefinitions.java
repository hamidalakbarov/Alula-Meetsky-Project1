package net.meetsky.step_definitions;

import io.cucumber.java.en.*;
import net.meetsky.pages.LoginPage;
import net.meetsky.utilities.BrowserUtils;
import net.meetsky.utilities.ConfigurationReader;
import net.meetsky.utilities.Driver;
import org.junit.Assert;

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

    @Given("user is on the files page")
    public void user_is_on_the_files_page() {
        loginPage.login();

    }
    @When("user clicks on the three dots icon next to the file with the favorite icon")
    public void user_clicks_on_the_three_dots_icon_next_to_the_file_with_the_favorite_icon() {

    }
    @When("user clicks {string} option")
    public void user_clicks_option(String string) {

    }
    @When("user clicks on the {string} option from top left corner.")
    public void user_clicks_on_the_option_from_top_left_corner(String string) {

    }
    @Then("user shouldn't see the removed file or folder among favorites")
    public void user_shouldn_t_see_the_removed_file_folder_among_favorites() {

    }

    @When("user clicks on the three dots icon next to the file or folder.")
    public void user_clicks_on_the_three_dots_icon_next_to_the_file_or_folder() {

    }

    @When("user clicks on the Favorites option from the top left menu")
    public void user_clicks_on_the_favorites_option_from_the_top_left_menu() {

    }
    @Then("user should see the added file in the favourites list")
    public void user_should_see_the_added_file_in_the_favourites_list() {

    }

}
