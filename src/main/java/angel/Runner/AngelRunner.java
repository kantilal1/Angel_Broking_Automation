package angel.Runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(features = "./Angel_Broking/Angel/TestCases", glue = { "angel.stepdefination" }, tags = {
        "@AngelLoginFunctionality" }, monochrome = true)

//If you don't want to run any feature file use the symbol "~"
//~@OrangeForgotPasswordFunctionality 

public class AngelRunner {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        System.out.println("scenarioName===>"  );
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

  
    @Test(dataProvider = "features")
    public void feature(PickleEventWrapper eventwrapper, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        // testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
        testNGCucumberRunner.runScenario(eventwrapper.getPickleEvent());
    }

    @DataProvider // (parallel=true)
    public Object[][] features() {
        // return testNGCucumberRunner.provideFeatures();
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}