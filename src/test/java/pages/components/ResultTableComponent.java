package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {
    protected final SelenideElement resultDialog = $(".modal-dialog"),
            table = $(".table");

    protected void checkResultTable(String key, String value) {
        table.$(byText(key)).parent().shouldHave(text(value));
    }

}
