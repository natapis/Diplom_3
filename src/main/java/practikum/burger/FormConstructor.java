package practikum.burger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormConstructor {
    private WebDriver driver;
    private By nameForm = By.xpath(".//h1[contains(text(), 'Соберите бургер')]");
    private By loginButton = By.xpath(".//button[contains(text(), 'Войти в аккаунт')]");
    private By bunsTab = By.xpath(".//span[contains(text(), 'Булки')]");
    private By sauceTab = By.xpath(".//span[contains(text(), 'Соусы')]");
    private By fillingTab = By.xpath(".//span[contains(text(), 'Начинки')]");
    private By bTab = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    private By sTab = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    private By fTab = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");

    public FormConstructor(WebDriver driver){
        this.driver = driver;
    }

    public void loginButtonClick(){
        driver.findElement(loginButton).click();
    }
    public boolean isVisibleFormConstructor(){
        return driver.findElement(nameForm).isDisplayed();
    }
    public void bunsTabClick(){
        driver.findElement(bunsTab).click();
    }
    public void sauceTabClick(){
        driver.findElement(sauceTab).click();
    }
    public void fillingTabClick(){
        driver.findElement(fillingTab).click();
    }

    public boolean isCurrentBunsTub(){
        String nameClass = driver.findElement(bTab).getAttribute("class");
        return nameClass.contains("current");
    }
    public boolean isCurrentSauceTub(){
        String nameClass = driver.findElement(sTab).getAttribute("class");
        return nameClass.contains("current");
    }

    public boolean isCurrentFillingTub(){
        String nameClass = driver.findElement(fTab).getAttribute("class");
        return nameClass.contains("current");
    }

}
