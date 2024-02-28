@balance-check
Feature: Bank Check Balance

  @deposit @example
  Scenario Outline: Checking Account Balance after Deposit
    Given my checking account has a balance of $<balance>
    And I have recently made a deposit of $<deposit>
    When I check my account balance
    Then I should see $<expectedBalance> as the balance
    But there is an overdraft fee of $<fee>
    And the available balance should be $<expectedAvailableBalance>
    @ex1
    Examples:
      | balance | deposit | expectedBalance | fee | expectedAvailableBalance |
      | 500     | 100     | 600             | 50  | 550                      |
      | 1000    | 200     | 1200            | 30  | 1170                     |
      | 2000    | 500     | 2500            | 100 | 2400                     |

  @csv
  Scenario: Checking Account Balance after Deposit CSV
    Given I have the following account details from CSV file "/Users/florinabagiu/Documents/GitHub/java-cucumber-framework/src/test/resources/testdata.csv"

  @withdrawal
  Scenario: Checking Account Balance after Withdrawal
    Given my checking account has a balance of $500
    And I have recently made a withdrawal of $100
    When I check my account balance
    Then I should see $400 as the balance
    But there is a credit interest of $50
    And the available balance should be $450