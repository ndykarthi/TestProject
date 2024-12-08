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

public class DocumentsPage extends CAProjectMethods {

	public DocumentsPage(AppiumDriver<MobileElement> driver,ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(xpath = "//ion-toolbar//ion-icon[@name='funnel']")
	public MobileElement docFunnel;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Date Range')]/following-sibling::ion-select")
	public MobileElement DateRange;
	
	@FindBy(xpath = "//div[contains(text(), 'Last Week')]")
	public MobileElement LastWeek;
	
	@FindBy(xpath = "//div[contains(text(), 'Last 2 Week')]")
	public MobileElement Last2Week;
	
	@FindBy(xpath = "//div[contains(text(), 'Last Month')]")
	public MobileElement LastMonth;
	
	@FindBy(xpath = "//span[contains(text(), 'OK')]")
	public MobileElement Ok;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Custom Date Range')]/following-sibling::ion-toggle")
	public MobileElement CustomDateRange;
	
	//@FindBy(xpath = "//ion-label[contains(text(),'Date From')]/ancestor::ion-row//ion-icon")
	//public MobileElement CustDtFrom;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Date From')]/following-sibling::ion-input/input")
	public MobileElement CustDtFromInput;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Date To')]/following-sibling::ion-input/input")
	public MobileElement CustDtToInput;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Date To')]/ancestor::ion-row//ion-icon")
	public MobileElement CustDtTo;
	
	@FindBy(xpath = "//button[contains(text(),'Done')]")
	public MobileElement CustDtDone;
	
	
	@FindBy(xpath = "//*[contains(text(),'Forms')]/ancestor::ion-item/ion-badge")
	public MobileElement FormCnt;
	
	@FindBy(xpath = "//ion-label/h2/span[contains(text(),'Notes')]/ancestor::ion-card-header//ion-badge")
	public MobileElement NotesCnt;
	
	@FindBy(xpath = "//*[contains(text(),'Forms')]/ancestor::ion-item//ion-icon[contains(@class,'expand')]")
	public MobileElement ExpandForms;
	
	@FindBy(xpath = "//*[contains(text(),'Notes')]/ancestor::ion-item//ion-icon[contains(@class,'expand')]")
	public MobileElement ExpandNotes;
	
	@FindBy(xpath = "//span[contains(text(),'Forms')]/ancestor::ion-card-header/following-sibling::ion-card-content//h2/span")
	public MobileElement FormName;
	
	@FindBy(xpath = "//span[contains(text(),'Forms')]/ancestor::ion-card-header/following-sibling::ion-card-content//h2/ion-badge")
	public MobileElement FormStatus;
	
	@FindBy(xpath = "//span[contains(text(),'Forms')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Created On')]/following-sibling::span")
	public MobileElement FormCreatedOn;
	
	@FindBy(xpath = "//span[contains(text(),'Forms')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Modified On')]/following-sibling::span")
	public MobileElement FormModOn;
	
	@FindBy(xpath = "//span[contains(text(),'Forms')]/ancestor::ion-card-header/following-sibling::ion-card-content//span[contains(text(),'Author')]/following-sibling::span")
	public MobileElement FormAuthor;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/preceding-sibling::span")
	public MobileElement SONotesType;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Created On')]/following-sibling::span")
	public MobileElement SONoteCreatedOn;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Modified On')]/following-sibling::span")
	public MobileElement SONoteModOn;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Author')]/following-sibling::span")
	public MobileElement SONoteAuthor;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/parent::h2/following-sibling::div//span[contains(text(),'Name')]/following-sibling::span")
	public MobileElement SONoteName;
	
	@FindBy(xpath = "//h2/ion-badge")
	public MobileElement SONoteStatus;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Status')]/following-sibling::ion-select")
	public MobileElement StatusFilter;
	
	@FindBy(xpath = "//div[contains(text(),'Entered-in-error')]/preceding-sibling::div")
	public MobileElement EnteredInEr_Option;
	
	@FindBy(xpath = "//div[contains(text(),'Superseded')]/preceding-sibling::div")
	public MobileElement Superseded_Option;
	
	@FindBy(xpath = "//ion-label[contains(text(),'Type')]/following-sibling::ion-select")
	public MobileElement TypeFilter;
	
	@FindBy(xpath = "//div[contains(text(),'Notes')]/preceding-sibling::div")
	public MobileElement Notes_Option;
	
	@FindBy(xpath = "//ion-button[contains(text(),'Clear Filters')]/ion-icon")
	public MobileElement ClearFilter;
	
	@FindBy(xpath = "//ion-card-content//ion-label/h2/span")
	public MobileElement NoteType;
	
	@FindBy(xpath = "//span[contains(text(),'Created On')]/following-sibling::span")
	public MobileElement NoteCreatedOn;
	
	@FindBy(xpath = "//span[contains(text(),'Modified On')]/following-sibling::span")
	public MobileElement NoteModOn;
	
	@FindBy(xpath = "//span[contains(text(),'Author')]/following-sibling::span")
	public MobileElement NoteAuthor;
	
	@FindBy(xpath = "//span[contains(text(),'Name')]/following-sibling::span")
	public MobileElement NoteName;
	
	@FindBy(xpath = "//h2/ion-badge")
	public MobileElement NoteStatus;
	

	@FindBy(xpath ="//ion-label[contains(text(),'Active')]/parent::ion-segment-button")
	public MobileElement activeFauxtab;
 
	@FindBy(xpath ="//ion-label[contains(text(),'All')]/parent::ion-segment-button")
	public MobileElement allFauxtab;
 
	@FindBy(xpath ="//ion-label[contains(text(),'Inactive')]/parent::ion-segment-button")
	public MobileElement inactiveFauxtab;
	
	@FindBy(xpath = "//ion-toggle")
	public MobileElement toggle;
	
	// Final Active --- fixed for active doc script
	@FindBy(xpath="(//dxc-documents//ion-label/h2/span)[3]")
	public MobileElement FinalDocType;
	
	@FindBy(xpath="(//dxc-documents//ion-badge)[3]")
	public MobileElement FinalDocStatus;
	
	@FindBy(xpath = "(//dxc-documents//span[contains(text(),'Created on')]/following-sibling::span)[3]")
	public MobileElement FinalDocCreatedOn;
	
	@FindBy(xpath="(//dxc-documents//span[contains(text(),'Author')]/following-sibling::span)[3]")
	public MobileElement FinalDocAuthor;
	
	@FindBy(xpath = "(//dxc-documents//span[contains(text(),'Name')]/following-sibling::span)[3]")
	public MobileElement FinalDocName;
	
	// Draft Active --- fixed for active doc script
	@FindBy(xpath="(//dxc-documents//ion-label/h2/span)[2]")
	public MobileElement DraftDocType;
	 
	@FindBy(xpath="(//dxc-documents//ion-badge)[2]")
	public MobileElement DraftDocStatus;

	@FindBy(xpath = "(//dxc-documents//span[contains(text(),'Created on')]/following-sibling::span)[2]")
	public MobileElement DraftDocCreatedOn;
	
	@FindBy(xpath="(//dxc-documents//span[contains(text(),'Author')]/following-sibling::span)[2]")
	public MobileElement DraftDocAuthor;
	
	@FindBy(xpath = "(//dxc-documents//span[contains(text(),'Name')]/following-sibling::span)[2]")
	public MobileElement DraftDocName;
	
	//Superseded Active
	@FindBy(xpath="(//dxc-documents//ion-label/h2/span)[1]")
	public MobileElement SupDocTypeActive;
	
	@FindBy(xpath="(//dxc-documents//ion-badge)[1]")
	public MobileElement SupDocStatusActive;
	
	@FindBy(xpath = "(//dxc-documents//span[contains(text(),'Created on')]/following-sibling::span)[1]")
	public MobileElement SupDocCreatedOnActive;
	
	@FindBy(xpath="(//dxc-documents//span[contains(text(),'Author')]/following-sibling::span)[1]")
	public MobileElement SupDocAuthorActive;
	
	@FindBy(xpath = "(//dxc-documents//span[contains(text(),'Name')]/following-sibling::span)[1]")
	public MobileElement SupDocNameActive;
	
	//Superseded Inactive
	@FindBy(xpath="//ion-badge[contains(text(),'Superseded')]/preceding-sibling::span")
	public MobileElement SupDocTypeInActive;
		
	@FindBy(xpath="//ion-badge[contains(text(),'Superseded')]")
	public MobileElement SupDocStatusInActive;
		
	@FindBy(xpath = "//ion-badge[contains(text(),'Superseded')]/ancestor::ion-label//span[contains(text(),'Created on')]/following-sibling::span")
	public MobileElement SupDocCreatedOnInActive;
	
	@FindBy(xpath="//ion-badge[contains(text(),'Superseded')]/ancestor::ion-label//span[contains(text(),'Author')]/following-sibling::span")
	public MobileElement SupDocAuthorInActive;
		
	@FindBy(xpath = "//ion-badge[contains(text(),'Superseded')]/ancestor::ion-label//span[contains(text(),'Name')]/following-sibling::span")
	public MobileElement SupDocNameInActive;
		
	//Entered in error Inactive
	@FindBy(xpath="//ion-badge[contains(text(),'Entered-in-error')]/preceding-sibling::span")
	public MobileElement EnteredInErrorDocType;
		 
	@FindBy(xpath="//ion-badge[contains(text(),'Entered-in-error')]")
	public MobileElement EnteredInErrorDocStatus;

	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-label//span[contains(text(),'Created on')]/following-sibling::span")
	public MobileElement EnteredInErrorDocCreatedOn;
		
	@FindBy(xpath="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-label//span[contains(text(),'Author')]/following-sibling::span")
	public MobileElement EnteredInErrorDocAuthor;
		
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-label//span[contains(text(),'Name')]/following-sibling::span")
	public MobileElement EnteredInErrorDocName;
	
	public int getDocumentActiveCount() throws InterruptedException {
		int AlertCnt = 0;
		try {
			List<MobileElement> alertCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Current')]");
			AlertCnt = alertCnt.size();
			takeSnap();
			reportStep("Active Document Count: "+AlertCnt, "PASS");
		}catch (InvalidElementStateException e) {
		      reportStep("The element is not available", "FAIL");
		    } catch (WebDriverException e) {
		      reportStep("The element is not available", "FAIL");
		    }
		return AlertCnt;	
	}
	
	public int getDocumentINActiveCount() throws InterruptedException {
		int totalCnt = 0;
		try {
			List<MobileElement> docCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Superseded')]");
			List<MobileElement> docCnt1 = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Entered-in-error')]");
			int documentCnt = docCnt.size();
			int documentCnt1 = docCnt1.size();
			totalCnt = documentCnt + documentCnt1;
			takeSnap();
			reportStep("InActive Document Count: "+totalCnt, "PASS");
		}catch (InvalidElementStateException e) {
		      reportStep("The element is not available", "FAIL");
		    } catch (WebDriverException e) {
		      reportStep("The element is not available", "FAIL");
		    }	
		return totalCnt;
	}

	@FindBy(xpath = "//dxc-no-data//h6")
	public MobileElement AccResDocData;
}
