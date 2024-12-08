package com.dedalus.capages_windows;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods_windows.GenericAppMethods;


public class SearchPage extends GenericAppMethods{
	
	public SearchPage(RemoteWebDriver driver, ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//h2[contains(@class,'aliasName')]")
	public WebElement AliasName;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(@class,'aliasName')]/span")
	public WebElement AliasIcon;
	
	
}

