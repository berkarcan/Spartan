package spartan.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends  BasePage{
    @FindBy(linkText = "Web Data")
    public WebElement webdataLink;

    @FindBy(linkText = "API Doc")
    public WebElement apiDocLink;
}
