package ua.org.autotest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewLetterPage {

    public void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebDriver driver;

    @FindBy(css = "#\\3a a4")
    WebElement sendTo;

    @FindBy(css = "#\\3a 9m")
    WebElement subject;

    @FindBy(css = "#\\3a ar")
    WebElement content;

    @FindBy(css = "#\\3a 9c")
    WebElement sendButton;

    public NewLetterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewLetterPage sendMessage(String email, String letterSubject, String letterContent) {
        sendTo.sendKeys(email);
        subject.sendKeys(letterSubject);
        content.sendKeys(letterContent);
        sendButton.click();
        return new NewLetterPage(driver);
    }
}
