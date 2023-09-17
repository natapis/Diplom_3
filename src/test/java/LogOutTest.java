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
import practikum.burger.*;
import user.LoginResponse;
import user.User;
import user.UserClient;
import user.UserCreds;

import java.util.concurrent.TimeUnit;

import static constants.Api.BASE_URL;

public class LogOutTest {
    private WebDriver driver;
    private String email;
    private String password;
    private UserClient userClient;
    private Faker faker = new Faker();
    private Header header;
    private ProfileUser profileUser;
    private FormLogin formLogin;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        RestAssured.baseURI = BASE_URL;
        userClient = new UserClient();
        String userName = faker.name().username();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        User user = new User(email, password, userName);
        userClient.createUser(user);

        header = new Header(driver);
        header.open();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        header.buttonAccountClick();

        formLogin = new FormLogin(driver);
        formLogin.isVisibleForm();
        formLogin.setInputEmail(email);
        formLogin.setInputPassword(password);
        formLogin.loginButtonClick();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        header.buttonAccountClick();

        profileUser = new ProfileUser(driver);
        profileUser.isVisibleProfile();
    }

    @DisplayName("Выход из аккаунта")
    @Test
    public void logOutAccount() {
        profileUser.buttonLogOutClick();
        formLogin.isVisibleForm();
        header.buttonAccountClick();
        boolean isLogOutActual = formLogin.isVisibleForm();
        Assert.assertEquals("Выход из аккаунта не осуществлен", true, isLogOutActual);
    }

    @After
    public void tearDown() {
        driver.quit();
        Response loginResponse = userClient.loginUser(new UserCreds(email, password));
        if (loginResponse.statusCode() == 200) {
            String token = loginResponse.body().as(LoginResponse.class).getAccessToken();
            userClient.deleteUser(token);
        }
    }
}
