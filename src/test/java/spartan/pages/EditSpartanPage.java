package spartan.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import spartan.utilities.BrowserUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static spartan.utilities.Names.Name;

public class EditSpartanPage extends BasePage{
    @FindBy(id = "name")
    public WebElement nameBox;

    @FindBy(id = "phone")
    public WebElement phoneBox;

    @FindBy(id="genderSelect")
    public WebElement genderDropdown;

    @FindBy(xpath="//*[@type='submit']")
    public WebElement updateButton;

    public Map<String,String> fillForm(){
        Faker faker=new Faker();
        //String expectedName=faker.name().firstName();
        String expectedNumber=faker.phoneNumber().subscriberNumber(10);
        String expectedGender=new Random().nextInt(2)==1? "Female":"Male";
        String expectedName=Name(expectedGender);
        BrowserUtils.waitFor(0.5);
        BrowserUtils.waitForVisibility(nameBox,10);
        BrowserUtils.waitForVisibility(phoneBox,10);
        BrowserUtils.waitForVisibility(genderDropdown,10);

        nameBox.sendKeys(expectedName);
        phoneBox.sendKeys(expectedNumber);
        Select select=new Select(genderDropdown);
        select.selectByVisibleText(expectedGender);
        updateButton.click();
        Map rowmap=new HashMap();
        rowmap.put("NAME",expectedName);
        rowmap.put("GENDER",expectedGender);
        rowmap.put("PHONE", expectedNumber);
        return rowmap;
    }
}
