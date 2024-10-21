package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class CalendarComponent {
    private final SelenideElement monthSelect = $(".react-datepicker__month-select");
    private final SelenideElement yearSelect = $(".react-datepicker__year-select");

    public void setDate(String month, String year, int day) {
        String daySelect;
        monthSelect.selectOption(month);
        yearSelect.selectOption(year);
        if (day < 10) {
            daySelect = format(".react-datepicker__day--00%d:not(.react-datepicker__day--outside-month)", day);
        } else {
            daySelect = format(".react-datepicker__day--0%d:not(.react-datepicker__day--outside-month)", day);
        }
        $(daySelect).click();
    }
}
