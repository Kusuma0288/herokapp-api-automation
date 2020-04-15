import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.*;
import org.testng.annotations.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

@CucumberOptions(
        strict = false,
        features = {"/Users/njbh1/Documents/Official/woolworths-app-apigee/src/test/resources/features/Homepage.feature"},
        plugin = {"json:/Users/njbh1/Documents/Official/woolworths-app-apigee/target/cucumber-reports/advanced-reports/2.json"},
        monochrome = false,
        tags = {"@REGRESSION"},
        glue = {"au.com.woolworths.apigee.stepdefinitions"},
        format = {
                        "pretty",
                        "rerun:target/cucumber-reports/rerun.txt"
                })

public class Parallel10IT extends AbstractTestNGCucumberTests {

    private static Logger logger = Logger.getLogger("Parallel10IT"+".class");

}


