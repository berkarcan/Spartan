package spartan.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import spartan.pages.AddNewSpartanPage;
import spartan.pages.AllSpartanPage;
import spartan.pages.EditSpartanPage;
import spartan.pages.HomePage;
import spartan.utilities.BrowserUtils;
import spartan.utilities.DBUtils;
import spartan.utilities.Driver;

import java.util.List;
import java.util.Map;
import java.util.Random;

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
    AddNewSpartanPage addNewPage=new AddNewSpartanPage();
    List<Map<String,String>> expectedListMap;
    @And("fills the form with name,gender and phone number")
    public void fillsTheFormWithNameGenderAndPhoneNumber() {
        expectedRowMap=addNewPage.fillForm();
        System.out.println("expectedRowMap.toString() = " + expectedRowMap.toString());


    }

    @And("fills the form with name,gender and phone number {int} times")
    public void fillsTheFormWithNameGenderAndPhoneNumberTimes(int n) {
        expectedListMap=addNewPage.InsertNSpartans(n);

    }

    @And("fills the form with {string} name,{string} gender and {string} phone")
    public void fillsTheFormWithNameGenderAndPhone(String name, String gender, String phone) {
        expectedRowMap=addNewPage.fillForm(name,gender,phone);
        System.out.println("expectedRowMap.toString() = " + expectedRowMap.toString());

    }

    @Then("the information should be same with database")
    public void theInformationShouldBeSameWithDatabase()  {
        BrowserUtils.waitFor(0.5);
        String query ="select name,gender,phone from spartans\n" +
                "where name='"+expectedRowMap.get("NAME")+"'\n"+
                "and gender='"+expectedRowMap.get("GENDER")+"'\n" +
                "and phone='"+expectedRowMap.get("PHONE")+"'";
        System.out.println("Obtain rowmap");
        Map<String, Object> rowMap = DBUtils.getRowMap(query);
        System.out.println("rowMap = " + rowMap.toString());

        Assert.assertEquals(expectedRowMap,rowMap);
    }


    @Then("the information for {int} spartan should be same with database")
    public void theInformationForSpartanShouldBeSameWithDatabase(int n)  {
        for (int i = 0; i < n; i++) {
            BrowserUtils.waitFor(0.5);
            String query ="select name,gender,phone from spartans\n" +
                    "where name='"+expectedListMap.get(i).get("NAME")+"'\n"+
                    "and gender='"+expectedListMap.get(i).get("GENDER")+"'\n" +
                    "and phone='"+expectedListMap.get(i).get("PHONE")+"'";
            System.out.println("Obtain rowmap");
            Map<String, Object> rowMap = DBUtils.getRowMap(query);
            System.out.println("rowMap = " + rowMap.toString());

            Assert.assertEquals(expectedListMap.get(i),rowMap);

        }

    }
    EditSpartanPage editSpartanPage=new EditSpartanPage();
    Map aRowMap;
    @When("user click on to edit one of the spartan and fills the information")
    public void userClickOnToEditOneOfTheSpartan() {
        BrowserUtils.waitFor(0.5);
        String query ="select spartan_id,name,gender,phone from spartans";
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap(query);
        aRowMap=queryResultMap.get(new Random().nextInt(queryResultMap.size()));
        System.out.println( aRowMap.toString());

        Driver.get().findElement(By.id("edit_spartan_"+aRowMap.get("SPARTAN_ID"))).click();
        BrowserUtils.waitForVisibility(editSpartanPage.nameBox,10);
        BrowserUtils.waitForVisibility(editSpartanPage.phoneBox,10);
        BrowserUtils.waitForVisibility(editSpartanPage.genderDropdown,10);
        editSpartanPage.nameBox.clear();
        editSpartanPage.phoneBox.clear();
        expectedRowMap=editSpartanPage.fillForm();
        System.out.println("expectedRowMap.toString() = " + expectedRowMap.toString());
    }

    @When("user click on to edit the spartan the spartan with ID: {int}")
    public void userClickOnToEditTheSpartanTheSpartanWithID(int ID) {
        Driver.get().findElement(By.id("edit_spartan_"+ID)).click();
        editSpartanPage.nameBox.clear();
        editSpartanPage.phoneBox.clear();
        expectedRowMap=editSpartanPage.fillForm();
        System.out.println("expectedRowMap.toString() = " + expectedRowMap.toString());
    }

    @Then("the information of the spartan with ID: {int} should be same with database")
    public void theInformationOfTheSpartanWithIDShouldBeSameWithDatabase(int ID) {
        BrowserUtils.waitFor(0.5);
        String query ="select name,gender,phone from spartans\n" +
                "where spartan_id='"+ID+"'";
        System.out.println("Obtain rowmap");
        Map<String, Object> rowMap = DBUtils.getRowMap(query);
        System.out.println("rowMap = " + rowMap.toString());

        Assert.assertEquals(expectedRowMap,rowMap);
    }

    @When("user click on to delete one of the spartan")
    public void userClickOnToDeleteOneOfTheSpartan() {
        BrowserUtils.waitFor(0.5);
        String query ="select spartan_id,name,gender,phone from spartans";
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap(query);
        expectedRowMap=queryResultMap.get(new Random().nextInt(queryResultMap.size()));
        System.out.println( expectedRowMap.toString());
        Driver.get().findElement(By.id("delete_spartan_"+expectedRowMap.get("SPARTAN_ID"))).click();


    }

    @Then("the information of the deleted spartan should not be in database")
    public void theInformationOfTheDeletedSpartanShouldNotBeInDatabase() {
        String query ="select spartan_id,name,gender,phone from spartans";
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap(query);
        for (Map<String, Object> actualmap : queryResultMap) {
            Assert.assertNotEquals(expectedRowMap,actualmap);

        }
    }
}
