package practikum.burger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormRestore {
    private WebDriver driver;
    public FormRestore(WebDriver driver){
        this.driver = driver;
    }
    private By loginButton = By.xpath(".//p[contains(text(), 'Вспомнили пароль?')]/a[contains(text(), 'Войти')]");
    public void loginButtonClick(){
        driver.findElement(loginButton).click();
    }
}
