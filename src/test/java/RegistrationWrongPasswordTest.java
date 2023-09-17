import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import practikum.burger.FormLogin;
import practikum.burger.FormRegistration;
import practikum.burger.Header;
import practikum.burger.ProfileUser;
import user.LoginResponse;
import user.UserClient;
import user.UserCreds;

import java.util.concurrent.TimeUnit;

import static constants.Api.BASE_URL;

public class RegistrationWrongPasswordTest {
    private WebDriver driver;
    private Header header;
    private Faker faker = new Faker();
    private String email;
    private String password;
    private FormLogin formLogin;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        RestAssured.baseURI = BASE_URL;
        header = new Header(driver);
        header.open();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        header.buttonAccountClick();
        formLogin = new FormLogin(driver);
        formLogin.registrationButtonClick();
    }

    @DisplayName("Регистрация пользователя c паролем до 6 символов")
    @Test
    public void registration() {
        FormRegistration formRegistration = new FormRegistration(driver);
        formRegistration.isVisibleFormRegistration();
        email = faker.internet().emailAddress();
        password = faker.internet().password(1, 5);
        formRegistration.setInputEmail(email);
        formRegistration.setInputName(faker.name().username());
        formRegistration.setInputPassword(password);
        formRegistration.registrationButtonClick();

        formRegistration.isVisibleMessageErrorPassword();

        header.buttonAccountClick();

        formLogin.isVisibleForm();
        formLogin.setInputEmail(email);
        formLogin.setInputPassword(password);
        formLogin.loginButtonClick();
        Assert.assertEquals("Регистрация прошла", true, formLogin.isVisibleForm());

    }

    @After
    public void tearDown() {
        driver.quit();
        UserClient userClient = new UserClient();
        Response loginResponse = userClient.loginUser(new UserCreds(email, password));
        if (loginResponse.statusCode() == 200) {
            String token = loginResponse.body().as(LoginResponse.class).getAccessToken();
            userClient.deleteUser(token);
        }
    }
}
