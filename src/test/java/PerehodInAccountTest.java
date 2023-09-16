import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import practikum.burger.Header;

import java.util.concurrent.TimeUnit;

public class PerehodInAccountTest {
    private WebDriver driver;
    @Before
    public void setUp(){
        driver = new ChromeDriver();
 //       driver = new FirefoxDriver();
 //       driver = new YandexDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void perehod(){
        Header header = new Header(driver);
        header.open();
        header.buttonAccountClick();
    }
    @After
    public void tearDown(){
        driver.quit();
    }


}
