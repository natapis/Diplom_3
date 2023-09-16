package practikum.burger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormLogin {
    private WebDriver driver;
    public FormLogin(WebDriver driver){
        this.driver = driver;
    }
    private By nameForm = By.xpath(".//h2[text()='Вход']");
    private By inputEmail = By.name("name");
    private By inputPassword = By.name("Пароль");
    private By loginButton = By.xpath(".//button[text()='Войти']");
    private By registrationButton = By.xpath(".//a[text()='Зарегистрироваться']");
    private By restoreButton = By.xpath(".//a[text()='Восстановить пароль']");

    public boolean isVisibleForm(){
        return driver.findElement(nameForm).isDisplayed();
    }

    public void setInputEmail(String email){
        driver.findElement(inputEmail).click();
        driver.findElement(inputEmail).clear();
        driver.findElement(inputEmail).sendKeys(email);
    }
    public void setInputPassword(String password){
        driver.findElement(inputPassword).click();
        driver.findElement(inputPassword).clear();
        driver.findElement(inputPassword).sendKeys(password);
    }
    public void loginButtonClick(){
        driver.findElement(loginButton).click();
    }
}
