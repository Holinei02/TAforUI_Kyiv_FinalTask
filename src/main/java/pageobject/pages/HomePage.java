package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    private static final String GENERATE_BUTTON = "//input[contains(@id,'generate')]";
    private static final String OPEN_UKRAINIAN_PAGE_BUTTON = "//a[contains(@href,'uk.lipsum')]";
    private static final String START_CHECKBOX = "//input[contains(@type,'checkbox')]";
    private static final String INPUT_FIELD = "//input[contains(@name,'amount')]";

    public HomePage(WebDriver driver) { super(driver); }

    public void clickOnGenerateButton(){ driver.findElement(By.xpath(GENERATE_BUTTON)).click(); }

    public void clickOpenUkrainianPageButton(){ driver.findElement(By.xpath(OPEN_UKRAINIAN_PAGE_BUTTON)).click(); }

    public void clickOnStartCheckbox(){ driver.findElement(By.xpath(START_CHECKBOX)).click(); }

    public void generateWithAmount(final int value){
        driver.findElement(By.xpath(INPUT_FIELD)).clear();
        driver.findElement(By.xpath(INPUT_FIELD)).sendKeys(String.valueOf(value));
        driver.findElement(By.xpath(GENERATE_BUTTON)).click();
    }

}
