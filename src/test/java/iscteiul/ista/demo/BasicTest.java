package iscteiul.ista.demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.*;

public class BasicTest {
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

    @Test
    public void testCheckboxes() {
        open("https://the-internet.herokuapp.com/checkboxes");

        SelenideElement checkbox1 = $$("input[type='checkbox']").get(0);
        SelenideElement checkbox2 = $$("input[type='checkbox']").get(1);

        checkbox1.click();
        checkbox1.shouldBe(checked);

        checkbox2.click();
        checkbox2.shouldNotBe(checked);
    }

    @Test
    public void testAuthentication() {
        open("https://the-internet.herokuapp.com/login");

        $("#username").setValue("tomsmith");
        $("#password").setValue("SuperSecretPassword!");

        $("button[type='submit']").click();

        $("#flash").shouldHave(text("You logged into a secure area!"));
    }

    @Test
    public void testDropdown() {
        open("https://the-internet.herokuapp.com/dropdown");

        SelenideElement dropdown = $("#dropdown");

        dropdown.selectOption("Option 2");

        dropdown.getSelectedOption().shouldHave(text("Option 2"));
    }

    @Test
    public void testInputs() {
        open("https://the-internet.herokuapp.com/inputs");

        SelenideElement input = $("input[type='number']");

        input.setValue("123");
        input.shouldHave(value("123"));

        input.clear();
        input.shouldHave(value(""));
    }

    @Test
    public void testHorizontalSlider() {
        open("https://the-internet.herokuapp.com/horizontal_slider");

        SelenideElement slider = $("#content input[type='range']");
        SelenideElement range = $("#range");

        slider.click();

        for (int i = 0; i < 4; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }

        range.shouldHave(text("4"));
    }
}