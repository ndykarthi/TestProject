package com.dedalus.capages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.dedalus.genericappmethods.CAProjectMethods;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class EncountersPage extends CAProjectMethods {
	
	public EncountersPage(AppiumDriver<MobileElement> driver, ExtentTest test) {
		this.test = test;
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/*@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Category')]/following-sibling::span"))
	public MobileElement ReqResCat;

	public String getRequestRReseultCategory() throws InterruptedException {
		String reqCat = ReqResCat.getText();
		takeSnap();
		return reqCat;
	}

	@FindBy(how = How.XPATH, using = ("//span[contains(@class,'header')]//span[contains(text(),'Status')]/following-sibling::span"))
	public MobileElement ReqStatus;

	public String getRequestStatus() throws InterruptedException {
		String reqStatus = ReqStatus.getText();
		takeSnap();
		return reqStatus;
	}
	
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Service Department')]/following-sibling::span"))
	public MobileElement ReqResSerDept;

	public String getRequestRResultServiceDept() throws InterruptedException {
		String reqSerDept = ReqResSerDept.getText();
		takeSnap();
		return reqSerDept;
	}
	
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Specimen Status')]/following-sibling::span"))
	public MobileElement ReqSpStatus;

	public String getRequestSpecimenStatus() throws InterruptedException {
		String reqSpStatus = ReqSpStatus.getText();
		takeSnap();
		return reqSpStatus;
	}
	 
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Requested By')]/following-sibling::span"))
	public MobileElement ReqReqtdBy;

	public String getRequestRequestedBy() throws InterruptedException {
		String reqReqtdBy = ReqReqtdBy.getText();
		takeSnap();
		return reqReqtdBy;
	}
	
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Requested On')]/following-sibling::span"))
	public MobileElement ReqReqtdOn;

	public String getRequestRequestedOn() throws InterruptedException {
		String reqReqtdOn = ReqReqtdOn.getText();
		takeSnap();
		return reqReqtdOn;
	}

	@FindBy(how = How.XPATH, using = ("(//ion-label/h2//ion-badge)[1]"))
	public MobileElement ResStatus;

	public String getResultStatus() throws InterruptedException {
		String resStatus = ResStatus.getText();
		takeSnap();
		return resStatus;
	}
		
	
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Sample Performed Date')]/following-sibling::span"))
	public MobileElement ResPerfDate;

	public String getResultPefDate() throws InterruptedException {
		String resPerDate = ResPerfDate.getText();
		takeSnap();
		return resPerDate;
	}
	
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Priority')]/following-sibling::span"))
	public MobileElement ResPriority;

	public String getResultPriority() throws InterruptedException {
		String resPriority = ResPriority.getText();
		takeSnap();
		return resPriority;
	}
	
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Result reported date')]/following-sibling::span"))
	public MobileElement ResRepDate;

	public String getResReportedDate() throws InterruptedException {
		String resRepDate = ResRepDate.getText();
		takeSnap();
		return resRepDate;
	}
	
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Result reported by')]/following-sibling::span"))
	public MobileElement ResRepBy;

	public String getResReportedBy() throws InterruptedException {
		String resRepBy = ResRepBy.getText();
		takeSnap();
		return resRepBy;
	}
	
	@FindBy(how = How.XPATH, using = ("//button//ion-icon[@name='checkmark-circle']"))
	public MobileElement selectAck;

	public String selectAcknowledgement() throws InterruptedException {
		actionClick(selectAck);
		Thread.sleep(2000);
		return null;
	}
/*	
	@FindBy(how=How.XPATH,using="//textarea[@placeholder='Acknowledged comments']")
	public MobileElement ackCommtext;
	
	public EncountersPage enterAckComments(String data) throws InterruptedException {
		String field = "Acknowledgement Comments";
		explicitWait(ackCommtext);
	    type(ackCommtext, data, field);
	    takeSnap();
	    return this;		
	}
*/	
	//@FindBy(how = How.XPATH, using = ("(//ion-icon[@name='information-circle'])[1]"))
/*	@FindBy(how = How.XPATH, using = ("(//ion-icon[@name='information-circle'])[1]/following-sibling::div//pre"))
	public MobileElement overallComm;

	public String getOverallComments() throws InterruptedException {
		String resComm = overallComm.getText();
		takeSnap();
		return resComm;
	}
	
	@FindBy(how = How.XPATH, using = ("//div[@class='comments']"))
	public MobileElement creatineComm;

	public String getCreatineComments() throws InterruptedException {
		String CreatineComm = creatineComm.getText();
		takeSnap();
		return CreatineComm;
	}

	@FindBy(how = How.XPATH, using = ("//span[@class='quantity']"))
	public MobileElement qty;

	public String getQuantity() throws InterruptedException {
		String Qty = qty.getText();
		takeSnap();
		return Qty;
	}
	
	@FindBy(how = How.XPATH, using = ("//span[@class='unit']"))
	public MobileElement unit;

	public String getUnit() throws InterruptedException {
		String Unit = unit.getText();
		takeSnap();
		return Unit;
	}
		
	@FindBy(how = How.XPATH, using = ("//div[@class='range']"))
	public MobileElement range;

	public String getRange() throws InterruptedException {
		String Range = range.getText();
		takeSnap();
		return Range;
	}
	
	@FindBy(how = How.XPATH, using = ("(//p[contains(text(),'Comments')])[1]"))
	public MobileElement blCultureComm;

	public String getBloodCultureComments() throws InterruptedException {
		String bloodCulComm = blCultureComm.getText();
		takeSnap();
		return bloodCulComm;
	}
	
	//@FindBy(how = How.XPATH, using = ("//div[contains(text(),'Blood culture, arterial blood')]/following-sibling::pre//p/font"))
	@FindBy(how = How.XPATH, using = ("//div[contains(text(),'Blood culture, arterial blood')]/following-sibling::pre//font"))
	public MobileElement blCultureVal;

	public String getBloodCultureValue() throws InterruptedException {
		String bloodCulVal = blCultureVal.getText();
		takeSnap();
		return bloodCulVal;
	}
	
	@FindBy(how = How.XPATH, using = ("(//p[contains(text(),'Comments')])[2]"))
	public MobileElement boneMarrowComm;

	public String getBoneMarrowComments() throws InterruptedException {
		String BoneMarrowComm = boneMarrowComm.getText();
		takeSnap();
		return BoneMarrowComm;
	}
	
	@FindBy(how = How.XPATH, using = ("//div[contains(text(),'WBC count, bone marrow')]/following-sibling::pre"))
	public MobileElement boneMarrowVal;

	public String getBoneMarrowValue() throws InterruptedException {
		String BoneMarrowVal = boneMarrowVal.getText();
		takeSnap();
		return BoneMarrowVal;
	}
	
	@FindBy(how = How.XPATH, using = ("(//ion-row[contains(@class,'time-row')]//ion-col)[2]"))
	public MobileElement ResDate;

	public String getResultDate() throws InterruptedException {
		String ResultDate = ResDate.getText();
		takeSnap();
		return ResultDate;
	}
			
	@FindBy(how = How.XPATH, using = "(//ion-col[@class='non-numeric-data col'])[1]")
	public MobileElement viewCharttype;

	public EncountersPage viewChartType() throws InterruptedException {
		actionClick(viewCharttype);
		Thread.sleep(5000);
		takeSnap();
		return this;
	}
	
	@FindBy(how = How.XPATH, using = ("(//ion-col[@class='numeric-data col'])[1]/div"))
	public MobileElement CreatinineVal;

	public String getCreatinineVal() throws InterruptedException {
		String CreatineVal = CreatinineVal.getText();
		takeSnap();
		return CreatineVal;
	}
	
	@FindBy(how = How.XPATH, using = ("//ion-icon[@name='warning']"))
	public MobileElement CreatinineStatus;

	public String getCreatinineStatus() throws InterruptedException {
		String CreatineStatus = CreatinineStatus.getAttribute("role");
		takeSnap();
		return CreatineStatus;
	}
	
	public List<String> getBloodCultureChartTypeValues() {
		List<String> BloodCultureChartTypeInfo = new ArrayList<>();
		
		WebElement eleStatus = driver.findElement(By.xpath("//span[contains(text(),'Status')]/following-sibling::ion-badge"));
		String Status = eleStatus.getText();
		BloodCultureChartTypeInfo.add(Status);
		
		WebElement eleCategory = driver.findElement(By.xpath("//span[contains(text(),'Category')]/following-sibling::span"));
		String Category = eleCategory.getText();
		BloodCultureChartTypeInfo.add(Category);
		
		WebElement eleAbStatus = driver.findElement(By.xpath("//span[contains(text(),'Abnormal status')]/following-sibling::ion-icon"));
		String AbnormalStatus = eleAbStatus.getAttribute("name");
		BloodCultureChartTypeInfo.add(AbnormalStatus);
		 
		WebElement elePriority = driver.findElement(By.xpath("//span[contains(text(),'Priority')]/following-sibling::span"));
		String Priority = elePriority.getText();
		BloodCultureChartTypeInfo.add(Priority);
	
		WebElement elePerfDate = driver.findElement(By.xpath("//span[contains(text(),'Sample Performed Date')]/following-sibling::span"));
		String PerfDate = elePerfDate.getText();
		BloodCultureChartTypeInfo.add(PerfDate);
	
		return BloodCultureChartTypeInfo;
	}

	@FindBy(how = How.XPATH, using = "//ion-icon[@name='text']")
	public MobileElement viewCreatineLvl;

	public EncountersPage viewCreatinineLevel() throws InterruptedException {
		actionClick(viewCreatineLvl);
		Thread.sleep(5000);
		takeSnap();
		return this;
	}
	
	public List<String> getCreatinineLvlValues() {
		List<String> CreatinineLevelInfo = new ArrayList<>();
		
		WebElement eleStatus = driver.findElement(By.xpath("//span[contains(text(),'Status')]/following-sibling::ion-badge"));
		String Status = eleStatus.getText();
		CreatinineLevelInfo.add(Status);
		
		WebElement eleValue = driver.findElement(By.xpath("//span[contains(text(),'Value')]/following-sibling::span"));
		String Value = eleValue.getText();
		CreatinineLevelInfo.add(Value);
		
		WebElement eleAbnormalIcon = driver.findElement(By.xpath("//span[contains(text(),'Value')]/parent::ion-row//ion-icon"));
		String AbnrmlIcon = eleAbnormalIcon.getAttribute("role");
		CreatinineLevelInfo.add(AbnrmlIcon);
		
		WebElement elePerfDate = driver.findElement(By.xpath("//span[contains(text(),'Sample Performed Date')]/following-sibling::span"));
		String PerfDate = elePerfDate.getText();
		CreatinineLevelInfo.add(PerfDate);
		 
		WebElement eleRange = driver.findElement(By.xpath("//ion-row[contains(text(),'Range')]/following-sibling::ion-row"));
		String Range = eleRange.getText();
		CreatinineLevelInfo.add(Range);
	
		WebElement eleComments = driver.findElement(By.xpath("//ion-row[contains(text(),'Comments')]/following-sibling::ion-row"));
		String Comments = eleComments.getText();
		CreatinineLevelInfo.add(Comments);
	
		return CreatinineLevelInfo;
	}

	  
	@FindBy(how = How.XPATH, using = ("//ion-icon[@aria-label='checkmark circle']"))
	public MobileElement ackIcon;

	public String getAckIcon() throws InterruptedException {
		String AckIcon = ackIcon.getAttribute("role");
		takeSnap();
		return AckIcon;
	}
	
	@FindBy(how = How.XPATH, using = ("(//ion-label//p[contains(text(),'Acknowledged')]/following-sibling::p)[1]"))
	public MobileElement ackUser;

	public String getAckdUser() throws InterruptedException {
		String AckUser = ackUser.getText();
		takeSnap();
		return AckUser;
	}
	
	@FindBy(how = How.XPATH, using = ("(//ion-label//p[contains(text(),'Acknowledged')]/following-sibling::p)[1]/span"))
	public MobileElement ackDate;

	public String getAckdDate() throws InterruptedException {
		String AckDate = ackDate.getText();
		takeSnap();
		return AckDate;
	}
	
	@FindBy(how = How.XPATH, using = ("(//ion-label//p[contains(text(),'Acknowledged')]/following-sibling::p)[2]/span"))
	public MobileElement ackComm;

	public String getAckdComm() throws InterruptedException {
		String AckComm = ackComm.getText();
		takeSnap();
		return AckComm;
	}
	 			
	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Type')]/following-sibling::span"))
	public MobileElement spType;

	public String getSpecimenType() throws InterruptedException {
		String SpType = spType.getText();
		takeSnap();
		return SpType;
	}

	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Body site')]/following-sibling::span"))
	public MobileElement spBodySite;

	public String getSpecimenBodySite() throws InterruptedException {
		String SpBodySite = spBodySite.getText();
		takeSnap();
		return SpBodySite;
	}

	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Laterality')]/following-sibling::span"))
	public MobileElement spLaterality;

	public String getSpecimenLaterality() throws InterruptedException {
		String SpLaterality = spLaterality.getText();
		takeSnap();
		return SpLaterality;
	}

	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Status')]/following-sibling::span"))
	public MobileElement spStatus;

	public String getSpecimenStatus() throws InterruptedException {
		String SpStatus = spStatus.getText();
		takeSnap();
		return SpStatus;
	}

	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Collected Date')]/following-sibling::span"))
	public MobileElement spCollDate;

	public String getSpecimenCollectedDate() throws InterruptedException {
		String SpCollDate = spCollDate.getText();
		takeSnap();
		return SpCollDate;
	}

	@FindBy(how = How.XPATH, using = ("//span[contains(text(),'Required collection Date')]/following-sibling::span"))
	public MobileElement spReqCollDate;

	public String getSpecimenReqCollDate() throws InterruptedException {
		String SpReqCollDate = spReqCollDate.getText();
		takeSnap();
		return SpReqCollDate;
	}
	
	*/
	//GetCurrentEnc ID
		@FindBy(how = How.XPATH, using = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]"))
		public MobileElement selectRec;
		
		@FindBy(how = How.XPATH, using = ("//dxc-encounters//ion-label[contains(text(),'Future')]/parent::ion-segment-button"))
		public MobileElement futureFauxtab;
		
		@FindBy(how = How.XPATH, using = ("//dxc-encounters//ion-label[contains(text(),'Past')]"))
		public MobileElement pastFauxtab;
		
		@FindBy(how = How.XPATH, using = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/h2/span"))
		public MobileElement ipEncID;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label//ion-badge"))
		public MobileElement IPEncBadge;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Admitted')]/following-sibling::span"))
		public MobileElement ipEncAdmittedDate;
		
		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Specialty')]/following-sibling::span"))
		public MobileElement ipEncSpecialty;
		
		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Discharge')]/following-sibling::span"))
		public MobileElement ipEncDischargeDate;
		
		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Care provider')]/following-sibling::span"))
		public MobileElement ipEncCareProvider;
		
		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-icon[contains(@name,'chevron-forward')]"))
		public MobileElement expandIPEncDetails;
		
		@FindBy(xpath = ("//*[contains(text(),'Seen by')]/parent::ion-row/following-sibling::ion-row[1]/ion-col[1]"))
		public MobileElement getIPEncSeen2SeenBy;
		
		@FindBy(xpath = ("//*[contains(text(),'Seen by type')]/parent::ion-row/following-sibling::ion-row[1]/ion-col[2]"))
		public MobileElement getIPEncSeen2SeenType;
		
		@FindBy(xpath = ("//*[contains(text(),'Start date and time')]/parent::ion-row/following-sibling::ion-row[1]/ion-col[3]"))
		public MobileElement getIPEncSeen2SeenStartDate;
		
		@FindBy(xpath = ("//*[contains(text(),'End date and time')]/parent::ion-row/following-sibling::ion-row[1]/ion-col[4]"))
		public MobileElement getIPEncSeen2SeenEndDate;
		
		@FindBy(xpath = ("//*[contains(text(),'Comments')]/parent::ion-row/following-sibling::ion-row[1]/ion-col[5]"))
		public MobileElement getIPEncSeen2Comments;
		
		@FindBy(xpath = ("//*[contains(text(),'Seen by')]/parent::ion-row/following-sibling::ion-row[2]/ion-col[1]"))
		public MobileElement getIPEncSeen1SeenBy;
		
		@FindBy(xpath = ("//*[contains(text(),'Seen by type')]/parent::ion-row/following-sibling::ion-row[2]/ion-col[2]"))
		public MobileElement getIPEncSeen1SeenType;
		
		@FindBy(xpath = ("//*[contains(text(),'Start date and time')]/parent::ion-row/following-sibling::ion-row[2]/ion-col[3]"))
		public MobileElement getIPEncSeen1SeenStartDate;
		
		@FindBy(xpath = ("//*[contains(text(),'End date and time')]/parent::ion-row/following-sibling::ion-row[2]/ion-col[4]"))
		public MobileElement getIPEncSeen1SeenEndDate;
		
		@FindBy(xpath = ("//*[contains(text(),'Comments')]/parent::ion-row/following-sibling::ion-row[2]/ion-col[5]"))
		public MobileElement getIPEncSeen1Comments;
		
		
		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 EMERGENCY DEPARTMENT ENCOUNTERS')]"))
		public MobileElement selectEDRec;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 EMERGENCY DEPARTMENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/h2/span"))
		public MobileElement EDEncID;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 EMERGENCY DEPARTMENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label//ion-badge"))
		public MobileElement EDEncBadge;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 EMERGENCY DEPARTMENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Attended')]/following-sibling::span"))
		public MobileElement EDEncAttendedDate;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 EMERGENCY DEPARTMENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Care Provider')]/following-sibling::span"))
		public MobileElement EDEncEncCareProvider;
		
		
		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 OUTPATIENT ENCOUNTERS')]"))
		public MobileElement selectOPRec;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 OUTPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/h2/span"))
		public MobileElement OPEncID;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 OUTPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label//ion-badge"))
		public MobileElement OPEncBadge;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 OUTPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Start')]/following-sibling::span"))
		public MobileElement OPEncStartDate;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 OUTPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Care Provider')]/following-sibling::span"))
		public MobileElement OPEncEncCareProvider;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 OUTPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Specialty')]/following-sibling::span"))
		public MobileElement OPEncSpecialty;
		
		
		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 SINGLE ENCOUNTERS')]"))
		public MobileElement selectContactRec;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 SINGLE ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/h2/span"))
		public MobileElement ContactEncID;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 SINGLE ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label//ion-badge"))
		public MobileElement ContactEncBadge;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 SINGLE ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Start')]/following-sibling::span"))
		public MobileElement ContactEncStartDate;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 SINGLE ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Care Provider')]/following-sibling::span"))
		public MobileElement ContactEncCareProvider;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 SINGLE ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Specialty')]/following-sibling::span"))
		public MobileElement ContactEncSpecialty;

		
		@FindBy(xpath = ("//ion-icon[contains(@name,'chevron-back-outline')]"))
		public MobileElement backToPatientsearchIcon;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency Department')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Encounter Id')]/following-sibling::ion-col"))
		public MobileElement encListEDEncID;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency Department')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Care Provider')]/following-sibling::ion-col"))
		public MobileElement encListEDEncCareProvider;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency Department')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Specialty')]/following-sibling::ion-col"))
		public MobileElement encListEDEncSpecialty;
		
		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency Department')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Status')]/following-sibling::ion-col"))
		public MobileElement encListEDEncStatus;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency Department')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Start')]/following-sibling::ion-col"))
		public MobileElement encListEDEncStartDate;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency Department')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'End')]/following-sibling::ion-col"))
		public MobileElement encListEDEncEndDate;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency Department')]/ancestor::div[contains(@class,'mainContinerSets')]//span[@id='starLayers']//ion-icon"))
		public MobileElement encListEDFavIcon;


		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Encounter Id')]/following-sibling::ion-col"))
		public MobileElement encListOPEncID;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Care Provider')]/following-sibling::ion-col"))
		public MobileElement encListOPEncCareProvider;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Specialty')]/following-sibling::ion-col"))
		public MobileElement encListOPEncSpecialty;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Status')]/following-sibling::ion-col"))
		public MobileElement encListOPEncStatus;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Start')]/following-sibling::ion-col"))
		public MobileElement encListOPEncStartDate;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'End')]/following-sibling::ion-col"))
		public MobileElement encListOPEncEndDate;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Outpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//span[@id='starLayers']//ion-icon"))
		public MobileElement encListOPFavIcon;


		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Encounter Id')]/following-sibling::ion-col"))
		public MobileElement encListContactEncID;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Care Provider')]/following-sibling::ion-col"))
		public MobileElement encListContactEncCareProvider;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Specialty')]/following-sibling::ion-col"))
		public MobileElement encListContactEncSpecialty;
		
		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Status')]/following-sibling::ion-col"))
		public MobileElement encListContactEncStatus;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Start')]/following-sibling::ion-col"))
		public MobileElement encListContactEncStartDate;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'End')]/following-sibling::ion-col"))
		public MobileElement encListContactEncEndDate;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Single')]/ancestor::div[contains(@class,'mainContinerSets')]//span[@id='starLayers']//ion-icon"))
		public MobileElement encListContactFavIcon;

		
		
		@FindBy(xpath = ("//Dxc-header/ion-toolbar//ion-icon[@name='star']"))
		public MobileElement clickMyWorklistFavIcon;
		
		@FindBy(xpath = ("//ion-icon[@name='star-outline']"))
		public MobileElement clickFavIcon;
		
		


		//WebPas
		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Encounter Id')]/following-sibling::ion-col"))
		public MobileElement encListIPEncID;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Care provider')]/following-sibling::ion-col"))
		public MobileElement encListIPEncCareProvider;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Specialty')]/following-sibling::ion-col"))
		public MobileElement encListIPEncSpecialty;
		
		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Status')]/following-sibling::ion-col"))
		public MobileElement encListIPEncStatus;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Start')]/following-sibling::ion-col"))
		public MobileElement encListIPEncStartDate;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'End')]/following-sibling::ion-col"))
		public MobileElement encListIPEncEndDate;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Inpatient')]/ancestor::div[contains(@class,'mainContinerSets')]//span[@id='starLayers']//ion-icon"))
		public MobileElement encListIPFavIcon;

		@FindBy(xpath = ("//PATIENTS-ENCOUNTER-LIST//*[contains(text(),'Emergency Department')]/ancestor::div[contains(@class,'mainContinerSets')]//*[contains(text(),'Care provider')]/following-sibling::ion-col"))
		public MobileElement encListEDEncCareProviderWP;
		
		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 EMERGENCY DEPARTMENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Care provider')]/following-sibling::span"))
		public MobileElement EDEncEncCareProviderWP;
		
		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/h2/span"))
		public MobileElement ipPreAdmitEncID;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label//ion-badge"))
		public MobileElement IPPreAdmitEncBadge;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Planned')]/following-sibling::span"))
		public MobileElement ipPreAdmitEncPlannedDate;
		
		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Care provider')]/following-sibling::span"))
		public MobileElement ipPreAdmitEncCareProvider;
		
		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'NEXT 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Specialty')]/following-sibling::span"))
		public MobileElement ipPreAdmitEncSpecialty;

		@FindBy(xpath = ("//ion-label/h2/span[contains(text(),'LAST 10 INPATIENT ENCOUNTERS')]/ancestor::ion-card/ion-card-content//ion-label/div//span[contains(text(),'Actual LOS')]/following-sibling::span"))
		public MobileElement ipEncActualLOS;
			
}
