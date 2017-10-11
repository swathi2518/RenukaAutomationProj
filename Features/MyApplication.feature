    Feature: Test Facebook smoke Scenario

  Scenario Outline: Test Login with valid credential
    Given Open Chrome/Firefox
    And nd start application
    When user enters "<username>"and "<password>"
    Then User shoud be Able to Login Succesful

    Examples: 
      | username   | password |
      | testuser_1 | Test@153 |
      | testuser_2 | Test@153 |
    