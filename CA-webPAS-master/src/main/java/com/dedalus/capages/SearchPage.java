package com.dedalus.capages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchPage extends CAProjectMethods {
	
	public SearchPage(AppiumDriver<MobileElement> driver,ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(how = How.XPATH, using = "//h2[contains(@class,'aliasName')]")
	public MobileElement AliasName;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(@class,'aliasName')]/span")
	public MobileElement AliasIcon;
	
}
