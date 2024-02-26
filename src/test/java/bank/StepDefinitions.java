package bank;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    private int accountNumber;
    private double balance;

    private int checkingAccountBalance;
    private int depositAmount;
    private int actualBalance;
    private int overdraftFee;
    private int availableBalance;

    private int withdrawalAmmout;
    private int creditInterest;


    @Given("my checking account has a balance of ${int}")
    public void myCheckingAccountHasABalanceOf$(int balance) {
        checkingAccountBalance = balance;
    }

    @And("I have recently made a deposit of ${int}")
    public void iHaveRecentlyMadeADepositOf$(int deposit) {
        depositAmount = deposit;
    }

    @When("I check my account balance")
    public void iCheckMyAccountBalance() {
        actualBalance = checkingAccountBalance + depositAmount - withdrawalAmmout;
    }

    @Then("I should see ${int} as the balance")
    public void iShouldSeeAsTheBalance(int expectedBalance) {
        assertEquals(expectedBalance, actualBalance);
    }

    @But("there is an overdraft fee of ${int}")
    public void thereIsAnOverdraftFeeOf$(int fee) {
        overdraftFee = fee;
    }

    @And("the available balance should be ${int}")
    public void theAvailableBalanceShouldBe$(int expectedAvailableBalance) {
        availableBalance = checkingAccountBalance + depositAmount - overdraftFee + creditInterest - withdrawalAmmout;
        assertEquals(expectedAvailableBalance, availableBalance);
    }


    @And("I have recently made a withdrawal of ${int}")
    public void iHaveRecentlyMadeAWithdrawalOf$(int withdrawal) {
        withdrawalAmmout = withdrawal;
    }

    @But("there is a credit interest of ${int}")
    public void thereIsACreditInterestOf$(int interest) {
        creditInterest = interest;
    }

}
