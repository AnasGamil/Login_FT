import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginPage {
    public WebElement usernamePOM(WebDriver driver) {
        By username = By.id("username");
        WebElement usernameEle = driver.findElement(username);
        return usernameEle;
    }

    public WebElement passPOM(WebDriver driver){
        return driver.findElement(By.id("password")) ;
    }

    public By flashPOM(){
        return By.id("flash") ;
    }

    public By loginbuttonPOM(){
        return By.cssSelector("a[href=\"/logout\"]");
    }

    public void Loginsteps(WebDriver driver, String username, String Password){
        //Enter username using POM
        usernamePOM(driver).clear();
        usernamePOM(driver).sendKeys(username);

        //Enter password using POM
        passPOM(driver).sendKeys(Password);
        passPOM(driver).sendKeys(Keys.ENTER);
    }

    public By addElm(){
        return By.cssSelector("div[class=\"example\"] > button[onclick=\"addElement()\"]") ;
    }

    public By newElem(){
        return By.className("added-manually");
    }
}
