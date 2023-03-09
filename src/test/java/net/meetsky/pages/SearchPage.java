package net.meetsky.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{

    @FindBy(xpath = "//input[@type='search']")
    public WebElement searchInputBox;

    //span[contains(@title, file)]

    @FindBy( xpath = "//span[@class = 'unified-search__result-content']")
    public WebElement displayedResult;
}
