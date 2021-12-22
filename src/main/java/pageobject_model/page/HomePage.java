package pageobject_model.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public  class HomePage {

    private WebDriver driver;

    @FindBy(xpath = "//span[contains(@style,'two-fingers')]")
    private WebElement newTicketButton;

    @FindBy(xpath = "//input[contains(@tabindex,'5')]")
    private WebElement tradeNumber;

    @FindBy(xpath = "//input[contains(@style,'height: 24px;')]")
    private WebElement inputNameOfLot;

    @FindBy(xpath = "//td[contains(@id,'combobox-2')]//input[contains(@tabindex,'6')]")
    private WebElement clientsCode;

    @FindBy(xpath = "//td[@colspan=3]//td[contains(@class,'x-form-trigger-input')]//input[contains(@tabindex,'1')]")
    private WebElement numberOfLotsToBuy; //5

    @FindBy(xpath = "//td[contains(@class,'x-form-tr')]//input[contains(@tabindex,'11')]")
    private WebElement costForInstrument; //1

    @FindBy(xpath = "//table[contains(@style,'195px; top: 25px')]//div[contains(@role,'input')]")
    private WebElement sumOfTransactionBeforeSubmit;
    //*[@id="submitBtn2-btnIconEl"]
    @FindBy(xpath = "//a[@tabindex=41]//span[contains(@style,'height: 19px')]//span[contains(@id,'submit')]//span[@role='img']")
    private WebElement submitTicketButton;

    @FindBy(xpath = "//a[contains(@style,'left: 271px; top: 0px;')]//span[contains(@style,'height: 19px')]//span[@role='img'][1]")
    private WebElement sendTicketButton;


    @FindBy(xpath = "//td[@colspan=2]//div[contains(@style,'width: 100%')][1]")
    private WebElement tradeNumberAfterSubmitTicket;


    @FindBy(xpath = "//div[contains(@style,'left: 706px; margin: 0px')]")
    private WebElement timeBeforeTicketCreate;

    @FindBy(xpath = "//div[contains(@style,'left: 706px; margin: 0px')]")
    private WebElement timeAfterTicketCreate;

    @FindBy(xpath = "//td[@colspan='3']//input[@tabindex='16']")
    private WebElement setStopTicket;

    @FindBy(xpath = "//a[contains(@style,'0px; margin: 0px; width: 154px;')]")
    private WebElement setMarkettypeOfTicket;


    @FindBy(xpath = "//table[contains(@style,'margin: 10px 0px; table-layout: auto;')]/tbody/tr/td[last()]/div")
    private WebElement sumOfTransactionMrketTypeOfTicket;

    @FindBy(xpath = "//table[@style='position: absolute; top: 0px; width: 1200px;']/tbody/tr[last()]/td[4]")
    private WebElement nameOfLotAfterSendticket;

    @FindBy(xpath = "//table[@style='position: absolute; top: 0px; width: 1200px;']/tbody/tr[last()]/td[7]")
    private WebElement costForTicketAfterSubmit;

    @FindBy(xpath = "//table[@style='position: absolute; top: 0px; width: 1200px;']/tbody/tr[last()]/td[8]")
    private WebElement numberOfLotsToBuyAfterSendingTIcket;

    @FindBy(xpath = "//a[@style=\"right: auto; left: 38px; margin: 0px; top: 0px; width: 180px;\"]")
    private WebElement addNewInstrument;

    @FindBy(xpath = "//table[@style=\"width: 100%; table-layout: fixed;\"]/tbody/tr/td/input[@style=\"padding-right: 20px !important; width: 100%; height: 28px;\"]")
    private WebElement inputNameOfLotInFiledOFAddNewInstrument;

    @FindBy(xpath = "//table[@style=\"width: 251px;\"]/tbody/tr[last()]/td[1]/div")
    private WebElement nameOfIntrumentAfterAddNewInstrument;



    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    protected static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, 100)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, 100)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void openWindowOfCreationTicket() {
        waitForElementToBeClickable(driver, newTicketButton);
        newTicketButton.click();
    }

    public String setDateBeforTicketCreation() {
        return timeBeforeTicketCreate.getText();
    }

    public String setDateAfterTicketCreation() {
        return timeAfterTicketCreate.getText();
    }

    public void setStopTicketMethod() {
        waitForElementToBeClickable(driver,setStopTicket);
        setStopTicket.click();
    }

    public String tradeNumberAfterSubmit() {
        return tradeNumberAfterSubmitTicket.getText();
    }

    public void fillFieldsOnTicketWindow(String nameOfLot, String countOfLots, String costPerInstruments) {
        waitForVisibilityOfElement(driver, tradeNumber);
        waitForVisibilityOfElement(driver, inputNameOfLot);
        waitForVisibilityOfElement(driver, numberOfLotsToBuy);
        waitForVisibilityOfElement(driver, costForInstrument);

        tradeNumber.sendKeys(Keys.ENTER+"\n");
        inputNameOfLot.sendKeys(nameOfLot);
        inputNameOfLot.sendKeys(Keys.ENTER);
        numberOfLotsToBuy.sendKeys(countOfLots);
        costForInstrument.sendKeys(costPerInstruments);
    }

    public void pressSubmitTicketButton() {
        waitForElementToBeClickable(driver, submitTicketButton);
        submitTicketButton.click();
    }

    public void sendOfCretedTicketButton() {
        waitForElementToBeClickable(driver, sendTicketButton);
        waitForVisibilityOfElement(driver, tradeNumberAfterSubmitTicket);
        sendTicketButton.click();
    }

    public String getExpectedTradeNumber() {
        return tradeNumberAfterSubmitTicket.getText();
    }

    public String getActualTradeNumber() {
        return tradeNumber.getText();
    }
}
