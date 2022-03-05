package spartan.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllSpartanPage extends BasePage{

    @FindBy(linkText = "Add Spartan")
    public WebElement addSpartanButton;


}
