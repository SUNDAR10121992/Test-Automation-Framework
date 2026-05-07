package com.ui.test;

import static com.constants.Browser.*;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.Loggerutility;

@Listeners({ com.ui.listeners.TestListner.class })
public class LoginTest3 extends TestBase {

	Logger logger = Loggerutility.getLogger(this.getClass());

	@Test(description = "verifies valid user able to login to app", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider", retryAnalyzer = com.ui.listeners.myRetryAnalyzer.class)
	public void LoginTest(User user) {

		Assert.assertEquals(
				homePage.goTOLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Sundar Kande");
	}

	@Test(description = "verifies valid user able to login to app", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
	public void LoginCSVTest(User user) {

		Assert.assertEquals(
				homePage.goTOLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Sundar Kande");
	}

	@Test(description = "verifies valid user able to login to app", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider", retryAnalyzer = com.ui.listeners.myRetryAnalyzer.class)
	public void LoginExcelTest(User user) {

		Assert.assertEquals(
				homePage.goTOLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Sundar Kande");

	}
}
