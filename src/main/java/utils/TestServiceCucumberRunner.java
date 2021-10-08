package utils;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {"pretty", "html:reports/CucumberTestServiceReport.html", "utils.TestServiceCucumberListener"},
        features = "src/test/java/features",
        glue = {"steps"},
        tags = "@basic"
)
public class TestServiceCucumberRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        System.setProperty("dataproviderthreadcount", TestProperties.PARALLEL_THREAD_COUNT);
        return super.scenarios();
    }

}
