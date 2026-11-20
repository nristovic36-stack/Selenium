import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Zadatak7 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.navigate().to("https://practicetestautomation.com/");

        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();

        WebElement testLoginPageButton = driver.findElement(By.linkText("Test Login Page"));
        testLoginPageButton.click();
    }

    @Test(priority = 1)
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
        Assert.assertEquals(message.getText(), "Congratulations " + validUsername + ". You successfully logged in!");
    }

    @Test(priority = 2)
    public void userCanLogOut() {
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

        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        logOutButton.click();

        String expectedURL = "https://practicetestautomation.com/practice-test-login/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        Assert.assertTrue(driver.findElement(By.id("submit")).isDisplayed());
    }

    @Test(priority = 3)
    public void userCannotLogInWithInvalidUsername() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        String invalidUsername = "incorrectUser";
        String validPassword = "Password123";

        usernameField.clear();
        usernameField.sendKeys(invalidUsername);

        passwordField.clear();
        passwordField.sendKeys(validPassword);

        submitButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Your username is invalid!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/practice-test-login/");
    }

    @Test(priority = 4)
    public void userCannotLogInWithInvalidPassword() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        String validUsername = "student";
        String invalidPassword = "incorrectPassword";

        usernameField.clear();
        usernameField.sendKeys(validUsername);

        passwordField.clear();
        passwordField.sendKeys(invalidPassword);

        submitButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Your password is invalid!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/practice-test-login/");
    }

    @Test(priority = 5)
    public void userCannotLogInWithEmptyFields() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.clear();
        passwordField.clear();

        submitButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Your username is invalid!");
    }

    @Test(priority = 6)
    public void userCannotLogInWithEmptyPassword() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        String validUsername = "student";

        usernameField.clear();
        usernameField.sendKeys(validUsername);

        passwordField.clear();

        submitButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Your password is invalid!");
    }

    @Test(priority = 7)
    public void userCannotLogInWithCaseSensitiveUsername() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        String upperCaseUsername = "STUDENT";
        String validPassword = "Password123";

        usernameField.clear();
        usernameField.sendKeys(upperCaseUsername);

        passwordField.clear();
        passwordField.sendKeys(validPassword);

        submitButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Your username is invalid!");
    }

    @Test (priority = 8)
    public void usernameFiledEmpty() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.click();
        usernameField.sendKeys("");

        passwordField.

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
    }


    @AfterMethod
    public void afterMethod() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
