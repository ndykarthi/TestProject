package com.dedalus.genericappmethods_windows;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
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
import com.dedalus.utilities.DataBaseConnect;

import io.appium.java_client.MobileElement;




public class CAProjectMethods extends GenericAppMethods implements DataBaseConnect {

	public String dataSheetName;
	public String dataSheetFileName;
	public String sheetName;
	public ExtentTest testSuite;

	String patID = prop.getProperty("PatientID");
	String UserFN = prop.getProperty("UserForeName");
	String UserSN = prop.getProperty("UserSurName");
	String serviceURL = prop.getProperty("serviceURL");
	String OSMUserFN = prop.getProperty("OSMUserForeName");
	String OSMUserSN = prop.getProperty("OSMUserSurName");
	String OSMpatID = prop.getProperty("OSMPatientID");
	String sPostAPIPath = prop.getProperty("PostAPIPath");

	@Parameters({"testSuiteName"})
	@BeforeSuite
	public void beforeSuite(String testSuiteName){
		startResult(testSuiteName);
	}

	@BeforeClass()
	public void beforeClass() throws FileNotFoundException, IOException {
		startTestModule(testCaseName, testDescription);
	}

	/*
	 * @Parameters({"applicationName"})
	 *
	 * @BeforeClass public void init(String applicationName) {
	 * this.applicationName = applicationName; // this.patientID = patientID; }
	 */

	@BeforeMethod
	public void beforeMethod() throws FileNotFoundException, IOException, AWTException, InterruptedException {
		test = startTestCase(nodes);
		test.assignCategory(category);
		test.assignAuthor(authors);
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
	public void afterMethod() throws FileNotFoundException, IOException {
		// driver.close();
	}
/*
	@DataProvider(name = "fetchData")
	public Object[][] getData() {

		return DataInputProvider.getSheet(dataSheetName);

	}

	@DataProvider(name = "fetchDataSheet")
	public Object[][] getDataOne() {

		return DataInputProvider.getSheet(dataSheetFileName, dataSheetName);

	}
*/
	@AfterSuite
	public void afterSuite() {
		endResult();
	}

	@AfterTest
	public void afterTest() {
	}

	public Connection dataBaseConnection() throws ClassNotFoundException, SQLException {
		Class.forName(sJdbcDriver);
		Connection conn = DriverManager.getConnection(sSqlServer, sUserName, sPassword);
		return conn;
	}

	// SQL Query passed as argument and the corresponding result set would be
	// returned on executing the query passed
	public HashMap getDatabaseResult(String sqlStr, Connection conn, String columnNameOne, String columnNameTwo)
			throws ClassNotFoundException, SQLException {
		Statement st = conn.createStatement();
		ResultSet result = st.executeQuery(sqlStr);
		String columnValue = null;
		String key = null;
		String value = null;
		// List<String> valueList = new ArrayList<String>();
		HashMap map = new HashMap();
		while (result.next()) {
			key = result.getString(columnNameOne);
			value = result.getString(columnNameTwo);

			map.put(key, value);

		}

		// System.out.println(result.next());
		return map;
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

	public String getTermText(String ConceptCode) throws ClassNotFoundException, SQLException {
		ResultSet ConceptCodeSQLQuery = getDatabaseResult(
				"select TermText from term where TRMTPCode = 'Display_text' and ConceptCodeOID in (select OID from ConceptCode where ConceptCode = '"
						+ ConceptCode + "')");
		String termText = ConceptCodeSQLQuery.getString("TermText");
		return termText;

	}



	
	@FindBy(how = How.XPATH, using = "//ion-icon[@name='close']")
	public WebElement CloseButton;
	
	//@FindBy(how = How.XPATH, using = "//ion-menu-button[@class='md button ion-activatable ion-focusable hydrated']")
	@FindBy(how = How.XPATH, using = "//ion-menu-button")
	public WebElement ClickMenuButton;

	public void ClickMenuButton() throws InterruptedException {
		Thread.sleep(2000);
		javascriptClick(ClickMenuButton);
		takeSnap();
	}

	// @FindBy(how=How.XPATH,using="//ion-label[contains(text(),'My
	// Specialties')]/parent::div")
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Specialties')]")
	public WebElement ClickMySpeciality;

	public void ClickMySpeciality() throws InterruptedException {
		Thread.sleep(2000);
		actionClick(ClickMySpeciality);
		takeSnap();
	}
	
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Wards')]")
	public WebElement ClickMyWards;
	public void ClickMyWards() throws InterruptedException {
		Thread.sleep(5000);
		actionClick(ClickMyWards);
		takeSnap();
	}
	
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Care Providers')]")
	public WebElement ClickMyCareProviders;
	public void ClickMyCareProviders() throws InterruptedException {
		Thread.sleep(5000);
		actionClick(ClickMyCareProviders);
		takeSnap();
	}
	
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Worklist Overview')]")
	public WebElement ClickMyWorklistOverview;

	public void ClickMyWorklistOverview() throws InterruptedException {
		Thread.sleep(5000);
		actionClick(ClickMyWorklistOverview);
		takeSnap();
	}

	
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'Search')]")
	  public WebElement Search;

	// @FindBy(how=How.XPATH,using="//ion-label[contains(text(),'My
	// Specialties')]/parent::div/ancestor::button/parent::div/child::div/button//ion-label")
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Specialties')]/parent::div/ancestor::button/parent::div/child::div/button//ion-label")

	public WebElement ClickSelectedSpeciality;

	public void ClickaddedSpeciality(String SORUserSplty) throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//ion-label[@class='sc-ion-label-md-h sc-ion-label-md-s md hydrated'][contains(text(),'"+SORUserSplty+"')]"));
		Thread.sleep(3000);
		actionClick(ele);
		takeSnap();
	}
	
	public void ClickaddedSpeciality() throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//ion-label[@class='sc-ion-label-md-h sc-ion-label-md-s md hydrated'][contains(text(),'CARDIOLOGY ')]"));
		Thread.sleep(3000);
		actionClick(ele);
		takeSnap();
	}
	
	public void ClickaddedMyWards() throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//ion-label[contains(text(),'My Wards')]//following::ion-label[1]"));
		Thread.sleep(5000);
		actionClick(ele);
		takeSnap();
	}
	
	public void ClickaddedMyCareProviders() throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//ion-label[contains(text(),'My Care Providers')]//following::ion-label[1]"));
		Thread.sleep(5000);
		actionClick(ele);
		takeSnap();
	}

	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Patient Identifier']")
	public WebElement SearchPatient;

	public void SearchPatient(String patientid) throws InterruptedException {
		try {
			Thread.sleep(2000);
			WebElement srchPas = driver.findElementByXPath("//input[@placeholder='Patient Identifier']");
			jsclick(srchPas, "Search Patient");
			srchPas.clear();
			srchPas.sendKeys("");
			srchPas.sendKeys(patientid);
			Thread.sleep(5000);
			takeSnap();
			reportStep("Patient: "+patientid+" is searched", "PASS");
  		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}

	public void SearchPatientByName(String patientname) throws InterruptedException {
		try {
			Thread.sleep(2000);
			WebElement srchPas = driver.findElementByXPath("//input[@placeholder='Name Search']");
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
	
	//@FindBy(how = How.XPATH, using = "(//ion-button[@class='md button button-clear button-has-icon-only ion-activatable ion-focusable hydrated'])[2]")
	@FindBy(how = How.XPATH, using = "//ion-icon[@name='filter-sharp']")
	public WebElement FilterButtonOnPatientList;
	
	@FindBy(how = How.XPATH, using = "//span[text()='OK']")
	public WebElement OKButton;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Ward')]")
	public WebElement ClickWard;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Care Provider')]")
	public WebElement CareProvider;
	
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Wards')]")
	public WebElement ClickMyWard;
	
	@FindBy(how = How.XPATH, using = "//ion-label[contains(text(),'My Care Provider')]")
	public WebElement CareMyCaresProvider;
	
	@FindBy(how = How.XPATH, using = "//h2//following::span[@class='mdi mdi-file-outline mergediconone']//preceding::h2[1]")
	public WebElement MedDischargedIcon;
	

	
	
	public void SearchMyWard(String ward) throws InterruptedException {
		Thread.sleep(2000);
		// javascriptClick(SearchSpeciality);
		SearchSpeciality.sendKeys(ward);
	}
	
	public void SearchCareProvider(String CareProvider) throws InterruptedException {
		Thread.sleep(2000);
		// javascriptClick(SearchSpeciality);
		SearchSpeciality.sendKeys(CareProvider);
	}

	public void SelectPatient(String patientname) throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//h2[contains(text(),'" + patientname + "')]"));
		Thread.sleep(2000);
		javascriptClick(ele);
		takeSnap();
	}

	public void ClickMoreButtonOnPatient(String patientname) throws InterruptedException {
		WebElement ele = driver
				.findElement(By.xpath("//h2[contains(text(),'" + patientname + "')]//preceding::div[1]"));
		Thread.sleep(6000);
		ele.click();
		//javascriptClick(ele);
		takeSnap();
	}
	
	public void ClickFavoutiteIconOnPatient(String patientname) throws InterruptedException {
	WebElement ele = driver.findElement(By.xpath("//h2[contains(text(),'" + patientname + "')]//following::ion-icon[@aria-label='star outline'][1]"));
		Thread.sleep(6000);
		//ele.click();
		javascriptClick(ele);
		takeSnap();
	}
	
	public void VerifyMedicallyDischargedIcon(String patientname) throws InterruptedException {
		    try {
		    	//WebElement ele = driver
					//	.findElement(By.xpath("//h2[text()='"+patientname+"']//following::div[@class='pats-flex-conatiner'][1]//div[@class='pat-list-icons']//span[@class='mdi mdi-file-outline mergediconone']"));
		        WebElement ele = driver.findElement(By.xpath("//h2[text()='"+patientname+"']//following::div[@class='pats-flex-conatiner'][1]//span[contains(@class,'mergediconone')]"));
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
	public void VerifyMedicallyDischargedIconNotDisplayedForAdmitted(String patientname) throws InterruptedException {
	    try {
	    	List <WebElement> ele = driver
					.findElements(By.xpath("//h2[text()='"+patientname+"']//following::div[@class='pats-flex-conatiner'][1]//div[@class='pat-list-icons']//span[@class='mdi mdi-file-outline mergediconone']"));
	      int val = ele.size();
	      if (val==0) {
	        reportStep("Medically discharged icon is not displayed for Admitted Patient", "PASS");
	      } else {
	        reportStep("Medically discharged icon is displayed for Admitted Patient", "FAIL");
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
	 
	    	List<WebElement> ele = driver
					.findElements(By.xpath("//h2[contains(text(),'" + patientname + "')]//following::span[@class='mdi mdi-file-outline mergediconone']"));
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
	

	
	
	public void SelectMyWard(String ward) throws InterruptedException {
		Thread.sleep(4000);
		WebElement ele = driver.findElement(
				By.xpath("//ion-label[@class='sizename sc-ion-label-md-h sc-ion-label-md-s md hydrated'][text()='"
						+ ward + "']/following::ion-icon[1]"));
		Thread.sleep(6000);
		javascriptClick(ele);
		takeSnap();
	}
	
	public void SelecCareProvider(String CareProvider) throws InterruptedException {
		Thread.sleep(6000);
		WebElement ele = driver.findElement(
				By.xpath("//ion-label[@class='sizename sc-ion-label-md-h sc-ion-label-md-s md hydrated'][text()='"
						+ CareProvider + "']/following::ion-icon[1]"));
		Thread.sleep(6000);
		actionClick(ele);
		takeSnap();
	}


	public void searchNselectPatient(String patientname) throws InterruptedException {
		String field = "Patient Name";
		WebElement patName = driver.findElement(By.xpath("//input[@placeholder='Name Search']"));
		explicitWait(patName);
		type(patName, patientname, field);
		patName.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		WebElement elePAt = driver.findElement(By.xpath("//div/h2[contains(text(),'" + patientname + "')]"));
		actionClick(elePAt);
		Thread.sleep(3000);
		takeSnap();
	}

	public void clickEncounterToggle() throws InterruptedException {
		//WebElement clickEncToggle = driver.findElement(By.xpath("//ion-toggle[@role='checkbox']"));
		WebElement clickEncToggle = driver.findElement(By.xpath("//ion-toggle[@role='switch']"));
		actionClick(clickEncToggle);
		Thread.sleep(2000);
		takeSnap();
	}
@FindBy(how=How.XPATH,using="//ion-button[contains(text(),'Add Specialty')]")
        public WebElement AddSpeciality;
 

        public void AddSpeciality() throws InterruptedException {
            Thread.sleep(1000);
          //  javascriptClick(AddSpeciality);
            AddSpeciality.click();
            takeSnap();
        }
        @FindBy(how=How.XPATH,using="//input[@class='searchbar-input sc-ion-searchbar-md']")
        public WebElement SearchSpeciality;
 
 public void SearchSpeciality(String speciality) throws InterruptedException {
            Thread.sleep(2000);
            //javascriptClick(SearchSpeciality);
            SearchSpeciality.sendKeys(speciality);
        }

	public void clickSearch() throws InterruptedException {
		Thread.sleep(3000);
		javascriptClick(Search);
	}

	public static String datetimeconvPAApp(String APPdate) throws ParseException {

		DateFormat inputFormat4 = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		DateFormat outputFormat4 = new SimpleDateFormat("yyyyMMddHHmm");
		outputFormat4.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
		java.util.Date date = inputFormat4.parse(APPdate);
		String APPdate1 = outputFormat4.format(date);
		System.out.println(APPdate1);
		return APPdate1;

	}
	
	public void SelectSpeciality(String speciality) throws InterruptedException {
            Thread.sleep(4000);
                     WebElement ele=driver.findElement(By.xpath("//ion-label[@class='sizename sc-ion-label-md-h sc-ion-label-md-s md hydrated'][text()='"+speciality+"']/following::ion-icon[1]"));
            Thread.sleep(6000);
                        javascriptClick(ele);
                        takeSnap();
                }
/*
	
	  public void VerifyFavouritePatient(String PatientName,String Encounter) {
      	int count = driver.findElementsByXPath("//h2").size();
  		System.out.println(count);
  		for(int i = 1; i<=count; i++) {
  			String patName = driver.findElementByXPath("(//h2)["+i+"]").getText();
  			if(patName.contains(PatientName)) {
  				reportStep(PatientName+ " is displayed in Worklist Overview under "+Encounter+" tab", "PASS");
  				break;
  			}
  		}
      } */
	  
	  public void VerifyNoFavouritePatient(String PatientName,String Encounter) {
      	int count = driver.findElementsByXPath("//h2").size();
  		System.out.println(count);
  		for(int i = 1; i<=count; i++) {
  			String patName = driver.findElementByXPath("(//h2)["+i+"]").getText();
  			if(patName.contains(PatientName)) {
  				reportStep(PatientName+ " is displayed in Worklist Overview under "+Encounter+" tab", "FAIL");
  				break;
  			}
  			else {
  				reportStep(PatientName+ " is not displayed in Worklist Overview under "+Encounter+" tab", "PASS");
  			}
  		}
      }
	  
	  public void VerifyToastMsg() {
      	/*String txt = driver.findElement(By.xpath("//ion-toast[contains(@class,'toast-error')]")).getAttribute("innerHTML");
      	System.out.println(txt);
			if(txt.contains("Unable to add in to My Worklist as no active encounter is available")) {
				reportStep("A pop with text "+txt+" is dispalyed as expected", "PASS");
			}
			else {
				reportStep("A pop with text "+txt+" is not dispalyed", "FAIL");
			}*/
      	
      	boolean exists = driver.findElement(By.xpath("//ion-toast[contains(@class,'toast-error')]")).isDisplayed();
      	String txt = "Unable to add in to My Worklist as no active encounter is available";
      	if(exists) {
      		reportStep("A pop with text "+txt+" is dispalyed as expected", "PASS");
			}
			else {
				reportStep("A pop with text "+txt+" is not dispalyed", "FAIL");
			}
      }
	  
	  public void SelectMyWorklistOverviewAddForm(String patientname) throws InterruptedException {
      	try {
      		WebElement elePAt = driver.findElement(By.xpath("//div/h2[contains(text(),'"+patientname+"')]/ancestor::ion-item/following-sibling::ion-item"));
      		actionClick(elePAt);
      		Thread.sleep(8000);
      		reportStep("Associate latest form instance for "+patientname+" is clicked", "PASS", false);
      		takeSnap();
  		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}	
  	}  
	  
	  public String GetFormNameMyWorkListOverview(String patientname) throws InterruptedException {
  	 	String elePAt = driver.findElement(By.xpath("//ion-label//h2[contains(text(),'"+patientname+"')]//ancestor::ion-item/following-sibling::ion-item//h2/span")).getText();
  		return elePAt;	
  }
  
	  public void CloseForm(String patientname) throws InterruptedException {
      	WebElement elePat = driver.findElement(By.xpath("//ion-label//h2[contains(text(),'"+patientname+"')]//ancestor::ion-item/following-sibling::ion-item//ion-icon[@name='close-circle']"));
      	actionClick(elePat);
      	Thread.sleep(3000);
      	driver.findElement(By.xpath("//button/span[contains(text(),'Remove')]")).click();
      	Thread.sleep(3000);
      }
	  
	   public void SelectFilteredPatient() throws InterruptedException {
				WebElement ele=driver.findElement(By.xpath("//div/h2"));
				Thread.sleep(6000);
				javascriptClick(ele);
			    takeSnap();			
		}
	  
	
	public void actionClickIcon(WebElement ele,String text) {
		//String text = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			//text = ele.getText();
			Actions action = new Actions(driver);
			action.moveToElement(ele).click().build().perform();
			reportStep(text + "  is clicked.", "PASS");
			takeSnap();
		} catch (InvalidElementStateException e) {
			reportStep("The element: " + text + " could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("The element "+ele+ "is not available", "FAIL");
		}
			}
	
	public void SearchNSelectPatient(String PASID) throws InterruptedException {
		try {
			WebElement ele = driver.findElementByXPath("//input[@aria-label='search text']");
			jsclick(ele,"Search Text");
			Thread.sleep(2000);
			ele.clear();
			ele.sendKeys(PASID);
			Thread.sleep(8000);
			WebElement sltpat = (WebElement)driver.findElementByXPath("//div[@id=\"dxc-list-item-"+PASID+"\"]//h2");
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
	
	public void jsclick(WebElement Element,String field) throws InterruptedException {
	    try {
	    	JavascriptExecutor js = driver;
	        js.executeScript("arguments[0].scrollTop = arguments[1];", Element, 100);
	        js.executeScript("arguments[0].click();", Element);
	        reportStep(field+" is clicked", "PASS");
	    } catch (InvalidElementStateException e) {
			reportStep(field+"  could not be found", "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while clicking", "FAIL");
		}
	    
	  }
	
	public void VerifySelectedTab(String tab,String Device) throws InterruptedException {
		try {
			WebElement ele;
			if((Device.contains("iPad")) || (Device.contains("Tab")) || (Device.contains("Windows"))) {
				 ele = driver.findElementByXPath("//ion-list[contains(@class,'scroll')]/div[not(contains(@class,'nonselected'))]");
			}else {
				 ele = driver.findElementByXPath("//div[contains(@class,'dataloadTab')]/div[not(contains(@class,'nonselected'))]");
			}		
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
	
	public void VerifyPatientDetails_SearchListWebPAS(String Surname,String Forename,String Title,String DOB, String HRN, String Address) throws InterruptedException {
		try {
			String PatName = driver.findElementByXPath("//ion-item-sliding//h2").getText();
			String PatDOB = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'DoB')]/parent::span").getText();
			String PatHRN = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'HRN')]/parent::span").getText();
		//	String PatAdd = driver.findElementByXPath("//ion-item-sliding//p[2]/span").getText();
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
			WebElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
			jsclick(PatDetMoreIcon, "Patient Details More Icon");
			String PatName = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'Title')]").getText();
		//	String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'DoB')]/following-sibling::span").getText();
		//	String PatHRN = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'HRN')]/following-sibling::span").getText();
		//	String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Gender')]/following-sibling::span").getText();
			String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row//ion-row//span[contains(text(),'DoB')]/following-sibling::span").getText();
			String PatHRN = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row//ion-row//span[contains(text(),'HRN')]/following-sibling::span").getText();
			String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row//ion-row//span[contains(text(),'Gender')]/following-sibling::span").getText();			
			
			WebElement closeicon = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='close']");
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
			WebElement elePAt = driver.findElementByXPath("//ion-label[contains(text(),'"+Name+"')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='star-outline']");
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
			WebElement elePAt = driver.findElementByXPath("//ion-label[contains(text(),'"+menu+"')]/parent::ion-item/following-sibling::div//ion-label[contains(text(),'"+Name+"')]");
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
			WebElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
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
			
			WebElement closeicon = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='close']");
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
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void SelectPatientViaName(String PatName) throws InterruptedException {
		try {
			
			WebElement sltpat = (WebElement)driver.findElementByXPath("//dxc-list-items//h2[contains(text(),'"+PatName+"')]");
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
	
	public void VerifyPatientDetails_SearchListMoreDeceasedWebPAS(String Surname,String Forename,String Title, String Gender,String DOB, String HRN,String DateDied,String Age) throws InterruptedException {
		try {
			WebElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
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
			
			WebElement closeicon = driver.findElementByXPath("//dxc-more-list//ion-icon[contains(@name,'close')]");
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
			WebElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
			jsclick(PatDetMoreIcon, "Patient Details More Icon");
			String PatName = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'Title')]").getText();
			String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'DoB')]/following-sibling::span").getText();
			String PatHRN = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'HRN')]/following-sibling::span").getText();
			String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Gender')]/following-sibling::span").getText();
			
			WebElement closeicon = driver.findElementByXPath("//dxc-more-list//ion-icon[contains(@name,'close')]");
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
			WebElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
			jsclick(PatDetMoreIcon, "Patient Details More Icon");
			String CP = driver.findElementByXPath("//span[contains(text(),'Care Provider Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'titleBold')]").getText();
			String Splty = driver.findElementByXPath("//span[contains(text(),'Care Provider Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Specialty')]/following-sibling::span").getText();
			String Location = driver.findElementByXPath("//span[contains(text(),'Care Provider Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(@class,'wardIcon')]/following-sibling::span").getText();
			String Site = driver.findElementByXPath("//span[contains(text(),'Care Provider Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Site')]/following-sibling::span").getText();
			String Ward = driver.findElementByXPath("//span[contains(text(),'Care Provider Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Ward')]/following-sibling::span").getText();
			String Bed = driver.findElementByXPath("//span[contains(text(),'Care Provider Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Bed')]/following-sibling::span").getText();
			WebElement closeicon = driver.findElementByXPath("//dxc-more-list//ion-icon[contains(@name,'close')]");
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
			WebElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
			jsclick(PatDetMoreIcon, "Patient Details More Icon");
			String VisitNo = driver.findElementByXPath("//span[contains(text(),'Encounter Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Visit No')]/following-sibling::span").getText();
			String AdmittedDate = driver.findElementByXPath("//span[contains(text(),'Encounter Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Admitted')]/following-sibling::span").getText();
		//	String ExpDiscDate = driver.findElementByXPath("//span[contains(text(),'Encounter Details')]/ancestor::ion-row[1]/following-sibling::div//span[contains(text(),'Expected Discharge')]/following-sibling::span").getText();
			AssertStringEquals(SORVisitNo, VisitNo, "Visit No in more Encounter Details pop up");
			AssertStringEquals(SORAdmittedDate, AdmittedDate, "Admitted Date Time in more Encounter Details pop up");
		//	AssertStringEquals(SORExpDiscDate, ExpDiscDate, "Expected Discharge Date in more Encounter Details pop up");
			WebElement closeicon = driver.findElementByXPath("//dxc-more-list//ion-icon[contains(@name,'close')]");
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
			WebElement elePAt = driver.findElementByXPath("//span[contains(text(),'"+HRN+"')]/ancestor::ion-row[3]/following-sibling::ion-row//ion-icon[@name='star-outline']");
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
			WebElement elePAt = driver.findElementByXPath("//span[contains(text(),'"+HRN+"')]/ancestor::ion-row[3]/following-sibling::ion-row//ion-icon[@name='star-outline']");
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
		WebElement elePAt = driver.findElementByXPath("//span[contains(text(),'"+HRN+"')]/ancestor::ion-row[3]/following-sibling::ion-row//ion-icon[@name='star']");
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
		WebElement srchPas = driver.findElementByXPath("//input[@placeholder='Patient Identifier']");
		jsclick(srchPas, "Search Patient");
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@placeholder='Surname']").sendKeys(Surname);
		driver.findElementByXPath("//input[@placeholder='Forename']").sendKeys(Forename);
		WebElement MaleGen = driver.findElementByXPath("//ion-radio[@value='male']");
		jsclick(MaleGen, "Male Gender");
		//driver.findElementByXPath("//ion-label[contains(text(),'Male')]").click();
		driver.findElementByXPath("//dxc-datepicker//input").sendKeys(DoB);
		WebElement SearchBtn = driver.findElementByXPath("//ion-icon[contains(@name,'checkbox')]");
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
		WebElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
		jsclick(PatDetMoreIcon, "Patient Details More Icon");
		Thread.sleep(5000);
		String PatAliasName = driver.findElementByXPath("(//span[contains(text(),'Alias Details')]/ancestor::ion-row/following-sibling::div//span)[2]").getText();
		AssertStringEquals(PatAliasName, AliasSurname+", "+AliasForename, "Patient Alias Name in Alias Details pop up");
		WebElement closeicon = driver.findElementByXPath("//dxc-more-list//ion-icon[contains(@name,'close')]");
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

		WebElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");

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

		WebElement closeicon = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='close']");

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


public void VerifyOrder(String SOROrderName,String SORReqDate,String SORLab,String SORDiagGp,String SORPriority,String SORStatus,String SORCopyTo,String SOROrdProv,String SORReqBy,String SORReqOn,String SORClinicalNotes) throws InterruptedException {
	try {
		WebElement ele = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-label//span[contains(text(),'Request date')]/following-sibling::span");
		javascriptScroll(ele,"Scroll to "+SOROrderName);
		String ReqDate = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-label//span[contains(text(),'Request date')]/following-sibling::span").getText();
		String Lab = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-label//span[contains(text(),'Lab')]/following-sibling::span").getText();
		String DiagGp = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-label//span[contains(text(),'Diagnostic group')]/following-sibling::span").getText();
		String Priority = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-label//span[contains(text(),'Priority')]/following-sibling::span").getText();
		String Status = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-label//span[contains(text(),'Status')]/following-sibling::span").getText();
		WebElement expand = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-item//ion-icon[contains(@class,'expand-button')]");
		jsclick(expand, "Click on expand buttond for "+SOROrderName);
		WebElement eleCopyTo = driver.findElementByXPath("//span[contains(text(),'"+SOROrderName+"')]//ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Copy to')]/following-sibling::span");
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
		WebElement indicator = driver.findElementByXPath("//span[contains(text(),'Hae')]/following-sibling::span/ion-icon");
		String indiName = getIconName(indicator, "Abnormility Icon of "+SORResultName);
		String Badge = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/parent::h2/ion-badge").getText();
		String Lab = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/parent::h2/following-sibling::div//span[contains(text(),'Lab')]/following-sibling::span").getText();
		String DiagGp = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/parent::h2/following-sibling::div//span[contains(text(),'Diagnostic group')]/following-sibling::span").getText();
		String SamplePerfDate = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/parent::h2/following-sibling::div//span[contains(text(),'Sample performed date')]/following-sibling::span").getText();
		WebElement ele = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header//ion-icon[contains(@class,'expand')]");
		jsclick(ele, "Expand record");
		Thread.sleep(2000);
		WebElement element = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[text()='Lab']/following-sibling::span");
		javascriptScroll(element, "Scroll to bottom");
		String Lab2 = driver.findElementByXPath("//span[contains(text(),'Haematology')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[text()='Lab']/following-sibling::span").getText();
		String DiagGp2 = driver.findElementByXPath("//span[contains(text(),'Haematology')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[text()='Diagnostic group']/following-sibling::span").getText();
		
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
		WebElement ResIndicator = driver.findElementByXPath("//span[contains(text(),'"+SORResultName+"')]/ancestor::ion-card-header/following-sibling::ion-card-content//div[text()='"+SORResultItem+"']/following-sibling::div[@class='indicator']/img");
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
		WebElement ele = null;
		WebElement abn = null;
		String ResValue = null;
		String ResUnit = driver.findElementByXPath("//div[contains(text(),'"+SORResultName+"')]/parent::div/following-sibling::div//div[text()='"+SORResultItem+"']/following-sibling::div/span[@class='unit']").getText();
		String ResRange = driver.findElementByXPath("//div[contains(text(),'"+SORResultName+"')]/parent::div/following-sibling::div//div[text()='"+SORResultItem+"']/following-sibling::div/span[@class='range']").getText();
		
		if(SORResultItem.equals("WBC")) {
			ResValue = driver.findElementByXPath("(//dxc-result-table//div[@class='text-overflow'])[1]").getText();
			ele = driver.findElementByXPath("(//dxc-result-table//img)[1]");	
			abn = driver.findElementByXPath("(//dxc-result-table//ion-icon)[1]");
		}else if(SORResultItem.equals("Hgb")) {
			ResValue = driver.findElementByXPath("(//dxc-result-table//div[@class='text-overflow'])[3]").getText();
			ele = driver.findElementByXPath("(//dxc-result-table//img)[2]");
			abn = driver.findElementByXPath("(//dxc-result-table//ion-icon)[2]");
		}else if(SORResultItem.equals("MCV")) {
			ResValue = driver.findElementByXPath("(//dxc-result-table//div[@class='text-overflow'])[4]").getText();
			ele = driver.findElementByXPath("(//dxc-result-table//img)[3]");
			abn = driver.findElementByXPath("(//dxc-result-table//ion-icon)[3]");
		}
		
		String Indicator = getIconSrc(ele, "Indicator Icon");
		String AbnIndicator = getIconName(abn, "Abnormility Indicator Icon");
		
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
		WebElement ele = driver.findElementByXPath("(//span[contains(text(),'"+SORResultName+"')]/following-sibling::span/ion-icon)[2]");
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
		WebElement ele = null;
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
		WebElement Abnormality = driver.findElementByXPath("//ion-content//span[text()='Abnormal status']/following-sibling::ion-icon");
		String AbnormalIcon = getIconName(Abnormality, "Abnormality Icon for "+SORResultItem);
		String SamplPerfDate = driver.findElementByXPath("//ion-content//span[text()='Sample performed date']/following-sibling::span").getText();
		
		AssertStringEquals(ResItem.trim(), SORResultItem, "Result item in pop up");
		AssertStringEquals(StatusBadge, "Final", "Status Badge in pop up");
		AssertStringEquals(Lab, SORLab, "Lab in pop up");
		AssertStringEquals(DiagGp, SORDiagGp, "Diagnostic group in pop up");
		AssertStringEquals(AbnormalIcon, "warning", "Abnormal Status in pop up");
		AssertStringEquals(SamplPerfDate, SORSamplePerfDate, "Sample performed date in pop up");
		
		WebElement eleClose = driver.findElementByXPath("(//dxc-result-popup//ion-col)[2]");
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

public void VerifyReadHaematologyTextTabView(String SORResultItem,String SORLab,String SORDiagGp, String SORReadByUser,String SORSamplePerfDate) throws InterruptedException {
	try {
		WebElement ele = null;
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
		WebElement Abnormality = driver.findElementByXPath("//ion-content//span[text()='Abnormal status']/following-sibling::ion-icon");
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
		
		WebElement eleClose = driver.findElementByXPath("(//dxc-result-popup//ion-col)[2]");
		jsclick(eleClose, "Close pop up of "+SORResultItem);
		
	}catch (InvalidElementStateException e) {
			reportStep("The element could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("The element is not available", "FAIL");
		}
}

public void VerifyPatientDetails_SearchListMore(String Surname,String Forename, String Gender,String DOB, String NHS, String PASID) throws InterruptedException {
	try {
		WebElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
		jsclick(PatDetMoreIcon, "Patient Details More Icon");
		String PatName = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'Title')]").getText();
		String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Born')]/following-sibling::span").getText();
		String PatNHS = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'NHS')]/following-sibling::span").getText();
		String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Gender')]/following-sibling::span").getText();
		String PatPASID = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'PAS')]/following-sibling::span").getText();
		WebElement closeicon = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='close']");
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

public void scrollMaxRange(WebElement ionRange) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    
    String jsScript = "let ionRange = arguments[0];" +
            "let maxValue = 5;" + // Set the maximum range value here
            "let currentValue = 0;" +
            "let step = 1;" + // Adjust the increment as needed
            "let interval = setInterval(() => {" +
            "  ionRange.value = currentValue;" + 
            "  ionRange.dispatchEvent(new Event('input', { bubbles: true }));" + 
            "  ionRange.dispatchEvent(new Event('change', { bubbles: true }));" + 
            "  if (currentValue >= maxValue) { clearInterval(interval); }" +
            "  currentValue += step;" +
            "}, 500);"; // Adjust interval time if necessary

    // Execute the JavaScript to trigger the slider change
    js.executeScript(jsScript, ionRange);
}
 

public void AdvSearchNSelectPatient(String Surname, String Forename, String Gender, String DoB, String HRN) throws InterruptedException {

	try {

		if((Gender.equals("Transgender")) || (Gender.equals("Intersex"))) {
	
			SearchNSelectPatient(HRN);

		}

		else

		{

			WebElement expandSearchOption = driver.findElementByXPath("//ion-icon[@name='chevron-forward-outline']");

			jsclick(expandSearchOption,"Expand Search");

			Thread.sleep(1000);

			WebElement inputSurname = driver.findElementByXPath("//input[@placeholder='Surname']");

			inputSurname.sendKeys(Surname);

			WebElement inputForename = driver.findElementByXPath("//input[@placeholder='Forename']");

			inputForename.sendKeys(Forename);

			WebElement GenderMale = driver.findElementByXPath("//ion-radio[@value='male']");

			WebElement GenderFemale = driver.findElementByXPath("//ion-radio[@value='female']");

			WebElement inputDoB = driver.findElementByXPath("//input[@placeholder='DD/MMM/YYYY']");

			if(Gender.equalsIgnoreCase("Male"))

			{

				jsclick(GenderMale,"Click Male");

			}

			else if(Gender.equalsIgnoreCase("Female"))

			{

				jsclick(GenderFemale,"Click Female");

			}

			inputDoB.sendKeys(DoB.replaceAll("-", "/"));		

			WebElement clickSearch = driver.findElementByXPath("//ion-button[contains(text(),'Search')]");

			jsclick(clickSearch,"Click Search");

			Thread.sleep(8000);

			WebElement sltpat = (WebElement)driver.findElementByXPath("//div[@id=\"dxc-list-item-"+HRN+"\"]//h2");

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

		WebElement ele;

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
    List<WebElement> allOptions = driver.findElements(By.xpath("//dxc-result-list-item"));
    int valueresult1=allOptions.size();
    //String result=String.valueOf(valueresult1);
    System.out.println(valueresult1);
    return valueresult1;
}


}
