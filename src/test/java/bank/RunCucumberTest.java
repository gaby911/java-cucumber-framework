package bank;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;

/*This class uses the Junit 5 platform to run cucumber tests.
*/
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("bank")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-report.html, json:target/cucumber-report.json")
@ConfigurationParameter(key = "cucumber.publish.enabled", value = "true")
@ConfigurationParameter(key = "cucumber.filter.tags", value = "@example")
public class RunCucumberTest {
}
