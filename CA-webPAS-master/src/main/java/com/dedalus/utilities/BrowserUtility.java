package com.dedalus.utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class BrowserUtility {

	
	//public static AndroidDriver getAndroidDriver() throws InterruptedException {
		
	 public static AppiumDriver getAppiumDriver() throws InterruptedException {
		
		String userName = "kperiyakarup"; 
	    String accessKey = "hMi58WJeTJggg1P7HBXuqMzxLDO0bWkbe9MPUBqLnuRpg5LOYY"; 
	    String gridURL = "@mobile-hub.lambdatest.com/wd/hub";	
	    AppiumDriver<?> driver = null;
	    //AndroidDriver driver = null;
	    try {
		DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("platformName", "iOS");
	    capabilities.setCapability("deviceName", "iPhone 12");
	    capabilities.setCapability("platformVersion", "16");  
	    capabilities.setCapability("isRealMobile", true);
	    capabilities.setCapability("app", "lt://APP1016035381697090761433513"); //iOS CAUK
	    capabilities.setCapability("deviceOrientation", "PORTRAIT");
	    capabilities.setCapability("console", true);
	    capabilities.setCapability("network", true);
	    capabilities.setCapability("visual", true);
	    capabilities.setCapability("devicelog", true);
	    capabilities.setCapability("autoWebview", true);
	    capabilities.setCapability("tunnel", true);
        capabilities.setCapability("tunnelName", "Kavitha");  
        
        String hub = "https://" + userName + ":" + accessKey + gridURL;
	   // driver = new AndroidDriver<WebElement>(new URL(hub), capabilities);
        driver = new AppiumDriver<WebElement>(new URL(hub), capabilities);
        
		Thread.sleep(10000);
	    }catch(MalformedURLException e) {
	    	e.printStackTrace();
	    }
		return driver;
	
	}
}
