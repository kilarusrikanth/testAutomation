package com.soltech.pages;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.soltech.base.CommonClass;

public class OpenPositionsPage extends CommonClass {

	WebDriver driver;
	public static final Logger log = Logger.getLogger(OpenPositionsPage.class.getName());

	@FindBy(css = "a[href='https://careers-soltech.icims.com/jobs/2011/quality-assurance-automation-engineer/job?in_iframe=1']")
	WebElement qaPosition;

	@FindBy(id = "icims_content_iframe")
	WebElement iFrameId;

	@FindBy(id = "jsb_f_keywords_i")
	WebElement jobKeywords;

	@FindBy(id = "jsb_form_submit_i")
	WebElement jobSearchButton;

	public OpenPositionsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private void switchToIframe() {
		driver.switchTo().frame(iFrameId);
	}

	private void scrollToDown() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scrollBy(0,2500)");
	}

	private void switchToNewWindow() {
		String parentTab = driver.getWindowHandle();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(parentTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
	}

	public void enterForSearch() throws InterruptedException {
		log.info("Switching to new window..");
		switchToNewWindow();
		switchToIframe();
		Thread.sleep(3000L);
		scrollToDown();
		log.info("Entering search string in Keywords field..");
		jobKeywords.sendKeys("QA");
	}

	public void clickOnSearch() throws InterruptedException {
		log.info("Clicking on job search button..");
		jobSearchButton.click();
		Thread.sleep(8000L);
	}

	public JobDetailPage clikOnQAPositionFromSerachResults() {
		log.info("Clicking on job position..");
		qaPosition.click();
		return new JobDetailPage(driver);
	}
}