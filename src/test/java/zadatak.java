import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class zadatak {
    //Zadatak 1
//Pokrenite browser, idite na Linkedin sajt, uradite refresh, idite na Joberty sajt
//vratite se nazad i odstampajte poslednji URL na kom se nalazite
//na kraju zatvorite driver
    static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage ().window().maximize();

        driver.navigate().to("https://rs.linkedin.com/");

        driver.navigate().refresh();

        driver.navigate().to("https://www.joberty.com/sr/");

        driver.navigate().back();

        System.out.println(driver.getCurrentUrl());

        driver.quit();
    }


}
