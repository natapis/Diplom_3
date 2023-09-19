import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import practikum.burger.FormConstructor;
import practikum.burger.Header;

import java.util.concurrent.TimeUnit;

public class TransitionTabsConstructorTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Header header = new Header(driver);
        header.open();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @DisplayName("Переход к начинкам")
    @Test
    public void transitionForTabFilling() {
        FormConstructor formConstructor = new FormConstructor(driver);
        Assert.assertEquals(false, formConstructor.isCurrentFillingTub());
        formConstructor.fillingTabClick();
        Assert.assertEquals(true, formConstructor.isCurrentFillingTub());
    }

    @DisplayName("Переход к соусам")
    @Test
    public void transitionForTabSauce() {
        FormConstructor formConstructor = new FormConstructor(driver);
        formConstructor.fillingTabClick();
        Assert.assertEquals(false, formConstructor.isCurrentSauceTub());
        formConstructor.sauceTabClick();
        Assert.assertEquals(true, formConstructor.isCurrentSauceTub());
    }

    @DisplayName("Переход к булкам")
    @Test
    public void transitionForTabBuns() {
        FormConstructor formConstructor = new FormConstructor(driver);
        formConstructor.fillingTabClick();
        Assert.assertEquals(false, formConstructor.isCurrentBunsTub());
        formConstructor.bunsTabClick();
        Assert.assertEquals(true, formConstructor.isCurrentBunsTub());
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
