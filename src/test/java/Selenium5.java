import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Selenium5 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://practicetestautomation.com/");

        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();

        WebElement testLoginPageButton = driver.findElement(By.linkText("Test Login Page"));
        testLoginPageButton.click();

        //------------Login page

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

        //-------------Asertacije

        String expectedURL = "https://practicetestautomation.com/logged-in-successfully/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        Assert.assertEquals(driver.getTitle(), "Logged In Successfully | Practice Test Automation");

        String actualLogInMessage = driver.findElement(By.className("post-title")).getText();
        String expectedLogInMessage = "Logged In Successfully";
        Assert.assertEquals(actualLogInMessage, expectedLogInMessage);

        Assert.assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed());

        WebElement message = driver.findElement(By.cssSelector(".has-text-align-center.wp-block-paragraph"));
        Assert.assertEquals(message.getText(), "Congratulations "+validUsername+". You successfully logged in!");

        //------------------------

        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        logOutButton.click();

        usernameField = driver.findElement(By.id("username"));
        //WebElement usernameFieldAfterLogOut = usernameField = driver.findElement(By.id("username"));
        Assert.assertTrue(usernameField.isDisplayed());

        Assert.assertNotEquals(driver.getCurrentUrl(), expectedURL);

        //------------------------

        // Kada zelimo da proverimo da li neki element NIJE PRISUTAN
        // ne mozemo tek tako da koristimo Assert.assertFalse(element)
        // jer program ce da vrati gresku da ne moze da pronadje element

        // Nacin da zaobidjemo ovaj problem je da koristimo try catch blok
        // Try deo ce uvek da pokusa da izvrsi deo koda ali ako ne uspe i ako dobijemo gresku
        // onda program nece da pukne tu nego ce nastaviti dalje
        // Catch deo nam nije bitan, ali je obavezna u javi kao deo try catch bloka i potrebno je
        // samo da kreiramo taj deo i da prosledimo da uhvati neki Exception
        // Ako zelimo taj Exception koji smo nazvali 'e' mozemo da odstampamo u catch bloku

        // Pre pocetka pravimo boolean cija je vrednost false
        // Na kraju cemo raditi asertaciju bas ovo booleana i ocekujemo da bude false
        // Taj boolean ce se promeniti na true samo ako element bude vidljiv na stranici

        //WebElement logOutButtonAfterLogout = driver.findElement(By.linkText("Log out"));
        //Assert.assertFalse(logOutButtonAfterLogout.isDisplayed());

        boolean isPresent = false;
        try {
            // Upravo u ovom delu kazemo programu - Ako pronadjes element, promeni vrednost booleana na true
            isPresent = driver.findElement(By.linkText("Log out")).isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(isPresent);

    }
}