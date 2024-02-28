package bank;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        tags = "@csv or @withdrawal" // will run only the tests with @csv and @withdrawal tag
)
public class TestRunner {
}

