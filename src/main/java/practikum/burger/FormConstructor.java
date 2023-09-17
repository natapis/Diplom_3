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
    private By bunsPartName = By.xpath(".//h2[contains(text(), 'Булки')]");
    private By saucePartName = By.xpath(".//h2[contains(text(), 'Соусы')]");
    private By fillingPartName = By.xpath(".//h2[contains(text(), 'Начинки')]");
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

    public boolean isSelectedBunsTub(){
        return driver.findElement(bunsTab).isSelected();
    }
    public boolean isSelectedFillingTub(){
        return driver.findElement(fillingTab).isSelected();
    }
    public boolean isSelectedSauceTub(){
        return driver.findElement(sauceTab).isSelected();
    }


}
