package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/java/featureFiles",
		glue="steps",
		dryRun=false,
		tags="not @DeleteCartItem"
		)
public class RunnerTest extends AbstractTestNGCucumberTests{

}
