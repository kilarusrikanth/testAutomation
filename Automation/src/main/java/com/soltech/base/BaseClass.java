package com.soltech.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	protected WebDriver driver;
	static Wait<WebDriver> wait;
	public static final Logger log = Logger.getLogger(BaseClass.class.getName());

	private void setDriver(String browserType, String baseURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(baseURL);
			break;
		case "ie":
			driver = initIEDriver(baseURL);
			break;
		default:
			driver = initFirefoxDriver(baseURL);
		}
	}

	private WebDriver initChromeDriver(String baseURL) {
		log.info("Launching google chrome browser..");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1814, 974));
		driver.navigate().to(baseURL);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;
	}

	private WebDriver initFirefoxDriver(String baseURL) {
		log.info("Launching Firefox browser..");
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1814, 974));
		driver.navigate().to(baseURL);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;
	}

	private WebDriver initIEDriver(String baseURL) {
		log.info("Launching Internet Explorer browser..");
		driver = new InternetExplorerDriver();
		driver.manage().window().setSize(new Dimension(1814, 974));
		driver.navigate().to(baseURL);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;
	}

	private static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

	@Parameters({ "browserType", "baseURL" })
	@BeforeClass(alwaysRun = true)
	public void initializeBaseTest(String browserType, String baseURL) {
		try {
			log.info("Initiate browser..");
			setDriver(browserType, baseURL);
			String baseDirectory = System.getProperty("user.dir");
			String log4jConfPath = baseDirectory + "\\src\\main\\resources\\config\\log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);
		} catch (Exception e) {
			System.out.println("Error:" + e.getStackTrace());
		}
	}

	@AfterMethod
	public void takeScreenshotIfFailed(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				log.info("Taking failed test screenshot..");
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("./FailScreenShots/" + "failed_" + result.getName().toLowerCase()
						+ "_" + timestamp() + ".png"));
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.close();
		driver.quit();
		log.info("Clean up activity: Closed all browser instances..");
	}

}
