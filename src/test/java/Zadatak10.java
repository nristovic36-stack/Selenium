import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Zadatak10 {
    WebDriver driver;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://demoqa.com");
    }

    @BeforeMethod
    public void pageSetUp() {
        List<WebElement> cards = driver.findElements(By.className("card-body"));
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getText().equals("Elements")) {
                cards.get(i).click();
                break;
            }
        }
        List<WebElement> sidebarButtons = driver.findElements(By.className("text"));
        for (int i = 0; i < sidebarButtons.size(); i++) {
            if (sidebarButtons.get(i).getText().equals("Web Tables")) {
                sidebarButtons.get(i).click();
                break;
            }
        }
        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        Assert.assertEquals(tableRows.size(), 3);
    }

    @Test
    public void userCanAddRecords() {
        WebElement addButton = driver.findElement(By.id("addNewRecordButton"));
        addButton.click();

        WebElement firstNameField = driver.findElement(By.id("firstName"));
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        WebElement emailAddress = driver.findElement(By.id("userEmail"));
        WebElement ageField = driver.findElement(By.id("age"));
        WebElement salaryField = driver.findElement(By.id("salary"));
        WebElement departmentField = driver.findElement(By.id("department"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        String firstName = "Michael";
        String lastName = "Scott";
        String email = "m.scott@email.com";
        String age = "46";
        String salary = "120000";
        String department = "Manager";

        firstNameField.clear();
        firstNameField.sendKeys("Michael");
        lastNameField.clear();
        lastNameField.sendKeys("Scott");
        emailAddress.clear();
        emailAddress.sendKeys("m.scott@email.com");
        ageField.clear();
        ageField.sendKeys("46");
        salaryField.clear();
        salaryField.sendKeys("120000");
        departmentField.clear();
        departmentField.sendKeys("Manager");
        submitButton.click();

        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        Assert.assertEquals(tableRows.size(), 4);
        Assert.assertTrue(tableRows.get(3).getText().contains(firstName));
        Assert.assertTrue(tableRows.get(3).getText().contains(lastName));
        Assert.assertTrue(tableRows.get(3).getText().contains(email));
        Assert.assertTrue(tableRows.get(3).getText().contains(age));
        Assert.assertTrue(tableRows.get(3).getText().contains(salary));
        Assert.assertTrue(tableRows.get(3).getText().contains(department));
    }

    @AfterMethod
    public void deleteRecord() {
        WebElement deleteButton = driver.findElement(By.id("delete-record-4"));
        deleteButton.click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}