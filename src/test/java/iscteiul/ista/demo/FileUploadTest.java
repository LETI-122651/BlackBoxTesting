package iscteiul.ista.demo;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FileUploadTest {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://the-internet.herokuapp.com/upload");
    }

    @Test
    public void testFileUpload() throws InterruptedException {

        SelenideElement uploadInput = $("#file-upload");
        uploadInput.scrollIntoView(true);
        uploadInput.uploadFile(new File("src/test/resources/test-upload.txt"));
        $("#file-submit").click();
        $("#uploaded-files").shouldHave(text("test-upload.txt"));
        Thread.sleep(2000); // só para observar o resultado

    }
}