package utils;

import com.aventstack.extentreports.gherkin.model.Given;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

import java.net.URI;
import java.nio.file.Paths;

public class TestServiceCucumberListener implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, this::onTestRunStarted);
        publisher.registerHandlerFor(TestRunFinished.class, this::onTestRunFinished);
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
        publisher.registerHandlerFor(TestStepStarted.class, this::onTestStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
    }

    private void onTestRunStarted(TestRunStarted testRunStarted) {
        // adding test properties to the extent test report
        TestExtentReport.init();
    }

    private void onTestCaseStarted(TestCaseStarted testCaseStarted) {
        // creating feature node if not already present
        URI uri = testCaseStarted.getTestCase().getUri();
        String scenarioName = testCaseStarted.getTestCase().getName();
        if (TestExtentReport.testFeature.get(uri) == null) {
            TestExtentReport.testFeature.putIfAbsent(uri,
                    TestExtentReport.extent.createTest(Paths.get(uri).getFileName().toString()));
        }
        // adding test scenario to the feature node
        TestExtentReport.testScenario.set(TestExtentReport.testFeature.get(uri).createNode(scenarioName));
    }

    private void onTestStepStarted(TestStepStarted testStepStarted) {
        // adding step node
        PickleStepTestStep testStep = (PickleStepTestStep) testStepStarted.getTestStep();
        TestExtentReport.testStep.set(TestExtentReport.testScenario.get().createNode(
                Given.class, testStep.getStep().getKeyword() + "  " + testStep.getStep().getText()));
    }

    private void onTestStepFinished(TestStepFinished testStepFinished) {
        Status stepStatus = testStepFinished.getResult().getStatus();
        String stepId = testStepFinished.getTestStep().getId().toString();
        if (stepStatus.equals(Status.PASSED)) {
            TestExtentReport.testStep.get().log(com.aventstack.extentreports.Status.PASS, "PASSED");
            TestExtentReport.testStep.get().info(stepId);
        } else if (stepStatus.equals(Status.SKIPPED)) {
            TestExtentReport.testStep.get().log(com.aventstack.extentreports.Status.SKIP, "SKIPPED");
            TestExtentReport.testStep.get().info(stepId);
        } else {
            TestExtentReport.testStep.get().log(com.aventstack.extentreports.Status.FAIL, "FAILED");
            TestExtentReport.testStep.get().info(stepId);
        }
        createRequestResponseReport(testStepFinished, "request", "response");
    }

    private void onTestCaseFinished(TestCaseFinished testCaseFinished) {
    }

    private void onTestRunFinished(TestRunFinished testRunFinished) {
        // finalising both the reports
        TestExtentReport.extent.flush();
        TestAllDataReport.close();
    }

    private void createRequestResponseReport(TestStepFinished testStepFinished, String request, String response) {
        TestAllDataReport.setServiceReportData(testStepFinished.getTestStep().getId().toString(),
                Paths.get(testStepFinished.getTestCase().getUri()).getFileName().toString(),
                testStepFinished.getTestCase().getName(),
                ((PickleStepTestStep) testStepFinished.getTestStep()).getStep().getText(),
                testStepFinished.getResult().toString(),
                request,
                response);
    }

}
