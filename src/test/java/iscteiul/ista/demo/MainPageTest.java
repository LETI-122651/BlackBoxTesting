package iscteiul.ista.demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://www.jetbrains.com/");
        SelenideElement cookieButton = $("button.ch2-allow-all-btn");
        if (cookieButton.exists() && cookieButton.isDisplayed()) {
            cookieButton.click();
            System.out.println("Cookies aceites");
        }
    }

    @Test
    public void search() {
        mainPage.searchButton.scrollTo().shouldBe(visible, enabled).click();
        SelenideElement searchInput =$("[data-test-id='search-input']");
        searchInput.shouldBe(visible).setValue("Selenium");
        $("[data-test='full-search-button']").click();
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();
        $("[data-test-marker=\"Developer Tools\"]").shouldBe(visible);
    }

    @Test
    public void navigationToAllTools() {
        mainPage.toolsMenu.click();
        mainPage.findYourToolsButton.click();

        $("#products-page").shouldBe(visible);

        assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());
    }
}
