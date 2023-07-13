package ru.apteka.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AptekaTest extends WebTest {

    MainPage mainPage = new MainPage();
    CityPopup cityPopup = new CityPopup();
    CatalogPage catalogPage = new CatalogPage();
    BasketPage basketPage = new BasketPage();
    PageHeader pageHeader = new PageHeader();
    FavoritesScreen favoritesScreen = new FavoritesScreen();

    @BeforeEach
    public void setSelenide() {
        open("https://klassika-apteka.ru");
        Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("BITRIX_SM_current_city", "114375"));
        refresh();
        cityPopup.modal.shouldNotBe(visible);
    }

    @Test
    @DisplayName("Переход по подкатегориям в каталоге товаров")
    @Feature("Каталог товаров")
    @Story("Подкатегории")
    public void shouldOpenCatalogTab() {
        SelenideElement tab = mainPage.tabs.filter(text("Лекарства и БАДы")).get(0);

        step("Навести курсор на вкладку", () -> {
            tab.hover();
        });

        step("Кликнуть на появившуюся подкатегорию", () -> {
            ElementsCollection subtabs = mainPage.getSubtabs(tab);
            subtabs.filter(text("Аллергия")).get(0).click();
        });

        step("Проверить, что произошел переход на страницу товаров категории", () -> {
            catalogPage.header.shouldHave(text("Аллергия"));
        });
    }

    @Test
    @DisplayName("Добавление товара в избранное")
    @Feature("Каталог товаров")
    @Story("Подкатегории")
    public void shouldAddToCartFromFavorite() {
        SelenideElement basket = pageHeader.basket;
        SelenideElement favorites = pageHeader.favorites;
        SelenideElement product = catalogPage.product;
        SelenideElement favoriteButton = catalogPage.favoriteButton;
        SelenideElement addToCart = favoritesScreen.addToCart;
        SelenideElement goodsTotal = basketPage.goodsTotal;
        SelenideElement tab = mainPage.tabs.filter(text("Лекарства и БАДы")).get(0);

        step("Навести курсор на вкладку", () -> {
            tab.hover();
        });

        step("Кликнуть на появившуюся подкатегорию", () -> {
            ElementsCollection subtabs = mainPage.getSubtabs(tab);
            subtabs.filter(text("Аллергия")).get(0).click();
        });

        step("Нажать на продукт", () -> {
            product.click();
        });

        step("Добавить в избранное", () -> {
            favoriteButton.click();
        });

        step("Перейти в избранное", () -> {
            favorites.click();
        });

        step("Добавить в корзину", () -> {
            addToCart.click();
        });

        step("Перейти в корзину и проверить отображается ли цена у товара", () -> {
            basket.click();
            String totalPrice = goodsTotal.getOwnText();
            Assertions.assertNotNull(totalPrice);
        });
    }

    @Test
    @DisplayName("Добавление товаров в корзину и избранное с последующей очисткой")
    @Feature("Каталог товаров")
    @Story("Подкатегории")
    public void shouldClearBasketAndFavorites() {
        SelenideElement basket = pageHeader.basket;
        SelenideElement favorites = pageHeader.favorites;
        SelenideElement product = catalogPage.product;
        SelenideElement buttonOrder = catalogPage.buttonOrder;
        SelenideElement favoriteButton = catalogPage.favoriteButton;
        SelenideElement removeAllButton = basketPage.removeAllButton;
        SelenideElement deleteFavsButton = favoritesScreen.deleteFavsButton;
        SelenideElement tab = mainPage.tabs.filter(text("Лекарства и БАДы")).get(0);

        step("Навести курсор на вкладку", () -> {
            tab.hover();
        });

        step("Кликнуть на появившуюся подкатегорию", () -> {
            ElementsCollection subtabs = mainPage.getSubtabs(tab);
            subtabs.filter(text("Аллергия")).get(0).click();
        });

        step("Нажать на продукт", () -> {
            product.click();
        });

        step("Добавить в корзину", () -> {
            buttonOrder.click();
        });

        step("Добавить в избранное", () -> {
            favoriteButton.click();
        });

        step("Перейти в корзину", () -> {
            basket.click();
        });

        step("Очистить корзину", () -> {
            removeAllButton.click();

        });

        step("Перейти в избранное", () -> {
            favorites.click();
        });

        step("Очистить избранное", () -> {
            deleteFavsButton.click();
        });
    }
}
