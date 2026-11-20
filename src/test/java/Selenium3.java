import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium3 {
    static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        Thread.sleep(3000);

        driver.findElement(By.xpath("/html/body/ntp-app//div/div[3]/ntp-searchbox//div/cr-searchbox-input//div/textarea")).sendKeys("ITBootcamp");
        driver.findElement(By.xpath("/html/body/ntp-app//div/div[3]/ntp-searchbox//div/cr-searchbox-input//div/textarea")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[3]/div/div[13]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div/div[1]/div/span/a")).click();

    }
}
