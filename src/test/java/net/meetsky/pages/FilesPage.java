package net.meetsky.pages;

import net.meetsky.utilities.ConfigurationReader;
import net.meetsky.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class FilesPage extends BasePage implements ElementDisplayed {

    @FindBy(linkText = "Favorites")
    public WebElement favouritesPageButton;

    @FindBy(linkText = "Recent")
    public WebElement recentPageButton;

    @FindBy(linkText = "Shares")
    public WebElement sharesPageButton;

    @FindBy(linkText = "Tags")
    public WebElement tagsPageButton;

    @FindBy(id = "fileList")
    public WebElement fileList;

    @FindBy(xpath = "//span[@class='icon icon-starred']")
    public WebElement favouriteIcon;

    @FindBy(xpath = "//tbody[@id='fileList']//tr")
    public List<WebElement> allFilesAndFoldersRowsElements;

    @FindBy(xpath = "//tbody[@id='fileList']//td[@class='filename']//span[@class='innernametext']")
    public List<WebElement> allFilesAndFolders_WEforNames_InFavorites;

    @FindBy(xpath = "//span[.='Add to favorites']")
    public WebElement addToFavoritesButton;

    @FindBy(xpath = "//span[.='Remove from favorites']")
    public WebElement removeFromFavoritesButton;

    public String getFileNameOf(int fileNumber) {
        return Driver.getDriver().findElement(By.xpath("//tbody[@id='fileList']//tr[" + fileNumber + "]//span[@class='innernametext']")).getText();
    }

    public WebElement get3dotsMenuOf(int fileNumber) {
        return Driver.getDriver().findElement(By.xpath("//tbody[@id='fileList']//tr[" + fileNumber + "]//a[@data-action='menu']"));
    }

    @FindBy(xpath = "//td//div[contains(@style, 'folder')]")
    public WebElement firstFolder;

    @FindBy(xpath = "//a[contains(@class,'button new')]")
    public WebElement addIcon;

    @FindBy(xpath = "//label[@for='file_upload_start']")
    public WebElement uploadFileButton;

    @FindBy(xpath = "//label[@for='checkbox-allnewfiles']")
    public WebElement newFilesCheckbox;

    @FindBy(xpath = "//button[.='Continue']")
    public WebElement continueButton;

    public void uploadFileForMac(String filePath) {
        // To copy file into the clipboard
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        // Creating instance of Robot class
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {

        }
        // to switch the window focus
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_META); // press cmd
        robot.keyPress(KeyEvent.VK_TAB); // press tab
        robot.keyRelease(KeyEvent.VK_META); // release cmd
        robot.keyRelease(KeyEvent.VK_TAB); // release tab
        // to open GOTO window on MAC
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_META); // press cmd
        robot.keyPress(KeyEvent.VK_SHIFT); // press shift
        robot.keyPress(KeyEvent.VK_G); // press enter
        robot.keyRelease(KeyEvent.VK_META); // release cmd
        robot.keyRelease(KeyEvent.VK_SHIFT); // release shift
        robot.keyRelease(KeyEvent.VK_G); // release enter
        // to paste file
        robot.keyPress(KeyEvent.VK_META); // press cmd
        robot.keyPress(KeyEvent.VK_V); // press V
        robot.keyRelease(KeyEvent.VK_META); // release cmd
        robot.keyRelease(KeyEvent.VK_V); // release V
        robot.keyPress(KeyEvent.VK_ENTER); // press enter
        robot.keyRelease(KeyEvent.VK_ENTER); // release enter
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER); // press enter
        robot.keyRelease(KeyEvent.VK_ENTER); // release enter

        // to handle file duplicate html pop-up
        try {
            newFilesCheckbox.click();
            continueButton.click();
        } catch (RuntimeException e) {
        }
    } // by using Robot class

    /**
     * This method will take a file path as argument and check if it is displayed on the web page.
     * --> takes String parameter
     * <-- returns boolean if displayed or not
     */
    @Override
    public boolean elementIsDisplayed(String text) {
        // to read the text from file path an locate web element
        String textToLocate = text.substring(text.lastIndexOf('/') + 1, text.lastIndexOf('.'));
        return Driver.getDriver().findElement(By.xpath("//*[.='" + textToLocate + "']")).isDisplayed();
    }


    @FindBy(xpath = "//a[@id='comments']")
    public WebElement commentBtn;

    @FindBy(xpath = "//div[contains(@placeholder,'Write message')]")
    public WebElement commentInputBox;

    @FindBy(xpath = "//input[@class='comment__submit icon-confirm has-tooltip']")
    public WebElement submitArrow;

    @FindBy(xpath = "//span[@class='icon icon-add']")
    public WebElement plusIcon;

    @FindBy(xpath = "//input[@type ='file']")
    public WebElement uploadFile;

    public void addedFileIsDisplayed() {
        java.util.List<WebElement> addedFiles = Driver.getDriver().findElements(By.xpath("//a[@class='name']"));
        List<String> filesTexts = new ArrayList<>();
        for (WebElement eachFile : addedFiles) {
            filesTexts.add(eachFile.getText());
        }
        String expectedName = ConfigurationReader.getProperty("filePathUS07").substring(ConfigurationReader.getProperty("filePathUS07").lastIndexOf('/') + 1);
        Assert.assertTrue(filesTexts.contains(expectedName));
    }


    @FindBy(xpath = "//span[contains(@class, 'extra-data')]")
    public List<WebElement> allDeletedFilesFoldersList;

    @FindBy(xpath = "//a[@class='action action-menu permanent']")
    public WebElement threeDots;

    @FindBy(xpath = "//span[contains(@class, 'extra-data')]")
    public WebElement firstFile;


    public void clickActionIcons(String action) {
        String locator = "//li[@class=' action-" + action.toLowerCase() + "-container']/a";
        Driver.getDriver().findElement(By.xpath(locator)).click();
    }

    public void clickOnSubModules(String subModule) {
        String locator = "//ul[@class ='with-icon']//a[.='" + subModule + "']";
        Driver.getDriver().findElement(By.xpath(locator)).click();
    }

}

