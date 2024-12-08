package com.dedalus.capages;

import java.util.List;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProceduresPage extends CAProjectMethods {
	
	public ProceduresPage(AppiumDriver<MobileElement> driver, ExtentTest test) {
		this.test = test; 
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}	
	
	@FindBy(xpath = "//ion-toggle")
	public MobileElement toggle;
	
	//@FindBy(how = How.XPATH, using = ("//ion-label[contains(text(),'Planned')]"))
	@FindBy(how = How.XPATH, using = ("//ion-label[contains(text(),'Planned')]/parent::ion-segment-button"))
	public MobileElement PlanFauxTab;
	
	@FindBy(how = How.XPATH, using = ("//ion-label[contains(text(),'Planned')]"))
	public MobileElement clickComplFauxTab;
	
	@FindBy(xpath = "//dxc-procedure-list-item//ion-icon[contains(@class,'expand')]")
	public MobileElement ExpandProcRecord;

	@FindBy(how = How.XPATH, using = ("(//p[contains(text(),'Procedures')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[1]"))
	public MobileElement summaryProcName1;
	
	@FindBy(how = How.XPATH, using = ("(//p[contains(text(),'Procedures')]/ancestor::div[@class='header']/following-sibling::div//p[contains(@class,'title')])[2]"))
	public MobileElement summaryProcName2;
	
	@FindBy(how = How.XPATH, using = ("//p[contains(text(),'Procedures')]/parent::div/following-sibling::div/p"))
	public MobileElement summaryCount;
	
	// Planned Procedure
	@FindBy(xpath = "//dxc-procedure-list-item//ion-label/h2/span")
	public MobileElement PlanProcName;
	
	@FindBy(xpath = "//h2/span/following-sibling::ion-badge")
	public MobileElement PlanProcStatus;
	
	@FindBy(xpath = "//span[contains(text(),'Planned date')]/following-sibling::span")
	public MobileElement PlanProcPlannedDate;
	
	@FindBy(xpath = "//span[contains(text(),'Surgeon')]/following-sibling::span")
	public MobileElement PlanProcSurgeon;
	
	@FindBy(xpath = "//span[contains(text(),'Priority')]/following-sibling::span")
	public MobileElement PlanProcPriority;
	
	@FindBy(xpath = "//span[contains(text(),'Recorded on')]/following-sibling::span")
	public MobileElement PlanProcRecordedOn;
	
	@FindBy(xpath = "//span[contains(text(),'Service Type')]/following-sibling::span")
	public MobileElement PlanProcServiceType;
	
	@FindBy(xpath = "//div[contains(text(),'COMMENTS')]/following-sibling::div/span")
	public MobileElement PlanProcComments;
	
	@FindBy(xpath = "//span[contains(text(),'Site')]/following-sibling::span")
	public MobileElement PlanProcSite;
	
	@FindBy(xpath = "//span[contains(text(),'Laterality')]/following-sibling::span")
	public MobileElement PlanProcLaterality;
	
	@FindBy(xpath = "//span[contains(text(),'Reason')]/following-sibling::span")
	public MobileElement PlanProcReason;
	
	
	

	// Completed Procedure
	@FindBy(xpath = "//dxc-procedure-list-item//ion-label/h2/span")
	public MobileElement CompletedProcName;
	
	@FindBy(xpath = "//h2/span/following-sibling::ion-badge")
	public MobileElement CompletedProcStatus;
	
	@FindBy(xpath = "//span[contains(text(),'Site')]/following-sibling::span")
	public MobileElement CompletedProcSite;
	
	@FindBy(xpath = "//span[contains(text(),'Laterality')]/following-sibling::span")
	public MobileElement CompletedProcLaterality;
	
	@FindBy(xpath = "//span[contains(text(),'Performed on')]/following-sibling::span")
	public MobileElement CompletedProcPerformedOn;
	
	@FindBy(xpath = "//span[contains(text(),'Performed by')]/following-sibling::span")
	public MobileElement CompletedProcPerformedBy;
	
	@FindBy(xpath = "//span[contains(text(),'Body site/Laterality')]/following-sibling::span")
	public MobileElement CompletedProcBSLaterality;
	
	@FindBy(xpath = "//span[contains(text(),'Method')]/following-sibling::span")
	public MobileElement CompletedProcMethod;
	
	@FindBy(xpath = "//span[contains(text(),'Direct Devices')]/following-sibling::span")
	public MobileElement CompletedProcDirectDev;
	
	@FindBy(xpath = "//span[contains(text(),'Service point')]/following-sibling::span")
	public MobileElement CompletedProcServPt;
	
	@FindBy(xpath = "//span[contains(text(),'Recorded by')]/following-sibling::span")
	public MobileElement CompletedProcRecordedBy;
	
	@FindBy(xpath = "//span[contains(text(),'Recorded on')]/following-sibling::span")
	public MobileElement CompletedProcRecordedOn;
	
	@FindBy(xpath = "//div[contains(text(),'COMMENTS')]/following-sibling::div/span")
	public MobileElement CompletedProcComments;
	
	
				
		
		
		
		
	
	
	@FindBy(how = How.XPATH, using = ("(//ion-label/h2/span)[3]"))
	public MobileElement clickProc1record;

		
	public int getProcedureCount() throws InterruptedException {
		//List<WebElement> procCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge");
		List<MobileElement> procCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Recorded')]");
		int ProcCnt = procCnt.size();
		takeSnap();
		return ProcCnt;
	}
	
	public int getPLProcedureCount() throws InterruptedException {
		List<MobileElement> procCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Booked')]");
		int ProcCnt = procCnt.size();
		takeSnap();
		return ProcCnt;
	}
	
	
	public void VerifyStrikeOutProcedure(String Name) {
		try {
			 List<MobileElement> ele = driver.findElementsByXPath("//dxc-procedure-list-item//ion-label/h2/span");
	         int eleresult = ele.size();
	         for(int i=1;i<=eleresult;i++) {
	          	String val = driver.findElementByXPath("(//dxc-procedure-list-item//ion-label/h2/span)["+i+"]").getText();
	           	if(val == Name) {
	           		reportStep("Strikeout Procedure is visible in CA App", "FAIL");
	           	}
	          }
	          reportStep("Strikeout Procedure is not visible in CA App", "PASS");
	          takeSnap();
		} catch (InvalidElementStateException e) {
			reportStep("Invalid Element State Exception "+e, "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception "+e, "FAIL");
		}
	}


	@FindBy(how = How.XPATH, using = ("//div[contains(text(),'procedures.reason')]/following-sibling::div/span"))
	public MobileElement proc2Reason;

	@FindBy(how = How.XPATH, using = "//div[@class='tab-btn nonselected-tab']//span[contains(text(),'Procedures')]")
	public MobileElement selectProcedures;
	
	// need to chk
	
	
	
	
	
	@FindBy(how = How.XPATH, using = ("//p[contains(text(),'Procedures')]/parent::div//ion-icon"))
	public MobileElement summaryIcon;
	
	@FindBy(how = How.XPATH, using = ("//ion-grid//span[contains(@class,'mdi-voice')]/following-sibling::span"))
	public MobileElement bannerCount; //no ion-icon tag is available in banner.
	
	@FindBy(how = How.XPATH, using = ("//ion-col//ion-icon[@name='md-more']"))
	public MobileElement morePatDetails;
	
	@FindBy(how = How.XPATH, using = "//ion-icon[@name='menu']")
	public MobileElement clickMenu;
	
	@FindBy(how = How.XPATH, using = "//div[@class='tab-btn nonselected-tab']//span[contains(text(),'Summary')]")
	public MobileElement selectSummary;
	
	
}


