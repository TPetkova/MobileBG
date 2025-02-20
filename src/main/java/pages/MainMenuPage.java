package pages;

import data.MainMenuItems;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class MainMenuPage extends BasePage {
    @FindBy(css = ".iMenu a") private List<WebElement> mainMenu;

    public MainMenuPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getMainMenuItems() {
        return mainMenu;
    }

    public void selectMainMenuItem(MainMenuItems item, String locale) {
        Optional<WebElement> element = getMainMenuItems().stream()
                        .filter(e -> e.getText().equals(item.getLocalized(locale))).findFirst();
        if (element.isPresent()) {
            element.get().click();
        } else {
            throw new NullPointerException("Cannot find item: " + item);
        }
    }
}
