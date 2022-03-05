Feature:
  Background:
    When user click on webdata link
    Then user lands on All Spartan homepage
  @wip @db
  Scenario: Add Spartan and verify in the Database(DB)
    When user click on add spartan
    And fills the form with name,gender and phone number
    Then the information should be same with database

  Scenario: Edit Spartan and verify in the Database(DB)

  Scenario: Delete Spartan and verify in the Database(DB)

  Scenario:	Verify the total number at UI and DB are equal(DB)