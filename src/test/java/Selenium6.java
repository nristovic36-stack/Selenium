import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Selenium6 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.navigate().to("https://demoqa.com");

        List<WebElement> cards = driver.findElements(By.className("card-body"));

        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getText().equals("Forms")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cards.get(i));
                cards.get(i).click();
                break;
            }
        }

    }
}
