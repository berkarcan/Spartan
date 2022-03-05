package spartan.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditSpartanPage {
    @FindBy(id = "name")
    public WebElement nameBox;

    @FindBy(id = "phone")
    public WebElement phoneBox;

    @FindBy(id="genderSelect")
    public WebElement genderDropdown;

    @FindBy(xpath="//*[@type='submit']")
    public WebElement updateButton;
}
