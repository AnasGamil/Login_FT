import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;



public class OpenChrome {

    WebDriver driver = null;
    loginPage login;

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

        //4- Create new object
        login = new loginPage();
    }

    @Test(priority=1)
    public void Validation() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/login");

        login.Loginsteps(driver, "tomsmith" ,"SuperSecretPassword!");

        Thread.sleep(2000);

        //create strings for expect and actuall
        String expecttedmess = "You logged into a secure area!" ;
        String actualmess = driver.findElement(login.flashPOM()).getText();

        //**Hard Assertion
        //First Assertion
        System.out.println("First Assertion");
        Assert.assertTrue(actualmess.contains(expecttedmess));
        Thread.sleep(3000);

        //Second Assertion
        System.out.println("Second Assertion");
        Assert.assertTrue(driver.findElement(login.loginbuttonPOM()).isDisplayed());

        //Third Assertion
        System.out.println("Third Assertion");
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure");

    }

    @Test(priority=2)
    public void InValide() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/login");

        login.Loginsteps(driver, "wrongusername" ,"Super");

        //create strings for expect and actuall
        String expecttedmess = "Your username is invalid!";
        String actualmess = driver.findElement(login.flashPOM()).getText();
        Assert.assertTrue(actualmess.contains(expecttedmess), "Error Measage : Text is wrong ");
        Thread.sleep(3000);

    }

    @Test(priority = 3)
    public void Add_Delete_Elements() throws InterruptedException {
        String url = "https://the-internet.herokuapp.com/add_remove_elements/" ;
        driver.navigate().to(url);

        //Add Elements
        driver.findElement(login.addElm()).click();
        driver.findElement(login.addElm()).click();
        Assert.assertTrue(driver.findElement(login.newElem()).isDisplayed());

        //Delete Elements
        Thread.sleep(2000);
        driver.findElement(login.newElem()).click();
        driver.findElement(login.newElem()).click();
        Thread.sleep(2000);
    }

    @AfterTest
    public void CloseBrowser()
    {
        driver.quit();
    }

}
