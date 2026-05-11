package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.Loggerutility;
import com.utility.PropertiesUtil;

public final class HomePage extends BrowserUtility {
	Logger logger = Loggerutility.getLogger(this.getClass());

	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless);
		// goToWebsite(PropertiesUtil.readProperty(QA, "URL"));
		goToWebsite(JSONUtility.readJSON(QA).getUrl());
	}

	public HomePage(WebDriver driver) {
		super(driver);
		goToWebsite(JSONUtility.readJSON(QA).getUrl());
	}

	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign in\")]");

	public LoginPage goTOLoginPage() {
		logger.info("Trying to perform click to goTo sign in page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}

}
