@db
Feature:Add,Insert and Delete Spartan funtionalities

  Background: lands on All Spartan homepage
    When user click on webdata link
    Then user lands on All Spartan homepage

  @add
  Scenario: Add Spartan randomly and verify in the Database(DB)
    When user click on add spartan
    And fills the form with name,gender and phone number
    Then the information should be same with database

  @add
  Scenario: Insert a Spartan and verify in the Database(DB)
    When user click on add spartan
    And fills the form with "Beth" name,"Female" gender and "9513786420" phone
    Then the information should be same with database

  @addn
  Scenario: Add n Spartan randomly and verify in the Database(DB)
    When user click on add spartan
    And fills the form with name,gender and phone number 35 times
    Then the information for 35 spartan should be same with database

  @edit
  Scenario: Select one Spartan randomly, edit and verify in the Database(DB)
    When user click on to edit one of the spartan and fills the information
    Then the information should be same with database

  @edit
  Scenario: Select one Spartan wrt ID, edit and verify in the Database(DB)
    When user click on to edit the spartan the spartan with ID: 133
    Then the information of the spartan with ID: 133 should be same with database

  @del
  Scenario: Delete one Spartan randomly, and verify in the Database(DB)
    When user click on to delete one of the spartan
    Then the information of the deleted spartan should not be in database

  @del
  Scenario Outline: Select Spartan wrt ID, delete and verify NOT in the Database(DB) id:<id>
    When user click on to delete the spartan the spartan with ID: <id>
    Then the information of the spartan with ID: <id> should not be in  database
    Examples:
      | id  |
      | 102 |
      | 106 |
      | 126  |
   @del @deln
  Scenario: Select many Spartan wrt ID, delete and verify NOT in the Database(DB)
    When user click on to delete the spartan the spartan with ID from 101 to 135
    Then the information of the deleted spartans with ID from 101 to 135 should not be in  database

  @num
  Scenario:    Verify the total number at UI and DB are equal(DB)
    Then the total information should be same with database
