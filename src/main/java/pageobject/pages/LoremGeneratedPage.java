package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoremGeneratedPage extends BasePage{

    public static final String DEFAULT_PARAGRAPH_LIST = "//div[contains(@id,'lipsum')]/p";
    public static final String BACK_TO_HOME_PAGE_BUTTON = "//a[contains(@title,'Lorem Ipsum')]";

    public LoremGeneratedPage(WebDriver driver) { super(driver); }

    public void clickOnBackToHomePageButton(){ driver.findElement(By.xpath(BACK_TO_HOME_PAGE_BUTTON)).click(); }

    public List<WebElement> getParagraphsList(){
        return driver.findElements(By.xpath(DEFAULT_PARAGRAPH_LIST));

    }
}
