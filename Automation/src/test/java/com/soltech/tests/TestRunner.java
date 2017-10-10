package com.soltech.tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.soltech.base.CommonClass;
import com.soltech.pages.JobDetailPage;
import com.soltech.pages.LandingPage;
import com.soltech.pages.OpenPositionsPage;

public class TestRunner extends CommonClass {
	public static final Logger log = Logger.getLogger(TestRunner.class.getName());
	LandingPage landingpage;
	OpenPositionsPage openpositionspage;
	JobDetailPage jobdetailspage;

	@Test
	public void clickOnCareersOpenPositionsMenu() throws IOException, InterruptedException {
		log.info("Starting LandingPageTest..");
		landingpage = new LandingPage(driver);
		Assert.assertEquals(getPageTitle(getObject().getProperty("landingPageTitle")), true);
		openpositionspage = landingpage.clickOpenPositionsMenu();
		Thread.sleep(9000L);
		log.info("Completed LandingPageTest");
	}

	@Test(dependsOnMethods = { "clickOnCareersOpenPositionsMenu" })
	public void searchForOpenQAPositions() throws InterruptedException, IOException {
		log.info("Starting OpenPositionsPageTest..");
		openpositionspage = new OpenPositionsPage(driver);
		Assert.assertEquals(getPageTitle(getObject().getProperty("openPositionsPageTitle")), true);
		openpositionspage.enterForSearch();
		openpositionspage.clickOnSearch();
		jobdetailspage = openpositionspage.clikOnQAPositionFromSerachResults();
		Thread.sleep(10000L);
		log.info("Completed OpenPositionsPageTest");
	}

	@Test(dependsOnMethods = { "searchForOpenQAPositions" })
	public void verifyJobDetailsPage() throws IOException {
		log.info("Starting JobDetailsPageTest..");
		jobdetailspage = new JobDetailPage(driver);
		softAssert().assertEquals(jobdetailspage.verifyPageTitle(), true);
		softAssert().assertEquals(jobdetailspage.verifyJobID(), true);
		Assert.assertEquals(jobdetailspage.verifyJobName(), true);
		System.out.println(jobdetailspage.printTitleOfThePosition());
		System.out.println(jobdetailspage.printJobID());
		log.info("Completed JobDetailsPageTest");
	}

}
