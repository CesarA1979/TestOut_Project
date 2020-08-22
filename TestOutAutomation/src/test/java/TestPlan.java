import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static org.junit.Assert.assertTrue;


public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        // ChromeDriver location set up in Utils class.
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Set Email Account And Connect to Wifi")
    public static void SetEmailAccountAndWifi(){
        driver.get(Utils.BASE_URL);
        SetEmailAccount();
        ConnecToWifi();
        VerifySimulationScore();
    }

    public static void SetEmailAccount() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("wpDesktop.DesktopIcon15.Grid.tbText")));
        PageObject webForm = new PageObject(driver);
        webForm.WaitAndClick("settings", 15);
        webForm.WaitAndClick("mailContactCalendar", 15);
        webForm.WaitAndClick("SelectContact", 20);
        webForm.WaitAndClick("SelectMail", 25);
        wait.until(ExpectedConditions.elementToBeClickable(webForm.selectAdvanced()));
        webForm.WaitAndClick("selectAdvanced", 30);
        webForm.SlideButton("SSSlider", 15);
        webForm.InputPort();
        webForm.WaitAndClick("account", 30);
        webForm.WaitAndClick("done", 30);
    }

    public static void ConnecToWifi() {
        PageObject webForm = new PageObject(driver);
        webForm.WaitAndClick("wifi", 30);
        webForm.WaitAndClick("corpnet", 30);
        webForm.EnterPass();
        webForm.WaitAndClick("join", 30);
    }

    public static void VerifySimulationScore() {
        PageObject webForm = new PageObject(driver);
        webForm.DoneButtonClick();
        String actualScore = webForm.getScore();
        String expectedScore = "1 of 1 (100%)";
        assertTrue("Incorrect score. Expected :" +  expectedScore + "Actual: "
                + actualScore, actualScore.contains(expectedScore));
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
