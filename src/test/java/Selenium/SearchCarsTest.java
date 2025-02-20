package Selenium;

import data.MainMenuItems;
import jdk.jfr.Description;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import pages.CookieBannerPage;
import pages.MainMenuPage;
import pages.ResultPage;
import pages.SearchFiltersPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchCarsTest extends BaseTest {
    private MainMenuPage mainMenuPage;
    private CookieBannerPage cookieBannerPage;
    private SearchFiltersPage searchFiltersPage;
    private ResultPage resultPage;

    @BeforeAll
    public void setup() {
        testSetup();
        mainMenuPage = new MainMenuPage(driver);
        cookieBannerPage = new CookieBannerPage(driver);
        searchFiltersPage = new SearchFiltersPage(driver);
        resultPage = new ResultPage(driver);

    }

    @Order(1)
    @Test
    @Description("Search car by brand, model and other filter - four wheel drive")
    public void searchByBrandModelAndFourWheelDrive() {
        cookieBannerPage.acceptCookies();
        cookieBannerPage.cookieDisappear();
        assertFalse(cookieBannerPage.isCookieBanner(), "Cookie banner is still on the page!");
        mainMenuPage.selectMainMenuItem(MainMenuItems.SEARCH,"BG");
        assertEquals("https://www.mobile.bg/search/avtomobili-dzhipove", driver.getCurrentUrl(),
                "Search page is not open");
        searchFiltersPage.selectCarBrand("VW");
        searchFiltersPage.selectCarModel("Golf");
        searchFiltersPage.clickFourWheelDrive();
        searchFiltersPage.clickSearchButton();
        assertTrue(resultPage.getTitleText().contains("Обяви за VW Golf"));
        resultPage.getTotalPosts();
    }

    @Order(2)
    @Test
    @Description("Count TOP and VIP posts for VW Golf 4x4")
    public void countTopVipPosts() {
        assertEquals("https://www.mobile.bg/obiavi/avtomobili-dzhipove/vw/golf?extri=3",
                driver.getCurrentUrl(), "Search page with filters is not open");
        resultPage.countTopAndVipPosts();
    }

    @AfterAll
    public void teardown(){
        testTearDown();
    }

}
