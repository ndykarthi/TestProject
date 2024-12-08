package com.dedalus.capages_windows;

import java.util.List;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods_windows.CAProjectMethods;
import com.dedalus.genericappmethods_windows.GenericAppMethods;

public class DocumentsPage extends GenericAppMethods {
	
	public DocumentsPage(RemoteWebDriver driver, ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//ion-label[contains(text(),'Active')]/parent::ion-segment-button")
	public WebElement activeFauxtab;
 
	@FindBy(xpath ="//ion-label[contains(text(),'All')]/parent::ion-segment-button")
	public WebElement allFauxtab;
 
	@FindBy(xpath ="//ion-label[contains(text(),'Inactive')]/parent::ion-segment-button")
	public WebElement inactiveFauxtab;
	
	@FindBy(xpath = "//ion-toggle")
	public WebElement toggle;
	
	// Final Active
	@FindBy(xpath="(//dxc-documents//ion-label/h2/span)[3]")
	public WebElement FinalDocType;
	
	@FindBy(xpath="(//dxc-documents//ion-badge)[3]")
	public WebElement FinalDocStatus;
	
	@FindBy(xpath = "(//dxc-documents//span[contains(text(),'Created on')]/following-sibling::span)[3]")
	public WebElement FinalDocCreatedOn;
	
	@FindBy(xpath="(//dxc-documents//span[contains(text(),'Author')]/following-sibling::span)[3]")
	public WebElement FinalDocAuthor;
	
	@FindBy(xpath = "(//dxc-documents//span[contains(text(),'Name')]/following-sibling::span)[3]")
	public WebElement FinalDocName;
	
	// Draft Active
	@FindBy(xpath="(//dxc-documents//ion-label/h2/span)[2]")
	public WebElement DraftDocType;
	 
	@FindBy(xpath="(//dxc-documents//ion-badge)[2]")
	public WebElement DraftDocStatus;

	@FindBy(xpath = "(//dxc-documents//span[contains(text(),'Created on')]/following-sibling::span)[2]")
	public WebElement DraftDocCreatedOn;
	
	@FindBy(xpath="(//dxc-documents//span[contains(text(),'Author')]/following-sibling::span)[2]")
	public WebElement DraftDocAuthor;
	
	@FindBy(xpath = "(//dxc-documents//span[contains(text(),'Name')]/following-sibling::span)[2]")
	public WebElement DraftDocName;
	
	//Superseded Active
	@FindBy(xpath="(//dxc-documents//ion-label/h2/span)[1]")
	public WebElement SupDocTypeActive;
	
	@FindBy(xpath="(//dxc-documents//ion-badge)[1]")
	public WebElement SupDocStatusActive;
	
	@FindBy(xpath = "(//dxc-documents//span[contains(text(),'Created on')]/following-sibling::span)[1]")
	public WebElement SupDocCreatedOnActive;
	
	@FindBy(xpath="(//dxc-documents//span[contains(text(),'Author')]/following-sibling::span)[1]")
	public WebElement SupDocAuthorActive;
	
	@FindBy(xpath = "(//dxc-documents//span[contains(text(),'Name')]/following-sibling::span)[1]")
	public WebElement SupDocNameActive;
	
	//Superseded Inactive
	@FindBy(xpath="//ion-badge[contains(text(),'Superseded')]/preceding-sibling::span")
	public WebElement SupDocTypeInActive;
	
	@FindBy(xpath="//ion-badge[contains(text(),'Superseded')]")
	public WebElement SupDocStatusInActive;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Superseded')]/ancestor::ion-label//span[contains(text(),'Created on')]/following-sibling::span")
	public WebElement SupDocCreatedOnInActive;
	
	@FindBy(xpath="//ion-badge[contains(text(),'Superseded')]/ancestor::ion-label//span[contains(text(),'Author')]/following-sibling::span")
	public WebElement SupDocAuthorInActive;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Superseded')]/ancestor::ion-label//span[contains(text(),'Name')]/following-sibling::span")
	public WebElement SupDocNameInActive;
	
	//Entered in error Inactive
	@FindBy(xpath="//ion-badge[contains(text(),'Entered-in-error')]/preceding-sibling::span")
	public WebElement EnteredInErrorDocType;
	 
	@FindBy(xpath="//ion-badge[contains(text(),'Entered-in-error')]")
	public WebElement EnteredInErrorDocStatus;

	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-label//span[contains(text(),'Created on')]/following-sibling::span")
	public WebElement EnteredInErrorDocCreatedOn;
	
	@FindBy(xpath="//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-label//span[contains(text(),'Author')]/following-sibling::span")
	public WebElement EnteredInErrorDocAuthor;
	
	@FindBy(xpath = "//ion-badge[contains(text(),'Entered-in-error')]/ancestor::ion-label//span[contains(text(),'Name')]/following-sibling::span")
	public WebElement EnteredInErrorDocName;
	
	public int getDocumentActiveCount() throws InterruptedException {
		int AlertCnt = 0;
		try {
			List<WebElement> alertCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Current')]");
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
			List<WebElement> docCnt = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Superseded')]");
			List<WebElement> docCnt1 = driver.findElementsByXPath("(//ion-label/h2/span)/following-sibling::ion-badge[contains(text(),'Entered-in-error')]");
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
	public WebElement AccResDocData;
}
