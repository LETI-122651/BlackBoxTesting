package iscteiul.ista.demo.form;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;

public class FormTest {

    private FormPage formPage;

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        formPage = new FormPage();
        formPage.open();
    }

    @Test
    public void testFormSubmissionSuccess() {
        String testFirstName = "QA";
        String testLastName = "Selenium";
        String testHandle = "user123";
        String testPassword = "Password123!";
        String testEmail = "teste@exemplo.com";

        formPage.fillForm(testFirstName, testLastName, testHandle, testPassword, testEmail);
        formPage.submitForm();

        formPage.getNotification().shouldBe(visible).shouldHave(text("Data saved, welcome user123"));
    }
}