import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Zadatak4Negativan {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://practicetestautomation.com");
        Thread.sleep(2000);

        WebElement practiseLink = driver.findElement(By.linkText("Practice"));
        practiseLink.click();
        Thread.sleep(2000);

        WebElement practisePageLink = driver.findElement(By.linkText("Test Login Page"));
        practisePageLink.click();
        Thread.sleep(2000);

        // Unos ispravnog korisničkog imena i pogrešne lozinke
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.clear();
        usernameField.sendKeys("student");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys("PogresnaLozinka123");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        Thread.sleep(2000);

        // Provera da je korisnik ostao na istoj stranici
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://practicetestautomation.compractice-test-login/";
        Assert.assertEquals(actualUrl, expectedUrl);

        // Provera da se pojavila poruka o grešci za lozinku
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());

        String actualErrorText = errorMessage.getText();
        String expectedErrorText = "Your password is invalid!";
        Assert.assertEquals(actualErrorText, expectedErrorText);

        // Provera da je Submit dugme i dalje tu, a Log out nije
        Assert.assertTrue(submitButton.isDisplayed());
        boolean isLogoutPresent = driver.findElements(By.linkText("Log out")).size() > 0;
        Assert.assertFalse(isLogoutPresent);

        driver.quit();
    }
}
