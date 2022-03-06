package spartan.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import spartan.utilities.BrowserUtils;

import java.util.*;
import static spartan.utilities.Names.*;

public class AddNewSpartanPage extends BasePage{
    @FindBy(id = "name")
    public WebElement nameBox;

    @FindBy(id = "phone")
    public WebElement phoneBox;

    @FindBy(id="genderSelect")
    public WebElement genderDropdown;

    @FindBy(id="submit_btn")
    public WebElement submitButton;
    @FindBy(linkText = "Back to the List")
    public WebElement backButton;

    @FindBy(id="total")
    public WebElement totalButton;



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
        submitButton.click();
        Map rowmap=new HashMap();
        rowmap.put("NAME",expectedName);
        rowmap.put("GENDER",expectedGender);
        rowmap.put("PHONE", expectedNumber);
        return rowmap;
    }

    public Map<String,String> fillForm(String expectedName,String expectedGender, String expectedNumber){
        BrowserUtils.waitForVisibility(nameBox,10);
        BrowserUtils.waitForVisibility(phoneBox,10);
        BrowserUtils.waitForVisibility(genderDropdown,10);

        nameBox.sendKeys(expectedName);
        phoneBox.sendKeys(expectedNumber);
        Select select=new Select(genderDropdown);
        select.selectByVisibleText(expectedGender);
        submitButton.click();
        Map rowmap=new HashMap();
        rowmap.put("NAME",expectedName);
        rowmap.put("GENDER",expectedGender);
        rowmap.put("PHONE", expectedNumber);
        return rowmap;
    }

    public List<Map<String,String>> InsertNSpartans(int n){
        List<Map<String,String>> list=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Map rowMap=fillForm();
            list.add(rowMap);
            backButton.click();
            new AllSpartanPage().addSpartanButton.click();
        }
        return list;
    }

}
