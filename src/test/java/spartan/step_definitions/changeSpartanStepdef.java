package spartan.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import spartan.pages.AddNewSpartanPage;
import spartan.pages.AllSpartanPage;
import spartan.pages.HomePage;
import spartan.utilities.DBUtils;
import spartan.utilities.Driver;

import java.util.Map;

public class changeSpartanStepdef {



    @When("user click on webdata link")
    public void user_click_on_webdata_link() {
        HomePage homePage=new HomePage();
        homePage.webdataLink.click();
    }

    @Then("user lands on All Spartan homepage")
    public void userLandsOnAllSpartanHomepage() {
        String expectedUrl="http://100.27.31.247:8000/spartans";
        Assert.assertEquals("Url is different",expectedUrl, Driver.get().getCurrentUrl());
    }


    @When("user click on add spartan")
    public void userClickOnAddSpartan() {
        AllSpartanPage allSpartanPage=new AllSpartanPage();
        allSpartanPage.addSpartanButton.click();

    }
    Map expectedRowMap;
    @And("fills the form with name,gender and phone number")
    public void fillsTheFormWithNameGenderAndPhoneNumber() {
        AddNewSpartanPage addNewPage=new AddNewSpartanPage();
        expectedRowMap=addNewPage.fillForm();
        System.out.println("expectedRowMap.toString() = " + expectedRowMap.toString());


    }

    @Then("the information should be same with database")
    public void theInformationShouldBeSameWithDatabase() throws InterruptedException {
        Thread.sleep(2000);
        String query ="select name,gender,phone from spartans\n" +
                "where name='"+expectedRowMap.get("NAME")+"'\n"+
                "and gender='"+expectedRowMap.get("GENDER")+"'\n" +
                "and phone='"+expectedRowMap.get("PHONE")+"'";
        System.out.println("Obtain rowmap");
        Map<String, Object> rowMap = DBUtils.getRowMap(query);
        System.out.println("rowMap = " + rowMap.toString());

        //Map<String, String> actualRowMap= (Map) rowMap;  //no need but a way to cast

        Assert.assertEquals(expectedRowMap,rowMap);
    }



}
