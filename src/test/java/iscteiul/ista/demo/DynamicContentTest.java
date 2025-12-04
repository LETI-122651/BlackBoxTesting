package iscteiul.ista.demo;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DynamicContentTest {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://the-internet.herokuapp.com/dynamic_content");
    }

    @Test
    public void testDynamicContentChangesAfterRefresh() throws InterruptedException {

        String before = $("div#content").getText();
        refresh();
        String after = $("div#content").getText();
        assertNotEquals(before, after, "o conteúdo deveria mudar depois do refresh");
        Thread.sleep(2000); // só para observar em execução

    }
}