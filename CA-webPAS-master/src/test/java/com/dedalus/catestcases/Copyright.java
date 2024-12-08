package com.dedalus.catestcases;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.dedalus.restasssured.RESTAssuredBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Copyright extends RESTAssuredBase {

	JsonPath jsonPath;
  @Parameters({"PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
		
  @BeforeTest
  public void setData(String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation) throws IOException, InterruptedException, AWTException {
    testCaseName = "CA App Logo and Copyright validations "+PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
    testDescription = "This test is to verify the CA App Logo and Copyright";
    nodes = PlatformName+" - "+DeviceName+" - "+PlatformVersion+" - "+DeviceOrientation;
    category = "Regression";
    authors = "Kavitha";
  }
		
  @Parameters({"SOR","PlatformName","DeviceName","PlatformVersion","DeviceOrientation"})
  @Test
  public void copyright(String SOR, String PlatformName,String DeviceName,String PlatformVersion,String DeviceOrientation, ITestContext context) throws InterruptedException, IOException, ClassNotFoundException, SQLException, ParseException, AWTException {
 
	  checkCopyright(SOR,PlatformName,DeviceName,PlatformVersion,DeviceOrientation);
		   	
	  reportStep("Script completed", "INFO");
		
	  driver.quit();
	  }  
  
	}