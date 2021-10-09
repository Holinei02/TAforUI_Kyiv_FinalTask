package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UkrainianHomePage extends BasePage{

    private static final String UKRAINIAN_PARAGRAPHS_LIST = "//div[contains(@id,'Panes')]/div";

    public UkrainianHomePage(WebDriver driver) { super(driver); }

    public int getParagraphListSize(){ return getParagraphsList().size(); }

    public List<WebElement> getParagraphsList(){
        return driver.findElements(By.xpath(UKRAINIAN_PARAGRAPHS_LIST));

    }
}
