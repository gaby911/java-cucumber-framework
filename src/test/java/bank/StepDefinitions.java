package bank;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        if (withdrawalAmmout > 0 && withdrawalAmmout > checkingAccountBalance + depositAmount + creditInterest - overdraftFee) {
            availableBalance = checkingAccountBalance + depositAmount - overdraftFee + creditInterest;
        } else {
            availableBalance = checkingAccountBalance + depositAmount - overdraftFee + creditInterest - withdrawalAmmout;
        }
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

    @When("I request to withdraw ${int}")
    public void iRequestToWithdraw$(int withdrawal) {
        withdrawalAmmout = withdrawal;
    }

    @Then("I should see an Error")
    public void iShouldSeeAnError() {
        String errorMessage = ">> Error: Withdrawal not allowed due to insufficient funds. <<";
        System.out.println(errorMessage);
    }

    @Given("I have the following account details from CSV file {string}")
    public void iHaveTheFollowingAccountDetailsFromCSVFile(String csvFilePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
            String line;
            boolean firstLineSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!firstLineSkipped) {
                    firstLineSkipped = true;
                    continue; //Skip the first line
                }
                String[] values = line.split(",");
                int balance = Integer.parseInt(values[0].trim());
                int deposit = Integer.parseInt(values[1].trim());
                int expectedBalance = Integer.parseInt(values[2].trim());
                int fee = Integer.parseInt(values[3].trim());
                int expectedAvailableBalance = Integer.parseInt(values[4].trim());

                myCheckingAccountHasABalanceOf$(balance);
                iHaveRecentlyMadeADepositOf$(deposit);
                iCheckMyAccountBalance();
                iShouldSeeAsTheBalance(expectedBalance);
                thereIsAnOverdraftFeeOf$(fee);
                theAvailableBalanceShouldBe$(expectedAvailableBalance);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("I do nothing")
    public void iDoNothing() {

    }

    //Hooks

    @Before
    public void before() {
        System.out.println("**** Before Scenario");
    }

    @After
    public void after() {
        System.out.println(">>> After Scenario");
    }

    @BeforeStep
    public static void beforeStep() {
        System.out.println("**> Before Step");
    }

    @AfterStep
    public static void afterStep() {
        System.out.println("**> After Step");
    }

    @Before("@Balance-check and not @ex1")
    public void before2() {
        System.out.println("**** Before Scenario");
    }

    @Before(order = 3)
    public void before3() {
        System.out.println("**** Before Scenario");
    }

    @Before(order = 4)
    public void before4() {
        System.out.println("**** Before Scenario");
    }

}
