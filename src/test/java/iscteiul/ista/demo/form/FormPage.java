package iscteiul.ista.demo.form;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FormPage {

    private static final String URL = "https://vaadin-form-example.demo.vaadin.com/";

    private final ElementsCollection allTextFields = $$("vaadin-text-field");
    private final ElementsCollection allPasswordFields = $$("vaadin-password-field");

    private final SelenideElement firstNameHost = allTextFields.get(0);
    private final SelenideElement lastNameHost = allTextFields.get(1);
    private final SelenideElement userHandleHost = allTextFields.get(2);

    private final SelenideElement wantedPasswordHost = allPasswordFields.get(0);
    private final SelenideElement passwordAgainHost = allPasswordFields.get(1);

    private final SelenideElement allowMarketingCheckbox = $("vaadin-checkbox");

    private final SelenideElement emailFieldHost = $("vaadin-email-field");

    private final SelenideElement joinCommunityButton = $("vaadin-button");
    private final SelenideElement notification = $("vaadin-notification-card");

    public void open() {
        Selenide.open(URL);
    }

    public void fillForm(String firstName, String lastName, String handle, String password, String email) {
        setValueInShadow(firstNameHost, firstName);
        setValueInShadow(lastNameHost, lastName);
        setValueInShadow(userHandleHost, handle);
        setValueInShadow(wantedPasswordHost, password);
        setValueInShadow(passwordAgainHost, password);

        allowMarketingCheckbox.click();

        emailFieldHost.shouldBe(visible);
        setValueInShadow(emailFieldHost, email);
    }

    public void submitForm() {
        joinCommunityButton.click();
    }

    public SelenideElement getNotification() {
        return notification;
    }

    private void setValueInShadow(SelenideElement host, String value) {
        WebElement inputElement = Selenide.executeJavaScript(
                "return arguments[0].shadowRoot.querySelector('input')", host
        );
        $(inputElement).setValue(value);
    }
}