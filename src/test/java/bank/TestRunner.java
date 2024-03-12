package bank;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/*This class uses Cucumber 6 with Junit 4 and doesn't have the same issue as cucumber 7 in automatically
* picking up properties file */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = {"scr/test/resources/bank/bank.feature"}
//        tags = "@csv or @withdrawal" // will run only the tests with @csv and @withdrawal tag
)
public class TestRunner {
}

