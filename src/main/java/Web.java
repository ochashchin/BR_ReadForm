import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Web {

    private Student student;
    private WebDriver driver;

    Web() {
        student = new Student(1);

        driver = init();

        typeFirstName();
        typeLastName();
        typeEmail();
        selectGender();
        typePhoneNumber();
        typeDOB();
        typeSubjects();
        selectHobbies();
        selectPicture();
        typeCurrentAddress();
        selectState();
        selectCity();
        clickSubmit();
        isFormCompleted();
        close();
    }

    private void close() {
        driver.quit();
    }

    WebDriver init() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\selenium\\chromedriver_97.0.4692.71.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/automation-practice-form");
        return driver;
    }

    private void typeFirstName() {
        type(By.id("firstName"), student.getFirstName());
    }

    private void typeLastName() {
        type(By.id("lastName"), student.getLastName());
    }

    private void typeEmail() {
        type(By.id("userEmail"), student.getEmail());
    }

    private void selectGender() {
        switch (student.getGender()) {
            case "Male" -> click(By.xpath("//div[@id='genterWrapper']/div[2]/div/label"));

            case "Female" -> click(By.xpath("//div[@id='genterWrapper']/div[2]/div[2]/label"));

            case "Other" -> click(By.xpath("//div[@id='genterWrapper']/div[2]/div[3]/label"));
        }
    }

    private void typePhoneNumber() {
        type(By.id("userNumber"), student.getMobile());
    }

    private void typeDOB() {
        click(By.id("dateOfBirthInput"));
        type(By.id("dateOfBirthInput"), Keys.CONTROL + "a");
        type(By.id("dateOfBirthInput"), student.getDob());
        type(By.id("dateOfBirthInput"), String.valueOf(Keys.ENTER));
    }

    private void typeSubjects() {
        for (String s : student.getSubjects()) {
            type(By.id("subjectsInput"), s);
            type(By.id("subjectsInput"), String.valueOf(Keys.ENTER));
        }
    }

    private void selectHobbies() {
        for (String s : student.getHobbies()) {
            switch (s) {
                case "Sports" -> click(By.xpath("//div[@id='hobbiesWrapper']/div[2]/div/label"));

                case "Reading" -> click(By.xpath("//div[@id='hobbiesWrapper']/div[2]/div[2]/label"));

                case "Music" -> click(By.xpath("//div[@id='hobbiesWrapper']/div[2]/div[3]/label"));
            }
        }
    }

    private void selectPicture() {
        WebElement fileInput = driver.findElement(By.id("uploadPicture"));
        fileInput.sendKeys(student.getPhoto().toString());
    }

    private void typeCurrentAddress() {
        type(By.id("currentAddress"), student.getCurrentAddress());
    }

    private void selectState() {
        type(By.id("react-select-3-input"), student.getState());
        type(By.id("react-select-3-input"), String.valueOf(Keys.ENTER));
    }

    private void selectCity() {
        type(By.id("react-select-4-input"), student.getCity());
        type(By.id("react-select-4-input"), String.valueOf(Keys.ENTER));
    }

    void clickSubmit() {
        type(By.id("submit"), String.valueOf(Keys.DOWN));
        type(By.id("submit"), String.valueOf(Keys.DOWN));
        driver.findElement(By.id("submit")).click();
    }

    private void isFormCompleted() {
        if (isElementPresent(By.id("example-modal-sizes-title-lg"))) {
            student.write(student);
        }
    }

    private void type(By by, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(text);
    }

    private void click(By by) {
        driver.findElement(by).click();
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
