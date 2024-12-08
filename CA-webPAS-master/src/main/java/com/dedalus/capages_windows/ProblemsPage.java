package com.dedalus.capages_windows;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods_windows.CAProjectMethods;


public class ProblemsPage extends CAProjectMethods{

	JavascriptExecutor js = driver;

	public ProblemsPage(RemoteWebDriver driver, ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//ion-toggle/input")
	public WebElement toggle;

	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']")
	public WebElement summaryProblemTile;
	
	//@FindBy(xpath ="//span[contains(text(),'Problems')]/parent::div/ion-icon[contains(@name,'medical')]")
	//public WebElement clickProblemsTab;
	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Problems')]")
	public WebElement clickProblemsTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Active')]/parent::ion-segment-button")
	public WebElement clickActiveTab;

	@FindBy(xpath ="//ion-label[contains(text(),'All')]/parent::ion-segment-button")
	public WebElement clickAllTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Inactive')]/parent::ion-segment-button")
	public WebElement clickInactiveTab;

	@FindBy(xpath = "//p[contains(@class,'title') and contains(text(),'Problems')]/parent::div/following-sibling::div/p")
	public WebElement getsummaryProblemsCount;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]//p")
	public WebElement getsummaryActiveProblem1;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]//div[@class='left']/child::div/span[1]/div")
	public WebElement getsummaryActiveProblem1Type;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]//div[@class='left']/child::div/span[2]/div")
	public WebElement getsummaryActiveProblem1OnsetDate;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]//p")
	public WebElement getsummaryActiveProblem2;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[1]/div")
	public WebElement getsummaryActiveProblem2Type;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[2]/div")
	public WebElement getsummaryActiveProblem2OnsetDate;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]//p")
	public WebElement getsummaryActiveProblem3;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]/div[@class='left']/child::div/span[1]/div")
	public WebElement getsummaryActiveProblem3Type;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]/div[@class='left']/child::div/span[2]/div")
	public WebElement getsummaryActiveProblem3OnsetDate;
	
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-item//H2/span")
	public WebElement getActiveProblemName;
	
	//@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-item//span[text()='Type']/following-sibling::span")
	@FindBy(xpath  = "//ion-badge[text()='Active']/parent::h2/following-sibling::div/span[1]/span/span[2]")
	public WebElement getActiveProblemType;
	
	//@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-item//span[text()='Onset date']/following-sibling::span")
	@FindBy(xpath  = "//ion-badge[text()='Active']/parent::h2/following-sibling::div/span[2]/span/span[2]")
	public WebElement getActiveProblemOnsetDate;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Problem description:']/following-sibling::span")
	public WebElement getActiveProblemDescription;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Subtype:']/following-sibling::span")
	public WebElement getActiveProblemSubtype;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Verification:']/following-sibling::span")
	public WebElement getActiveProblemVerification;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Certainity:']/following-sibling::span")
	public WebElement getActiveProblemCertainity;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Severity:']/following-sibling::span")
	public WebElement getActiveProblemSeverity;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Scope:']/following-sibling::span")
	public WebElement getActiveProblemScope;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Abatement:']/following-sibling::span")
	public WebElement getActiveProblemAbatement;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Recorded by:']/following-sibling::span")
	public WebElement getActiveProblemRecordedBy;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Recorded on:']/following-sibling::span")
	public WebElement getActiveProblemRecordedOn;
	

	
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-item//H2/span")
	public WebElement getInactiveProblemName;
					 
	//@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-item//span[text()='Type']/following-sibling::span")
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/parent::h2/following-sibling::div/span[1]/span/span[2]")
	public WebElement getInactiveProblemType;
	
	//@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-item//span[text()='Onset date']/following-sibling::span")
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/parent::h2/following-sibling::div/span[2]/span/span[2]")
	public WebElement getInactiveProblemOnsetDate;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Problem description:']/following-sibling::span")
	public WebElement getInactiveProblemDescription;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Subtype:']/following-sibling::span")
	public WebElement getInactiveProblemSubtype;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Verification:']/following-sibling::span")
	public WebElement getInactiveProblemVerification;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Certainity:']/following-sibling::span")
	public WebElement getInactiveProblemCertainity;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Severity:']/following-sibling::span")
	public WebElement getInactiveProblemSeverity;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Scope:']/following-sibling::span")
	public WebElement getInactiveProblemScope;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Abatement:']/following-sibling::span")
	public WebElement getInactiveProblemAbatement;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Recorded by:']/following-sibling::span")
	public WebElement getInactiveProblemRecordedBy;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Recorded on:']/following-sibling::span")
	public WebElement getInactiveProblemRecordedOn;
	
	

	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-item//H2/span")
	public WebElement getStrikeoutProblemName;
	
	//@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-item//span[text()='Type']/following-sibling::span")
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/parent::h2/following-sibling::div/span[1]/span/span[2]")
	public WebElement getStrikeoutProblemType;
	
	//@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-item//span[text()='Onset date']/following-sibling::span")
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/parent::h2/following-sibling::div/span[2]/span/span[2]")
	public WebElement getStrikeoutProblemOnsetDate;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Problem description:']/following-sibling::span")
	public WebElement getStrikeoutProblemDescription;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Subtype:']/following-sibling::span")
	public WebElement getStrikeoutProblemSubtype;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Verification:']/following-sibling::span")
	public WebElement getStrikeoutProblemVerification;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Certainity:']/following-sibling::span")
	public WebElement getStrikeoutProblemCertainity;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Severity:']/following-sibling::span")
	public WebElement getStrikeoutProblemSeverity;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Scope:']/following-sibling::span")
	public WebElement getStrikeoutProblemScope;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Abatement:']/following-sibling::span")
	public WebElement getStrikeoutProblemAbatement;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Recorded by:']/following-sibling::span")
	public WebElement getStrikeoutProblemRecordedBy;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Recorded on:']/following-sibling::span")
	public WebElement getStrikeoutProblemRecordedOn;
	
	

	
	public int getProblemsCount() {
		List<WebElement> allOptions = driver.findElements(By.xpath("//dxc-issue-list-item//ion-item//ion-label/h2/span"));
		int result = allOptions.size();
		return result;
	}
	
	
	
	@FindBy(xpath  = "//*[@name='funnel']")
	public WebElement clickFilterIcon;
	

	@FindBy(xpath  = "//ion-select[contains(@aria-label,'Type ')]")
	public WebElement clickTypeFilter;
	
	
	
	@FindBy(xpath  = "//*[contains(text(),'Complications') and contains(@class,'alert-checkbox-label')]")
	public WebElement clickComplicationsFilter;
	
	@FindBy(xpath  = "//*[contains(text(),'Diagnosis') and contains(@class,'alert-checkbox-label')]")
	public WebElement clickDiagnosisFilter;
	
	@FindBy(xpath  = "//*[contains(text(),'Findings') and contains(@class,'alert-checkbox-label')]")
	public WebElement clickFindingsFilter;
	
	@FindBy(xpath  = "//*[contains(text(),'Presenting Complaints') and contains(@class,'alert-checkbox-label')]")
	public WebElement clickPresComplaintsFilter;
	
	@FindBy(xpath  = "//*[contains(text(),'Symptoms') and contains(@class,'alert-checkbox-label')]")
	public WebElement clickSymptomsFilter;
	
	//@FindBy(xpath  = "//*[coontains(text(),'OK')]/parent::button")
	@FindBy(xpath  = "//div[contains(@class,'alert-button-group')]//button[2]")
	public WebElement clickFilterOKbutton;
	
	@FindBy(xpath  = "//*[contains(@class,'filter')]/span[contains(text(),'Type (')]")
	public WebElement getFilterValue;
	
	
	//WebPas
	//Webpas
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-item//H2/span)[1]")
		public WebElement WPgetInactiveProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/parent::h2/following-sibling::div/span[1]/span/span[2])[1]")
		public WebElement WPgetInactiveProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/parent::h2/following-sibling::div/span[2]/span/span[2])[1]")
		public WebElement WPgetInactiveProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-card//ion-icon)[1]")
		public WebElement WPexpandCollapseInactiveProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[1]")
		public WebElement WPgetInactiveProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[1]")
		public WebElement WPgetInactiveProblemRecordedDate;
		
		
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-item//H2/span)[2]")
		public WebElement WPgetHistoryInactiveProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/parent::h2/following-sibling::div/span[1]/span/span[2])[2]")
		public WebElement WPgetHistoryInactiveProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/parent::h2/following-sibling::div/span[2]/span/span[2])[2]")
		public WebElement WPgetHistoryInactiveProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-card//ion-icon)[2]")
		public WebElement WPexpandCollapseHistoryInactiveProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[2]")
		public WebElement WPgetHistoryInactiveProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[2]")
		public WebElement WPgetHistoryInactiveProblemRecordedDate;
		

		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-item//H2/span)[1]")
		public WebElement WPgetResolvedProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/parent::h2/following-sibling::div/span[1]/span/span[2])[1]")
		public WebElement WPgetResolvedProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/parent::h2/following-sibling::div/span[2]/span/span[2])[1]")
		public WebElement WPgetResolvedProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-card//ion-icon)[1]")
		public WebElement WPexpandCollapseResolvedProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[1]")
		public WebElement WPgetResolvedProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[1]")
		public WebElement WPgetResolvedProblemRecordedDate;
		
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-item//H2/span)[2]")
		public WebElement WPgetHistoryResolvedProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/parent::h2/following-sibling::div/span[1]/span/span[2])[2]")
		public WebElement WPgetHistoryResolvedProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/parent::h2/following-sibling::div/span[2]/span/span[2])[2]")
		public WebElement WPgetHistoryResolvedProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-card//ion-icon)[2]")
		public WebElement WPexpandCollapseHistoryResolvedProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[2]")
		public WebElement WPgetHistoryResolvedProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[2]")
		public WebElement WPgetHistoryResolvedProblemRecordedDate;
		
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-item//H2/span)[1]")
		public WebElement WPgetActiveProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/parent::h2/following-sibling::div/span[1]/span/span[2])[1]")
		public WebElement WPgetActiveProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/parent::h2/following-sibling::div/span[2]/span/span[2])[1]")
		public WebElement WPgetActiveProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-card//ion-icon)[1]")
		public WebElement WPexpandCollapseActiveProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[1]")
		public WebElement WPgetActiveProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[1]")
		public WebElement WPgetActiveProblemRecordedDate;
		
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-item//H2/span)[2]")
		public WebElement WPgetHistoryActiveProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/parent::h2/following-sibling::div/span[1]/span/span[2])[2]")
		public WebElement WPgetHistoryActiveProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/parent::h2/following-sibling::div/span[2]/span/span[2])[2]")
		public WebElement WPgetHistoryActiveProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-card//ion-icon)[2]")
		public WebElement WPexpandCollapseHistoryActiveProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[2]")
		public WebElement WPgetHistoryActiveProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[2]")
		public WebElement WPgetHistoryActiveProblemRecordedDate;
		

		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-item//H2/span)[1]")
		public WebElement WPgetCurrentProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/parent::h2/following-sibling::div/span[1]/span/span[2])[1]")
		public WebElement WPgetCurrentProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/parent::h2/following-sibling::div/span[2]/span/span[2])[1]")
		public WebElement WPgetCurrentProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-card//ion-icon)[1]")
		public WebElement WPexpandCollapseCurrentProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[1]")
		public WebElement WPgetCurrentProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[1]")
		public WebElement WPgetCurrentProblemRecordedDate;
		
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-item//H2/span)[2]")
		public WebElement WPgetHistoryCurrentProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/parent::h2/following-sibling::div/span[1]/span/span[2])[2]")
		public WebElement WPgetHistoryCurrentProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/parent::h2/following-sibling::div/span[2]/span/span[2])[2]")
		public WebElement WPgetHistoryCurrentProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-card//ion-icon)[2]")
		public WebElement WPexpandCollapseHistoryCurrentProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[2]")
		public WebElement WPgetHistoryCurrentProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[2]")
		public WebElement WPgetHistoryCurrentProblemRecordedDate;
		
		
		
		
		@FindBy(xpath  = "//*[contains(text(),'Medical') and contains(@class,'alert-checkbox-label')]")
		public WebElement clickMedicalFilter;
		
		@FindBy(xpath  = "//*[contains(text(),'Surgical') and contains(@class,'alert-checkbox-label')]")
		public WebElement clickSurgicalFilter;
		
		@FindBy(xpath  = "//*[contains(text(),'Cardio-thoracic') and contains(@class,'alert-checkbox-label')]")
		public WebElement clickCardiothoracicFilter;

		@FindBy(xpath  = "//*[contains(text(),'Psychiatric') and contains(@class,'alert-checkbox-label')]")
		public WebElement clickPsychiatricFilter;

}
