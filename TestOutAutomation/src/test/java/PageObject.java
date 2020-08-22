import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class PageObject {
    protected WebDriver driver;
    public WebElement settings;
    public WebElement mailContactCalendar;
    public WebElement SelectContact;
    public  WebElement SelectMail;
    public  WebElement selectAdvanced;
    public  WebElement SSSlider;
    public  WebElement ServerPort;
    public  WebElement Account;
    public  WebElement Done;
    public  WebElement Wifi;
    public  WebElement CorpNet;
    public  WebElement Password;
    public  WebElement Join;
    public  WebElement FrameElement;
    public  WebElement ScoreElement;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement settings() {
        settings = driver.findElement(By.xpath("//div/img[@class='clsFrameworkElement'][@id='wpDesktop.DesktopIcon15.Grid.gContent.imImage']"));
        return  settings;
    }

    public WebElement mailContactCalendar() {
        mailContactCalendar = driver.findElement(By.xpath("//div[@class='clsFrameworkElement'][@id='siMailContactsCalendars.Grid.tbText']"));
        return mailContactCalendar;
    }

    public WebElement SelectContact() {
        SelectContact = driver.findElement(By.xpath("//div[contains(text() ,'Maggie Brown')]/../div[contains(text() ,'Mail, Calendars, Notes')]"));
        return SelectContact;
    }

    public WebElement SelectMail() {
        SelectMail = driver.findElement(By.id("siAccount.Grid.tbArrow"));
        return SelectMail;
    }

    public WebElement selectAdvanced() {
        selectAdvanced = driver.findElement(By.id("siAdvanced.Grid.tbArrow"));
        return selectAdvanced;
    }

    public WebElement SSSlider() {
        SSSlider = driver.findElement(By.xpath("//div[@data-typename='HorizontalToggleSwitch']/./div[@id='siUseSSL.Grid.tbOnOff.Grid.SwitchRoot']"));
        return SSSlider;
    }

    public WebElement ServerPort() {
        ServerPort = driver.findElement(By.id("tbServerPort"));
        return ServerPort;
    }

    public WebElement Account() {
        Account = driver.findElement(By.id("btnAccount.grid.contentPresenter.TextBlock"));
        return Account;
    }

    public WebElement Done() {
        Done = driver.findElement(By.id("btnDone"));
        return Done;
    }

    public WebElement Wifi() {
        Wifi = driver.findElement(By.id("siWiFi.Grid.tbText"));
        return Wifi;
    }

    public WebElement CorpNet() {
        CorpNet = driver.findElement(By.xpath("//div[contains(text() ,'CorpNet')]"));
        return CorpNet;
    }

    public WebElement Password() {
        Password = driver.findElement(By.cssSelector("[data-typename='PasswordBox']"));
        return Password;
    }

    public WebElement Join() {
        Join = driver.findElement(By.xpath("//div[@data-typename='ButtonArrow'][@id='btnJoin']"));
        return Join;
    }

    public void DoneButtonClick() {
        driver.findElement(By.xpath("//div[@data-typename='Button'][@id='bDone'][@tabindex='-1']")).click();
    }

    public WebElement FrameElement() {
        FrameElement = driver.findElements(By.tagName("iframe")).get(1);
        return FrameElement;
    }

    public WebElement ScoreElement() {
        ScoreElement = driver.findElement(By.cssSelector(".clsBox2"));
        return ScoreElement;
    }

    public void WaitAndClick(String element, int i) {
        driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);

        if(element.contains("settings")) {
            settings().click();
        }
        else if (element == "mailContactCalendar") {
            mailContactCalendar().click();
        }
        else if (element == "SelectContact") {
            SelectContact().click();
        }
        else if (element == "SelectMail") {
            SelectMail().click();
        }
        else if (element =="selectAdvanced") {
            selectAdvanced().click();
        }
        else if (element == "account") {
            Account().click();
        }
        else if (element == "done") {
            Done().click();
        }
        else if (element == "wifi") {
            Wifi().click();
        }
        else if (element == "corpnet") {
            CorpNet().click();
        }
        else if (element == "join") {
            Join().click();
        }
        else {
            Assert.assertTrue(!element.equals(""));
        }
    }

    public void SlideButton(String element, int x) {
        WebElement e = null;
        if(element.contains("SSSlider"))
        {
            e = SSSlider();
        }

        int width = e.getSize().getWidth();
        Actions move = new Actions(driver);
        move.moveToElement(e, ((width*x)/100), 0).click();
        move.build().perform();
    }

    public void InputPort() {
        Actions actions = new Actions(driver);
        actions.moveToElement(ServerPort());
        actions.perform();
        ServerPort().click();
        ServerPort().clear();
        ServerPort().sendKeys("993");
        Logger.getLogger("Port Server set to 993");
    }

    public void EnterPass() {
        Password().sendKeys("@CorpNetWeRSecure!&");
        Logger.getLogger("Password entered");
    }

    public String getScore() {
        driver.switchTo().frame(FrameElement());
        return  ScoreElement().getText();
    }

}