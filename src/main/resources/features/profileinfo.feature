Feature: Profile Update OrangeHRM

  @regression
  Scenario Outline: Existing User update the personal details
    Given I login with existing "<Username>" and "<Password>"
    Then I should see dashboard
    And I update the blood group "<BloodGroup>"
    Examples:
      | Username  | Password | BloodGroup |
      | Admin  | admin123 |     O+        |
