package ua.org.autotest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AutoTest {

    private static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 5);

    public static NewLetterPage newLetterPage;

    @BeforeClass
    public static void Enter() {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        newLetterPage = new NewLetterPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://accounts.google.com");
    }

    @Test
    public void testRunning() {
        loginGoogle("malaktest01", "TestTest");
        sendMail();
        loginMailinator("malaktest01@gmail.com");
        checkingFields("Андрей Малахов", "1st letter");
        checkingContent();
    }

    public void loginGoogle(String username, String password) {
        driver.findElement(By.cssSelector("#identifierId")).sendKeys(username);
        driver.findElement(By.cssSelector("#identifierNext > content > span")).click();
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("#passwordNext > content > span")).click();
    }

    public void sendMail() {
        driver.findElement(By.cssSelector("#yDmH0d > div.FaV4Jb.xAuNcb >" +
                " c-wiz > div > div > div.hdPVYc.xpKNgc > div:nth-child(2) > c-wiz > " +
                "div > div.PoG2cf > div > div.ZY8k5b > div.vmZ0T > a.WaidBe")).click();
        driver.findElement(By.cssSelector("#\\3a 4s > div > div")).click();
        newLetterPage.sendMessage("malaktest01@mailinator.com", "1st letter", "Pronto pronto");
        WebElement dynamicElement = (new WebDriverWait(driver, 5)).
                until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span > span.bAq")));
        }

    public void loginMailinator(String username) {
        driver.get("https://mailinator.com");
        driver.findElement(By.cssSelector("#inboxfield")).sendKeys(username);
        driver.findElement(By.xpath("/html/body/section[1]/div/div[3]/" +
                "div[2]/div[2]/div[1]/span/button")).click();
    }

    public void checkingFields(String username, String subject){
        String verifyString1 = driver.findElement(By.cssSelector("#row_malaktest01-" +
                "1539437648-17294726 > td:nth-child(3)")).getText();
        Assert.assertEquals(username, verifyString1);

        String verifyString2 = driver.findElement(By.cssSelector("#row_malaktest01-1539437648" +
                "-17294726 > td:nth-child(4)")).getText();
        Assert.assertEquals(subject, verifyString2);
    }

    public void checkingContent() {
        driver.findElement(By.cssSelector("#row_malaktest01-1539437648" +
                "-17294726 > td:nth-child(4)")).click();
        try
        {
            driver.findElement(By.xpath("/html/body/div"));
        } catch(NoSuchElementException e)
        {
            System.out.print("Try again, the element was not found");
        }
    }

    @AfterClass
    public static void CLose()
    {
        driver.quit();
    }
}
