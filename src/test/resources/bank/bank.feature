Feature: Bank Check Balance Special

  Scenario: Checking Account Balance after Deposit
    Given my checking account has a balance of $500
    And I have recently made a deposit of $100
    When I check my account balance
    Then I should see $600 as the balance
    But there is an overdraft fee of $50
    And the available balance should be $550

  Scenario: Checking Account Balance after Withdrawal
    Given my checking account has a balance of $500
    And I have recently made a withdrawal of $100
    When I check my account balance
    Then I should see $400 as the balance
    But there is a credit interest of $50
    And the available balance should be $450

  Scenario: Checking Account Balance after Withdrawal In-Sufficient Balance
    Given my checking account has a balance of $500
    When I request to withdraw $700
    Then I should see an Error
    And the available balance should be $500
