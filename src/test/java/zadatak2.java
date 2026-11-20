import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class zadatak2 {
    /*
    //Zadatak 2
    //Otvoriti browser i jos 5 tabova
    //Na svakom tabu otvoriti URL po zelji
    //Zatvoriti sve tabove osim onog gde je otvoren Google
     */
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://www.google.com");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.open()");
        }

        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        System.out.println("LISTA TABOVA " + listaTabova);

        driver.switchTo().window(listaTabova.get(1));
        driver.navigate().to("https://www.joberty.com");

        driver.switchTo().window(listaTabova.get(2));
        driver.navigate().to("https://www.wikipedia.org");

        driver.switchTo().window(listaTabova.get(3));
        driver.navigate().to("https://www.github.com");

        driver.switchTo().window(listaTabova.get(4));
        driver.navigate().to("https://www.reddit.com");

        driver.switchTo().window(listaTabova.get(5));
        driver.navigate().to("https://www.tiktok.com");

        for (int i = 1; i < listaTabova.size(); i++) {
            driver.switchTo().window(listaTabova.get(i));
            driver.close();
        }

        driver.switchTo().window(listaTabova.get(0));
    }
}
