package com.ui.test;

import static com.constants.Browser.*;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.Loggerutility;

public class TestBase {
	protected HomePage homePage;
	Logger logger = Loggerutility.getLogger(this.getClass());
	private boolean isLambdaTest;
	// private boolean isHeadLess = true;

	@Parameters({ "browser", "isLambdaTest", "isHeadLess" })
	@BeforeMethod(description = "Load the homepage of the website")
	public void setUp(
			@Optional("chrome")  String browser, ITestResult result, @Optional("false") boolean isLambdaTest,@Optional("false") boolean isHeadLess) {
		this.isLambdaTest = isLambdaTest;
		WebDriver lambdaDriver;
		if (isLambdaTest) {

			lambdaDriver = LambdaTestUtility.initializedLambdaTestsession(browser, result.getMethod().getMethodName());
			homePage = new HomePage(lambdaDriver);

		} else {
			// running test on local machine
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadLess);
			logger.info("Load the homepage of the website");

		}
	}

	public BrowserUtility getInstance() {
		return homePage;
	}

	@AfterMethod(description = "Tear down the brower")
	public void tearDown() {

		if (isLambdaTest) {
			LambdaTestUtility.quiteSession();
		} else {
			// homePage.quit();

		}

	}

}
