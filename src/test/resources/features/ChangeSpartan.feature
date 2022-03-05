Feature:Add,Insert and Delete Spartan funtionalities
  Background: lands on All Spartan homepage
    When user click on webdata link
    Then user lands on All Spartan homepage
   @db
  Scenario: Add Spartan randomly and verify in the Database(DB)
    When user click on add spartan
    And fills the form with name,gender and phone number
    Then the information should be same with database

  @db
  Scenario: Insert a Spartan and verify in the Database(DB)
    When user click on add spartan
    And fills the form with "Beth" name,"Female" gender and "9513786420" phone
    Then the information should be same with database

  @wip @db
  Scenario: Add n Spartan randomly and verify in the Database(DB)
    When user click on add spartan
    And fills the form with name,gender and phone number 5 times
    Then the information for 5 spartan should be same with database

  Scenario: Select one Spartan randomly, edit and verify in the Database(DB)
    When user click on to edit one of the spartan
    Then the information should be same with database

  Scenario: Delete Spartan and verify in the Database(DB)

  Scenario:	Verify the total number at UI and DB are equal(DB)