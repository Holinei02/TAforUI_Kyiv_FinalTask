package pageobject.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;



public class GenerationOptionsTests extends BaseTest{

    private String EXPECTED_WORD = "риба";
    private String SEARCH_WORD = "lorem";
    private String START_GENERATED_STRING = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";

    @Test(priority = 1)
    public void checkThatCorrectWordAppearingInFirstParagraph() {
        getHomePage().clickOpenUkrainianPageButton();
        getHomePage().implicitWait(30);
        assertThat(getUkrainianHomePage().getParagraphsList()).hasSizeGreaterThan(1);
        assertThat(getUkrainianHomePage().getParagraphsList().get(0).getText())
                .contains(EXPECTED_WORD);
    }

    @Test(priority = 2)
    public void checkThatDefaultGenerationStartsWithCorrectString(){
        getHomePage().clickOnGenerateButton();
        getHomePage().implicitWait(30);
        assertThat(getLoremGeneratedPage().getParagraphsList()).hasSizeGreaterThan(1);
        assertThat(getLoremGeneratedPage().getParagraphsList().get(0).getText())
                .startsWith(START_GENERATED_STRING);
    }

    @Test(priority = 3)
    public void verifyTheCheckbox(){
        getHomePage().clickOnStartCheckbox();
        getHomePage().clickOnGenerateButton();
        assertThat(getLoremGeneratedPage().getParagraphsList()).hasSizeGreaterThan(1);
        assertThat(getLoremGeneratedPage().getParagraphsList().get(0).getText())
                .doesNotStartWith(START_GENERATED_STRING);
    }

    @Test(priority = 4)
    public void checkWordInParagraphsGenerationProbability() throws Exception {
        int loremCounter = 0;
        for (int i = 0; i < 10; i++) {
            getHomePage().clickOnGenerateButton();
            getHomePage().implicitWait(30);
            assertThat(getLoremGeneratedPage().getParagraphsList()).hasSizeGreaterThan(1);
            for (WebElement webElement : getLoremGeneratedPage().getParagraphsList()) {
                if (webElement.getText().contains(SEARCH_WORD)) {
                    loremCounter++;
                }
            }
            if (loremCounter < 2) {
                throw new Exception("Not enough 'lorems' generated");
            }
            getLoremGeneratedPage().clickOnBackToHomePageButton();
        }
        if (loremCounter/10 < 0.4){
            throw new Exception("Not enough 'lorems' generated");
        }
    }
}
