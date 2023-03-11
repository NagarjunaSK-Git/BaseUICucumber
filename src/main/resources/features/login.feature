Feature: Login OrangeHRM

  @regression @smoke
  Scenario Outline: Login with existing user to the app
    Given I login with existing "<Username>" and "<Password>"
    Then I should see dashboard
    Examples:
      | Username  | Password |
      | Admin  | admin123 |

  @regression
  Scenario Outline: Login with wrong credentials of existing user to the app
    Given I login with existing "<Username>" and "<InvalidPassword>"
    Then I should see "<ErrorText>" message
    Examples:
      | Username  | InvalidPassword | ErrorText |
      | Admin  | wrongdummy | Invalid credentials |
      | AdminDummy  | wrongdummy | Invalid credentials |
      | AdminDummy  | wrongdummy | Invalid credentials|