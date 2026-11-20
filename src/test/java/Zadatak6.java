import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Zadatak6 {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://practicetestautomation.com/");
    }

    @BeforeMethod
    public void beforeMethod() {
        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();

        WebElement testLoginPageButton = driver.findElement(By.linkText("Test Login Page"));
        testLoginPageButton.click();
    }

    @Test
    public void userCanLogIn() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        String validUsername = "student";
        String validPassword = "Password123";

        usernameField.clear();
        usernameField.sendKeys(validUsername);

        passwordField.clear();
        passwordField.sendKeys(validPassword);

        submitButton.click();

        String expectedURL = "https://practicetestautomation.com/logged-in-successfully/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        Assert.assertEquals(driver.getTitle(), "Logged In Successfully | Practice Test Automation");

        String actualLogInMessage = driver.findElement(By.className("post-title")).getText();
        String expectedLogInMessage = "Logged In Successfully";
        Assert.assertEquals(actualLogInMessage, expectedLogInMessage);

        Assert.assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed());

        WebElement message = driver.findElement(By.cssSelector(".has-text-align-center.wp-block-paragraph"));
        Assert.assertEquals(message.getText(), "Congratulations "+validUsername+". You successfully logged in!");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @AfterClass
    public void afterClass() {

    }

}