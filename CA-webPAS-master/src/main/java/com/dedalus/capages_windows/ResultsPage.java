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

import io.appium.java_client.MobileElement;

public class ResultsPage extends CAProjectMethods {
	//testing
	public ResultsPage(RemoteWebDriver driver, ExtentTest test) {
		this.test = test;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ion-label[contains(text(),'List')]/parent::ion-segment-button")
	public WebElement ListTab;

	@FindBy(xpath = "//ion-label[contains(text(),'Tabular')]/parent::ion-segment-button")
	public WebElement TabularTab;

	@FindBy(xpath = "//ion-label[contains(text(),'Orders')]/parent::ion-segment-button")
	public WebElement OrdersTab;

	@FindBy(xpath = "//ion-card-content//ion-button[contains(text(),'Mark as read')]")
	public WebElement MarkAsRead;
		
	@FindBy(xpath ="//dxc-result-list-item[1]//h2/span[1]")	
	public WebElement WPResultName;
		
	@FindBy(xpath ="//dxc-result-list-item[1]//h2/ion-badge")	
	public WebElement WPResultStatus;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//h2/following-sibling::div//span[contains(text(),'Lab')]/following-sibling::span")	
	public WebElement WPResultLab;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//span[contains(text(),'Diagnostic group')]/following-sibling::span")	
	public WebElement WPResultDiagnosticGroup;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//span[contains(text(),'Sample performed date')]/following-sibling::span")	
	public WebElement WPResultPerformedDate;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//h2/parent::ion-label/following-sibling::div//ion-icon")	
	public WebElement WPExpandResultIcon;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'PACK CELL')]/following-sibling::div")	
	public WebElement ResPackCell;

	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'WHOLE BLOOD')]/following-sibling::div")	
	public WebElement ResWholeBlood;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'FFP')]/following-sibling::div")	
	public WebElement ResFFP;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'PLATELETS')]/following-sibling::div")	
	public WebElement ResPlatelets;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'OTHERS')]/following-sibling::div")	
	public WebElement ResOthers;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'Comments')]/following-sibling::div")	
	public WebElement ResComments;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'ABO Grouping')]/following-sibling::div")	
	public WebElement ResABO;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'Antibody Screening')]/following-sibling::div")	
	public WebElement ResAntibodyScreening;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'Hold Serum')]/following-sibling::div")	
	public WebElement ResHoldSerum;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'Processed By')]/following-sibling::div")	
	public WebElement ResProcessedBy;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'Checked By')]/following-sibling::div")	
	public WebElement ResCheckedBy;
	
	@FindBy(xpath ="(//dxc-result-list-item[1]//span[contains(text(),'Lab')]/following-sibling::span)[2]")	
	public WebElement ResLabMore;
	
	@FindBy(xpath ="(//dxc-result-list-item[1]//span[contains(text(),'Diagnostic group')]/following-sibling::span)[2]")	
	public WebElement ResDiagnosticGroupMore;
	
	@FindBy(xpath ="//ion-button[contains(@class,'ackButton')]")	
	public WebElement clickMarkasRead;
		
	@FindBy(xpath ="//dxc-result-data//div[@class='text-overflow header']")	
	public WebElement tabularViewResname;
	
	@FindBy(xpath ="//dxc-result-table//ion-col")	
	public WebElement tabularViewResPerformedDate;
	
	@FindBy(xpath ="//div[contains(text(),'PACK CELL')]/parent::div/parent::div//ion-checkbox")	
	public WebElement ResPackCellCheckBox;

	@FindBy(xpath ="//div[contains(text(),'WHOLE BLOOD')]/parent::div/parent::div//ion-checkbox")	
	public WebElement ResWholeBloodCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'FFP')]/parent::div/parent::div//ion-checkbox")	
	public WebElement ResFFPCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'PLATELETS')]/parent::div/parent::div//ion-checkbox")	
	public WebElement ResPlateletsCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'OTHERS')]/parent::div/parent::div//ion-checkbox")	
	public WebElement ResOthersCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'Comments')]/parent::div/parent::div//ion-checkbox")	
	public WebElement ResCommentsCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'ABO Grouping')]/parent::div/parent::div//ion-checkbox")	
	public WebElement ResABOCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'Antibody Screening')]/parent::div/parent::div//ion-checkbox")	
	public WebElement ResAntibodyScreeningCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'Hold Serum')]/parent::div/parent::div//ion-checkbox")	
	public WebElement ResHoldSerumCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'Processed By')]/parent::div/parent::div//ion-checkbox")	
	public WebElement ResProcessedByCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'Checked By')]/parent::div/parent::div//ion-checkbox")	
	public WebElement ResCheckedByCheckBox;
	
	@FindBy(xpath ="//span[contains(text(),'Search History')]")	
	public WebElement clickSearchHistory;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[1]")	
	public WebElement packCellCommentIcon;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[2]")	
	public WebElement WholeBloodCommentIcon;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[3]")	
	public WebElement ResFFPCommentIcon;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[4]")	
	public WebElement ResPlateletsCommentIcon;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[5]")	
	public WebElement ResOthersCommentIcon;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[6]")	
	public WebElement ResCommentsCommentIcon;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[7]")	
	public WebElement ResABOCommentIcon;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[8]")	
	public WebElement ResAntibodyScreeningCommentIcon;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[9]")	
	public WebElement ResHoldSerumCommentIcon;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[10]")	
	public WebElement ResProcessedByCommentIcon;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[11]")	
	public WebElement ResCheckedByCommentIcon;
	
	@FindBy(xpath ="//dxc-result-popup//ion-header")	
	public WebElement popupResItemName;
	
	@FindBy(xpath ="//dxc-result-popup//ion-badge")	
	public WebElement popupResItemStatus;
	
	@FindBy(xpath ="//dxc-result-popup//span[contains(text(),'Lab')]/following-sibling::span")	
	public WebElement popupResItemLab;
	
	@FindBy(xpath ="//dxc-result-popup//span[contains(text(),'Diagnostic group')]/following-sibling::span")	
	public WebElement popupResItemDiagnosticGroup;
	
	@FindBy(xpath ="//dxc-result-popup//span[contains(text(),'Sample performed date')]/following-sibling::span")	
	public WebElement popupResItemPerformedDate;
	
	@FindBy(xpath ="//dxc-result-popup//span[contains(text(),'Read by')]/following-sibling::span")	
	public WebElement popupResItemReadBy;
	
	@FindBy(xpath ="//dxc-result-popup//span[contains(text(),'Read by')]/following-sibling::ion-icon")	
	public WebElement popupAckIcon;
	
	@FindBy(xpath ="//a[contains(@class,'detail')]")	
	public WebElement clickViewMoreDetails;

	@FindBy(xpath ="//dxc-result-list-item[1]//h2//ion-icon")	
	public WebElement ResultAckIcon;
	
	@FindBy(xpath ="(//*[contains(@class,'info')])[1]")	
	public WebElement ResultInfo1;
	
	@FindBy(xpath ="(//*[contains(@class,'info')])[2]")	
	public WebElement ResultInfo2;
	
	@FindBy(xpath ="//dxc-result-popup//ion-header//ion-icon")	
	public WebElement ClosePopup;
		
	@FindBy(xpath = ("//ion-label[contains(text(),'Tabular')]/parent::ion-segment-button"))
    public WebElement tabularFauxtab;
}



