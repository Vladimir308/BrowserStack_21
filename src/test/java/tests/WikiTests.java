package tests;

import data.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

@Tag("search")
@DisplayName("Проверка поиска в приложении Wiki")
public class WikiTests extends TestBase {

    TestData data = new TestData();

    @Test
    @DisplayName("Успешный поиск по поисковому запросу")
    void successfulSearchTest() {
        step("Вводим поисковый запрос в серч бар", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(data.searchTerm);
        });
        step("Проверяем результаты поиска", () ->
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @DisplayName("Открытие первой статьи в результатах поиска")
    void openArticleTest() {
        step("Вводим поисковый запрос в серч бар", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(data.searchTerm);

    });
        step("Проверяем результаты поиска", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0))
        );
        step("Нажимаем на первую статью в списке результата поиска", () ->
            $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click()
        );
        step("Проверяем открытие страницы статьи", () ->
                $(byClassName("android.widget.TextView")).shouldHave(text(data.errorMessage))
        );

    }

    @Test
    @DisplayName("Проверка краткого описания статьи")
    void checkDescriptionTest() {
        step("Вводим поисковый запрос в серч бар", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(data.searchTerm);
        });

        step("Проверяем, что найденный заголовок имеет корректное описание", () ->
                $(id("org.wikipedia.alpha:id/page_list_item_description"))
                        .shouldHave(text(data.description)));
    }
}