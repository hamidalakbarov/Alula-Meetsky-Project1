package net.meetsky.pages;

import net.meetsky.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FilesPage extends BasePage{
    public FilesPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (linkText = "Favorites")
    public WebElement favouritesPageButton;

    @FindBy (linkText = "Recent")
    public WebElement recentPageButton;

    @FindBy (linkText = "Shares")
    public WebElement sharesPageButton;

    @FindBy (linkText = "Tags")
    public WebElement tagsPageButton;

    @FindBy (id = "fileList")
    public WebElement fileList;

    @FindBy (xpath = "//span[@class='icon icon-starred']")
    public WebElement favouriteIcon;

    @FindBy (xpath = "//tbody[@id='fileList']//tr")
    public List<WebElement> allFilesAndFoldersRowsElements;

    @FindBy (xpath = "//tbody[@id='fileList']//td[@class='filename']//span[@class='innernametext']")
    public List<WebElement> allFilesAndFolders_WEforNames_InFavorites;

    @FindBy (xpath = "//span[.='Add to favorites']")
    public WebElement addToFavoritesButton;

    @FindBy (xpath = "//span[.='Remove from favorites']")
    public WebElement removeFromFavoritesButton;

    public String getFileNameOf(int fileNumber){
            return Driver.getDriver().findElement(By.xpath("//tbody[@id='fileList']//tr["+fileNumber+"]//span[@class='innernametext']")).getText();
    }

    public WebElement get3dotsMenuOf(int fileNumber){
        return Driver.getDriver().findElement(By.xpath("//tbody[@id='fileList']//tr["+fileNumber+"]//a[@data-action='menu']"));
    }


}
