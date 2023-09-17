package practikum.burger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private String url="https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;
    private By buttonConstructor = By.xpath(".//p[contains(text(), 'Конструктор')]");
    private By buttonHistory = By.xpath(".//li[contains(@class, 'undefined ml-2')]");
    private By buttonLogotip = By.xpath(".//div[contains(@class, 'AppHeader_header__logo__2D0X2')]");
    private By buttonAccount = By.xpath(".//p[contains(text(), 'Личный Кабинет')]");
    public Header(WebDriver driver){this.driver = driver;}
    public void open(){

        driver.get(url);

    }
    public void buttonConstructorClick(){
        driver.findElement(buttonConstructor).click();
    }
    public void buttonHistoryClick(){
        driver.findElement(buttonHistory).click();
    }
    public void buttonLogotipClick(){
        driver.findElement(buttonLogotip).click();
    }

    public void buttonAccountClick(){
        driver.findElement(buttonAccount).click();
    }


}
