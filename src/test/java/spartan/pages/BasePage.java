package spartan.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import spartan.utilities.Driver;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id = "name")
    public WebElement nameBox;

    @FindBy(id = "phone")
    public WebElement phoneBox;

    @FindBy(id="genderSelect")
    public WebElement genderDropdown;
}
