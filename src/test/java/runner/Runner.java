package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json"},
        features = "src\\test\\resources\\features", // runs all feature files
        glue = "steps",
        tags = "@Regression",
        dryRun = false

)
public class Runner {

}
