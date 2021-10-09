package pageobject.pages;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class RadioButton {

    WebDriver driver;

    public void setValue(String value){
        driver.findElement(xpath("//input[contains(@value, '" + value + "')]")).click();
    }
}
