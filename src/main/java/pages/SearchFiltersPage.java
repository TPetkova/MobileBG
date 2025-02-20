package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Optional;

public class SearchFiltersPage extends BasePage {

    @FindBy(css = "input[type=text][name=marka]") private WebElement carBrandInput;

    @FindBy(css = "#akSearchMarki div.scroll div.a[data-popular=true]") private List<WebElement> carPopularBrandList;

    @FindBy(css = "input[type=text][name=model]") private WebElement carModelInput;

    @FindBy(css = "#akSearchModeli div.scroll div.a > span:first-child") private List<WebElement> carModelList;

    @FindBy(id = "eimg88") private WebElement fourWheelDriveOption;

    @FindBy(css = "a.SEARCH_btn") private WebElement searchButton;


    Actions action = new Actions(driver);

    public SearchFiltersPage(WebDriver driver) {
        super(driver);
    }

    public void clickCarBrandInput() {
        carBrandInput.click();
    }

    public void clickCarModelInput() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(carModelInput));
            wait.until(ExpectedConditions.domAttributeToBe(carBrandInput,"value","VW"));
//            wait.until(ExpectedConditions.stalenessOf(carModelInput));
            carModelInput.click();
        }
        catch (NullPointerException exception) {
            System.out.println("Something went wrong.");
        }

    }

    public List<WebElement> getPopularCarBrandList() {
        return carPopularBrandList;
    }

    public List<WebElement> getCarModelList() {
        return carModelList;
    }

    public void selectCarBrand(String brand) {
        clickCarBrandInput();
        Optional<WebElement> element = getPopularCarBrandList().stream()
                .filter(e -> e.getText().contains(brand)).findFirst();
        if (element.isPresent()) {
            element.get().click();
        } else {
            throw new NullPointerException("There is no such car brand: " + brand);
        }
    }

    public void selectCarModel(String model) {
        clickCarModelInput();
        carModelInput.sendKeys(model);
        Optional<WebElement> element = getCarModelList().stream()
                .filter(e -> e.getText().equals(model)).findFirst();
        if (element.isPresent()) {
            element.get().click();
        } else {
            throw new NullPointerException("There is no such car model for this brand " + model);
        }
    }

    public void clickFourWheelDrive() {
        action.moveToElement(fourWheelDriveOption).perform();
        fourWheelDriveOption.click();
    }

    public void clickSearchButton() {
        action.moveToElement(searchButton).perform();
        searchButton.click();
    }

}
