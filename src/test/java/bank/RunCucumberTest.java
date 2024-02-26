package bank;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("bank/bank.feature")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty")
public class RunCucumberTest {
}
