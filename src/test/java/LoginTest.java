import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import practikum.burger.*;
import user.LoginResponse;
import user.User;
import user.UserClient;
import user.UserCreds;

import java.util.concurrent.TimeUnit;

import static constants.Api.BASE_URL;
@RunWith(Parameterized.class)

public class LoginTest {
    private WebDriver driver;
    private String userName;
    private String email;
    private String password;
    private UserClient userClient;
    private boolean isLoginExpected;
    private By buttonLogin;
    private Faker faker = new Faker();
    public LoginTest(String userName, String email, String password, UserClient userClient, boolean isLoginExpected, By buttonLogin){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userClient = userClient;
        this.isLoginExpected = isLoginExpected;
        this.buttonLogin = buttonLogin;
    }
    @Parameterized.Parameters(name="Авторизация пользователя по кнопке {5} = {4}")
    public static Object[][] loginTestData(){
        return new Object[][]{
                {"test1988test", "testJava@mail.ru", "password1", new UserClient(), true, By.xpath(".//p[contains(text(), 'Личный Кабинет')]")},
                {"test1898test", "testJava22@mail.ru", "password2", new UserClient(), true, By.xpath(".//button[contains(text(), 'Войти в аккаунт')]")},
                {"test1899test1", "testJava2231@mail.ru", "password231", new UserClient(), true, By.xpath(".//p[contains(text(), 'Уже зарегистрированы?')]/a[contains(text(), 'Войти')]")},
                {"test1777test2", "testJava2232@mail.ru", "pass213word", new UserClient(), true,By.xpath(".//p[contains(text(), 'Вспомнили пароль?')]/a[contains(text(), 'Войти')]")},

        };
    }

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        RestAssured.baseURI = BASE_URL;
 //       userClient = new UserClient();
 //       Faker faker = new Faker();
 //       userName = faker.name().username();
 //       email = faker.internet().emailAddress();
//        password = faker.internet().password();
        User user = new User(email, password, userName);
        userClient.createUser(user);
    }

    @DisplayName("Авторизация пользователя")
    @Test
    public void login(){
        Header header = new Header(driver);
        header.open();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        FormConstructor formConstructor = new FormConstructor(driver);
        FormLogin formLogin = new FormLogin(driver);
        if (buttonLogin.equals(By.xpath(".//p[contains(text(), 'Личный Кабинет')]"))){
            header.buttonAccountClick();
        } else if (buttonLogin.equals(By.xpath(".//button[contains(text(), 'Войти в аккаунт')]"))){
            formConstructor.loginButtonClick();
        }else if (buttonLogin.equals(By.xpath(".//p[contains(text(), 'Уже зарегистрированы?')]/a[contains(text(), 'Войти')]"))){
            header.buttonAccountClick();
            formLogin.registrationButtonClick();
            FormRegistration formRegistration = new FormRegistration(driver);
            formRegistration.loginButtonClick();
        } else if (buttonLogin.equals(By.xpath(".//p[contains(text(), 'Вспомнили пароль?')]/a[contains(text(), 'Войти')]"))){
            header.buttonAccountClick();
            formLogin.restoreButtonClick();
            FormRestore formRestore = new FormRestore(driver);
            formRestore.loginButtonClick();
        }
     //   FormLogin formLogin = new FormLogin(driver);
        formLogin.isVisibleForm();
        formLogin.setInputEmail(email);
        formLogin.setInputPassword(password);
        formLogin.loginButtonClick();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        header.buttonAccountClick();
        ProfileUser profileUser = new ProfileUser(driver);
        boolean isLoginActual = profileUser.isVisibleProfile();
        Assert.assertEquals("Вход не осуществлен", isLoginExpected, isLoginActual);
    }
    @After
    public void tearDown(){
        driver.quit();
        Response loginResponse = userClient.loginUser(new UserCreds(email, password));
        if (loginResponse.statusCode() == 200) {
            String token = loginResponse.body().as(LoginResponse.class).getAccessToken();
            userClient.deleteUser(token);
        }
    }
}
