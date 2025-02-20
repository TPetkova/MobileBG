package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultPage extends BasePage{

    @FindBy(css = "div > h1") private WebElement title;

    @FindBy(css = "input[type='hidden'] ~ div[style*='font-size:14']") private WebElement totalPosts;

    @FindBy(css = "div.item.TOP") private List<WebElement> topPosts;

    @FindBy(css = "div.item.VIP") private List<WebElement> vipPosts;

    @FindBy(css = "div.pagination a:last-child") private WebElement nextButton;

    Logger logger = Logger.getLogger("");

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        wait.until(ExpectedConditions
                .urlToBe("https://www.mobile.bg/obiavi/avtomobili-dzhipove/vw/golf?extri=3"));
        return title.getText();
    }

    public void getTotalPosts() {
        String total = totalPosts.getText();
        String substrTotal = total.substring(15);
        logger.log(Level.INFO, substrTotal);
    }

    public void clickNextPage() {
        JavascriptExecutor je = (JavascriptExecutor)driver;
        je.executeScript("arguments[0].scrollIntoView();",nextButton);
        nextButton.click();
    }

    public void countTopAndVipPosts() {
        int countTop = 0;
        int countVip = 0;

        while (topPosts.stream().count() > 0 || vipPosts.stream().count() > 0) {
            countTop = countTop + topPosts.size();
            countVip = countVip + vipPosts.size();
            clickNextPage();
        }

        int totalVipTop = countTop + countVip;
        logger.log(Level.INFO, "Топ обяви: " + countTop);
        logger.log(Level.INFO, "Вип обяви: " + countVip);
        logger.log(Level.INFO, "Общо вип и топ обяви: " + totalVipTop);

    }
}
