package com.westpac.fx.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(features = "features", plugin = { "pretty", "html:target/cucumber",
		"json:target/Userstories.json" }, tags = { "@tag1,@tag2" }, glue = "com.westpac.fx.stepdefinitions")

public class Runner_1 {

}
