package com.dedalus.capages;

import java.util.List;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AlertsPage extends CAProjectMethods {
	
	public AlertsPage(AppiumDriver<MobileElement> driver,ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@FindBy(xpath = "//dxc-alert-list-item")
	public List<MobileElement> getAlertCount;
	
	@FindBy(xpath = "//ion-toggle")
	public MobileElement toggle;
	
	@FindBy(xpath = "//ion-icon[@class='tab-button-icon md hydrated']/following::span[text()='Alerts']")
	public MobileElement AlertsTab;

	@FindBy(xpath = "//dxc-alert-list-item[1]//ion-item//h2//span")
	public MobileElement AlertName1;

	@FindBy(xpath = "(//ion-item//ion-label/h2/ion-badge)[1]")
	public MobileElement AlertStatus1;

	@FindBy(xpath = "(//span[contains(text(),'Type')])[1]/following-sibling::span")
	public MobileElement AlertType1;

	@FindBy(xpath = "(//span[contains(text(),'Is significant')])[1]/following-sibling::span")
	public MobileElement IsSignificant1;

	@FindBy(xpath = "(//span[contains(text(),'Severity')])[1]/following-sibling::span")
	public MobileElement AlertSeverity1;

	@FindBy(xpath = "(//span[contains(text(),'Information source:')]/following-sibling::span)[1]")
	public MobileElement AlertInfoSource1;

	@FindBy(xpath = "(//span[contains(text(),'Onset')])[1]/following-sibling::span")
	public MobileElement AlertOnset1;
	
	@FindBy(xpath = "(//ion-icon[contains(@name,'chevron-forward')])[2]")
	public MobileElement ExpandAlert1;
	
	@FindBy(xpath = "(//span[contains(text(),'Scope:')])[1]/following-sibling::span")
	public MobileElement AlertScope1;

	@FindBy(xpath = "(//span[contains(text(),'Recorded date and time:')])[1]/following-sibling::span")
	public MobileElement AlertRecordedDateTime1;

	@FindBy(xpath = "(//span[contains(text(),'Recorded by:')])/following-sibling::span")
	public MobileElement AlertRecordedBy1;
	
	@FindBy(xpath = "(//span[contains(text(),'Description')])/following-sibling::span")
	public MobileElement AlertDesc1;
	
	@FindBy(xpath = "(//span[contains(text(),'Expected conclusion')])/following-sibling::span")
	public MobileElement AlertExpConcl;
	
	@FindBy(xpath = "(//span[contains(text(),'Modified date')])/following-sibling::span")
	public MobileElement AlertModDtm;
	
	@FindBy(xpath = "(//span[contains(text(),'Review date')])/following-sibling::span")
	public MobileElement AlertRevDtm;
	

	
	public int getAlertActiveCount() throws InterruptedException {
		int AlertCnt = 0;
		try {
			List<MobileElement> alertCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Active')]");
			AlertCnt = alertCnt.size();
			takeSnap();
			reportStep("Active Alert Count: "+AlertCnt, "PASS");
		}catch (InvalidElementStateException e) {
		      reportStep("The element is not available", "FAIL");
		    } catch (WebDriverException e) {
		      reportStep("The element is not available", "FAIL");
		    }
		return AlertCnt;	
	}
	
	public int getAlertINActiveCount() throws InterruptedException {
		int totalCnt = 0;
		try {
			List<MobileElement> alertCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Inactive')]");
			List<MobileElement> alertCnt1 = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Entered-in-error')]");
			int AlertCnt = alertCnt.size();
			int AlertCnt1 = alertCnt1.size();
			totalCnt = AlertCnt + AlertCnt1;
			takeSnap();
			reportStep("InActive Alert Count: "+totalCnt, "PASS");
		}catch (InvalidElementStateException e) {
		      reportStep("The element is not available", "FAIL");
		    } catch (WebDriverException e) {
		      reportStep("The element is not available", "FAIL");
		    }	
		return totalCnt;
	}
	
	// Inactive   - //ion-badge[contains(text(),'Inactive')]/parent::h2/following-sibling::div//span[contains(text(),'Severity')]/following-sibling::span
	@FindBy(xpath = "//h2/ion-badge[contains(text(),'Inactive')]/ancestor::ion-item//div[contains(@class,'expand')]")
	public MobileElement expandClosedRec;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/parent::h2/following-sibling::div//span[contains(text(),'Type')]/following-sibling::span")
	public MobileElement ClosedAlertType;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/parent::h2/following-sibling::div//span[contains(text(),'Is significant')]/following-sibling::span")
	public MobileElement ClosedAlertIsSign;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/parent::h2/following-sibling::div//span[contains(text(),'Severity')]/following-sibling::span")		
	public MobileElement ClosedAlertSev;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Information source')]/following-sibling::span")
	public MobileElement ClosedAlertInfoSrc;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/parent::h2/following-sibling::div//span[contains(text(),'Onset')]/following-sibling::span")
	public MobileElement ClosedAlertOnset;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Scope')]/following-sibling::span")
	public MobileElement ClosedAlertScope;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Description')]/following-sibling::span")
	public MobileElement ClosedAlertDesc;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Recorded date and time')]/following-sibling::span")
	public MobileElement ClosedAlertRecDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Recorded by')]/following-sibling::span")
	public MobileElement ClosedAlertRecBy;
	
	@FindBy(xpath = "//ion-card//h2//ion-badge[contains(text(),'Inactive')]/preceding-sibling::span")
	public MobileElement ClosedAlertName;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Expected conclusion')]/following-sibling::span")
	public MobileElement ClosedAlertExpConclDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Modified date')]/following-sibling::span")
	public MobileElement ClosedAlertModDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Closing Reason')]/following-sibling::span")
	public MobileElement ClosedAlertCloseRsn;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Status comments')]/following-sibling::span")
	public MobileElement ClosedAlertComments;
	
//	@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Review date')]/following-sibling::span")
//	public MobileElement ClosedAlertRevDate;
	
	
	
	// Strikeout - Entered-in-error
	@FindBy(xpath = "//h2/ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-item//div[contains(@class,'expand')]")
	public MobileElement expandStrikeOutRec; 
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Type')]/following-sibling::span")
	public MobileElement StrikedOutAlertType;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Is significant')]/following-sibling::span")
	public MobileElement StrikedOutAlertIsSign;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Severity')]/following-sibling::span")
	public MobileElement StrikedOutAlertSev;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Information source')]/following-sibling::span")
	public MobileElement StrikedOutAlertInfoSrc;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Onset')]/following-sibling::span")
	public MobileElement StrikedOutAlertOnset;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Scope')]/following-sibling::span")
	public MobileElement StrikedOutAlertScope;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Description')]/following-sibling::span")
	public MobileElement StrikedOutAlertDesc;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Recorded date and time')]/following-sibling::span")
	public MobileElement StrikedOutAlertRecDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Recorded by')]/following-sibling::span")
	public MobileElement StrikedOutAlertRecBy;
	
	@FindBy(xpath = "//ion-card//h2//ion-badge[contains(text(),'Entered-in-error')]/preceding-sibling::span")
	public MobileElement StrikedOutAlertName;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Expected conclusion date')]/following-sibling::span")
	public MobileElement StrikedOutAlertExpConclDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Review date')]/following-sibling::span")
	public MobileElement StrikedOutAlertRevDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Modified date')]/following-sibling::span")
	public MobileElement StrikedOutAlertModDtm;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Deletion Reason')]/following-sibling::span")
	public MobileElement StrikedOutAlertDeletionRsn;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Status comments')]/following-sibling::span")
	public MobileElement StrikedOutAlertComments;
	
	
	/*
	
		public AlertsPage validateAlertSortOrderRecordedDateTime() throws ParseException, InterruptedException {

			List<MobileElement> ele = driver.findElementsByXPath("//dxc-alert-list-item");
			for (int i = 1; i <= ele.size(); i++) {
				ClickExpandButton(i);
			}

			List<String> RecordedDateTimeL  = new ArrayList<String>();
			//actionClick(clickAlertsExpand);
			//actionClick(clickAlertsExpand1);

			List<MobileElement> elements = driver
	        .findElements(By.xpath("//span[contains(text(),'Recorded date and time:')]/following-sibling::span"));
			for (int i = 0; i < elements.size(); i++) {
	      String RecordedDateTimeL1 = elements.get(i).getText();

	      // RecordedDateTimeL.add(datetimeconvPAApp(elements.get(i).getText()));
			}

			RecordedDateTimeL.remove(3);
			RecordedDateTimeL.remove(2);
			boolean sorted = Ordering.natural().reverse().isOrdered(RecordedDateTimeL);
			System.out.println(sorted);
			if (sorted) {
				System.out.println("Alerts sort order displayed correctly based on Recorded Date time");
				reportStep("Alerts sort order displayed correctly based on Recorded Date time", "PASS");
			} else {
				System.out.println("Alerts sort order not displayed correctly based on Recorded Date time");
				reportStep("Alerts sort order not displayed correctly based on Recorded Date time", "FAIL");
			}
			return this;
		}

	  public AlertsPage validateAlertSortOrderRecordedDateTimeWithSameSeverity() throws ParseException, InterruptedException {

	    List<MobileElement> ele = driver.findElementsByXPath("//dxc-alert-list-item");
	    for (int i = 1; i <= ele.size() - 2; i++) {
	      ClickExpandButton(i);
	    }

	    List<String> RecordedDateTimeL = new ArrayList<String>();
	    // actionClick(clickAlertsExpand);
	    // actionClick(clickAlertsExpand1);

	    List<MobileElement> elements = driver.findElements(By.xpath("//span[contains(text(),'Recorded date and time:')]/following-sibling::span"));
	    for (int i = 0; i < elements.size() - 2; i++) {
	      String RecordedDateTimeL1 = elements.get(i).getText();
	      System.out.println(RecordedDateTimeL1);
	      String RecordedDateTimeL2 = datetimeconvPAApp(RecordedDateTimeL1);
	      RecordedDateTimeL.add(RecordedDateTimeL2);
	    }

	    boolean sorted = Ordering.natural().reverse().isOrdered(RecordedDateTimeL);
	    System.out.println(sorted);
	    if (sorted) {
	      System.out.println("Alerts sort order displayed correctly based on Recorded Date time");
	      reportStep("Alerts sort order displayed correctly based on Recorded Date time", "PASS");
	    } else {
	      System.out.println("Alerts sort order not displayed correctly based on Recorded Date time");
	      reportStep("Alerts sort order not displayed correctly based on Recorded Date time", "FAIL");
	    }
	    return this;
	  }


		public AlertsPage validateAlertSortOrderSeverity() throws ParseException {

			List<String> Severity  = new ArrayList<String>();
			List<MobileElement> elements = driver
					.findElements(By.xpath("//span[text()='Severity: ']/following-sibling::span"));
			for (int i = 0; i < elements.size(); i++) {
				//datetimeconvPAApp(elements.get(i).getText());
				Severity.add(elements.get(i).getText());
			}

			List<String> expectedlist = new ArrayList<String>();
			expectedlist.add("Mild to moderate");
			expectedlist.add("Fatal");
			expectedlist.add("Fatal");
			expectedlist.add("Mild");
			expectedlist.add("Severe");
			

			boolean testPassed = expectedlist.containsAll(Severity);
			System.out.println(testPassed);
			if (testPassed) {
				System.out.println("Alerts sort order displayed correctly based on Severity");
				reportStep("Alerts order sort displayed correctly based on Severity", "PASS");
			} else {
				System.out.println("Alerts sort order not displayed correctly based on Severity");
				reportStep("Alerts order sort not displayed correctly based on Severity", "FAIL");
			}
			return this;
		}

	  */
	  @FindBy(xpath = "//p[@class='title' and contains(text(),'Alerts')]")
		public MobileElement summaryAlertstext;
	
		@FindBy(xpath ="//ion-label[contains(text(),'Active')]/parent::ion-segment-button")
		public MobileElement activeFauxtab;
	 
		@FindBy(xpath ="//ion-label[contains(text(),'All')]/parent::ion-segment-button")
		public MobileElement allFauxtab;
	 
		@FindBy(xpath ="//ion-label[contains(text(),'Inactive')]/parent::ion-segment-button")
		public MobileElement inactiveFauxtab;
		
		// Web PAS
		@FindBy(xpath = "(//span[contains(text(),'Recorded by')])/following-sibling::span")
		public MobileElement AlertRecordedBy;
				
		@FindBy(xpath = "(//span[contains(text(),'Recorded on')])/following-sibling::span")
		public MobileElement AlertRecordedOn;
				
		@FindBy(xpath = "//span[contains(text(),'Initiating hospital')]/following-sibling::span")
		public MobileElement AlertInitHospital;
				
		@FindBy(xpath = "//span[contains(text(),'Requested by HCP')]/following-sibling::span")
		public MobileElement AlertRequestBy;
		
		@FindBy(xpath = "(//span[contains(text(),'Recorded on')])/following-sibling::span")
		public MobileElement AlertEndDate;
				
		@FindBy(xpath = "//span[contains(text(),'Site')]/following-sibling::span")
		public MobileElement AlertSite;
				
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Recorded on')]/following-sibling::span")
		public MobileElement ClosedAlertRecOn;
				
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Recorded by')]/following-sibling::span")
		public MobileElement ClosedAlertRecordedBy;
			
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Site')]/following-sibling::span")
		public MobileElement ClosedAlertSite;
				
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Initiating hospital')]/following-sibling::span")
		public MobileElement ClosedAlertInitHospital;
				
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Requested by HCP')]/following-sibling::span")
		public MobileElement ClosedAlertRequestBy;
				
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'End date')]/following-sibling::span")
		public MobileElement ClosedAlertEndDate;
				
		@FindBy(xpath = "//ion-badge[contains(text(),'Inactive')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Review date')]/following-sibling::span")
		public MobileElement ClosedAlertRevDate;
		
		@FindBy(xpath = "//p[@class='title spacing']")
		public MobileElement SummaryAlertName;

		@FindBy(xpath = "//span[@class='details'][1]/div")
		public MobileElement SummaryAlertType;

		@FindBy(xpath = "//span[@class='details'][2]/div")
		public MobileElement SummaryAlertOnsetDate;
		
}
