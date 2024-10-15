package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;


public class SearchTests extends TestBase {

    @Test
    @Tag("android")
    void successfulSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("android")
    void openPageTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
        step("Open page", () ->
        $(id("org.wikipedia.alpha:id/page_list_item_description")).click());
        step("Verify error after opening the page", () -> {
            $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldHave(text("Error"));
            $(id("org.wikipedia.alpha:id/view_wiki_error_button")).click();
        });
    }

    @Tag("ios")
    @Test
    public void searchIosTest() {
        step("iOS type search", () -> {
            $(id("Text Button")).click();
            $(id("Text Input")).sendKeys("hello@browserstack.com");
            $(id("Text Input")).pressEnter();
        });
        step("Verify content found", () -> {
            $(id("Text Output")).shouldHave((text("hello@browserstack.com")));
        });
    }
}