package net.meetsky.pages;

import net.meetsky.utilities.BrowserUtils;
import net.meetsky.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    public static void clickDashboardModules(String option){
        String locator="//li[@data-id='"+option.toLowerCase()+"']";
        Driver.getDriver().findElement(By.xpath(locator)).click();
    }
}
