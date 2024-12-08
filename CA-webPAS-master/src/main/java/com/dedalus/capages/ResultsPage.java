package com.dedalus.capages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.GenericAppMethods;
import com.dedalus.genericappmethods.CAProjectMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ResultsPage extends GenericAppMethods {
	
	public ResultsPage(AppiumDriver<MobileElement> driver,ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(xpath = "//ion-label[contains(text(),'List')]/parent::ion-segment-button")
	public MobileElement ListTab;

	@FindBy(xpath = "//ion-label[contains(text(),'Tabular')]/parent::ion-segment-button")
	public MobileElement TabularTab;

	@FindBy(xpath = "//ion-label[contains(text(),'Orders')]/parent::ion-segment-button")
	public MobileElement OrdersTab;
	
	@FindBy(xpath = "//ion-card-content//ion-button[contains(text(),'Mark as read')]")
	public MobileElement MarkAsRead;
		
	@FindBy(xpath ="//dxc-result-list-item[1]//h2/span[1]")	
	public MobileElement WPResultName;
		
	@FindBy(xpath ="//dxc-result-list-item[1]//h2/ion-badge")	
	public MobileElement WPResultStatus;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//h2/following-sibling::div//span[contains(text(),'Lab')]/following-sibling::span")	
	public MobileElement WPResultLab;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//span[contains(text(),'Diagnostic group')]/following-sibling::span")	
	public MobileElement WPResultDiagnosticGroup;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//span[contains(text(),'Sample performed date')]/following-sibling::span")	
	public MobileElement WPResultPerformedDate;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//h2/parent::ion-label/following-sibling::div//ion-icon")	
	public MobileElement WPExpandResultIcon;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'PACK CELL')]/following-sibling::div")	
	public MobileElement ResPackCell;

	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'WHOLE BLOOD')]/following-sibling::div")	
	public MobileElement ResWholeBlood;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'FFP')]/following-sibling::div")	
	public MobileElement ResFFP;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'PLATELETS')]/following-sibling::div")	
	public MobileElement ResPlatelets;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'OTHERS')]/following-sibling::div")	
	public MobileElement ResOthers;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'Comments')]/following-sibling::div")	
	public MobileElement ResComments;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'ABO Grouping')]/following-sibling::div")	
	public MobileElement ResABO;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'Antibody Screening')]/following-sibling::div")	
	public MobileElement ResAntibodyScreening;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'Hold Serum')]/following-sibling::div")	
	public MobileElement ResHoldSerum;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'Processed By')]/following-sibling::div")	
	public MobileElement ResProcessedBy;
	
	@FindBy(xpath ="//dxc-result-list-item[1]//div[contains(text(),'Checked By')]/following-sibling::div")	
	public MobileElement ResCheckedBy;
	
	@FindBy(xpath ="(//dxc-result-list-item[1]//span[contains(text(),'Lab')]/following-sibling::span)[2]")	
	public MobileElement ResLabMore;
	
	@FindBy(xpath ="(//dxc-result-list-item[1]//span[contains(text(),'Diagnostic group')]/following-sibling::span)[2]")	
	public MobileElement ResDiagnosticGroupMore;
	
	@FindBy(xpath ="//ion-button[contains(@class,'ackButton')]")	
	public MobileElement clickMarkasRead;
		
	@FindBy(xpath ="//dxc-result-data//div[@class='text-overflow header']")	
	public MobileElement tabularViewResname;
	
	@FindBy(xpath ="//dxc-result-table//ion-col")	
	public MobileElement tabularViewResPerformedDate;
	
	@FindBy(xpath ="//div[contains(text(),'PACK CELL')]/parent::div/parent::div//ion-checkbox")	
	public MobileElement ResPackCellCheckBox;

	@FindBy(xpath ="//div[contains(text(),'WHOLE BLOOD')]/parent::div/parent::div//ion-checkbox")	
	public MobileElement ResWholeBloodCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'FFP')]/parent::div/parent::div//ion-checkbox")	
	public MobileElement ResFFPCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'PLATELETS')]/parent::div/parent::div//ion-checkbox")	
	public MobileElement ResPlateletsCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'OTHERS')]/parent::div/parent::div//ion-checkbox")	
	public MobileElement ResOthersCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'Comments')]/parent::div/parent::div//ion-checkbox")	
	public MobileElement ResCommentsCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'ABO Grouping')]/parent::div/parent::div//ion-checkbox")	
	public MobileElement ResABOCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'Antibody Screening')]/parent::div/parent::div//ion-checkbox")	
	public MobileElement ResAntibodyScreeningCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'Hold Serum')]/parent::div/parent::div//ion-checkbox")	
	public MobileElement ResHoldSerumCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'Processed By')]/parent::div/parent::div//ion-checkbox")	
	public MobileElement ResProcessedByCheckBox;
	
	@FindBy(xpath ="//div[contains(text(),'Checked By')]/parent::div/parent::div//ion-checkbox")	
	public MobileElement ResCheckedByCheckBox;
	
	@FindBy(xpath ="//span[contains(text(),'Search History')]")	
	public MobileElement clickSearchHistory;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[1]")	
	public MobileElement packCellCommentIcon;
	
	@FindBy(xpath ="(//dxc-result-table//ion-row[1]//ion-col/span)[2]")	
	public MobileElement WholeBloodCommentIcon;
	
	@FindBy(xpath ="//dxc-result-popup//ion-header")	
	public MobileElement popupResItemName;
	
	@FindBy(xpath ="//dxc-result-popup//ion-badge")	
	public MobileElement popupResItemStatus;
	
	@FindBy(xpath ="//dxc-result-popup//span[contains(text(),'Lab')]/following-sibling::span")	
	public MobileElement popupResItemLab;
	
	@FindBy(xpath ="//dxc-result-popup//span[contains(text(),'Diagnostic group')]/following-sibling::span")	
	public MobileElement popupResItemDiagnosticGroup;
	
	@FindBy(xpath ="//dxc-result-popup//span[contains(text(),'Sample performed date')]/following-sibling::span")	
	public MobileElement popupResItemPerformedDate;
	
	@FindBy(xpath ="//dxc-result-popup//span[contains(text(),'Read by')]/following-sibling::span")	
	public MobileElement popupResItemReadBy;
	
	@FindBy(xpath ="//dxc-result-popup//span[contains(text(),'Read by')]/following-sibling::ion-icon")	
	public MobileElement popupAckIcon;
	
	@FindBy(xpath ="//a[contains(@class,'detail')]")	
	public MobileElement clickViewMoreDetails;

	@FindBy(xpath ="//dxc-result-list-item[1]//h2//ion-icon")	
	public MobileElement ResultAckIcon;

	@FindBy(xpath ="(//*[contains(@class,'info')])[1]")	
	public MobileElement ResultInfo1;
	
	@FindBy(xpath ="(//*[contains(@class,'info')])[2]")	
	public MobileElement ResultInfo2;

	@FindBy(xpath ="//dxc-result-popup//ion-header//ion-icon")	
	public MobileElement ClosePopup;
	
	@FindBy(xpath = ("//ion-label[contains(text(),'Tabular')]/parent::ion-segment-button"))
    public MobileElement tabularFauxtab;
		
}
