import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class zadatak3 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1. Otvoriti bing pretrazivac
        driver.navigate().to("https://bing.com");
        Thread.sleep(2000);

        // 2. Pretrazite 'Wikipedia'
        WebElement bingSearch = driver.findElement(By.name("q"));
        bingSearch.sendKeys("Wikipedia");
        bingSearch.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        // 3. Otvorite wikipedia stranicu
        WebElement wikipediaLink = driver.findElement(By.xpath("//a[contains(@href, 'wikipedia.org')]"));
        wikipediaLink.click();
        Thread.sleep(2000);

        // PREBACIVANJE NA NOVI TAB (u kojem se otvorila Wikipedia)
        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova.get(1));

        // 4. Na njoj uradite pretragu 'Nikola Tesla'
        WebElement wikipediaSearch = driver.findElement(By.name("search"));
        wikipediaSearch.sendKeys("Nikola Tesla");
        wikipediaSearch.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        driver.quit();
    }
}
