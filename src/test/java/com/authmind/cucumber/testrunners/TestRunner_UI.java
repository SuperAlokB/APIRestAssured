package com.authmind.cucumber.testrunners;

import com.authmind.cucumber.AbtractTestNGCucumberTest;

import cucumber.api.CucumberOptions;
//
//@CucumberOptions(tags = {"@UI_success"},features = "src/test/resources/features", glue = {
//"com.authmind.cucumber.stepdefinition" },plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
//public class TestRunner_UI extends AbtractTestNGCucumberTest {
//
//}

//{"@Baseline,@UI_success"}

@CucumberOptions(tags = {"@Baseline"},features = "src/test/resources/features", glue = {
"com.authmind.cucumber.stepdefinition" },plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class TestRunner_UI extends AbtractTestNGCucumberTest {

}
