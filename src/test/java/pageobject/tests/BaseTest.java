package pageobject.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pageobject.pages.HomePage;
import pageobject.pages.LoremGeneratedPage;
import pageobject.pages.UkrainianHomePage;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BaseTest {

    private WebDriver driver;
    private static final String LOREMIPSUM_URL = "https://lipsum.com/";

    @BeforeTest
    public void profileSetUp() {
        chromedriver().setup();
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(LOREMIPSUM_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public HomePage getHomePage() {
        return new HomePage(getDriver());
    }

    public UkrainianHomePage getUkrainianHomePage() {
        return new UkrainianHomePage(getDriver());
    }

    public LoremGeneratedPage getLoremGeneratedPage() { return new LoremGeneratedPage(getDriver()); }

}
