import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import practikum.burger.FormLogin;
import practikum.burger.Header;
import practikum.burger.ProfileUser;
import user.LoginResponse;
import user.User;
import user.UserClient;
import user.UserCreds;

import java.util.concurrent.TimeUnit;

import static constants.Api.BASE_URL;

public class LoginTest {
    private WebDriver driver;
    String userName;
    String email;
    String password;
    UserClient userClient;
    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        RestAssured.baseURI = BASE_URL;
        userClient = new UserClient();
        Faker faker = new Faker();
        userName = faker.name().username();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        User user = new User(email, password, userName);
        userClient.createUser(user);
    }

    @Test
    public void login(){
        Header header = new Header(driver);
        header.open();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        header.buttonAccountClick();
        FormLogin formLogin = new FormLogin(driver);
        formLogin.isVisibleForm();
        formLogin.setInputEmail(email);
        formLogin.setInputPassword(password);
        formLogin.loginButtonClick();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        header.buttonAccountClick();
        ProfileUser profileUser = new ProfileUser(driver);
        Assert.assertEquals(true, profileUser.isVisibleProfile());
    }
    @After
    public void tearDown(){
        Response loginResponse = userClient.loginUser(new UserCreds(email, password));
        String token = loginResponse.body().as(LoginResponse.class).getAccessToken();
        userClient.deleteUser(token);
        driver.quit();
    }
}
