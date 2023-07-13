package ru.apteka.test;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FavoritesScreen {
    SelenideElement deleteFavsButton = $("[class='delete_fav_products']");
    SelenideElement addToCart = $("[class='btn btn-size-11 btn-blue to-cart js-to-cart']");

}
