package ru.apteka.test;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PageHeader {
    SelenideElement favorites = $("[class='favs']");
    SelenideElement basket = $("#basket_small");
}
