package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CookieBannerPage extends BasePage{

    @FindBy(id = "cookiescript_injected") private WebElement cookieBanner;
    @FindBy(id = "cookiescript_close") private WebElement cookieBannerClose;
    @FindBy(id = "cookiescript_accept") private WebElement cookieAccept;

    public CookieBannerPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCookieBanner() {
        return cookieBanner.isDisplayed();
    }

    public void closeCookieBanner() {
        if (isCookieBanner()) {
            cookieBannerClose.click();
        }
    }

    public void acceptCookies() {
        cookieAccept.click();
    }

    public void cookieDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("cookiescript_injected")));
    }

}
