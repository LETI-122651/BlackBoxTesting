package iscteiul.ista.demo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecureDownloadTest {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.downloadsFolder = "build/downloads";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    public void testSecureFileDownload() {
        open("https://admin:admin@the-internet.herokuapp.com/download_secure");

        SelenideElement fileLink = $(".example a");

        String expectedFileName = fileLink.text();

        File downloadedFile = fileLink.download();

        assertTrue(downloadedFile.exists());

        assertTrue(downloadedFile.getName().startsWith(expectedFileName));
    }
}