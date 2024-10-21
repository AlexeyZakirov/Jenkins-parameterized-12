import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomUtils;

import static io.qameta.allure.Allure.step;

@Tag("simple")
public class RegistrationWithFakerTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    RandomUtils randomUtils = new RandomUtils();

    String firstName = randomUtils.getFirstName();
    String lastName = randomUtils.getLastName();
    String email = randomUtils.getEmail();
    String phoneNumber = randomUtils.getNumber();
    String studentGender = randomUtils.getStudentGender();
    String month = randomUtils.getMonth();
    String year = randomUtils.getYear();
    int day = randomUtils.getDay();
    String subject = randomUtils.getSubject();
    String hobby = randomUtils.getHobbies();
    String pathToPicture = randomUtils.getImage();
    String currentAddress = randomUtils.getAddress();
    String state = randomUtils.getState();
    String city = randomUtils.getCity(state);
    String resultName = randomUtils.getResultName(firstName, lastName);
    String resultBirthday = randomUtils.getResultBirthday(year, month, day);
    String resultStateAndCity = randomUtils.getResultStateAndCity(state, city);
    String invalidStudentNumber = randomUtils.getInvalidNumber();


    @DisplayName("Заполнение всех полей при регистрации")
    @Test
    public void fillAllFieldsTest() {

        registrationPage.openPage();
        step("Заполнить все поля на странице", () -> {
            registrationPage.setStudentName(firstName, lastName)
                    .setEmail(email)
                    .setStudentGenderWrapper(studentGender)
                    .setStudentNumber(phoneNumber)
                    .setDateOfBirth(month, year, day)
                    .setSubjects(subject)
                    .setHobby(hobby)
                    .uploadPicture(pathToPicture)
                    .setCurrentAddressInput(currentAddress)
                    .setStateAndCity(state, city);
        });

        step("Подтвердить регистрацию", () -> {
            registrationPage.submitRegistration();
        });

        step("Проверить соответствие введенных значений в таблице результатов с введенными при регистрации",
                () -> {
                    registrationPage.checkResultDialogIsAppear()
                            .checkRegistrationResult("Student Name", resultName)
                            .checkRegistrationResult("Student Email", email)
                            .checkRegistrationResult("Gender", studentGender)
                            .checkRegistrationResult("Mobile", phoneNumber)
                            .checkRegistrationResult("Date of Birth", resultBirthday)
                            .checkRegistrationResult("Subjects", subject)
                            .checkRegistrationResult("Hobbies", hobby)
                            .checkRegistrationResult("Picture", pathToPicture)
                            .checkRegistrationResult("Address", currentAddress)
                            .checkRegistrationResult("State and City", resultStateAndCity);
                });
    }

    @DisplayName("Заполнение только обязательных полей при регистрации")
    @Test
    public void fillMinimumFieldsTest() {

        registrationPage.openPage();

        step("Заполнить только обязательные поля", () -> {
        registrationPage.setStudentName(firstName, lastName)
                .setEmail(email)
                .setStudentGenderWrapper(studentGender)
                .setStudentNumber(phoneNumber)
                .setDateOfBirth(month, year, day);
        });

        step("Подтвердить регистрацию", () -> {
        registrationPage.submitRegistration();
        });

        step("Проверить соответствие введенных значений в таблице результатов с введенными при регистрации",
                () -> {
        registrationPage.checkResultDialogIsAppear()
                .checkRegistrationResult("Student Name", resultName)
                .checkRegistrationResult("Student Email", email)
                .checkRegistrationResult("Gender", studentGender)
                .checkRegistrationResult("Mobile", phoneNumber)
                .checkRegistrationResult("Date of Birth", resultBirthday);
                });
    }

    @DisplayName("Ввод нечислового значения номера телефона при заполнении обязательных полей регистрации")
    @Test
    public void fillMinimumFieldsWithNonNumericNumberTest() {

        registrationPage.openPage();

        step("Заполнить только обязательные поля, в поле ввода номера телефона ввести нечисловое значение",
                () -> {
        registrationPage.setStudentName(firstName, lastName)
                .setEmail(email)
                .setStudentGenderWrapper(studentGender)
                .setStudentNumber(invalidStudentNumber)
                .setDateOfBirth(month, year, day);
                });

        step("Подтвердить регистрацию", () -> {
        registrationPage.submitRegistration();
        });

        registrationPage.checkResultIsNotAppear();
    }
}
