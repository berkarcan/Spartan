@db
Feature:Add,Insert and Delete Spartan funtionalities
  Background: lands on All Spartan homepage
    When user click on webdata link
    Then user lands on All Spartan homepage

  Scenario: Add Spartan randomly and verify in the Database(DB)
    When user click on add spartan
    And fills the form with name,gender and phone number
    Then the information should be same with database

  Scenario: Insert a Spartan and verify in the Database(DB)
    When user click on add spartan
    And fills the form with "Beth" name,"Female" gender and "9513786420" phone
    Then the information should be same with database

  Scenario: Add n Spartan randomly and verify in the Database(DB)
    When user click on add spartan
    And fills the form with name,gender and phone number 5 times
    Then the information for 5 spartan should be same with database

  Scenario: Select one Spartan randomly, edit and verify in the Database(DB)
    When user click on to edit one of the spartan and fills the information
    Then the information should be same with database

  Scenario: Select one Spartan wrt ID, edit and verify in the Database(DB)
    When user click on to edit the spartan the spartan with ID: 101
    Then the information of the spartan with ID: 101 should be same with database
  @wip
  Scenario: Delete one Spartan randomly, and verify in the Database(DB)
    When user click on to delete one of the spartan
    Then the information of the deleted spartan should not be in database

  Scenario:	Verify the total number at UI and DB are equal(DB)