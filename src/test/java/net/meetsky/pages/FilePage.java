package net.meetsky.pages;

import net.meetsky.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.nio.file.WatchEvent;

public class FilePage {

    public FilePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

   @FindBy(xpath = "(//li[@data-id='files'])[1]")
    public WebElement File;

   @FindBy(xpath = "//a[@class='button new']")
   public WebElement AddIcon;

   @FindBy(xpath = "//span[.='New folder']")
    public WebElement NewFolder;

   @FindBy(xpath = "(//input[@type='text'])[2]")
    public WebElement inputNewFolder;

   @FindBy(xpath = "(//input[@type='submit'])[2]")
    public WebElement submit;

   @FindBy(xpath = "//label[@for='select_all_files']/..")
    public WebElement SelectFiles;




}
