package loremipsum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoremIpsumTests {

    private WebDriver driver;
    public void waitForPageLoadComplete(long timeToWait) {
        new WebDriverWait(driver, timeToWait).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    public void waitForAjaxToCompletePdp(long timeToWait) {
        new WebDriverWait(driver, timeToWait).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return window.jQuery != undefined && jQuery.active <=2;"));
    }




        @BeforeTest
    public void profileSetUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://lipsum.com/");

    }

    @Test(priority = 1)
    public void checkThatCorrectWordAppearingInFirstParagraph(){
        driver.findElement(xpath("//a[contains(@href,'uk.lipsum')]")).click();
        List<WebElement> paragraphList = driver.findElements(xpath("//div[contains(@id,'Panes')]/div"));
        assertThat(paragraphList).hasSizeGreaterThan(1);
        assertThat(paragraphList.get(0).getText())
                .contains("риба");
    }

    @Test(priority = 2)
    public void SecTask() {
        driver.findElement(xpath("//input[contains(@id,'generate')]")).click();
        waitForPageLoadComplete(30);
        List<WebElement> elementList = driver.findElements(xpath("//div[contains(@id,'lipsum')]/p"));
        assertThat(elementList).hasSizeGreaterThan(1);
        assertThat(elementList.get(0).getText())
                .startsWith("Lorem ipsum dolor sit amet, consectetur adipiscing elit");

    }

    static Object[][] TestDataProvider(){
        return new Object[][]{
                {10, 10},
                {-1, 5},
                {0, 5},
                {20, 20}
        };
    }

    @ParameterizedTest
    @MethodSource("TestDataProvider")
    void test(int value, int expected) {

    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
