package practikum.burger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormRegistration {
    private WebDriver driver;
    public FormRegistration(WebDriver driver){
        this.driver = driver;
    }
    private By inputName = By.xpath(".//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name='name']");
    private By inputEmail = By.xpath(".//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name= 'name']");
    private By inputPassword = By.xpath(".//input[@name='Пароль']");
    private By registrationButton = By.xpath(".//button[contains(text(), 'Зарегистрироваться')]");
    private By nameForm = By.xpath(".//h2[contains(text(), 'Регистрация')]");
    private By loginButton = By.xpath(".//p[contains(text(), 'Уже зарегистрированы?')]/a[contains(text(), 'Войти')]");
    private By messageErrorPassword = By.xpath(".//p[@class='input__error text_type_main-default']");
    public void loginButtonClick(){
        driver.findElement(loginButton).click();
    }
    public boolean isVisibleFormRegistration(){
        return driver.findElement(nameForm).isDisplayed();
    }
    public void setInputName(String name){
        driver.findElement(inputName).click();
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
    }

    public void setInputEmail(String name){
        driver.findElement(inputEmail).click();
        driver.findElement(inputEmail).clear();
        driver.findElement(inputEmail).sendKeys(name);
    }

    public void setInputPassword(String name){
        driver.findElement(inputPassword).click();
        driver.findElement(inputPassword).clear();
        driver.findElement(inputPassword).sendKeys(name);
    }

    public void registrationButtonClick(){
        driver.findElement(registrationButton).click();
    }
    public boolean isVisibleMessageErrorPassword(){
        return driver.findElement(messageErrorPassword).isDisplayed();
    }

}
