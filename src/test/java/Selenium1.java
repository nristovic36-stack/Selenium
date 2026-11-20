import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium1 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        // U ovoj komandi postavljam driver

        WebDriver driver = new ChromeDriver();
        // U ovoj komandi kreiram objekat kom cu davati komande

        driver.navigate().to("https://www.google.com");
        //driver.get("https://www.google.com");

        driver.manage().window().maximize();

        System.out.println(driver.getCurrentUrl());

        driver.navigate().to("https://www.youtube.com");

        System.out.println(driver.getCurrentUrl());

        driver.navigate().back();

        driver.navigate().refresh();

        driver.navigate().forward();

        driver.quit();

    }
}