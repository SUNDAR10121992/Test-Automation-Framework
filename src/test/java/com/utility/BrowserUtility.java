package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = Loggerutility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
	}

	public BrowserUtility(String browsername) {
		if (browsername.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());

		} else if (browsername.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());

		} else {
			System.out.println("Invalid Browser Name");
		}
	}

	public BrowserUtility(Browser browserName) {
		logger.info("Launching browser for " + browserName);

		if (browserName == Browser.CHROME) {

			driver.set(new ChromeDriver());

		} else if (browserName == Browser.EDGE) {

			driver.set(new EdgeDriver());

		} else {
			logger.error("Invalid Browser Name Please select chrome or edge only " + browserName);

			System.out.println("Invalid Browser Name");
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching browser for " + browserName);

		if (browserName == Browser.CHROME) {
			if (isHeadless){
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old");

				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
			} else {
				driver.set(new ChromeDriver());
			}

		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));

			} else {
				driver.set(new EdgeDriver());
			}

		} else {
			logger.error("Invalid Browser Name Please select chrome or edge only " + browserName);

			System.out.println("Invalid Browser Name");
		}
	}

	public void goToWebsite(String url) {
		logger.info("Launching browser" + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing  browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {

		logger.info("Finding element with locator" + locator);

		WebElement element = driver.get().findElement(locator);
		logger.info("Element found Now performing click" + locator);

		element.click();

	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with locator" + locator);

		WebElement element = driver.get().findElement(locator);
		logger.info("Element found Now Enter Text" + textToEnter);
		element.sendKeys(textToEnter);

	}

	public String getVisibleText(By locator) {
		logger.info("Finding element with locator" + locator);

		WebElement element = driver.get().findElement(locator);
		logger.info("Element found returning visible text" + element.getText());

		return element.getText();

	}

	public String takeScreenshot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenShotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		String path = System.getProperty("user.dir") + "//screenshots//" + name + " - " + timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenShotData, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
