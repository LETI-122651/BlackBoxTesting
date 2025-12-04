package iscteiul.ista.demo.database;

import com.codeborne.selenide.*;
import static com.codeborne.selenide.Selenide.$$;

public class DatabaseExamplePage {

    public ElementsCollection movieGrid = $$("vaadin-grid-cell-content");

    public ElementsCollection getCollection(){
        return movieGrid;
    }

    public void open(){
        Selenide.open("https://vaadin-database-example.demo.vaadin.com/");
    }

}