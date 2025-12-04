package iscteiul.ista.demo.database;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

public class DatabaseTest {

    DatabaseExamplePage page = new DatabaseExamplePage();

    @BeforeAll
    public static void beforeAll() {
        Configuration.browserSize = "1280x800";
        Configuration.pageLoadTimeout= 10000;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void openPage() {
        page.open();
    }

    @Test
    public void testFirstMovie() throws InterruptedException {

        String name = page.movieGrid.get(0).getText();
        String year = page.movieGrid.get(1).getText();
        String director = page.movieGrid.get(2).getText();
        String linkText = page.movieGrid.get(3).getText();

        Thread.sleep(2000);

        Assertions.assertEquals("Law Abiding Citizen", name);
        Assertions.assertEquals("2009", year);
        Assertions.assertEquals("F. Gardy Gray", director);
        Assertions.assertEquals("Click to IMBD site", linkText);
    }

}