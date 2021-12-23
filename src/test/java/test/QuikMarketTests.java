package test;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.junit.Test;
import pageobject_model.page.HomePage;
import pageobject_model.page.LoginPage;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class QuikMarketTests {

    private WebDriver driver;
    private ChromeOptions options;
    private LoginPage loginPageObj;
    private HomePage homePage;

    private String nameOfLotBeforeSubmit = "CNYRUB_SPT";
    private String nameOfLotAfterTocketSend;

    private String costToPayForTicketBefore;
    private String costToPayForTicketAfter;

    private String timeBeforeTicketSending;
    private String timeAfterTicketSending;

    private String numberOfLotsBeforeTicketSend = "5";
    private String costPerInstrument = "0.000100";
    private String numberOfLotsAfterTicketSend;
    private String costPerInstrumentAfterTicketSend;
    private String nameOfNewInstrument = "CNYRUB_SPT";
    private String getNameOfNewInstumentAfterSubmit;




    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @Before
    public void browserSetup() {
        options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        options.addArguments("start-maximized");
        options.addArguments("--auto-open-devtools-for-tabs");


        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(15000,
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(15000,
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(16000,
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

       setDriver();
    }

    @Test
    public  void createNewTicketWithLimits() throws InterruptedException {
        driver.get("https://junior.webquik.ru/");
        Thread.sleep(20000);

        loginPageObj = new LoginPage(driver);
        loginPageObj.singIntoSystemAsUser("U0193146","07181");

        homePage = new HomePage(driver);
        //timeBeforeTicketSending = homePage.getTimeBeforTicketSend();
        homePage.openWindowOfCreationTicket();
        homePage.fillFieldsOnTicketWindow(nameOfLotBeforeSubmit,numberOfLotsBeforeTicketSend,costPerInstrument);

        costToPayForTicketBefore = homePage.getSumOfTransactionOfLimitTicketBeforTicketSend();

        homePage.pressSubmitTicketButton();
        homePage.sendOfCretedTicketButton();


        nameOfLotAfterTocketSend = homePage.getNameOfLotAfterSubmit();
        costToPayForTicketAfter = homePage.getSumForTicketAfterSubmit();
        numberOfLotsAfterTicketSend = homePage.getCountOfLotsAfterTicketSend();
        costPerInstrumentAfterTicketSend = homePage.getCostPerIntrumentAfterSubmit();
        //timeAfterTicketSending = homePage.getTimeAfterTicketSend();

        Thread.sleep(5000);

        Assert.assertEquals(nameOfLotBeforeSubmit,nameOfLotAfterTocketSend);
        Assert.assertEquals(costToPayForTicketBefore,costToPayForTicketAfter);
        //Assert.assertNotEquals(timeBeforeTicketSending,timeAfterTicketSending);
        Assert.assertEquals(numberOfLotsBeforeTicketSend,numberOfLotsAfterTicketSend);
        Assert.assertEquals(costPerInstrument,costPerInstrumentAfterTicketSend);
    }

    @Test
    public  void createNewTicketWithMarketablePrice() throws InterruptedException {
        driver.get("https://junior.webquik.ru/");
        Thread.sleep(20000);

        loginPageObj = new LoginPage(driver);
        loginPageObj.singIntoSystemAsUser("U0193146","07181");

        homePage = new HomePage(driver);
        //timeBeforeTicketSending = homePage.getTimeBeforTicketSend();
        homePage.openWindowOfCreationTicket();

        homePage.fillFieldsOnTicketWindow(nameOfLotBeforeSubmit,numberOfLotsBeforeTicketSend,costPerInstrument);

        costToPayForTicketBefore = homePage.getSumOfTransactionOfLimitTicketBeforTicketSend();

        homePage.setStopTicketMethod();
        homePage.pressSubmitTicketButton();
        homePage.sendOfCretedTicketButton();


        //timeAfterTicketSending = homePage.getTimeAfterTicketSend();
        nameOfLotAfterTocketSend = homePage.getNameOfLotAfterSubmit();
        costToPayForTicketAfter = homePage.getSumForTicketAfterSubmit();
        numberOfLotsAfterTicketSend = homePage.getCountOfLotsAfterTicketSend();
        costPerInstrumentAfterTicketSend = homePage.getCostPerIntrumentAfterSubmit();

        Thread.sleep(5000);

        Assert.assertEquals(nameOfLotBeforeSubmit,nameOfLotAfterTocketSend);
        Assert.assertEquals(costToPayForTicketBefore,costToPayForTicketAfter);
        //Assert.assertNotEquals(timeBeforeTicketSending,timeAfterTicketSending);
        Assert.assertEquals(numberOfLotsBeforeTicketSend,numberOfLotsAfterTicketSend);
        Assert.assertEquals(costPerInstrument,costPerInstrumentAfterTicketSend);
    }

    /*@Test
    public  void addingNewInstrument() throws InterruptedException {
        driver.get("https://junior.webquik.ru/");
        Thread.sleep(15000);

        loginPageObj = new LoginPage(driver);
        loginPageObj.singIntoSystemAsUser("U0193146","08134");

        homePage = new HomePage(driver);

        homePage.addNewInstrumentButtonClick();
        homePage.inputNameOfNewInstrument(nameOfNewInstrument);
        Thread.sleep(5000);
        homePage.selectElementOfAddingListOfNewInstument();


        getNameOfNewInstumentAfterSubmit = homePage.getNameOfNewInstumentAfterSubmit();
    }

     */

    @After
    public void browserShutDown() {
        driver.quit();
        driver = null;
    }
}
