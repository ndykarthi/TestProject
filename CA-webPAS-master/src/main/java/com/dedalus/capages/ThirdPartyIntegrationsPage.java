package com.dedalus.capages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;
import com.dedalus.genericappmethods.GenericAppMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ThirdPartyIntegrationsPage extends CAProjectMethods {
	
	public ThirdPartyIntegrationsPage(AppiumDriver<MobileElement> driver,ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(xpath = "//span[contains(text(),'Party Int')]")
	public MobileElement clickTITab;
	
	
	@FindBy(xpath = "//*[text()=\"External Observation\"]/ancestor::ion-item/following::div/descendant::ion-col")
	
	  public  List<MobileElement> ThirdPartyLinks;
	  
	
	public void clickThirdPartyLink() throws InterruptedException {		
			

		for(int i=0;i<=ThirdPartyLinks.size()-1;i++){

			//MobileElement ele = driver.findElementByXPath("(//*[@aria-label=\"chevron forward outline\"])[+i+]/parent::ion-col");

					  explicitWait(ThirdPartyLinks.get(i));
					    String windowHandle = driver.getWindowHandle();
					    String Text = ThirdPartyLinks.get(i).getText();
					    
					    javascriptclick(ThirdPartyLinks.get(i));
					    Thread.sleep(10000);
					    for (String winHandle : driver.getWindowHandles()) {
					      driver.switchTo().window(winHandle);
					    }

					    String ThirdPartyWindow = driver.getTitle();
					    // driver.close();
					    System.out.println("Third Party Integration Window Title is- "+Text  + ThirdPartyWindow);
					    reportStep("Third Party Integration Window Title : %%%  "+ThirdPartyWindow+"%%%", "PASS");
					    driver.switchTo().window(windowHandle);
					   
				}
		
				
				}
	
	
	public ThirdPartyIntegrationsPage getExternalObservation() throws InterruptedException {
		
		driver.context("NATIVE_APP");
        Thread.sleep(20000);
		
		MobileElement ele = driver.findElementByXPath("(//*[text()=\"External Observation\"]/ancestor::ion-item/following::div/descendant::ion-col)[1]");
      
		  explicitWait(ele);
		    String windowHandle = driver.getWindowHandle();
		    javascriptclick(ele);
			   switchToWebView();
				Thread.sleep(5000);
		    Thread.sleep(10000);
		    for (String winHandle : driver.getWindowHandles()) {
		      driver.switchTo().window(winHandle);
		    }

		    String ThirdPartyWindow = driver.getTitle();
		    // driver.close();
		    System.out.println("Third Party Integration Window Title is- " + ThirdPartyWindow);
		  //  DBValidation(ThirdPartyWindow, "Integration Client App - DXC", "Third Party Integration Window Title");
		    
		    reportStep("Third Party Integration Window Title : of External Observation is  "+ThirdPartyWindow+".", "PASS");
		    driver.switchTo().window(windowHandle);
		    return this;
	}
	
	
	
	public ThirdPartyIntegrationsPage getLaunchFLOW() throws InterruptedException {
		MobileElement ele = driver.findElementByXPath("(//*[text()=\"External Observation\"]/ancestor::ion-item/following::div/descendant::ion-col/ion-icon[@aria-label='chevron forward outline'])[2]");

		  explicitWait(ele);
		    String windowHandle = driver.getWindowHandle();
		    javascriptclick(ele);
		    Thread.sleep(10000);
		    for (String winHandle : driver.getWindowHandles()) {
		      driver.switchTo().window(winHandle);
		    }

		    String ThirdPartyWindow = driver.getTitle();
		    // driver.close();
		    System.out.println("Third Party Integration Window Title is- " + ThirdPartyWindow);
		    reportStep("Third Party Integration Window Title : of Launch FLOW is  "+ThirdPartyWindow+".", "PASS");
		    driver.switchTo().window(windowHandle);
		    return this;
	}
	
	public ThirdPartyIntegrationsPage getLaunchHDV() throws InterruptedException {
		MobileElement ele = driver.findElementByXPath("(//*[text()=\"External Observation\"]/ancestor::ion-item/following::div/descendant::ion-col/ion-icon[@aria-label='chevron forward outline'])[3]");

		  explicitWait(ele);
		    String windowHandle = driver.getWindowHandle();
		    javascriptclick(ele);
		    Thread.sleep(10000);
		    for (String winHandle : driver.getWindowHandles()) {
		      driver.switchTo().window(winHandle);
		    }

		    String ThirdPartyWindow = driver.getTitle();
		    // driver.close();
		    System.out.println("Third Party Integration Window Title is- " + ThirdPartyWindow);
		    reportStep("Third Party Integration Window Title :of Launch HDV is "+ThirdPartyWindow+".", "PASS");
		    driver.switchTo().window(windowHandle);
		    return this;
	}
	
	public ThirdPartyIntegrationsPage getLaunchKAINOS_EVO() throws InterruptedException {
		MobileElement ele = driver.findElementByXPath("(//*[text()=\"External Observation\"]/ancestor::ion-item/following::div/descendant::ion-col/ion-icon[@aria-label='chevron forward outline'])[4]");

		  explicitWait(ele);
		    String windowHandle = driver.getWindowHandle();
		    javascriptclick(ele);
		    Thread.sleep(10000);
		    for (String winHandle : driver.getWindowHandles()) {
		      driver.switchTo().window(winHandle);
		    }

		    String ThirdPartyWindow = driver.getTitle();
		    // driver.close();
		    System.out.println("Third Party Integration Window Title is- " + ThirdPartyWindow);
		    reportStep("Third Party Integration Window Title :of Launch KAINOS_EVO is   "+ThirdPartyWindow+".", "PASS");
		    driver.switchTo().window(windowHandle);
		    return this;
	}
	
	
	
	
	
	public ThirdPartyIntegrationsPage getLaunch_MUSE() throws InterruptedException {
		MobileElement ele = driver.findElementByXPath("(//*[text()=\"External Observation\"]/ancestor::ion-item/following::div/descendant::ion-col/ion-icon[@aria-label='chevron forward outline'])[5]");

		  explicitWait(ele);
		    String windowHandle = driver.getWindowHandle();
		    javascriptclick(ele);
		    Thread.sleep(10000);
		    for (String winHandle : driver.getWindowHandles()) {
		      driver.switchTo().window(winHandle);
		    }

		    String ThirdPartyWindow = driver.getTitle();
		    // driver.close();
		    System.out.println("Third Party Integration Window Title is- " + ThirdPartyWindow);
		    reportStep("Third Party Integration Window Title : of Launch MUSE is   "+ThirdPartyWindow+".", "PASS");
		    driver.switchTo().window(windowHandle);
		    return this;
	}
	
	public ThirdPartyIntegrationsPage getLaunch_Synapse_PACS() throws InterruptedException {
		MobileElement ele = driver.findElementByXPath("(//*[text()=\"External Observation\"]/ancestor::ion-item/following::div/descendant::ion-col/ion-icon[@aria-label='chevron forward outline'])[6]");

		  explicitWait(ele);
		    String windowHandle = driver.getWindowHandle();
		    javascriptclick(ele);
		    Thread.sleep(10000);
		    for (String winHandle : driver.getWindowHandles()) {
		      driver.switchTo().window(winHandle);
		    }

		    String ThirdPartyWindow = driver.getTitle();
		    // driver.close();
		    System.out.println("Third Party Integration Window Title is- " + ThirdPartyWindow);
		    reportStep("Third Party Integration Window Title : of Launch Synapse PACS is   "+ThirdPartyWindow+".", "PASS");
		    driver.switchTo().window(windowHandle);
		    return this;
	}
	
	
	public ThirdPartyIntegrationsPage getLaunchUV_PAT() throws InterruptedException {
		MobileElement ele = driver.findElementByXPath("(//*[text()=\"External Observation\"]/ancestor::ion-item/following::div/descendant::ion-col/ion-icon[@aria-label='chevron forward outline'])[7]");

		  explicitWait(ele);
		    String windowHandle = driver.getWindowHandle();
		    javascriptclick(ele);
		    Thread.sleep(10000);
		    for (String winHandle : driver.getWindowHandles()) {
		      driver.switchTo().window(winHandle);
		    }

		    String ThirdPartyWindow = driver.getTitle();
		    // driver.close();
		    System.out.println("Third Party Integration Window Title is- " + ThirdPartyWindow);
		    reportStep("Third Party Integration Window Title :of Launch UV-PAT is  "+ThirdPartyWindow+".", "PASS");
		    driver.switchTo().window(windowHandle);
		    return this;
	}
	
	
	public ThirdPartyIntegrationsPage getLaunchWINDIP_PAT() throws InterruptedException {
		MobileElement ele = driver.findElementByXPath("(//*[text()=\"External Observation\"]/ancestor::ion-item/following::div/descendant::ion-col/ion-icon[@aria-label='chevron forward outline'])[8]");

		  explicitWait(ele);
		    String windowHandle = driver.getWindowHandle();
		    javascriptclick(ele);
		    Thread.sleep(10000);
		    for (String winHandle : driver.getWindowHandles()) {
		      driver.switchTo().window(winHandle);
		    }

		    String ThirdPartyWindow = driver.getTitle();
		    // driver.close();
		    System.out.println("Third Party Integration Window Title is- " + ThirdPartyWindow);
		    reportStep("Third Party Integration Window Title :of Launch WINDIP-PAT is  "+ThirdPartyWindow+".", "PASS");
		    driver.switchTo().window(windowHandle);
		    return this;
	}
	
	
	
	
	
	
	
	
	
}
