package net.meetsky.pages;

import net.meetsky.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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


}
