package utils;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {"pretty", "html:reports/CucumberTestWebReport.html", "utils.TestWebCucumberListener"},
        features = "src/test/java/features",
        glue = {"steps"},
        tags = "@title"
)
public class TestWebCucumberRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        System.setProperty("dataproviderthreadcount", TestProperties.PARALLEL_THREAD_COUNT);
        return super.scenarios();
    }

}
