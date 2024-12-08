package com.dedalus.genericappmethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.beust.jcommander.Parameter;
import com.dedalus.utilities.DataBaseConnect;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CAProjectMethods extends GenericAppMethods implements DataBaseConnect{

	
	public ExtentTest testSuite;

	@Parameters({"testSuiteName"})
	@BeforeSuite
	public void beforeSuite(String testSuiteName){
		startResult(testSuiteName);
	}

	@BeforeClass
	public void beforeClass() throws FileNotFoundException, IOException{
		startTestModule(testCaseName, testDescription);
		//startApp();
	}


	@BeforeMethod
	public void beforeMethod() throws FileNotFoundException, IOException
	{
		test = startTestCase(nodes);
		test.assignCategory(category);
		test.assignAuthor(authors);
		//startApp();
	}

	public ExtentTest startTestCase(String testCaseName, String testDescription) {
		testSuite = extent.createTest(testCaseName, testDescription);
		return testSuite;
	}

	public ExtentTest startTestModule(String nodes) {
		test = testSuite.createNode(nodes);
		return test;
	}

	@AfterMethod
	public void afterMethod() throws FileNotFoundException, IOException
	{
		//driver.close();
	}

/*	@DataProvider(name="fetchData")
	public Object[][] getData(){

		return DataInputProvider.getSheet(dataSheetName);

	}

	@DataProvider(name="fetchDataSheet")
	public Object[][] getDataOne(){

		return DataInputProvider.getSheet(dataSheetFileName, dataSheetName);

	}
*/
	@AfterSuite
	public void afterSuite(){
		endResult();
	}

	@AfterTest
	public void afterTest(){
	}

	
	
		
	public void SearchNMarkFavourite(String type,String Name) throws InterruptedException {
		try {
			driver.findElementByXPath("//*[contains(@placeholder,'"+type+"')]").click();
			driver.findElementByXPath("//*[contains(@placeholder,'"+type+"')]").clear();

			if(Name.contains(" "))
			{
				String temp = Name.split(" ")[0];
				driver.findElementByXPath("//*[contains(@placeholder,'"+type+"')]").sendKeys(temp);
				Thread.sleep(5000);
			}
			else
			{
				driver.findElementByXPath("//*[contains(@placeholder,'"+type+"')]").sendKeys(Name);
				Thread.sleep(5000);
			}
			MobileElement elePAt = driver.findElementByXPath("//ion-label[contains(text(),'"+Name+"')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='star-outline']");
			Thread.sleep(3000);
			jsclick(elePAt,Name);
      		Thread.sleep(2000);
      		//reportStep(Name+" is clicked", "PASS", false);
      		takeSnap();
  		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}	
	}
		 
	public void SelectFavourite(String menu,String Name) throws InterruptedException {
		try {
			MobileElement elePAt = driver.findElementByXPath("//ion-label[contains(text(),'"+menu+"')]/parent::ion-item/following-sibling::div//ion-label[contains(text(),'"+Name+"')]");
			click(elePAt, Name);
      		Thread.sleep(2000);
      		//reportStep(Name+" is clicked", "PASS", false);
      		takeSnap();
  		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}	
	}
	
	
	public int getCount(List<MobileElement> ele) {
		//List<MobileElement> count = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Active')]");
		int count = ele.size();
		takeSnap();
		return count;
	}
	
	 public static String datetimeconv(String APPdate,String dbFormat) throws ParseException {
     	Date appDate;
     	String myDate=null;
     	int ln=APPdate.length();
     	//System.out.println(ln);

     	switch(dbFormat) {
 	    case ("dd/MM/yyyy") :
 	    	DateFormat inFormat =new SimpleDateFormat("dd-MMM-yyyy");
 	        appDate=inFormat.parse(APPdate);
 	        DateFormat outFormat=new SimpleDateFormat(dbFormat);
 	        myDate=outFormat.format(appDate);
 	        System.out.println(myDate);
 	        break;
 	    case("yyyy/MM/dd HH:mm"):
 	    	DateFormat inFormat1 =new SimpleDateFormat("dd-MMM-yyyy");
 	    	appDate=inFormat1.parse(APPdate);
 	    	DateFormat outFormat1=new SimpleDateFormat(dbFormat);
 	        myDate=outFormat1.format(appDate);
 	        System.out.println(myDate);
 	        break;
 	    case("yyyy/MM/dd"):
 	    	DateFormat inFormat2 =new SimpleDateFormat("dd-MMM-yyyy");
 	    	appDate=inFormat2.parse(APPdate);
 	    	DateFormat outFormat2=new SimpleDateFormat(dbFormat);
 	        myDate=outFormat2.format(appDate);
 	        System.out.println(myDate);
 	        break;
 	    case("dd/MM/yyyy HH:mm"):
 	    	DateFormat inFormat3 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
     		appDate=inFormat3.parse(APPdate);
 	    	DateFormat outFormat3 = new SimpleDateFormat(dbFormat);
 		   	outFormat3.setTimeZone(TimeZone.getTimeZone("England/London"));
 		   	outFormat3.setTimeZone(TimeZone.getTimeZone("UTC"));
 		   	myDate=outFormat3.format(appDate);
 		   	System.out.println(myDate);
 		   	break;
 	    case ("dd-MM-yyyy") :
 	    	DateFormat inFormat4 =new SimpleDateFormat("dd-MMM-yyyy");
 	        appDate=inFormat4.parse(APPdate);
 	        DateFormat outFormat4=new SimpleDateFormat(dbFormat);
 	        outFormat4.setTimeZone(TimeZone.getTimeZone("England/London"));
 		   	outFormat4.setTimeZone(TimeZone.getTimeZone("UTC"));
 	        myDate=outFormat4.format(appDate);
 	        System.out.println(myDate);
 	        break;
 	    /*case ("dd-MMM-yyyy") :
 	    	DateFormat inFormat5 =new SimpleDateFormat("dd/MM/yy");
 	        appDate=inFormat5.parse(APPdate);
 	        DateFormat outFormat5=new SimpleDateFormat(dbFormat);
 	        myDate=outFormat5.format(appDate);
 	        System.out.println(myDate);
 	        break; */
 	    case ("yyyy-MM-dd") :
 	    	DateFormat inFormat6 =new SimpleDateFormat("dd-MMM-yyyy");
 	        appDate=inFormat6.parse(APPdate);
 	        DateFormat outFormat6=new SimpleDateFormat(dbFormat);
 	       // outFormat6.setTimeZone(TimeZone.getTimeZone("England/London"));
 		   // outFormat6.setTimeZone(TimeZone.getTimeZone("UTC"));
 	        myDate=outFormat6.format(appDate);
 	        System.out.println(myDate);
 	        break;
 	    case("yyyy-MM-dd HH:mm:ss.s"):
 	        DateFormat inForm =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
 	        appDate=inForm.parse(APPdate);
 	        DateFormat outForm=new SimpleDateFormat(dbFormat);
 	        myDate=outForm.format(appDate);
 	        //System.out.println(myDate);
 	        break;
 	    case ("yyyy-MM-dd HH:mm") :
 	    	DateFormat inFormat7 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
 	        appDate=inFormat7.parse(APPdate);
 	        DateFormat outFormat7=new SimpleDateFormat(dbFormat);
 	        outFormat7.setTimeZone(TimeZone.getTimeZone("UTC"));
 	        myDate=outFormat7.format(appDate);
 	        break;
 	   case ("yyy-MM-dd HH:mm") :
	    	DateFormat inFormat10 =new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        appDate=inFormat10.parse(APPdate);
	        //DateFormat outFormat10=new SimpleDateFormat(dbFormat);
	        //outFormat10.setTimeZone(TimeZone.getTimeZone("UTC"));
	       // myDate=outFormat10.format(appDate);
	        break;
 	   case ("dd-MMM-yyyy"):
	    	DateFormat inFormat8 =new SimpleDateFormat("yyyy-MM-dd");
	        appDate=inFormat8.parse(APPdate);
	        DateFormat outFormat8=new SimpleDateFormat(dbFormat);
	        myDate=outFormat8.format(appDate);
	        System.out.println(myDate);
	        break;
 	   case ("HH:mm") :
	    	DateFormat inFormat9 =new SimpleDateFormat("HH:mm");
	        appDate=inFormat9.parse(APPdate);
	        DateFormat outFormat9=new SimpleDateFormat(dbFormat);
	        outFormat9.setTimeZone(TimeZone.getTimeZone("UTC"));
	        myDate=outFormat9.format(appDate);
	        break;
 	    }
     	return myDate;
     }

	@Override
	public ResultSet getDatabaseResult(String sqlStr) throws ClassNotFoundException, SQLException {
		Class.forName(sJdbcDriver);
		Connection conn = DriverManager.getConnection(sSqlServer, sUserName, sPassword);
		Statement st = conn.createStatement();
		ResultSet result = st.executeQuery(sqlStr);
		System.out.println(result.next());
		return result;
	}


	public void searchNselectPatient(String patientname) throws InterruptedException {
		String field = "Patient Name";
		MobileElement patName = driver.findElement(By.xpath("//input[@placeholder='Name Search']"));
		explicitWait(patName);
		enterText(patName, patientname, field);//Karthi - changed method
		patName.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		MobileElement elePAt = driver.findElement(By.xpath("//div/h2[contains(text(),'" + patientname + "')]"));
		click(elePAt,patientname);
		Thread.sleep(3000);
		takeSnap();
	}
	
	public void SearchNSelectPatient(String PASID) throws InterruptedException {
		try {
			MobileElement ele = driver.findElementByXPath("//input[@aria-label='search text']");
			jsclick(ele,"Search Text");
			Thread.sleep(2000);
			ele.clear();
			ele.sendKeys(PASID);
			Thread.sleep(8000);
			MobileElement sltpat = (MobileElement)driver.findElementByXPath("//div[@id=\"dxc-list-item-"+PASID+"\"]//h2");
			sltpat.click();
			Thread.sleep(2000);
			takeSnap();
			reportStep("Patient: "+PASID+" is searched and clicked", PASID);
  		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	

/*	 // New method added for this
	public void VerifySelectedTab(String tab) throws InterruptedException {
		try {
			MobileElement ele = driver.findElementByXPath("//ion-list[contains(@class,'scroll')]//div[contains(@class,'btn selected')]//span");
			String selectedTab = getText(ele, "Actual selected tab");
			if(selectedTab.equals(tab)) {
				reportStep("Actual and expected selected tab are equal - "+selectedTab, "PASS");
			}else {
				reportStep("Actual and expected selected tab are not equal. Actual - "+selectedTab+". But expected tab to be selected is "+tab, "FAIL");
			}
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}*/
	
	public static String replaceLast(String string) {
	 	String find = "-";
	 	String replace = ":";
        int lastIndex = string.lastIndexOf(find);
        if (lastIndex == -1) {
            return string;
        }
        String beginString = string.substring(0, lastIndex);
        String endString = string.substring(lastIndex + find.length());
        return beginString + replace + endString;
    }
	

	public void VerifySelectedTab(String tab) throws InterruptedException { 
		try {
	 		MobileElement ele = driver.findElementByXPath("//ion-list[contains(@class,'scroll')]/div[not(contains(@class,'nonselected'))]");
	 		String selectedTab = getText(ele, "Actual selected tab");
	 		if(selectedTab.contains(tab)) {
	 			reportStep("Actual and expected selected tab are equal - "+selectedTab, "PASS");
	 		}else {
	 			reportStep("Actual and expected selected tab are not equal. Actual - "+selectedTab+". But expected tab to be selected is "+tab, "FAIL");
	 		}
	   	}catch (InvalidElementStateException e) {
	 		reportStep("The element could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("The element is not available", "FAIL");
  		}
	}	

	public void VerifySelectedTab(String tab,String Device) throws InterruptedException {
		try {
			MobileElement ele;
	//		if(DeviceOrientation.contains("LANDSCAPE"))  {
			if((Device.contains("iPad")) || (Device.contains("Tab"))) {				
				 ele = driver.findElementByXPath("//ion-list[contains(@class,'scroll')]/div[not(contains(@class,'nonselected'))]");
				 //tab = "Allergies / Intolerances";
			}else {
				 ele = driver.findElementByXPath("//div[contains(@class,'dataloadTab')]/div[not(contains(@class,'nonselected'))]");
				 //tab = "Allergy/Intol";
			}		
			String selectedTab = getText(ele, "Actual selected tab");
			if(selectedTab.equals(tab)) {
				reportStep("Actual and expected selected tab are equal - "+selectedTab, "PASS");
			}else {
				reportStep("Actual and expected selected tab are not equal. Actual - "+selectedTab+". But expected tab to be selected is "+tab, "FAIL");
			}
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
		
	public void SearchPatient(String PASDetail) throws InterruptedException {
		try {
			MobileElement ele = driver.findElementByXPath("//input[@aria-label='search text']");
			jsclick(ele,"Search Text");
			Thread.sleep(8000);
			ele.clear();
			ele.sendKeys(PASDetail);
			Thread.sleep(20000);
			takeSnap();
			reportStep("Patient: "+PASDetail+" is searched", PASDetail);
  		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
		
	public void SelectPatient(String PASID) throws InterruptedException {
		try {
			
		//	MobileElement sltpat = (MobileElement)driver.findElementByXPath("//div[@id=\"dxc-list-item-"+PASID+"\"]//h2");
			MobileElement sltpat = (MobileElement)driver.findElementByXPath("//h2[contains(text(),'"+PASID+"')]");
			//MobileElement sltpat = (MobileElement)driver.findElementByXPath("//div[contains(@id,'"+PASID+"']//h2");
			click(sltpat, PASID);
			Thread.sleep(2000);
			takeSnap();
			reportStep("Patient: "+PASID+" is clicked", PASID);
  		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void VerifyPatientDetails_SearchList(String Surname,String Forename,String DOB, String NHS, String Address) throws InterruptedException {
		try {
			
			String PatName = driver.findElementByXPath("//ion-item-sliding//h2").getText();
			String PatDOB = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'Born')]/parent::span").getText();
			String PatNHS = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'NHS')]/parent::span").getText();
			String PatAdd = driver.findElementByXPath("//ion-item-sliding//p[2]/span").getText();
			System.out.println("PatDOB: "+PatDOB+" and "+DOB); 
			System.out.println("PatNHS: "+PatNHS+" and "+NHS); 
			System.out.println("PatAdd: "+PatAdd+" and "+Address);
			AssertStringContains((Surname.toUpperCase()+", "+Forename), PatName, "Patient Name in Search list");
			AssertStringEquals(("Born "+DOB), PatDOB, "Patient DOB in Search list");
			AssertStringEquals(("NHS "+NHS), PatNHS, "Patient NHS in Search list");
			AssertStringEquals(Address, PatAdd, "Patient Address in Search list");
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void VerifyPatientDetails_SearchListMore(String Surname,String Forename, String Gender,String DOB, String NHS, String PASID) throws InterruptedException {
		try {
			MobileElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
			jsclick(PatDetMoreIcon, "Patient Details More Icon");
			String PatName = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'Title')]").getText();
			String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Born')]/following-sibling::span").getText();
			String PatNHS = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'NHS')]/following-sibling::span").getText();
			String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Gender')]/following-sibling::span").getText();
			String PatPASID = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'PAS')]/following-sibling::span").getText();
			MobileElement closeicon = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='close']");
			jsclick(closeicon, "Patient details close icon");
			if((Gender.equals("Not known"))||(Gender.equals("Indeterminate"))) {
				Gender = "Other";
			}
			AssertStringContains((Surname.toUpperCase()+", "+Forename), PatName, "Patient Name in Patient Details pop up");
			AssertStringEquals(DOB, PatDOB, "Patient DOB in Patient Details pop up");
			AssertStringEquals(Gender, PatGender, "Patient Gender in Patient Details pop up");
			AssertStringEquals(NHS, PatNHS, "Patient NHS in Patient Details pop up");
			AssertStringEquals(PASID, PatPASID, "Patient PASID in Patient Details pop up");
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void VerifyPatientDetails_SearchListWebPAS(String Surname,String Forename,String Title,String DOB, String HRN, String Address) throws InterruptedException {
		try {
			String PatName = driver.findElementByXPath("//ion-item-sliding//h2").getText();
			String PatDOB = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'DoB')]/parent::span").getText();
			String PatHRN = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'HRN')]/parent::span").getText();
	//		String PatAdd = driver.findElementByXPath("//ion-item-sliding//p[2]/span").getText();
			String PatAdd = driver.findElementByXPath("//ion-row[@id='lineOfParagraphGroup'][2]//span").getText();

			AssertStringContains((Surname.toUpperCase()+", "+Forename+" ("+Title+")"), PatName, "Patient Name in Search list");
			AssertStringEquals(("DoB "+DOB), PatDOB, "Patient DOB in Search list");
			AssertStringEquals(("HRN "+HRN), PatHRN, "Patient NHS in Search list");
			AssertStringEquals(Address, PatAdd, "Patient Address in Search list");
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void VerifyPatientDetails_SearchListMoreWebPAS(String Surname,String Forename,String Title, String Gender,String DOB, String HRN) throws InterruptedException {
		try {
			MobileElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
			jsclick(PatDetMoreIcon, "Patient Details More Icon");
			String PatName = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'Title')]").getText();
			//String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'DoB')]/following-sibling::span").getText();
			//String PatHRN = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'HRN')]/following-sibling::span").getText();
			//String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Gender')]/following-sibling::span").getText();
			String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row//ion-row//span[contains(text(),'DoB')]/following-sibling::span").getText();
			String PatHRN = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row//ion-row//span[contains(text(),'HRN')]/following-sibling::span").getText();
			String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row//ion-row//span[contains(text(),'Gender')]/following-sibling::span").getText();
			MobileElement closeicon = driver.findElementByXPath("//dxc-more-list//ion-icon[contains(@name,'close')]");
			jsclick(closeicon, "Patient details close icon");
			if((Gender.equals("Transgender")) || (Gender.equals("Intersex"))) {
				Gender = "Other";
			}
			AssertStringContains((Surname.toUpperCase()+", "+Forename+" ("+Title+")"), PatName, "Patient Name in Patient Details pop up");
			AssertStringEquals(DOB, PatDOB, "Patient DOB in Patient Details pop up");
			AssertStringEquals(Gender, PatGender, "Patient Gender in Patient Details pop up");
			AssertStringEquals(HRN, PatHRN, "Patient NHS in Patient Details pop up");
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}

	public void searchNselectPatientMySpecialty(String specialty) throws InterruptedException 
	{
		MobileElement clickMenu = driver.findElementByXPath("//ion-menu-button");
		click(clickMenu, "Menu icon");
		Thread.sleep(3000);
		MobileElement clickMySpecialties = driver.findElementByXPath("//ion-label[contains(text(),'My Specialties')]");
		click(clickMySpecialties, "My Specialties");
		Thread.sleep(3000);
		MobileElement clickAddSpecialty =driver.findElementByXPath("//ion-button[contains(text(),'Add Specialty')]");
		click(clickAddSpecialty,"Add Speacialty");
		Thread.sleep(5000);
		MobileElement inputSpeacialty = driver.findElementByXPath("//input[@placeholder = 'Specialty']");
		if(specialty.contains(" "))
		{
			enterText(inputSpeacialty,specialty.substring(0, 3));
		}
		else
		{
			enterText(inputSpeacialty,specialty);
		}
		Thread.sleep(15000);
		jsclick(driver.findElementByXPath("//*[text()='"+specialty+"']/ancestor::ion-item//ion-icon"), "Favourite icon for "+specialty+"");
		Thread.sleep(3000);
		click(driver.findElementByXPath("//ion-menu-button"), "Menu icon");
		Thread.sleep(3000);
		click(clickMySpecialties, "My Specialties");
		Thread.sleep(3000);
		click(driver.findElementByXPath("//*[contains(text(),'"+specialty+"')]/ancestor::ion-item"), "Added specialty");
		Thread.sleep(15000);
		takeSnap();
	}
	
	
	public void SearchAndVerifyPatientdetails_MySpecialty(String patientname, String Gender, String DOB, String NHS, String PASID,String CPDetails,String CPSplty,String CPTmntFn,String Ward,String Building, String Floor,String EncDate) throws InterruptedException {
		MobileElement patNameinput = driver.findElement(By.xpath("//input[@placeholder='Name Search']"));
		Thread.sleep(15000);
		explicitWait(driver.findElementByTagName("DXC-LIST-ITEMS"));
		enterText(patNameinput, patientname);
		Thread.sleep(15000);
		MobileElement elePAt =  (MobileElement) driver.findElement(By.xpath("//*[text()='"+patientname+"']"));
		try {
			MobileElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
			jsclick(PatDetMoreIcon, "Patient Details More Icon");
			Thread.sleep(3000);
			String PatName = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'Title')]").getText();
			String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Born')]/following-sibling::span").getText();
			String PatNHS = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'NHS')]/following-sibling::span").getText();
			String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Gender')]/following-sibling::span").getText();
			String PatPASID = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'PAS')]/following-sibling::span").getText();
			String CPName = driver.findElementByXPath("//span[contains(text(),'Care Provider')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'title')]").getText();
			String CPSpecialty = driver.findElementByXPath("//span[contains(text(),'Care Provider')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Specialty')]/following-sibling::span").getText();		
			String CPTreatmentFn = driver.findElementByXPath("//span[contains(text(),'Care Provider')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Treatment')]/following-sibling::span").getText();		
			String IPWard = driver.findElementByXPath("//ion-icon[@name='pin']/parent::span/following-sibling::span").getText();
			String IPBuilding = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Building')]/following-sibling::span").getText();
			String IPFloor = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Floor')]/following-sibling::span").getText();
			String IPAdmDtm = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Admitted')]/following-sibling::span").getText();
			MobileElement closeicon = driver.findElementByXPath("//ion-icon[@aria-label='close']");
			jsclick(closeicon, "Patient details close icon");
			Thread.sleep(3000);
			if((Gender.equals("Not known"))||(Gender.equals("Indeterminate"))) {
				Gender = "Other";
			}
			AssertStringContains(patientname, PatName, "Patient Name in Patient Details pop up");
			AssertStringEquals(DOB, PatDOB, "Patient DOB in Patient Details pop up");
			AssertStringEquals(Gender, PatGender, "Patient Gender in Patient Details pop up");
			AssertStringEquals(NHS, PatNHS, "Patient NHS in Patient Details pop up");
			AssertStringEquals(PASID, PatPASID, "Patient PASID in Patient Details pop up");
			AssertStringEquals(CPDetails,CPName,"Care Provider Name");
			AssertStringEquals(CPSplty, CPSpecialty.toUpperCase(), "Care Provider Specialty");
			AssertStringEquals(CPTmntFn, CPTreatmentFn.toUpperCase(), "Care Provider Treatment Function");
			AssertStringEquals(Ward,IPWard,"IP Ward");
			AssertStringEquals(Building,IPBuilding,"IP Building");
			AssertStringEquals(Floor,IPFloor,"IP Floor");
			EncDate = replaceLast(EncDate);
			AssertStringEquals(EncDate,IPAdmDtm,"Encounter Admitted Date");
			jsclick(elePAt,"Patient record");
			Thread.sleep(10000);
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}

	}
	
	public void SelectPatientViaName(String PatName) throws InterruptedException {
		try {
			
			MobileElement sltpat = (MobileElement)driver.findElementByXPath("//dxc-list-items//h2[contains(text(),'"+PatName+"')]");
			click(sltpat, PatName);
			Thread.sleep(2000);
			takeSnap();
			reportStep("Patient: "+PatName+" is clicked", PatName);
  		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void VerifyPatientDetails_WardSearchList(String PatienFN,String DOB, String NHS, String Building,String Floor) throws InterruptedException {
		try {
			
			String PatName = driver.findElementByXPath("//ion-item-sliding//h2").getText();
			String PatDOB = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'Born')]/parent::span").getText();
			String PatNHS = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'NHS')]/parent::span").getText();
			String IPWardBuilding = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'Building')]/following-sibling::span").getText();
			String IPWardFloor = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'Floor')]/following-sibling::span").getText();
			
			AssertStringContains(PatienFN, PatName, "Patient Name in Search list");
			AssertStringEquals(("Born "+DOB), PatDOB, "Patient DOB in Search list");
			AssertStringEquals(("NHS "+NHS), PatNHS, "Patient NHS in Search list");
			AssertStringEquals(Building, IPWardBuilding, "IP Ward Building");
			AssertStringEquals(Floor, IPWardFloor, "IP Ward Floor");
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void VerifyPatientDetails_WardSearchListMore(String PatientFN, String Gender,String DOB, String NHS, String PASID,String CPDetails,String CPSplty,String CPTmntFn,String Ward,String Building, String Floor,String EncDate) throws InterruptedException {
		try {
			MobileElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
			jsclick(PatDetMoreIcon, "Patient Details More Icon");
			Thread.sleep(3000);
			String PatName = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'Title')]").getText();
			String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Born')]/following-sibling::span").getText();
			String PatNHS = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'NHS')]/following-sibling::span").getText();
			String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Gender')]/following-sibling::span").getText();
			String PatPASID = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'PAS')]/following-sibling::span").getText();
			String CPName = driver.findElementByXPath("//span[contains(text(),'Care Provider')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'title')]").getText();
			String CPSpecialty = driver.findElementByXPath("//span[contains(text(),'Care Provider')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Specialty')]/following-sibling::span").getText();		
			String CPTreatmentFn = driver.findElementByXPath("//span[contains(text(),'Care Provider')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Treatment')]/following-sibling::span").getText();		
			String IPWard = driver.findElementByXPath("//ion-icon[@name='pin']/parent::span/following-sibling::span").getText();
			String IPBuilding = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Building')]/following-sibling::span").getText();
			String IPFloor = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Floor')]/following-sibling::span").getText();
			String IPAdmDtm = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Admitted')]/following-sibling::span").getText();
			
			MobileElement closeicon = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='close']");
			jsclick(closeicon, "Patient details close icon");
			Thread.sleep(3000);
			if((Gender.equals("Not known"))||(Gender.equals("Indeterminate"))) {
				Gender = "Other";
			}
			AssertStringContains(PatientFN, PatName, "Patient Name in Patient Details pop up");
			AssertStringEquals(DOB, PatDOB, "Patient DOB in Patient Details pop up");
			AssertStringEquals(Gender, PatGender, "Patient Gender in Patient Details pop up");
			AssertStringEquals(NHS, PatNHS, "Patient NHS in Patient Details pop up");
			AssertStringEquals(PASID, PatPASID, "Patient PASID in Patient Details pop up");
			AssertStringEquals(CPDetails,CPName,"Care Provider Name");
			AssertStringEquals(CPSplty, CPSpecialty.toUpperCase(), "Care Provider Specialty");
			AssertStringEquals(CPTmntFn, CPTreatmentFn.toUpperCase(), "Care Provider Treatment Function");
			AssertStringEquals(Ward,IPWard,"IP Ward");
			AssertStringEquals(Building,IPBuilding,"IP Building");
			AssertStringEquals(Floor,IPFloor,"IP Floor");
			//EncDate = replaceLast(EncDate);
			AssertStringEquals(EncDate.trim(),IPAdmDtm.trim(),"Encounter Admitted Date");
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void ClearFilterDate(MobileElement ele,String field) {
		try {
			ele.sendKeys(Keys.BACK_SPACE);
			ele.sendKeys(Keys.BACK_SPACE);
			ele.sendKeys(Keys.BACK_SPACE);
			ele.sendKeys(Keys.BACK_SPACE);
			ele.sendKeys(Keys.BACK_SPACE);
			ele.sendKeys(Keys.BACK_SPACE);
			ele.sendKeys(Keys.BACK_SPACE);
			ele.sendKeys(Keys.BACK_SPACE);
			ele.sendKeys(Keys.BACK_SPACE);
			ele.sendKeys(Keys.BACK_SPACE);
			ele.sendKeys(Keys.BACK_SPACE);
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	

	public void SearchPatient_AdvSearch(String Surname,String Forename, String Gender,String DOB) throws InterruptedException {
		try {
			driver.findElementByXPath("//input[@aria-label='search text']").click();
			Thread.sleep(5000);
			driver.findElementByXPath("//input[contains(@placeholder,'Surname')]").sendKeys(Surname);
			driver.findElementByXPath("//input[contains(@placeholder,'Forename')]").sendKeys(Forename);
			driver.findElementByXPath("//ion-radio[@value='"+Gender.toLowerCase()+"']").click();
			driver.findElementByXPath("//input[contains(@placeholder,'DD-MMM-YYYY')]").sendKeys(DOB);
			takeSnap();
			reportStep("Surname, Forename, Gender and DOB is entered in Advance Search screen", "PASS");
			driver.findElementByXPath("//ion-button[contains(text(),'Search')]").click();
			Thread.sleep(5000);
			reportStep("Patient is search using Advance Search", "PASS");
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void VerifyAddressDetails(String SORHouseNo,String SORCity,String SORCountry, String SORPostCode) {
		try {
			String HouseNoCity = driver.findElementByXPath("//ion-label[contains(text(),'Address')]/ancestor::ion-item/following-sibling::div/div[1]").getText();
			String PostCode = driver.findElementByXPath("//ion-label[contains(text(),'Address')]/ancestor::ion-item/following-sibling::div/div[2]").getText();
			String Country = driver.findElementByXPath("//ion-label[contains(text(),'Address')]/ancestor::ion-item/following-sibling::div/div[3]").getText();
			
			AssertStringEquals(SORHouseNo+","+SORCity, HouseNoCity, "House No and City");
			AssertStringEquals(SORPostCode, PostCode, "PostCode");
			AssertStringEquals(SORCountry, Country, "Country");
			
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	
	public void VerifyContactDetails(String SORHomeNo,String SORMobileNo,String SORWorkNo, String SOREmail) {
		try {
			String HomeNo = driver.findElementByXPath("//span[contains(text(),'Home')]/following-sibling::span").getText();
			String MobileNo = driver.findElementByXPath("//span[contains(text(),'Mobile')]/following-sibling::span").getText();
			String WorkNo = driver.findElementByXPath("//span[contains(text(),'Work')]/following-sibling::span").getText();
			String Email = driver.findElementByXPath("//span[contains(text(),'Email')]/following-sibling::A").getText();
			
			AssertStringEquals(HomeNo, SORHomeNo, "Contact No - Home");
			AssertStringEquals(MobileNo, SORMobileNo, "Contact No - Moile");
			AssertStringEquals(WorkNo, SORWorkNo, "Contact No - Work");
			AssertStringContains(Email, SOREmail, "Contact No - Email");
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void VerifyGeneralInfoDetails(String SORGender,String SORNHS,String SORPAS) {
		try {
			String Gender = driver.findElementByXPath("//ion-label[contains(text(),'Information')]/parent::ion-item/following-sibling::ion-grid//span[contains(text(),'Gender')]/following-sibling::span").getText();
			String NHS = driver.findElementByXPath("//ion-label[contains(text(),'Information')]/parent::ion-item/following-sibling::ion-grid//span[contains(text(),'NHS')]/following-sibling::span").getText();
			String PAS = driver.findElementByXPath("//ion-label[contains(text(),'Information')]/parent::ion-item/following-sibling::ion-grid//span[contains(text(),'PAS')]/following-sibling::span").getText();
			
			AssertStringEquals(SORGender, Gender, "Information - Gender");
			AssertStringEquals(SORNHS, NHS, "Information - NHS");
			AssertStringEquals(SORPAS, PAS, "Information - PAS");
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
 	
 	
	public void VerifyPreferenceDetails(String SORPrefType,String SORPrefDetails,String SORPrefDate, String SORActionable) {
		try {
			String PrefType = driver.findElementByXPath("//ion-label[contains(text(),'Preferences')]/ancestor::ion-row/following-sibling::ion-row//ion-col[1][contains(text(),'"+SORPrefType+"')]").getText();
			String PrefDetail = driver.findElementByXPath("//ion-label[contains(text(),'Preferences')]/ancestor::ion-row/following-sibling::ion-row//ion-col[1][contains(text(),'"+SORPrefType+"')]/following-sibling::ion-col[1]").getText();
			String PrefDate = driver.findElementByXPath("//ion-label[contains(text(),'Preferences')]/ancestor::ion-row/following-sibling::ion-row//ion-col[1][contains(text(),'"+SORPrefType+"')]/following-sibling::ion-col[2]").getText();
			String Actionable = driver.findElementByXPath("//ion-label[contains(text(),'Preferences')]/ancestor::ion-row/following-sibling::ion-row//ion-col[1][contains(text(),'"+SORPrefType+"')]/following-sibling::ion-col[3]").getText();
			
			if(PrefType.equals("Communication language")) {
				String NewSORPrefDetail = "Communication language : "+SORPrefDetails+", Preferred communication:"+SORActionable+", Primary Language:"+SORActionable;
				System.out.println("NewSORPrefDetail: "+NewSORPrefDetail);
				System.out.println("PrefDetail: "+PrefDetail);
				AssertStringEquals(PrefDetail, NewSORPrefDetail, "Preference Details");
			}else {
				AssertStringEquals(PrefDetail, SORPrefDetails, "Preference Details");
			}
			
			AssertStringEquals(PrefType, SORPrefType, "Preference Type");
			AssertStringEquals(PrefDate, SORPrefDate, "Preference Date");
			AssertStringContains(Actionable, SORActionable, "Preference Actionable");
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
 	
	 public void VerifyMedicallyDischargedPatient(String patientname) throws InterruptedException {
		   try {
		    	MobileElement ele = driver.findElement(By.xpath("//h2[text()='"+patientname+"']//following::div[@class='pats-flex-conatiner'][1]//span[contains(@class,'mergediconone')]"));
		    	boolean val = ele.isDisplayed();
		      if (val) {
		        reportStep("Medically discharged icon is displayed for Medically discharged Patient", "PASS");
		      } else {
		        reportStep("Medically discharged icon is not displayed Medically discharged Patient", "FAIL");
		      }
		      takeSnap();
		    } catch (InvalidElementStateException e) {
		      reportStep("Medically discharged icon is not displayed.", "FAIL");
		    } catch (WebDriverException e) {
		      reportStep("Medically discharged icon is not displayed", "FAIL");
		    }
		 }
	 
	 public void VerifyMedicallyDischargedPatientNotInList(String patientname) throws InterruptedException {
		    try {
		 
		    	List<MobileElement> ele = driver.findElements(By.xpath("//h2[contains(text(),'" + patientname + "')]"));
		      int val = ele.size();
		      if (val==0) {
		        reportStep("Medically discharged Patient Not displayed in the List", "PASS");
		      } else {
		        reportStep("Medically discharged Patient displayed in the List", "FAIL");
		      }
		      takeSnap();
		    } catch (InvalidElementStateException e) {
		      reportStep("Medically discharged icon is not displayed.", "FAIL");
		    } catch (WebDriverException e) {
		      reportStep("Medically discharged icon is not displayed", "FAIL");
		    }
		  }

	 public void VerifyMedDiscPatientDetails_WardSearchListMore(String PatientFN, String Gender,String DOB, String NHS, String PASID,String CPDetails,String CPSplty,String CPTmntFn,String Ward,String Building, String Floor,String EncDate,String MedDisDate) throws InterruptedException {
			try {
				MobileElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
				jsclick(PatDetMoreIcon, "Patient Details More Icon");
				Thread.sleep(3000);
				String PatName = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'Title')]").getText();
				String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Born')]/following-sibling::span").getText();
				String PatNHS = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'NHS')]/following-sibling::span").getText();
				String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Gender')]/following-sibling::span").getText();
				String PatPASID = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'PAS')]/following-sibling::span").getText();
				String CPName = driver.findElementByXPath("//span[contains(text(),'Care Provider')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'title')]").getText();
				String CPSpecialty = driver.findElementByXPath("//span[contains(text(),'Care Provider')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Specialty')]/following-sibling::span").getText();		
				String CPTreatmentFn = driver.findElementByXPath("//span[contains(text(),'Care Provider')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Treatment')]/following-sibling::span").getText();		
				String IPWard = driver.findElementByXPath("//ion-icon[@name='pin']/parent::span/following-sibling::span").getText();
				String IPBuilding = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Building')]/following-sibling::span").getText();
				String IPFloor = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Floor')]/following-sibling::span").getText();
				String IPAdmDtm = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Admitted')]/following-sibling::span").getText();
				String MedDiscDate = driver.findElementByXPath("//span[contains(text()='Discharge Status')]/following-sibling::span").getText();
				
				MobileElement closeicon = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='close']");
				jsclick(closeicon, "Patient details close icon");
				Thread.sleep(3000);
				if((Gender.equals("Not known"))||(Gender.equals("Indeterminate"))) {
					Gender = "Other";
				}
				AssertStringContains(PatientFN, PatName, "Patient Name in Patient Details pop up");
				AssertStringEquals(DOB, PatDOB, "Patient DOB in Patient Details pop up");
				AssertStringEquals(Gender, PatGender, "Patient Gender in Patient Details pop up");
				AssertStringEquals(NHS, PatNHS, "Patient NHS in Patient Details pop up");
				AssertStringEquals(PASID, PatPASID, "Patient PASID in Patient Details pop up");
				AssertStringEquals(CPDetails,CPName,"Care Provider Name");
				AssertStringEquals(CPSplty, CPSpecialty.toUpperCase(), "Care Provider Specialty");
				AssertStringEquals(CPTmntFn, CPTreatmentFn.toUpperCase(), "Care Provider Treatment Function");
				AssertStringEquals(Ward,IPWard,"IP Ward");
				AssertStringEquals(Building,IPBuilding,"IP Building");
				AssertStringEquals(Floor,IPFloor,"IP Floor");
				EncDate = replaceLast(EncDate);
				AssertStringEquals(EncDate.trim(),IPAdmDtm.trim(),"Encounter Admitted Date");
				AssertStringEquals("Medically discharged "+MedDisDate, MedDiscDate, "Discharge Status");
			}catch (InvalidElementStateException e) {
	  			reportStep("The element could not be clicked", "FAIL");
	  		} catch (WebDriverException e) {
	  			reportStep("The element is not available", "FAIL");
	  		}
		}

	 
	 public void searchNselectPatientMyCP(String careProvider) throws InterruptedException 
		{
			MobileElement clickMenu = driver.findElementByXPath("//ion-menu-button");
			click(clickMenu, "Menu icon");
			Thread.sleep(3000);
			MobileElement clickMyCP = driver.findElementByXPath("//ion-label[contains(text(),'My Care Providers')]");
			click(clickMyCP, "My CareProvider");
			Thread.sleep(3000);
			MobileElement clickAddCP =driver.findElementByXPath("//ion-button[contains(text(),'Add Care Provider')]");
			click(clickAddCP,"Add CareProvider");
			Thread.sleep(5000);
			MobileElement inputCP = driver.findElementByXPath("//input[@placeholder = 'Care Provider']");
			if(careProvider.contains("Automation"))
			{
				enterText(inputCP,careProvider.substring(11,17));
			}
			else
			{
				enterText(inputCP,careProvider);
			}
			Thread.sleep(15000);
			jsclick(driver.findElementByXPath("//*[text()='"+careProvider+"']/ancestor::ion-item//ion-icon"), "Favourite icon for "+careProvider+"");
			Thread.sleep(3000);
			click(driver.findElementByXPath("//ion-menu-button"), "Menu icon");
			Thread.sleep(3000);
			click(clickMyCP, "My Specialties");
			Thread.sleep(3000);
			click(driver.findElementByXPath("//*[contains(text(),'"+careProvider+"')]/ancestor::ion-item"), "Added specialty");
			Thread.sleep(15000);
			takeSnap();
		}
	 
	 public void VerifyPatientDetails_SearchListMoreDeceasedWebPAS(String Surname,String Forename,String Title, String Gender,String DOB, String HRN,String DateDied,String Age) throws InterruptedException {
			try {
				MobileElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
				jsclick(PatDetMoreIcon, "Patient Details More Icon");
				String PatName = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'Title')]").getText();
				//String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'DoB')]/following-sibling::span").getText();
				//String PatHRN = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'HRN')]/following-sibling::span").getText();
				//String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Gender')]/following-sibling::span").getText();
				String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row//ion-row//span[contains(text(),'DoB')]/following-sibling::span").getText();
				String PatHRN = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row//ion-row//span[contains(text(),'HRN')]/following-sibling::span").getText();
				String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row//ion-row//span[contains(text(),'Gender')]/following-sibling::span").getText();
				String PatDOD = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row//ion-row//span[contains(text(),'Died')]/following-sibling::span").getText();
				String DiedDuration = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row//ion-row//span[contains(@class,'date-value')]").getText();
				
				MobileElement closeicon = driver.findElementByXPath("//dxc-more-list//ion-icon[contains(@name,'close')]");
				jsclick(closeicon, "Patient details close icon");
				if((Gender.equals("Transgender")) || (Gender.equals("Intersex"))) {
					Gender = "Other";
				}
				
				AssertStringEquals((Surname.toUpperCase()+", "+Forename+" ("+Title+")"), PatName, "Patient Name in Patient Details pop up");
				AssertStringEquals(DOB, PatDOB, "Patient DOB in Patient Details pop up");
				AssertStringEquals(Gender, PatGender, "Patient Gender in Patient Details pop up");
				AssertStringEquals(HRN, PatHRN, "Patient NHS in Patient Details pop up");
				AssertStringEquals(DateDied, PatDOD, "Patient DOD in Patient Details pop up");
				AssertStringEquals("("+Age+"y)", DiedDuration, "Patient Died duration in Patient Details pop up");
				
			}catch (InvalidElementStateException e) {
	  			reportStep("The element could not be clicked", "FAIL");
	  		} catch (WebDriverException e) {
	  			reportStep("The element is not available", "FAIL");
	  		}
		}




	public void VerifyPatientDetails_WardListWebPAS(String Surname,String Forename,String Title,String DOB, String HRN, String Site,String Ward, String Bed) throws InterruptedException {
			try {
				String PatName = driver.findElementByXPath("//ion-item-sliding//h2").getText();
				String PatDOB = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'DoB')]/parent::span").getText();
				String PatHRN = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'HRN')]/parent::span").getText();
				String IPSite = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'Site')]/following-sibling::span").getText();
				String IPWard = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'Ward')]/following-sibling::span").getText();
				String IPBed = 	driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'Bed')]/following-sibling::span").getText();	
				
				AssertStringContains((Surname.toUpperCase()+", "+Forename+" ("+Title+")"), PatName, "Patient Name in Search list");
				AssertStringEquals(("DoB "+DOB), PatDOB, "Patient DOB in Search list");
				AssertStringEquals(("HRN "+HRN), PatHRN, "Patient NHS in Search list");
				AssertStringContains(Site, IPSite, "Site in Ward list");
				AssertStringEquals(Ward, IPWard, "Ward in Ward list");
				AssertStringEquals(Bed, IPBed, "Bed in Ward list");
			}catch (InvalidElementStateException e) {
	  			reportStep("The element could not be clicked", "FAIL");
	  		} catch (WebDriverException e) {
	  			reportStep("The element is not available", "FAIL");
	  		}
		}
		
		public void VerifyPatientDetails_WardListMoreWebPAS(String Surname,String Forename,String Title, String Gender,String DOB, String HRN) throws InterruptedException {
			try {
				MobileElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
				jsclick(PatDetMoreIcon, "Patient Details More Icon");
				String PatName = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'Title')]").getText();
				String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'DoB')]/following-sibling::span").getText();
				String PatHRN = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'HRN')]/following-sibling::span").getText();
				String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Gender')]/following-sibling::span").getText();
				
				MobileElement closeicon = driver.findElementByXPath("//dxc-more-list//ion-icon[contains(@name,'close')]");
				jsclick(closeicon, "Patient details close icon");
				if((Gender.equals("Transgender")) || (Gender.equals("Intersex"))) {
					Gender = "Other";
				}
				
				AssertStringContains((Surname.toUpperCase()+", "+Forename+" ("+Title+")"), PatName, "Patient Name in Patient Details pop up");
				AssertStringEquals(DOB, PatDOB, "Patient DOB in Patient Details pop up");
				AssertStringEquals(Gender, PatGender, "Patient Gender in Patient Details pop up");
				AssertStringEquals(HRN, PatHRN, "Patient NHS in Patient Details pop up");
				
			}catch (InvalidElementStateException e) {
	  			reportStep("The element could not be clicked", "FAIL");
	  		} catch (WebDriverException e) {
	  			reportStep("The element is not available", "FAIL");
	  		}
		}
		
		
		public void VerifyCareProviderDetails_WardListMoreWebPAS(String CPName,String CPSplty,String IPLocation, String IPSite,String IPWard, String IPBed) throws InterruptedException {
			try {
				MobileElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
				jsclick(PatDetMoreIcon, "Patient Details More Icon");
				String CP = driver.findElementByXPath("//span[contains(text(),'Care Provider Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'titleBold')]").getText();
				String Splty = driver.findElementByXPath("//span[contains(text(),'Care Provider Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Specialty')]/following-sibling::span").getText();
				String Location = driver.findElementByXPath("//span[contains(text(),'Care Provider Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(@class,'wardIcon')]/following-sibling::span").getText();
				String Site = driver.findElementByXPath("//span[contains(text(),'Care Provider Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Site')]/following-sibling::span").getText();
				String Ward = driver.findElementByXPath("//span[contains(text(),'Care Provider Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Ward')]/following-sibling::span").getText();
				String Bed = driver.findElementByXPath("//span[contains(text(),'Care Provider Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Bed')]/following-sibling::span").getText();
				MobileElement closeicon = driver.findElementByXPath("//dxc-more-list//ion-icon[contains(@name,'close')]");
				jsclick(closeicon, "Patient details close icon");
				
				AssertStringEquals(CPName, CP, "Care Provider Name in more CP Details pop up");
				AssertStringEquals(CPSplty, Splty, "Care Provider Specialty in more CP Details pop up");
				AssertStringEquals(IPLocation, Location, "Location Name in more CP Details pop up");
				AssertStringContains(IPSite, Site, "Site details in more CP Details pop up");
				AssertStringEquals(IPWard, Ward, "Ward Name in more CP Details pop up");
				AssertStringEquals(IPBed, Bed, "Bed details in more CP Details pop up");
				
			}catch (InvalidElementStateException e) {
	  			reportStep("The element could not be clicked", "FAIL");
	  		} catch (WebDriverException e) {
	  			reportStep("The element is not available", "FAIL");
	  		}
		}

		public void VerifyEncounterDetails_WardListMoreWebPAS(String SORVisitNo,String SORAdmittedDate) throws InterruptedException {
			try {
				MobileElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
				jsclick(PatDetMoreIcon, "Patient Details More Icon");
				String VisitNo = driver.findElementByXPath("//span[contains(text(),'Encounter Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Visit No')]/following-sibling::span").getText();
				String AdmittedDate = driver.findElementByXPath("//span[contains(text(),'Encounter Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Admitted')]/following-sibling::span").getText();
			//	String ExpDiscDate = driver.findElementByXPath("//span[contains(text(),'Encounter Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Expected Discharge')]/following-sibling::span").getText();
				AssertStringEquals(SORVisitNo, VisitNo, "Visit No in more Encounter Details pop up");
				AssertStringEquals(SORAdmittedDate, AdmittedDate, "Admitted Date Time in more Encounter Details pop up");
			//	AssertStringEquals(SORExpDiscDate, ExpDiscDate, "Expected Discharge Date in more Encounter Details pop up");
				MobileElement closeicon = driver.findElementByXPath("//dxc-more-list//ion-icon[contains(@name,'close')]");
				jsclick(closeicon, "Patient details close icon");
				Thread.sleep(2000);
				
			}catch (InvalidElementStateException e) {
	  			reportStep("The element could not be clicked", "FAIL");
	  		} catch (WebDriverException e) {
	  			reportStep("The element is not available", "FAIL");
	  		}
		}


	public void SearchNMarkFavourite_WardLeave(String type,String HRN,String Name) throws InterruptedException {
			try {
				driver.findElementByXPath("//*[contains(@placeholder,'"+type+"')]").click();
				driver.findElementByXPath("//*[contains(@placeholder,'"+type+"')]").clear();
				driver.findElementByXPath("//*[contains(@placeholder,'"+type+"')]").sendKeys(Name);
				Thread.sleep(5000);
				MobileElement elePAt = driver.findElementByXPath("//span[contains(text(),'"+HRN+"')]/ancestor::ion-row[3]/following-sibling::ion-row//ion-icon[@name='star-outline']");
				Thread.sleep(3000);
				jsclick(elePAt,HRN);
	      		Thread.sleep(2000);
	      		//reportStep(Name+" is clicked", "PASS", false);
	      		takeSnap();
	  		}catch (InvalidElementStateException e) {
	  			reportStep("The element could not be clicked", "FAIL");
	  		} catch (WebDriverException e) {
	  			reportStep("The element is not available", "FAIL");
	  		}	
		}



	public void SearchNMarkFavourite_Search(String type,String HRN) throws InterruptedException {
			try {
				driver.findElementByXPath("//*[contains(@placeholder,'"+type+"')]").click();
				driver.findElementByXPath("//*[contains(@placeholder,'"+type+"')]").clear();
				driver.findElementByXPath("//*[contains(@placeholder,'"+type+"')]").sendKeys(HRN);
				Thread.sleep(5000);
				MobileElement elePAt = driver.findElementByXPath("//span[contains(text(),'"+HRN+"')]/ancestor::ion-row[3]/following-sibling::ion-row//ion-icon[@name='star-outline']");
				Thread.sleep(3000);
				jsclick(elePAt,HRN);
	      		Thread.sleep(2000);
	      		//reportStep(Name+" is clicked", "PASS", false);
	      		takeSnap();
	  		}catch (InvalidElementStateException e) {
	  			reportStep("The element could not be clicked", "FAIL");
	  		} catch (WebDriverException e) {
	  			reportStep("The element is not available", "FAIL");
	  		}	
		}
		
	public void VerifyFavouritePatient(String PatientName,String Encounter) {
      	int count = driver.findElementsByXPath("//h2").size();
  		System.out.println(count);
  		for(int i = 1; i<=count; i++) {
  			String patName = driver.findElementByXPath("(//h2)["+i+"]").getText();
  			if((patName.toUpperCase()).contains(PatientName.toUpperCase())) {
  				reportStep(PatientName+ " is displayed in Worklist Overview under "+Encounter+" tab", "PASS");
  				break;
  			}
  		}
      }
	
 public void MarkUnFavourite_WardLeave(String HRN) throws InterruptedException {
		try {
			MobileElement elePAt = driver.findElementByXPath("//span[contains(text(),'"+HRN+"')]/ancestor::ion-row[3]/following-sibling::ion-row//ion-icon[@name='star']");
			Thread.sleep(3000);
			jsclick(elePAt,HRN);
      		Thread.sleep(2000);
      		//reportStep(Name+" is clicked", "PASS", false);
      		takeSnap();
  		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}	
	}


 public void AdvanceSearchPatient(String Surname, String Forename, String Gender, String DoB) throws InterruptedException {
		try {
			MobileElement srchPas = driver.findElementByXPath("//input[@placeholder='Patient Identifier']");
			jsclick(srchPas, "Search Patient");
			Thread.sleep(2000);
			driver.findElementByXPath("//input[@placeholder='Surname']").sendKeys(Surname);
			driver.findElementByXPath("//input[@placeholder='Forename']").sendKeys(Forename);
			MobileElement MaleGen = driver.findElementByXPath("//ion-radio[@value='male']");
			jsclick(MaleGen, "Male Gender");
			//driver.findElementByXPath("//ion-label[contains(text(),'Male')]").click();
			driver.findElementByXPath("//dxc-datepicker//input").sendKeys(DoB);
			MobileElement SearchBtn = driver.findElementByXPath("//ion-icon[contains(@name,'checkbox')]");
			jsclick(SearchBtn, "Search Button");
			Thread.sleep(5000);
			takeSnap();
			reportStep("Patient: "+Surname+" is searched", "PASS");
		}catch (InvalidElementStateException e) {
			reportStep("The element could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("The element is not available", "FAIL");
		}
	}
	
	
	public void VerifyPatientDetails_AdvSearchListMoreWebPAS(String AliasSurname,String AliasForename) throws InterruptedException {
		try {
			MobileElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
			jsclick(PatDetMoreIcon, "Patient Details More Icon");
			Thread.sleep(5000);
			String PatAliasName = driver.findElementByXPath("(//span[contains(text(),'Alias Details')]/ancestor::ion-row/following-sibling::div//span)[2]").getText();
			AssertStringEquals(PatAliasName, AliasSurname+", "+AliasForename, "Patient Alias Name in Alias Details pop up");
			MobileElement closeicon = driver.findElementByXPath("//dxc-more-list//ion-icon[contains(@name,'close')]");
			jsclick(closeicon, "Patient details close icon");
			
		}catch (InvalidElementStateException e) {
			reportStep("The element could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("The element is not available", "FAIL");
		}
	}
	
	public void VerifyPatientDetails_AdvanceSearchListWebPAS(String Surname,String Forename,String Title,String DOB, String HRN, String Address) throws InterruptedException {
		try {
			String PatName = driver.findElementByXPath("(//ion-item-sliding//h2)[2]").getText();
			String PatDOB = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'DoB')]/parent::span").getText();
			String PatHRN = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'HRN')]/parent::span").getText();
			//String PatAdd = driver.findElementByXPath("//ion-item-sliding//p[2]/span").getText();
			String PatAdd = driver.findElementByXPath("//ion-row[@id='lineOfParagraphGroup'][2]//span").getText();
			
			AssertStringEquals((Surname.toUpperCase()+", "+Forename+" ("+Title+")"), PatName, "Patient Name in Search list");
			AssertStringEquals(("DoB "+DOB), PatDOB, "Patient DOB in Search list");
			AssertStringEquals(("HRN "+HRN), PatHRN, "Patient NHS in Search list");
			AssertStringEquals(Address, PatAdd, "Patient Address in Search list");
		}catch (InvalidElementStateException e) {
			reportStep("The element could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("The element is not available", "FAIL");
		}
	}

 
	public void VerifyPatientDetails_WardSearchListWebPAS(String PatientFullname,String DOB, String HRN, String Bed ,String Ward, String Site) throws InterruptedException {

		try {

			String PatName = driver.findElementByXPath("//ion-item-sliding//h2").getText();

			String PatDOB = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'DoB')]/parent::span").getText();

			String PatHRN = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'HRN')]/parent::span").getText();

			String IPBed = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'Bed')]/following-sibling::span").getText();

			IPBed = IPBed.substring(4);

			String IPWard = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'Ward')]/following-sibling::span").getText();

			String IPSite = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'Site')]/following-sibling::span").getText();

			AssertStringContains(PatName, PatientFullname, "Patient Name in Search list");

			AssertStringEquals(("DoB "+DOB), PatDOB, "Patient DOB in Search list");

			AssertStringEquals(("HRN "+HRN), PatHRN, "Patient HRN in Search list");

			AssertStringEquals(Bed, IPBed, "IP Ward Bed");

			AssertStringEquals(Ward, IPWard, "IP Ward");

			AssertStringEquals(Site, IPSite, "IP Ward Site");

		}catch (InvalidElementStateException e) {

  			reportStep("The element could not be clicked", "FAIL");

  		} catch (WebDriverException e) {

  			reportStep("The element is not available", "FAIL");

  		}

	}

	public void VerifyPatientDetails_WardSearchListMoreWebPAS(String PatientTitle, String PatientFN, String DOB ,String HRN, String Gender, String CPName ,String CPSplty,String Site ,String Ward ,String Bed ,String EncID, String AdmittedDate, String status) throws InterruptedException {

		try {

			MobileElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");

			jsclick(PatDetMoreIcon, "Patient Details More Icon");

			Thread.sleep(10000);

			String PatName =driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::dxc-more-list//span[contains(@class,'Title')]").getText();

			String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::dxc-more-list//span[contains(text(),'DoB')]/following-sibling::span").getText();

			String PatHRN = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::dxc-more-list//span[contains(text(),'HRN')]/following-sibling::span").getText();

			String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::dxc-more-list//span[contains(text(),'Gender')]/following-sibling::span").getText();

			String IPCPName = driver.findElementByXPath("//span[contains(text(),'Care Provider')]/ancestor::dxc-more-list//span[contains(@class,'titleBold')]").getText();

			String IPCPSpecialty = driver.findElementByXPath("//span[contains(text(),'Care Provider')]/ancestor::dxc-more-list//span[contains(text(),'Specialty')]/following-sibling::span").getText();		

			String IPWard = driver.findElementByXPath("//ion-icon[@name='pin']/parent::span/following-sibling::span").getText();

			String IPSite = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Site')]/following-sibling::span").getText();

			String IPWardMore = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Ward')]/following-sibling::span").getText();

			String IPBed =driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Bed')]/following-sibling::span").getText();

			IPBed = IPBed.substring(4);

			String IPEncID = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Visit No')]/following-sibling::span").getText();

			String IPAdmDtm = null;

			if(status.equals("Admit"))

			{

				IPAdmDtm = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Admitted')]/following-sibling::span").getText();

			}

			else if(status.equals("PreAdmit"))

			{

				IPAdmDtm = driver.findElementByXPath("//dxc-more-list//span[contains(text(),'Planned')]/following-sibling::span").getText();

			}

			MobileElement closeicon = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='close']");

			jsclick(closeicon, "Patient details close icon");

			Thread.sleep(3000);

			if((Gender.equals("Not known"))||(Gender.equals("Indeterminate"))) {

				Gender = "Other";

			}

			AssertStringContains(PatName, PatientFN ,"Patient Name in Patient Details pop up");

			AssertStringContains(PatName, PatientTitle ,"Patient Name in Patient Details pop up");

			AssertStringEquals(DOB, PatDOB, "Patient DOB in Patient Details pop up");

			AssertStringEquals(Gender, PatGender, "Patient Gender in Patient Details pop up");

			AssertStringEquals(HRN, PatHRN, "Patient HRN in Patient Details pop up");

			AssertStringEquals(IPWard , Ward, "Location");

			AssertStringEquals(IPWardMore , Ward, "ward");

			AssertStringEquals(IPCPName ,CPName,"Care Provider Name");

			AssertStringEquals(CPSplty.toUpperCase(), IPCPSpecialty.toUpperCase(), "Care Provider Specialty");

			AssertStringEquals(IPBed, Bed, "Bed");

			AssertStringEquals(Ward,IPWard,"IP Ward");

			AssertStringEquals(Site,IPSite,"IP Building");

			AssertStringEquals(AdmittedDate,IPAdmDtm,"Admitted date");

			AssertStringEquals(EncID,IPEncID,"Enc Id");

		}catch (InvalidElementStateException e) {

  			reportStep("The element could not be clicked", "FAIL");

  		} catch (WebDriverException e) {

  			reportStep("The element is not available"+e, "FAIL");

  		}

	}	
 

 
	public void SearchPatientByName(String patientname) throws InterruptedException {
		try {
			Thread.sleep(2000);
			MobileElement srchPas = driver.findElementByXPath("//input[@placeholder='Name Search']");
			jsclick(srchPas, "Search Patient");
			srchPas.clear();
			srchPas.sendKeys(patientname);
			Thread.sleep(15000);
			takeSnap();
			reportStep("Patient: "+patientname+" is searched", "PASS");
  		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}

	public void VerifyOrder(String SOROrderName,String SORReqDate,String SORLab,String SORDiagGp,String SORPriority,String SORStatus,String SORCopyTo,String SOROrdProv,String SORReqBy,String SORReqOn,String SORClinicalNotes) throws InterruptedException {
		try {
			MobileElement ele = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-label//span[contains(text(),'Request date')]/following-sibling::span");
			javascriptScroll(ele,"Scroll to "+SOROrderName);
			String ReqDate = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-label//span[contains(text(),'Request date')]/following-sibling::span").getText();
			String Lab = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-label//span[contains(text(),'Lab')]/following-sibling::span").getText();
			String DiagGp = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-label//span[contains(text(),'Diagnostic group')]/following-sibling::span").getText();
			String Priority = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-label//span[contains(text(),'Priority')]/following-sibling::span").getText();
			String Status = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-label//span[contains(text(),'Status')]/following-sibling::span").getText();
			MobileElement expand = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-item//ion-icon[contains(@class,'expand-button')]");
			jsclick(expand, "Click on expand buttond for "+SOROrderName);
			MobileElement eleCopyTo = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Copy to')]/following-sibling::span");
			javascriptScroll(eleCopyTo, "Scroll to Copy to of "+SOROrderName);
			String CopyTo = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Copy to')]/following-sibling::span").getText();
			String OrdProv = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Order provider')]/following-sibling::span").getText();
			String ReqBy = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Requested by')]/following-sibling::span").getText();
			String ReqOn = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Requested on')]/following-sibling::span").getText();
			String ClinicalNotes = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Clinical notes')]/following-sibling::span").getText();
			
			AssertStringEquals(ReqDate,SORReqDate,"Requested Date for "+SOROrderName);
			AssertStringEquals(Lab,SORLab,"Lab for "+SOROrderName);
			AssertStringEquals(DiagGp,SORDiagGp,"Diagnostic Group for "+SOROrderName);
			AssertStringEquals(Priority,SORPriority,"Priority for "+SOROrderName);
			AssertStringEquals(Status,SORStatus,"Status for "+SOROrderName);
			AssertStringEquals(CopyTo,SORCopyTo,"Copy To for "+SOROrderName);
			AssertStringEquals(OrdProv,SOROrdProv,"Order Provider for "+SOROrderName);
			AssertStringEquals(ReqBy,SORReqBy,"Requested By for "+SOROrderName);
			AssertStringEquals(ReqOn,SORReqOn,"Requested On for "+SOROrderName);
			AssertStringEquals(ClinicalNotes,SORClinicalNotes,"Clinical Notes for "+SOROrderName);
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void VerifyHaematologyResult(String SORResultName,String SORBadge,String SORLab,String SORDiagGp,String SORSamplePerfDate) throws InterruptedException {
		try {
			MobileElement indicator = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/following-sibling::span/ion-icon");
			String indiName = getIconName(indicator, "Abnormility Icon of "+SORResultName);
			String Badge = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/parent::h2/ion-badge").getText();
			String Lab = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/parent::h2/following-sibling::div//span[contains(text(),'Lab')]/following-sibling::span").getText();
			String DiagGp = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/parent::h2/following-sibling::div//span[contains(text(),'Diagnostic group')]/following-sibling::span").getText();
			String SamplePerfDate = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/parent::h2/following-sibling::div//span[contains(text(),'Sample performed date')]/following-sibling::span").getText();
			MobileElement ele = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header//ion-icon[contains(@class,'expand')]");
			jsclick(ele, "Expand record");
			Thread.sleep(2000);
			MobileElement element = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[text()='Lab']/following-sibling::span");
			javascriptScroll(element, "Scroll to bottom");
			String Lab2 = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[text()='Lab']/following-sibling::span").getText();
			String DiagGp2 = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[text()='Diagnostic group']/following-sibling::span").getText();
		//	javascriptScroll(ele, "Scroll to start");
			
			AssertStringEquals(indiName,"warning","Abnormility Icon for "+SORResultName);
			AssertStringEquals(Badge,SORBadge,"Badge for "+SORResultName);
			AssertStringEquals(Lab,SORLab,"Lab for "+SORResultName);
			AssertStringEquals(DiagGp,SORDiagGp,"Diagnostic Group for "+SORResultName);
			AssertStringEquals(SamplePerfDate,SORSamplePerfDate,"Sample Performed Date for "+SORResultName);
			AssertStringEquals(Lab2,SORLab,"Lab2 for "+SORResultName);
			AssertStringEquals(DiagGp2,SORDiagGp,"Diagnostic2 Group for "+SORResultName);
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void VerifyNormalResultItemListView(String SORResultName,String SORResultItem, String SORValue,String SORUnit,String SORRange) {
		try {
			String ResValue = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//div[text()='"+SORResultItem+"']/following-sibling::div[@class='value']/span[1]").getText();
			String ResUnit = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//div[text()='"+SORResultItem+"']/following-sibling::div[@class='value']/span[2]").getText();
			String ResRange = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//div[text()='"+SORResultItem+"']/following-sibling::div[@class='range']").getText();
			
			AssertStringEquals(ResValue, SORValue, "Value of "+SORResultItem);
			AssertStringEquals(ResUnit, SORUnit, "Unit of "+SORResultItem);
			AssertStringEquals(ResRange, "[ "+SORRange+"]", "Range of "+SORResultItem);
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
		
	}
	
	public void VerifyAbnormalResultItemListView(String SORResultName,String SORResultItem, String SORValue,String SORUnit,String SORRange, String SORIndicator) throws InterruptedException {
		try {
			String ResValue = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//div[text()='"+SORResultItem+"']/following-sibling::div[@class='value']/span[1]").getText();
			String ResUnit = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//div[text()='"+SORResultItem+"']/following-sibling::div[@class='value']/span[2]").getText();
			String ResRange = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//div[text()='"+SORResultItem+"']/following-sibling::div[@class='range']").getText();
			MobileElement ResIndicator = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//div[text()='"+SORResultItem+"']/following-sibling::div[@class='indicator']/img");
			String ResultIndicator = getIconSrc(ResIndicator, "Indicator of "+SORResultItem);
			
			AssertStringEquals(ResValue, SORValue, "Value of "+SORResultItem);
			AssertStringEquals(ResUnit, SORUnit, "Unit of "+SORResultItem);
			AssertStringEquals(ResRange, "[ "+SORRange+"]", "Range of "+SORResultItem);
			AssertStringContains(ResultIndicator, SORIndicator, "Indicator of "+SORResultItem);
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
		
	}
	
	public void VerifyAbnormalResultItemTabView(String SORResultName,String SORResultItem, String SORValue,String SORUnit,String SORRange, String SORIndicator) throws InterruptedException {
		try {
			String ResValue = "";
			String Indicator = "";
			String AbnIndicator = "";
			String ResUnit = driver.findElementByXPath("//div[contains(text(),'"+SORResultName+"')]/parent::div/following-sibling::div//div[text()='"+SORResultItem+"']/following-sibling::div/span[@class='unit']").getText();
			String ResRange = driver.findElementByXPath("//div[contains(text(),'"+SORResultName+"')]/parent::div/following-sibling::div//div[text()='"+SORResultItem+"']/following-sibling::div/span[@class='range']").getText();
			
			if(SORResultItem.equals("WBC")) {
				ResValue = driver.findElementByXPath("(//dxc-result-table//div[@class='text-overflow'])[1]").getText();
				MobileElement ele = driver.findElementByXPath("(//dxc-result-table//img)[1]");
				Indicator = getIconSrc(ele, "Indicator Icon");
				MobileElement abn = driver.findElementByXPath("(//dxc-result-table//ion-icon)[1]");
				AbnIndicator = getIconName(abn, "Abnormility Indicator Icon");
				
			}else if(SORResultItem.equals("Hgb")) {
				ResValue = driver.findElementByXPath("(//dxc-result-table//div[@class='text-overflow'])[3]").getText();
				MobileElement ele = driver.findElementByXPath("(//dxc-result-table//img)[2]");
				Indicator = getIconSrc(ele, "Indicator Icon");
				MobileElement abn = driver.findElementByXPath("(//dxc-result-table//ion-icon)[2]");
				AbnIndicator = getIconName(abn, "Abnormility Indicator Icon");
			}else if(SORResultItem.equals("MCV")) {
				ResValue = driver.findElementByXPath("(//dxc-result-table//div[@class='text-overflow'])[4]").getText();
				MobileElement ele = driver.findElementByXPath("(//dxc-result-table//img)[3]");
				Indicator = getIconSrc(ele, "Indicator Icon");
				MobileElement abn = driver.findElementByXPath("(//dxc-result-table//ion-icon)[3]");
				AbnIndicator = getIconName(abn, "Abnormility Indicator Icon");
			}	
			
			AssertStringEquals(ResValue, SORValue, "Value of "+SORResultItem);
			AssertStringEquals(ResUnit, SORUnit, "Unit of "+SORResultItem);
			AssertStringEquals(ResRange, "[ "+SORRange+"]", "Range of "+SORResultItem);
			AssertStringContains(Indicator, SORIndicator, "Indicator of "+SORResultItem);
			AssertStringEquals(AbnIndicator, "warning", "AbnIndicator Indicator of "+SORResultItem);
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
		
	}
	
	public void VerifyNormalResultItemTabView(String SORResultName,String SORResultItem, String SORValue,String SORUnit,String SORRange) throws InterruptedException {
		try {
			
			String ResUnit = driver.findElementByXPath("//div[contains(text(),'"+SORResultName+"')]/parent::div/following-sibling::div//div[text()='"+SORResultItem+"']/following-sibling::div/span[@class='unit']").getText();
			String ResRange = driver.findElementByXPath("//div[contains(text(),'"+SORResultName+"')]/parent::div/following-sibling::div//div[text()='"+SORResultItem+"']/following-sibling::div/span[@class='range']").getText();
			String ResValue = driver.findElementByXPath("(//dxc-result-table//div[@class='text-overflow'])[2]").getText();
			
			AssertStringEquals(ResValue, SORValue, "Value of "+SORResultItem);
			AssertStringEquals(ResUnit, SORUnit, "Unit of "+SORResultItem);
			AssertStringEquals(ResRange, "[ "+SORRange+"]", "Range of "+SORResultItem);
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}	
	}
	
	public void VerifyCheckMark(String SORResultName) throws InterruptedException {
		try {
			MobileElement ele = driver.findElementByXPath("(//span[contains(text(),'"+SORResultName+"')]/following-sibling::span/ion-icon)[2]");
			String ReadIcon = getIconName(ele, "Read Icon");
			
			AssertStringEquals(ReadIcon, "checkmark-circle", "Read Icon of "+SORResultName);
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
		
	}
	
	public void VerifyResultTabView(String SORResultName,String SORSamplePerfDate) throws InterruptedException {
		try {
			
			String ResName = driver.findElementByXPath("//div[@class='text-overflow header']").getText();
			String ResPerfDate = driver.findElementByXPath("//ion-col[contains(@class,'datetime')]").getText();
			
			AssertStringEquals(ResName, SORResultName, "Result name in Tabular view");
			AssertStringEquals(ResPerfDate, SORSamplePerfDate, "Result Performed date time in Tabular view");
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
		
	}

	public void VerifyHaematologyTextTabView(String SORResultItem,String SORLab,String SORDiagGp,String SORSamplePerfDate) throws InterruptedException {
		try {
			MobileElement ele = null;
			if(SORResultItem.equals("Hct")) {
				ele = driver.findElementByXPath("(//span[contains(@class,'mdi-card')])[1]");
			}else if(SORResultItem.equals("Others Specify")){
				ele = driver.findElementByXPath("(//span[contains(@class,'mdi-card')])[2]");
			}else if(SORResultItem.equals("Film")){
				ele = driver.findElementByXPath("(//span[contains(@class,'mdi-card')])[3]");
			}else if(SORResultItem.equals("Processed By")){
				ele = driver.findElementByXPath("(//span[contains(@class,'mdi-card')])[4]");
			}else if(SORResultItem.equals("Checked By")){
				ele = driver.findElementByXPath("(//span[contains(@class,'mdi-card')])[5]");
			}
			jsclick(ele, "Click on Textual pop up for "+SORResultItem);
			String ResItem = driver.findElementByXPath("(//dxc-result-popup//ion-col)[1]").getText();
			String StatusBadge = driver.findElementByXPath("//ion-content//ion-badge").getText();
			String Lab = driver.findElementByXPath("//ion-content//span[text()='Lab']/following-sibling::span").getText();
			String DiagGp = driver.findElementByXPath("//ion-content//span[text()='Diagnostic group']/following-sibling::span").getText();
			MobileElement Abnormality = driver.findElementByXPath("//ion-content//span[text()='Abnormal status']/following-sibling::ion-icon");
			String AbnormalIcon = getIconName(Abnormality, "Abnormality Icon for "+SORResultItem);
			String SamplPerfDate = driver.findElementByXPath("//ion-content//span[text()='Sample performed date']/following-sibling::span").getText();
			
			AssertStringEquals(ResItem.trim(), SORResultItem, "Result item in pop up");
			AssertStringEquals(StatusBadge, "Final", "Status Badge in pop up");
			AssertStringEquals(Lab, SORLab, "Lab in pop up");
			AssertStringEquals(DiagGp, SORDiagGp, "Diagnostic group in pop up");
			AssertStringEquals(AbnormalIcon, "warning", "Abnormal Status in pop up");
			AssertStringEquals(SamplPerfDate, SORSamplePerfDate, "Sample performed date in pop up");
			
			MobileElement eleClose = driver.findElementByXPath("(//dxc-result-popup//ion-col)[2]");
			jsclick(eleClose, "Close pop up of "+SORResultItem);
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void VerifyTextValueListView(String SORResultName,String SORResultTextItem,String SORResultValue) {
		try {
			String ResultValue = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//div[text()='"+SORResultTextItem+"']/following-sibling::pre").getText();			
			AssertStringEquals(ResultValue, SORResultValue, "Value of "+SORResultTextItem);
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	
	public void VerifyReadHaematologyTextTabView(String SORResultItem,String SORLab,String SORDiagGp,String SORReadByUser, String SORSamplePerfDate) throws InterruptedException {
		try {
			MobileElement ele = null;
			if(SORResultItem.equals("Hct")) {
				ele = driver.findElementByXPath("(//span[contains(@class,'mdi-card')])[1]");
			}else if(SORResultItem.equals("Others Specify")){
				ele = driver.findElementByXPath("(//span[contains(@class,'mdi-card')])[2]");
			}else if(SORResultItem.equals("Film")){
				ele = driver.findElementByXPath("(//span[contains(@class,'mdi-card')])[3]");
			}else if(SORResultItem.equals("Processed By")){
				ele = driver.findElementByXPath("(//span[contains(@class,'mdi-card')])[4]");
			}else if(SORResultItem.equals("Checked By")){
				ele = driver.findElementByXPath("(//span[contains(@class,'mdi-card')])[5]");
			}
			jsclick(ele, "Click on Textual pop up for "+SORResultItem);
			String ResItem = driver.findElementByXPath("(//dxc-result-popup//ion-col)[1]").getText();
			String StatusBadge = driver.findElementByXPath("//ion-content//ion-badge").getText();
			String Lab = driver.findElementByXPath("//ion-content//span[text()='Lab']/following-sibling::span").getText();
			String DiagGp = driver.findElementByXPath("//ion-content//span[text()='Diagnostic group']/following-sibling::span").getText();
			MobileElement Abnormality = driver.findElementByXPath("//ion-content//span[text()='Abnormal status']/following-sibling::ion-icon");
			String AbnormalIcon = getIconName(Abnormality, "Abnormality Icon for "+SORResultItem);
			String SamplPerfDate = driver.findElementByXPath("//ion-content//span[text()='Sample performed date']/following-sibling::span").getText();
			String ReadBy = driver.findElementByXPath("//ion-content//span[text()='Read by']/following-sibling::span").getText();
			
			
			AssertStringEquals(ResItem.trim(), SORResultItem, "Result item in pop up");
			AssertStringEquals(StatusBadge, "Final", "Status Badge in pop up");
			AssertStringEquals(Lab, SORLab, "Lab in pop up");
			AssertStringEquals(DiagGp, SORDiagGp, "Diagnostic group in pop up");
			AssertStringEquals(AbnormalIcon, "warning", "Abnormal Status in pop up");
			AssertStringEquals(SamplPerfDate, SORSamplePerfDate, "Sample performed date in pop up");
			AssertStringEquals(ReadBy, SORReadByUser, "Read By User in pop up");
			
			MobileElement eleClose = driver.findElementByXPath("(//dxc-result-popup//ion-col)[2]");
			jsclick(eleClose, "Close pop up of "+SORResultItem);
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
    public void scrollMaxRange(MobileElement ionRange)
    {
       try {

           // JavaScript to set the value of ion-range
           JavascriptExecutor js = (JavascriptExecutor) driver;
           
           String jsScript = "let ionRange = arguments[0];" +
                   "let currentValue = 0;" +
                   "let maxValue = 5;" +
                   "let interval = setInterval(() => {" +
                   "  ionRange.value = currentValue;" +
                   "  ionRange.dispatchEvent(new Event('input'));" + // Trigger input event
                   "  ionRange.dispatchEvent(new Event('change'));" + // Trigger change event
                   "  if (currentValue >= maxValue) { clearInterval(interval); }" +
                   "  currentValue += 1;" + // Increment by 1
                   "}, 500);"; // Adjust interval time as needed

           // Execute JavaScript to drag the slider
           js.executeScript(jsScript, ionRange);

           System.out.println("Slider dragged to maximum value of 5");
       }
       catch (InvalidElementStateException e) {
 			reportStep("The element could not be clicked"+e, "FAIL");
 		} catch (WebDriverException e) {
 			reportStep("The element is not available"+e, "FAIL");
       }
    }
    
    public void AdvSearchNSelectPatient(String Surname, String Forename, String Gender, String DoB, String HRN) throws InterruptedException {

		try {

			if (!Gender.equalsIgnoreCase("Male") && !Gender.equalsIgnoreCase("Female")) {

				SearchNSelectPatient(HRN);

			}

			else

			{

				MobileElement expandSearchOption = driver.findElementByXPath("//ion-icon[@name='chevron-forward-outline']");

				jsclick(expandSearchOption,"Expand Search");

				Thread.sleep(1000);

				MobileElement inputSurname = driver.findElementByXPath("//input[@placeholder='Surname']");

				inputSurname.sendKeys(Surname);

				MobileElement inputForename = driver.findElementByXPath("//input[@placeholder='Forename']");

				inputForename.sendKeys(Forename);

				MobileElement GenderMale = driver.findElementByXPath("//ion-radio[@value='male']");

				MobileElement GenderFemale = driver.findElementByXPath("//ion-radio[@value='female']");

				MobileElement inputDoB = driver.findElementByXPath("//input[@placeholder='DD/MMM/YYYY']");

				if(Gender.equalsIgnoreCase("Male"))

				{

					jsclick(GenderMale,"Click Male");

				}

				else if(Gender.equalsIgnoreCase("Female"))

				{

					jsclick(GenderFemale,"Click Female");

				}

				inputDoB.sendKeys(DoB.replaceAll("-", "/"));		

				MobileElement clickSearch = driver.findElementByXPath("//ion-button[contains(text(),'Search')]");

				jsclick(clickSearch,"Click Search");

				Thread.sleep(8000);

				MobileElement sltpat = (MobileElement)driver.findElementByXPath("//div[@id=\"dxc-list-item-"+HRN+"\"]//h2");

				javascriptScroll(sltpat, "Patient record");

				sltpat.click();

				Thread.sleep(2000);

				takeSnap();

				reportStep("Patient: "+HRN+" is searched and clicked", HRN);

			}

  		}catch (InvalidElementStateException e) {

  			reportStep("The element could not be clicked", "FAIL");

  		} catch (WebDriverException e) {

  			reportStep("The element is not available", "FAIL");

  		}

	}
 
public void VerifySelectedFauxTab(String Fauxtab) throws InterruptedException {

		try {

			MobileElement ele;

            ele = driver.findElementByXPath("//ion-segment-button[contains(@class,'segment-button-checked')]");

			String selectedTab = getText(ele, "Actual selected faux tab");

			if(selectedTab.toLowerCase().contains(Fauxtab.toLowerCase())) {

				reportStep("Actual and expected selected Fauxtab are equal - "+selectedTab, "PASS");

			}else {

				reportStep("Actual and expected selected Fauxtab are not equal. Actual - "+selectedTab+". But expected Fauxtab to be selected is "+Fauxtab, "FAIL");

			}

		}catch (InvalidElementStateException e) {

  			reportStep("The element could not be clicked", "FAIL");

  		} catch (WebDriverException e) {

  			reportStep("The element is not available", "FAIL");

  		}

	}
 
public int getResultsCount(){
    List<MobileElement> allOptions = driver.findElements(By.xpath("//dxc-result-list-item"));
    int valueresult1=allOptions.size();
    //String result=String.valueOf(valueresult1);
    System.out.println(valueresult1);
    return valueresult1;
}

}
