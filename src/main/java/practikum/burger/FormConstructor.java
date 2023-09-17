package practikum.burger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormConstructor {
    private WebDriver driver;
    private By nameForm = By.xpath(".//h1[contains(text(), 'Соберите бургер')]");
    private By loginButton = By.xpath(".//button[contains(text(), 'Войти в аккаунт')]");
    public FormConstructor(WebDriver driver){
        this.driver = driver;
    }

    public void loginButtonClick(){
        driver.findElement(loginButton).click();
    }
    public boolean isVisibleFormConstructor(){
        return driver.findElement(nameForm).isDisplayed();
    }
}
