import org.testng.annotations.*;

import org.testng.annotations.*;

public class Zadatak5 {

    // Zadatak 5
// Napisati jednom na pocetku "Dobrodosli na kurs"
// Napisati pre svakog testa "Ucim za test"
// Napisati tri test metode - u prvoj odstampati "SQL"
// u drugom odstampati "Java"
// u trecem odstampati "Selenium"
// Nakon svakog testa ispisati "Polozen test"
// Na kraju ispisati "Polozen kurs"

    @BeforeClass
    public void beforeClass() {
        System.out.println("Dobrodosli na kurs");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Ucim za test");
    }

    @Test(priority = 1)
    public void test1() {
        System.out.println("SQL");
    }

    @Test(priority = 2)
    public void test2() {
        System.out.println("Java");
    }

    @Test(priority = 3)
    public void test3() {
        System.out.println("Selenium");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Polozen test");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Polozen kurs");
    }
}