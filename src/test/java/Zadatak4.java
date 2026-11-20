import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Zadatak4 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://practicetestautomation.com");

        WebElement practiceMenu = driver.findElement(By.id("menu-item-20"));
        practiceMenu.click();

        Thread.sleep(2000);

        WebElement testLoginPageLink = driver.findElement(By.linkText("Test Login Page"));
        testLoginPageLink.click();

        Thread.sleep(2000);

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.clear();
        usernameField.sendKeys("student");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys("Password123");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        Thread.sleep(2000);

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://practicetestautomation.com/logged-in-successfully/";
        Assert.assertEquals(actualURL, expectedURL);

        String actualTitle = driver.getTitle();
        String expectedTitle = "Logged In Successfully | Practice Test Automation";
        Assert.assertEquals(actualTitle, expectedTitle);

        WebElement pageHeading = driver.findElement(By.className("post-title"));
        String actualHeading = pageHeading.getText();
        String expectedHeading = "Logged In Successfully";
        Assert.assertEquals(actualHeading, expectedHeading);

        WebElement successMessage = driver.findElement(By.tagName("strong"));
        String actualMessage = successMessage.getText();
        String expectedMessage = "Congratulations student. You successfully logged in!";
        Assert.assertEquals(actualMessage, expectedMessage);

        WebElement logoutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logoutButton.isDisplayed());

        Assert.assertTrue(driver.findElements(By.id("submit")).isEmpty());
    }
}
