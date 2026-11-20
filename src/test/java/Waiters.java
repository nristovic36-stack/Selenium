import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {
    public static void main(String[] args) {

        // Sleepers - ceka toliko vremena koliko mu prosledimo u testu
        // Thread.sleep(5000) - iznos uvek pisemo u milisekundama, u ovom slucaju ce program sacekati 5 sekundi
        // Potrebno je da se u metodu doda exception tako sto se klikne na 'sleep' koje je podvuceno crvenom bojom
        // i da se izabere opcija da se doda exception u metodu
        // Cekanje je fiksno koristecu sleep, sto znaci da ce program sacekati tacno toliko vremena
        // bez obzira da li se pojavio ili nije element koji zelite da sacekate
        // Moze se koristiti tokom kreiranja testova, ali se ne preporucuje da bude u finalnoj verziji testova

        // Implicit wait - ceka zadato vreme dok se element ne pojavi
        // Odnosno ceka odredjeno vreme dok ne dobijemo "NoSuchElementException"
        // Definise se samo jednom na pocetku
        // Ovde ce driver pokusati na svakih 500ms da pronadje element
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        // Ovde smo definisali implicit wait na 30 sekundi
        // Sto znaci da ako program ne moze da pronadje element, sacekace 30 sekundi
        // pre nego sto vam vrati gresku da element nije prisutan
        // i za to vreme ce pokusati na svakih 500ms da pronadje element

        // Explicit wait - ceka zadato vreme dok se ne ispuni uslov
        // Objekat se kreira nakon sto se driver inicijalizuje, a koristi se po potrebi
        // Odnosno morate prvo napraviti objekat drivera jer taj driver morate da prosledite
        // u objekat WebDriverWait
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Ovaj wait cemo najcesce koristiti da sacekamo da element bude vidljiv, klikabilan
        // ili da se URL promeni na zeljeni string


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://practicetestautomation.com/");

        // Implicit wait definisemo samo jednom, idealno bi bilo da bude na pocetku
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // Explicit wait definisemo samo jednom ali pozivamo po potrebi
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ovde zadajemo komandu da zelimo da sacekamo da element 'practiceButton' bude vidljiv
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-item-20"))));
        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();

        // Ovde zadajemo komandu da zelimo da sacekamo da element 'testLoginPageButton' bude klikabilan
        WebElement testLoginPageButton = driver.findElement(By.linkText("Test Login Page"));
        wait.until(ExpectedConditions.elementToBeClickable(testLoginPageButton));
        testLoginPageButton.click();

        // Ovde zadajemo komandu da zelimo da sacekamo da URL postane ovaj prosledjeni string
        wait.until(ExpectedConditions.urlToBe("https://practicetestautomation.com/practice-test-login/"));

        // U praksi bismo koristili Thread.sleep da utvrdimo da je problem do cekanja
        // Ako jeste onda cemo obrisati to cekanje i dodati odredjeni explicit wait

        // Primer:
        // Vrsim pretragu "Nikola Tesla" na Wikipedia stranici ali program previse
        // brzo krene da radi asertacije i vraca mi gresku da element nije pronadjen
        // Pre te greske dodajem Thread.sleep(3000)
        // Problem je sada resen i program nastavlja dalje sa radom
        // Kada utvrdim da je problem bio cekanje onda cu da obrisem Thread.sleep
        // i da dodam explicit wait, na primer:
        //wait.until(ExpectedConditions.urlToBe("https://en.wikipedia.org/wiki/Nikola_Tesla"));

    }
}