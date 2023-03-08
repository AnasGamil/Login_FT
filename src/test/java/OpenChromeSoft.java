import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class OpenChromeSoft {

    WebDriver driver = null;

    @BeforeTest
    public void OpenBrowser() throws InterruptedException {
        String ChromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.out.println(ChromePath);
        System.setProperty("webdriver.chrome.driver", ChromePath);

        //2 new object
        driver = new ChromeDriver();

        //3 - Navigate to google.com and maxmize then sleep 3 sec

        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    @Test(priority=1)
    public void Validation() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);

        Thread.sleep(2000);

        String expecttedmess = "You logged into a secure area!" ;
        String actualmess = driver.findElement(By.id("flash")).getText();

        //**Soft Assertion

        SoftAssert soft = new SoftAssert();

        //First Assertion
        System.out.println("First Assertion");
        soft.assertTrue(actualmess.contains(expecttedmess), "First Assertion");
        Thread.sleep(3000);

        //Second Assertion
        System.out.println("Second Assertion");
        soft.assertTrue(driver.findElement(By.cssSelector("a[href=\"/logout\"]")).isDisplayed(),"Second Assertion");

        //Third Assertion
        System.out.println("Third Assertion");
        soft.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure", "Third Assertion");

        //Assert all
        soft.assertAll();

    }

    @Test(priority=2)
    public void InValide() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("omsmith");
        driver.findElement(By.id("password")).sendKeys("uperSecretPassword!");
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        String expecttedmess = "Your username is invalid!";
        String actualmess = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(actualmess.contains(expecttedmess), "Error Measage : Text is wrong ");
        Thread.sleep(3000);

    }

    @AfterTest
    public void CloseBrowser()
    {
        driver.quit();
    }

}
