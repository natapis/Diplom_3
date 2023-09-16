package practikum.burger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormRegistration {
    private WebDriver driver;
    public FormRegistration(WebDriver driver){
        this.driver = driver;
    }
    private By loginButton = By.xpath(".//p[contains(text(), 'Уже зарегистрированы?')]/a[contains(text(), 'Войти')]");
    public void loginButtonClick(){
        driver.findElement(loginButton).click();
    }
}
