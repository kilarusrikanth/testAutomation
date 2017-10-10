package com.soltech.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.soltech.base.CommonClass;

public class LandingPage extends CommonClass {

	WebDriver driver;
	public static final Logger log = Logger.getLogger(LandingPage.class.getName());

	@FindBy(xpath = "//*[@id='navbar']/div/ul/li[5]/a")
	WebElement careers;

	@FindBy(xpath = "//*[@id='navbar']/div/ul/li[5]/ul/li[2]/a")
	WebElement openPositions;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public OpenPositionsPage clickOpenPositionsMenu() throws InterruptedException {
		log.info("Selecting careers menu..");
		careers.click();
		Thread.sleep(3000L);
		log.info("Clicking Open Positions menu..");
		openPositions.click();
		return new OpenPositionsPage(driver);
	}

}
