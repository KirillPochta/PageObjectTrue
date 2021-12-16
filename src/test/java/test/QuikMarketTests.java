package test;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.HomePage;
import pageobject_model.page.LoginPage;

import java.util.concurrent.TimeUnit;

public class QuikMarketTests {

    private WebDriver driver;
    private ChromeOptions options;
    private LoginPage loginPageObj;
    private HomePage homePage;

    private String timeBeforeTicket;
    private String timeAfterTicket;

    private String myTradeNumber = "MB1000100002";
    private String tradeNumberAfter;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        options.addArguments("start-maximized");


        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(15000,
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(15000,
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(16000,
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @Test(description = "create ticket with limits")
    public  void createNewTicketWithLimits() throws InterruptedException {
        driver.get("https://junior.webquik.ru/");
        Thread.sleep(10000);

        loginPageObj = new LoginPage(driver);
        loginPageObj.singIntoSystemAsUser("U0193146","08134");

        homePage = new HomePage(driver);

        timeBeforeTicket = homePage.setDateBeforTicketCreation();

        homePage.openWindowOfCreationTicket();
        homePage.fillFieldsOnTicketWindow("CNYRUB_SPT","5","1");

        homePage.pressSubmitTicketButton();
        tradeNumberAfter = homePage.tradeNumberAfterSubmit();
        homePage.sendOfCretedTicketButton();

        timeAfterTicket = homePage.setDateAfterTicketCreation();

        Assert.assertNotEquals(timeBeforeTicket,timeAfterTicket);
        Assert.assertEquals(tradeNumberAfter,"MB1000100002");
    }

    @Test(description = "create ticket with stop option")
    public  void createNewTicketWithMarketablePrice() throws InterruptedException {
        driver.get("https://junior.webquik.ru/");
        Thread.sleep(10000);

        loginPageObj = new LoginPage(driver);
        loginPageObj.singIntoSystemAsUser("U0193146","08134");

        homePage = new HomePage(driver);
        homePage.openWindowOfCreationTicket();
        homePage.fillFieldsOnTicketWindow("CNYRUB_SPT","5","1");
        homePage.setStopTicketMethod();
        homePage.pressSubmitTicketButton();
        tradeNumberAfter = homePage.tradeNumberAfterSubmit();
        homePage.sendOfCretedTicketButton();
        Assert.assertEquals(tradeNumberAfter,myTradeNumber);
    }

    @AfterMethod(alwaysRun = true)
    public void browserShutDown() {
        driver.quit();
        driver = null;
    }
}