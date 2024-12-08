package com.dedalus.catestcases_windows;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dedalus.restasssured_windows.RESTAssuredBase;


public class Copyright extends RESTAssuredBase {

  @Parameters({"SOR"})
		
  @BeforeTest
  public void setData(String SOR) throws IOException, InterruptedException, AWTException {
    testCaseName = "CA App Logo and Copyright validations ";
    testDescription = "This test is to verify the CA App Logo and Copyright";
    nodes = SOR;
    category = "Regression";
    authors = "Kavitha";
  }
		

  @Test
  public void copyright() throws InterruptedException, IOException, ClassNotFoundException, SQLException, ParseException, AWTException {

	  checkCopyright();

	    //Warning: This computer program is protected by copyright law and international treaties. Unauthorized reproduction or distribution of this program, or any portion of it, may result in severe civil and criminal penalties, and will be prosecuted to the maximum extent possible under the law.
	  
	  //© 2017-2024 Dedalus. All rights reserved.
 
  reportStep("Script completed", "INFO");	
  driver.quit();
   
  }
}