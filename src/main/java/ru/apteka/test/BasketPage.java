package ru.apteka.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasketPage {
    SelenideElement removeAllButton = $("[class='goods__remove_all']");
    SelenideElement goodsTotal = $("[class='goods__total']");

    public ElementsCollection getSubtabs(SelenideElement tab) {
        return tab.$$("ul li");
    }
}
