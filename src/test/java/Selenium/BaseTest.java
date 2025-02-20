package Selenium;

import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected WebDriver driver;
    private String baseUrl;


    protected void testSetup() {
        driver = new ChromeDriver();
        baseUrl = "https://www.mobile.bg/";
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    protected void testTearDown() {
        driver.quit();
    }

}
