import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class Selenium7 {
    public static void main(String[] args) throws InterruptedException {

        /*
https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2
Testirati dodavanje knjige u korpu i da li se knjiga obrise kada obrisete kolacice.
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2");

        WebElement popupButton = driver.findElement(By.cssSelector("button[type='submit']"));
        popupButton.click();

        Thread.sleep(3000);
        driver.navigate().to("https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2");

        // Kada proveravam korpu nakon dodavanja knjige
        // zelim da proverim da li sam dodao bas tu knjigu koju sam hteo
        // Iz tog razloga je potrebno da na ovom koraku pronadjem naziv knjige
        // i sacuvam u string koji cu kasnije koristiti za asertaciju
        WebElement itemName = driver.findElement(By.id("productTitle"));
        String itemNameText = itemName.getText();

        // Pre dodavanja u korpu zelim da proverim da li je korpa prazna
        // da ne dodjem u situaciju da je korpa puna, ne radi dugme za dodavanje
        // i meni prodje test jer bih ocitao nesto u korpi

        // Proveravam da li na korpi pise 0
        WebElement cartNumber = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartNumber.getText(), "0");

        // Odlazim na korpu da proverim da li je zaista prazna
        cartNumber.click();
        WebElement emptyCart = driver.findElement(By.id("sc-empty-cart"));
        Assert.assertTrue(emptyCart.isDisplayed());

        driver.navigate().back();

        // Dodajem knjigu u korpu
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        // Proveravam da li je dodato nesto u korpu i da li se broj promenio sa 0 na 1
        WebElement addedToCartMessage = driver.findElement(By.id("NATC_SMART_WAGON_CONF_MSG_SUCCESS"));
        Assert.assertTrue(addedToCartMessage.isDisplayed());
        cartNumber = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartNumber.getText(), "1");
        cartNumber.click();

        // Sad proveravam da li sam dodao bas tu knjigu u korpu
        WebElement itemNameInCart = driver.findElement(By.className("a-truncate-cut"));
        Assert.assertEquals(itemNameInCart.getText(), itemNameText);

        //-----------------------

        // Nakon provera brisem cookies da bi mi korpa bila prazna
        // kad pokrenem ponovo test naredni put
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        emptyCart = driver.findElement(By.id("sc-empty-cart"));
        Assert.assertTrue(emptyCart.isDisplayed());
        cartNumber = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartNumber.getText(), "0");

        driver.quit();

    }
}