package loremipsum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.nio.charset.StandardCharsets;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;

public class Task2tests {

    private WebDriver driver;

    @BeforeAll
    public static void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeEach
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://lipsum.com/");
    }

    static Object[][] TestDataProvider() {
        return new Object[][]{
                {10, 10},
                {-1, 5},
                {0, 5},
                {20, 20}
        };
    }

    @ParameterizedTest
    @MethodSource("TestDataProvider")
    public void test(int value, int expected) {
        driver.findElement(xpath("//input[contains(@value,'words')]")).click();
        driver.findElement(xpath("//input[contains(@name,'amount')]")).clear();
        driver.findElement(xpath("//input[contains(@name,'amount')]")).sendKeys(String.valueOf(value));
        driver.findElement(xpath("//input[contains(@id,'generate')]")).click();
        String generatedString = driver.findElement(xpath("//div[contains(@id,'lipsum')]")).getText();
        String[] words = generatedString.split("\\s+");
        assertEquals(words.length, expected);

    }


    @ParameterizedTest
    @MethodSource("TestDataProvider")
    public void testbytes(int value, int expected) {
        driver.findElement(xpath("//input[contains(@value,'bytes')]")).click();
        driver.findElement(xpath("//input[contains(@name,'amount')]")).clear();
        driver.findElement(xpath("//input[contains(@name,'amount')]")).sendKeys(String.valueOf(value));
        driver.findElement(xpath("//input[contains(@id,'generate')]")).click();
        String generatedString = driver.findElement(xpath("//div[contains(@id,'lipsum')]")).getText();
        assertEquals(generatedString.getBytes(StandardCharsets.UTF_8).length, expected);

    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
