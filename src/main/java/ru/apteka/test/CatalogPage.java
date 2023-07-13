package ru.apteka.test;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CatalogPage {
    SelenideElement header = $("h1");
    SelenideElement product = $("#bx_2957495936_202423");
    SelenideElement favoriteButton = $("[class='events']");
    SelenideElement buttonOrder = $("[class='button green verybig']");
}
