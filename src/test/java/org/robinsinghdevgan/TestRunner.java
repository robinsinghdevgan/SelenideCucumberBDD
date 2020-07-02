package org.robinsinghdevgan;

import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(io.cucumber.junit.Cucumber.class)
//@io.cucumber.junit.CucumberOptions(
@CucumberOptions(
        //plugin = {"pretty",  "html:target/cucumber-html-report", "json:target/report.json", "cucumber-reporting:target/pretty-cucumber"}
        //,
        strict = true,
        features = {"src/test/resources/features/"}
        , glue = {"org.robinsinghdevgan.stepdefinitions"}
)
public class TestRunner {
    @BeforeClass
    public static void beforeall() {
        System.out.println("Before all suit");
    }

    @AfterClass
    public static void all() {
        System.out.println("After All Suit");
    }
}