package practikum.burger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormConstructor {
    private WebDriver driver;
    public FormConstructor(WebDriver driver){
        this.driver = driver;
    }
    private By loginButton = By.xpath(".//button[contains(text(), 'Войти в аккаунт')]");
    public void loginButtonClick(){
        driver.findElement(loginButton).click();
    }
}
