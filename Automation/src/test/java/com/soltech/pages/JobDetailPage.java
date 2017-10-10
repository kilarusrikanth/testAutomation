package com.soltech.pages;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.soltech.base.CommonClass;

public class JobDetailPage extends CommonClass {

	WebDriver driver;
	public static final Logger log = Logger.getLogger(JobDetailPage.class.getName());
	OpenPositionsPage openpositionpage = new OpenPositionsPage(driver);

	@FindBy(id = "icims_content_iframe")
	WebElement iFrameId;

	@FindBy(css = "div.iCIMS_MainWrapper.iCIMS_JobPage.iCIMS_Desktop > h1")
	WebElement jobTitle;

	@FindBy(css = "dl:nth-child(1) > dd")
	WebElement jobID;

	public JobDetailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyPageTitle() throws IOException {
		if (driver.getTitle().toString().contains(getObject().getProperty("jobDetailPageTitle")))
			return true;
		return false;
	}

	public String printTitleOfThePosition() {
		return jobTitle.getText().toString();
	}

	public String printJobID() {
		return jobID.getText().toString();
	}

	public boolean verifyJobName() throws IOException {
		log.info("Verifying Job name on page..");
		if (driver.getPageSource().contains(getObject().getProperty("jobName")))
			return true;
		return false;
	}

	public boolean verifyJobID() throws IOException {
		log.info("Verifying Job id ..");
		if (driver.getPageSource().contains(getObject().getProperty("jobIdNumber")))
			return true;
		return false;
	}
}
