package utils;

import com.github.javafaker.Faker;

public class RandomUtils {
    private final Faker faker = new Faker();

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getNumber() {
        return faker.numerify("##########");
    }

    public String getStudentGender() {
        String[] genders = {"Male", "Female", "Other"};
        return faker.options().option(genders);
    }

    public String getMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};
        return faker.options().option(months);
    }

    public String getYear() {
        int intYear = faker.number().numberBetween(1970, 2024);
        return Integer.toString(intYear);
    }

    public int getDay() {
        return faker.number().numberBetween(1, 28);
    }

    public String getSubject() {
        String[] subjects = {"English", "Chemistry", "Computer Science", "Commerce", "Economics", "Social Studies",
                "Arts", "History", "Maths", "Accounting", "Physics", "Biology", "Hindi"};
        return faker.options().option(subjects);
    }

    public String getHobbies() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return faker.options().option(hobbies);
    }

    public String getImage() {
        String[] images = {"img1.png", "img2.png", "img3.png"};
        return faker.options().option(images);
    }

    public String getAddress() {
        return faker.address().streetAddress();
    }

    public String getState() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return faker.options().option(states);
    }

    public String getCity(String state) {
        return switch (state) {
            case ("NCR") -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case ("Uttar Pradesh") -> faker.options().option("Agra", "Lucknow", "Merrut");
            case ("Haryana") -> faker.options().option("Karnal", "Panipat");
            case ("Rajasthan") -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }

    public String getResultName(String firstName, String lastName) {
        return String.format("%s %s", firstName, lastName);
    }

    public String getResultBirthday(String year, String month, int day) {
        return String.format("%d %s,%s", day, month, year);
    }

    public String getResultStateAndCity(String state, String city) {
        return String.format("%s %s", state, city);
    }

    public String getInvalidNumber() {
        return faker.letterify("?????");
    }
}
