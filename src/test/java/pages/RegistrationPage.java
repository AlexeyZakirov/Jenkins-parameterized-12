package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.ResultTableComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage extends ResultTableComponent {

    public final String registrationEndpoint = "/automation-practice-form";
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            studentGenderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit");


    private void removeAdd() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    @Step("Открыть страницу регистрации")
    public RegistrationPage openPage() {
        open(registrationEndpoint);
        removeAdd();
        return this;
    }

    @Step("Ввести имя - {first} и фамилию - {last}")
    public RegistrationPage setStudentName(String first, String last) {
        firstNameInput.setValue(first);
        lastNameInput.setValue(last);
        return this;
    }

    @Step("Ввести email - {email}")
    public RegistrationPage setEmail(String email) {
        userEmailInput.setValue(email);
        return this;
    }

    @Step("Ввести пол студента - {gender}")
    public RegistrationPage setStudentGenderWrapper(String gender) {
        studentGenderWrapper.$(byText(gender)).click();
        return this;
    }

    @Step("Ввести номер телефона студента - {number}")
    public RegistrationPage setStudentNumber(String number) {
        userNumberInput.setValue(number);
        return this;
    }

    @Step("Выбрать дату рождения студента (год - {year}, месяц - {month}, день - {day})")
    public RegistrationPage setDateOfBirth(String month, String year, int day) {
        CalendarComponent calendarComponent = new CalendarComponent();
        dateOfBirthInput.click();
        calendarComponent.setDate(month, year, day);
        return this;
    }

    @Step("Выбрать любимый предмет студента - {subject}")
    public RegistrationPage setSubjects(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    @Step("Выбрать хобби студента - {hobby}")
    public RegistrationPage setHobby(String hobby) {
        hobbiesWrapper.$(byText(hobby)).scrollTo().click();
        return this;
    }

    @Step("Загрузить картинку {picture}")
    public RegistrationPage uploadPicture(String picture) {
        if(Configuration.browser.equals("firefox")){
            return this;
        }
        uploadPicture.uploadFromClasspath(picture);
        return this;
    }

    @Step("Ввести текущий адрес студента - {address}")
    public RegistrationPage setCurrentAddressInput(String address) {
        currentAddressInput.scrollTo().setValue(address);
        return this;
    }

    @Step("Выбрать штат - {state} и город - {city}")
    public RegistrationPage setStateAndCity(String state, String city) {
        stateInput.scrollTo().click();
        stateInput.$(byText(state)).click();
        cityInput.click();
        cityInput.$(byText(city)).click();
        return this;
    }

    @Step("Кликнуть на кнопку Submit")
    public RegistrationPage submitRegistration() {
        submitButton.scrollTo().click();
        return this;
    }

    @Step("Проверить результат регистрации для поля {key} со значением {value}")
    public RegistrationPage checkRegistrationResult(String key, String value) {
        if(Configuration.browser.equals("firefox") && key.equals("Picture")){
            return this;
        }
        checkResultTable(key, value);
        return this;
    }

    @Step("Проверить, что таблица с результатами не отобразилась")
    public void checkResultIsNotAppear() {
        resultDialog.shouldNot(appear);
    }

    @Step("Проверить, что таблица с результатами отобразилась")
    public RegistrationPage checkResultDialogIsAppear() {
        resultDialog.should(appear);
        return this;
    }
}
