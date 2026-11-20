import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WordpressLoginTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://wordpress.com");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebDriverWait captchaWait = new WebDriverWait(driver, Duration.ofSeconds(60));

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log in")));
        loginButton.click();

        wait.until(ExpectedConditions.urlContains("://wordpress.com"));

        WebElement usernameField = captchaWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")));
        usernameField.sendKeys("nristovic36@gmail.com");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        continueButton.click();

        WebElement passwordField = captchaWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordField.sendKeys("nikola123U");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        submitButton.click();

        captchaWait.until(ExpectedConditions.urlContains("://wordpress.com"));

        System.out.println("Uspesno logovanje!");
    }
}
