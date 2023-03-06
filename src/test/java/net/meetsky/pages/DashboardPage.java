package net.meetsky.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends BasePage{

    @FindBy(xpath = "//li[@tabindex]//a[@aria-label]")
    public List<WebElement> topModules;
}
