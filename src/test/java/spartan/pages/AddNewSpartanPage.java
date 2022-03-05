package spartan.pages;

import com.github.javafaker.Faker;
import io.cucumber.java.it.Ma;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class AddNewSpartanPage extends BasePage{
    @FindBy(id = "name")
    public WebElement nameBox;

    @FindBy(id = "phone")
    public WebElement phoneBox;

    @FindBy(id="genderSelect")
    public WebElement genderDropdown;

    @FindBy(id="submit_btn")
    public WebElement submitButton;

    Select select=new Select(genderDropdown);

    public Map<String,String> fillForm(){
        Faker faker=new Faker();
        String expectedName=faker.name().firstName();
        String expectedNumber=faker.phoneNumber().subscriberNumber(10);
        String expectedGender=new Random().nextInt(2)==1? "Female":"Male";
        nameBox.sendKeys(expectedName);
        phoneBox.sendKeys(expectedNumber);
        select.selectByVisibleText(expectedGender);
        submitButton.click();
        Map rowmap=new HashMap();
        rowmap.put("NAME",expectedName);
        rowmap.put("GENDER",expectedGender);
        rowmap.put("PHONE", expectedNumber);
        return rowmap;
    }

    public List<Map<String,String>> Insert300Spartans(){
        List<Map<String,String>> list=new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            Map rowMap=fillForm();
            list.add(rowMap);
        }
        return list;
    }

}
