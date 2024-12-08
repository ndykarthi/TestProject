package com.dedalus.capages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProblemsPage extends CAProjectMethods {
	JavascriptExecutor js = driver;

	public ProblemsPage(AppiumDriver<MobileElement> driver, ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	

	@FindBy(xpath = "//ion-toggle/input")
	public MobileElement toggle;

	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']")
	public MobileElement summaryProblemTile;
	
	//@FindBy(xpath ="//span[contains(text(),'Problems')]/parent::div/ion-icon[contains(@name,'medical')]")
	//public MobileElement clickProblemsTab;
	@FindBy(xpath = "//*[contains(@class,'tab-button') and contains(text(),'Problems')]")
	public MobileElement clickProblemsTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Active')]/parent::ion-segment-button")
	public MobileElement clickActiveTab;

	@FindBy(xpath ="//ion-label[contains(text(),'All')]/parent::ion-segment-button")
	public MobileElement clickAllTab;

	@FindBy(xpath ="//ion-label[contains(text(),'Inactive')]/parent::ion-segment-button")
	public MobileElement clickInactiveTab;

	@FindBy(xpath = "//p[contains(@class,'title') and contains(text(),'Problems')]/parent::div/following-sibling::div/p")
	public MobileElement getsummaryProblemsCount;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]//p")
	public MobileElement getsummaryActiveProblem1;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]//div[@class='left']/child::div/span[1]/div")
	public MobileElement getsummaryActiveProblem1Type;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[1]//div[@class='left']/child::div/span[2]/div")
	public MobileElement getsummaryActiveProblem1OnsetDate;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]//p")
	public MobileElement getsummaryActiveProblem2;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[1]/div")
	public MobileElement getsummaryActiveProblem2Type;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[2]/div[@class='left']/child::div/span[2]/div")
	public MobileElement getsummaryActiveProblem2OnsetDate;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]//p")
	public MobileElement getsummaryActiveProblem3;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]/div[@class='left']/child::div/span[1]/div")
	public MobileElement getsummaryActiveProblem3Type;
	
	@FindBy(xpath = "//p[contains(@class,'title') and text()='Problems']/ancestor::div[@class='left']/parent::div/following-sibling::div[@class='record-list']/child::div[3]/div[@class='left']/child::div/span[2]/div")
	public MobileElement getsummaryActiveProblem3OnsetDate;
	
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-item//H2/span")
	public MobileElement getActiveProblemName;
	
	//@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-item//span[text()='Type']/following-sibling::span")
	@FindBy(xpath  = "//ion-badge[text()='Active']/parent::h2/following-sibling::div/span[1]/span/span[2]")
	public MobileElement getActiveProblemType;
	
	//@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-item//span[text()='Onset date']/following-sibling::span")
	@FindBy(xpath  = "//ion-badge[text()='Active']/parent::h2/following-sibling::div/span[2]/span/span[2]")
	public MobileElement getActiveProblemOnsetDate;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Problem description:']/following-sibling::span")
	public MobileElement getActiveProblemDescription;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Subtype:']/following-sibling::span")
	public MobileElement getActiveProblemSubtype;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Verification:']/following-sibling::span")
	public MobileElement getActiveProblemVerification;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Certainity:']/following-sibling::span")
	public MobileElement getActiveProblemCertainity;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Severity:']/following-sibling::span")
	public MobileElement getActiveProblemSeverity;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Scope:']/following-sibling::span")
	public MobileElement getActiveProblemScope;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Abatement:']/following-sibling::span")
	public MobileElement getActiveProblemAbatement;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Recorded by:']/following-sibling::span")
	public MobileElement getActiveProblemRecordedBy;
	
	@FindBy(xpath  = "//ion-badge[text()='Active']/ancestor::ion-card//span[text()='Recorded on:']/following-sibling::span")
	public MobileElement getActiveProblemRecordedOn;
	

	
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-item//H2/span")
	public MobileElement getInactiveProblemName;
					 
	//@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-item//span[text()='Type']/following-sibling::span")
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/parent::h2/following-sibling::div/span[1]/span/span[2]")
	public MobileElement getInactiveProblemType;
	
	//@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-item//span[text()='Onset date']/following-sibling::span")
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/parent::h2/following-sibling::div/span[2]/span/span[2]")
	public MobileElement getInactiveProblemOnsetDate;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Problem description:']/following-sibling::span")
	public MobileElement getInactiveProblemDescription;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Subtype:']/following-sibling::span")
	public MobileElement getInactiveProblemSubtype;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Verification:']/following-sibling::span")
	public MobileElement getInactiveProblemVerification;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Certainity:']/following-sibling::span")
	public MobileElement getInactiveProblemCertainity;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Severity:']/following-sibling::span")
	public MobileElement getInactiveProblemSeverity;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Scope:']/following-sibling::span")
	public MobileElement getInactiveProblemScope;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Abatement:']/following-sibling::span")
	public MobileElement getInactiveProblemAbatement;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Recorded by:']/following-sibling::span")
	public MobileElement getInactiveProblemRecordedBy;
	
	@FindBy(xpath  = "//ion-badge[text()='Inactive']/ancestor::ion-card//span[text()='Recorded on:']/following-sibling::span")
	public MobileElement getInactiveProblemRecordedOn;
	
	

	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-item//H2/span")
	public MobileElement getStrikeoutProblemName;
	
	//@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-item//span[text()='Type']/following-sibling::span")
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/parent::h2/following-sibling::div/span[1]/span/span[2]")
	public MobileElement getStrikeoutProblemType;
	
	//@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-item//span[text()='Onset date']/following-sibling::span")
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/parent::h2/following-sibling::div/span[2]/span/span[2]")
	public MobileElement getStrikeoutProblemOnsetDate;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Problem description:']/following-sibling::span")
	public MobileElement getStrikeoutProblemDescription;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Subtype:']/following-sibling::span")
	public MobileElement getStrikeoutProblemSubtype;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Verification:']/following-sibling::span")
	public MobileElement getStrikeoutProblemVerification;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Certainity:']/following-sibling::span")
	public MobileElement getStrikeoutProblemCertainity;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Severity:']/following-sibling::span")
	public MobileElement getStrikeoutProblemSeverity;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Scope:']/following-sibling::span")
	public MobileElement getStrikeoutProblemScope;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Abatement:']/following-sibling::span")
	public MobileElement getStrikeoutProblemAbatement;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Recorded by:']/following-sibling::span")
	public MobileElement getStrikeoutProblemRecordedBy;
	
	@FindBy(xpath  = "//ion-badge[text()='Strikeout']/ancestor::ion-card//span[text()='Recorded on:']/following-sibling::span")
	public MobileElement getStrikeoutProblemRecordedOn;
	
	

	
	public int getProblemsCount() {
		List<MobileElement> allOptions = driver.findElements(By.xpath("//dxc-issue-list-item//ion-item//ion-label/h2/span"));
		int result = allOptions.size();
		return result;
	}
	
	
	
	@FindBy(xpath  = "//*[@name='funnel']")
	public MobileElement clickFilterIcon;
	

	@FindBy(xpath  = "//ion-select[contains(@aria-label,'Type ')]")
	public MobileElement clickTypeFilter;
	
	
	
	@FindBy(xpath  = "//*[contains(text(),'Complications') and contains(@class,'alert-checkbox-label')]")
	public MobileElement clickComplicationsFilter;
	
	@FindBy(xpath  = "//*[contains(text(),'Diagnosis') and contains(@class,'alert-checkbox-label')]")
	public MobileElement clickDiagnosisFilter;
	
	@FindBy(xpath  = "//*[contains(text(),'Findings') and contains(@class,'alert-checkbox-label')]")
	public MobileElement clickFindingsFilter;
	
	@FindBy(xpath  = "//*[contains(text(),'Presenting Complaints') and contains(@class,'alert-checkbox-label')]")
	public MobileElement clickPresComplaintsFilter;
	
	@FindBy(xpath  = "//*[contains(text(),'Symptoms') and contains(@class,'alert-checkbox-label')]")
	public MobileElement clickSymptomsFilter;
	
	//@FindBy(xpath  = "//*[coontains(text(),'OK')]/parent::button")
	@FindBy(xpath  = "//div[contains(@class,'alert-button-group')]//button[2]")
	public MobileElement clickFilterOKbutton;
	
	@FindBy(xpath  = "//*[contains(@class,'filter')]/span[contains(text(),'Type (')]")
	public MobileElement getFilterValue;
	
	
	//WebPas
	//Webpas
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-item//H2/span)[1]")
		public MobileElement WPgetInactiveProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/parent::h2/following-sibling::div/span[1]/span/span[2])[1]")
		public MobileElement WPgetInactiveProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/parent::h2/following-sibling::div/span[2]/span/span[2])[1]")
		public MobileElement WPgetInactiveProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-card//ion-icon)[1]")
		public MobileElement WPexpandCollapseInactiveProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[1]")
		public MobileElement WPgetInactiveProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[1]")
		public MobileElement WPgetInactiveProblemRecordedDate;
		
		
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-item//H2/span)[2]")
		public MobileElement WPgetHistoryInactiveProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/parent::h2/following-sibling::div/span[1]/span/span[2])[2]")
		public MobileElement WPgetHistoryInactiveProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/parent::h2/following-sibling::div/span[2]/span/span[2])[2]")
		public MobileElement WPgetHistoryInactiveProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-card//ion-icon)[2]")
		public MobileElement WPexpandCollapseHistoryInactiveProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[2]")
		public MobileElement WPgetHistoryInactiveProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Inactive']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[2]")
		public MobileElement WPgetHistoryInactiveProblemRecordedDate;
		

		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-item//H2/span)[1]")
		public MobileElement WPgetResolvedProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/parent::h2/following-sibling::div/span[1]/span/span[2])[1]")
		public MobileElement WPgetResolvedProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/parent::h2/following-sibling::div/span[2]/span/span[2])[1]")
		public MobileElement WPgetResolvedProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-card//ion-icon)[1]")
		public MobileElement WPexpandCollapseResolvedProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[1]")
		public MobileElement WPgetResolvedProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[1]")
		public MobileElement WPgetResolvedProblemRecordedDate;
		
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-item//H2/span)[2]")
		public MobileElement WPgetHistoryResolvedProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/parent::h2/following-sibling::div/span[1]/span/span[2])[2]")
		public MobileElement WPgetHistoryResolvedProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/parent::h2/following-sibling::div/span[2]/span/span[2])[2]")
		public MobileElement WPgetHistoryResolvedProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-card//ion-icon)[2]")
		public MobileElement WPexpandCollapseHistoryResolvedProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[2]")
		public MobileElement WPgetHistoryResolvedProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Resolved']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[2]")
		public MobileElement WPgetHistoryResolvedProblemRecordedDate;
		
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-item//H2/span)[1]")
		public MobileElement WPgetActiveProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/parent::h2/following-sibling::div/span[1]/span/span[2])[1]")
		public MobileElement WPgetActiveProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/parent::h2/following-sibling::div/span[2]/span/span[2])[1]")
		public MobileElement WPgetActiveProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-card//ion-icon)[1]")
		public MobileElement WPexpandCollapseActiveProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[1]")
		public MobileElement WPgetActiveProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[1]")
		public MobileElement WPgetActiveProblemRecordedDate;
		
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-item//H2/span)[2]")
		public MobileElement WPgetHistoryActiveProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/parent::h2/following-sibling::div/span[1]/span/span[2])[2]")
		public MobileElement WPgetHistoryActiveProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/parent::h2/following-sibling::div/span[2]/span/span[2])[2]")
		public MobileElement WPgetHistoryActiveProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-card//ion-icon)[2]")
		public MobileElement WPexpandCollapseHistoryActiveProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[2]")
		public MobileElement WPgetHistoryActiveProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Active']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[2]")
		public MobileElement WPgetHistoryActiveProblemRecordedDate;
		

		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-item//H2/span)[1]")
		public MobileElement WPgetCurrentProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/parent::h2/following-sibling::div/span[1]/span/span[2])[1]")
		public MobileElement WPgetCurrentProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/parent::h2/following-sibling::div/span[2]/span/span[2])[1]")
		public MobileElement WPgetCurrentProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-card//ion-icon)[1]")
		public MobileElement WPexpandCollapseCurrentProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[1]")
		public MobileElement WPgetCurrentProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[1]")
		public MobileElement WPgetCurrentProblemRecordedDate;
		
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-item//H2/span)[2]")
		public MobileElement WPgetHistoryCurrentProblemName;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/parent::h2/following-sibling::div/span[1]/span/span[2])[2]")
		public MobileElement WPgetHistoryCurrentProblemType;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/parent::h2/following-sibling::div/span[2]/span/span[2])[2]")
		public MobileElement WPgetHistoryCurrentProblemOnsetDate;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-card//ion-icon)[2]")
		public MobileElement WPexpandCollapseHistoryCurrentProblem;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-card//span[contains(text(),'Recorded by:')]/following-sibling::span)[2]")
		public MobileElement WPgetHistoryCurrentProblemRecordedBy;
		
		@FindBy(xpath  = "(//ion-badge[text()='Current']/ancestor::ion-card//span[contains(text(),'Onset date:')]/following-sibling::span)[2]")
		public MobileElement WPgetHistoryCurrentProblemRecordedDate;
		
		
		
		
		@FindBy(xpath  = "//*[contains(text(),'Medical') and contains(@class,'alert-checkbox-label')]")
		public MobileElement clickMedicalFilter;
		
		@FindBy(xpath  = "//*[contains(text(),'Surgical') and contains(@class,'alert-checkbox-label')]")
		public MobileElement clickSurgicalFilter;
		
		@FindBy(xpath  = "//*[contains(text(),'Cardio-thoracic') and contains(@class,'alert-checkbox-label')]")
		public MobileElement clickCardiothoracicFilter;

		@FindBy(xpath  = "//*[contains(text(),'Psychiatric') and contains(@class,'alert-checkbox-label')]")
		public MobileElement clickPsychiatricFilter;
}
