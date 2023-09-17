package practikum.burger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfileUser {
    private WebDriver driver;
    private By partProfile = By.xpath(".//a[contains(text(),'Профиль')]");
    private By buttonLogOut = By.xpath(".//button[contains(text(), 'Выход')]");

    public ProfileUser(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isVisibleProfile() {
        return driver.findElement(partProfile).isDisplayed();
    }
    public void buttonLogOutClick(){
        driver.findElement(buttonLogOut).click();
    }
}
