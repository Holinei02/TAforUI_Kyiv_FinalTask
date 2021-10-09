package pageobject.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.nio.charset.StandardCharsets;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;

public class RadioButtonsTests extends BaseTest{
    private WebDriver driver;
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
    public void checkCorrectWordsAmountGeneration(int value, int expected) {

        driver.findElement(xpath("//input[contains(@name,'amount')]")).clear();
        driver.findElement(xpath("//input[contains(@name,'amount')]")).sendKeys(String.valueOf(value));
        driver.findElement(xpath("//input[contains(@id,'generate')]")).click();
        String generatedString = driver.findElement(xpath("//div[contains(@id,'lipsum')]")).getText();
        String[] words = generatedString.split("\\s+");
        assertEquals(words.length, expected);

    }


    @ParameterizedTest
    @MethodSource("TestDataProvider")
    public void checkCorrectBytesAmountGeneration(int value, int expected) {
        driver.findElement(xpath("//input[contains(@value,'bytes')]")).click();
        driver.findElement(xpath("//input[contains(@name,'amount')]")).clear();
        driver.findElement(xpath("//input[contains(@name,'amount')]")).sendKeys(String.valueOf(value));
        driver.findElement(xpath("//input[contains(@id,'generate')]")).click();
        String generatedString = driver.findElement(xpath("//div[contains(@id,'lipsum')]")).getText();
        assertEquals(generatedString.getBytes(StandardCharsets.UTF_8).length, expected);

    }
}
