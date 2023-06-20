package ru.apteka.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    ElementsCollection tabs = $$("#left_nav_main ul li");

    public ElementsCollection getSubtabs(SelenideElement tab) {
        return tab.$$("ul li");
    }

}
