package com.dedalus.restasssured_windows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
//import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;


public class RESTAssuredBase extends PreAndTest {

	public String sSqlServer;
	public String sJdbcDriver;
	public String sUserName;
	public String sPassword;
	public Properties prop;
	public String patID;
	public String UserSN;
	public String UserFN;
	public String serviceURL; 
	public String OSMUserSN;
	public String OSMUserFN;
	public String OSMpatID; 
	public String LCAUserName;
	public String LCAPassword;
	public String sOpenIDUrl;
	public String newID;//Globally declare
	

	
	public static RequestSpecification setLogs() {

		RequestLogSpecification log = RestAssured.given().auth().basic("admin", "Tuna@123").log();
		// System.out.println(log.all().contentType(getContentType()));
		return log.all().contentType(getContentType());
	}

	public static Response get(String URL) {
		return setLogs().when().get(URL);
	}

	public static Response get() {
		return setLogs().get();
	}

	public static Response getWithHeader(Map<String, String> headers, String URL) {

		return setLogs().when().headers(headers).get(URL);
	}

	public static Response post() {

		return setLogs().post();
	}

	public static Response post(String URL) {

		return setLogs().post(URL);
	}

	public static Response postWithBodyAsFile(File bodyFile) {

		return setLogs().body(bodyFile).post();
	}
	
	public static Response postWithBodyAsFile(Map<String, String> headers, File bodyFile, String URL) {

		return setLogs().headers(headers).body(bodyFile).post(URL);
	}


	public static Response postWithHeaderAndForm(Map<String, String> headers, JSONObject jsonObject, String URL) {

		return setLogs().headers(headers).body(jsonObject).post(URL);
	}

	public static Response postWithJsonAsBody(String jsonObject, String URL) {

		return setLogs().body(jsonObject).post(URL);
	}

	public static Response postWithHeaderAndJsonBody(Map<String, String> headers, String jsonObject, String URL) {
	//	RestAssured.given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/json+fhir", ContentType.TEXT)));
		return setLogs().when().headers(headers).body(jsonObject).post(URL);
	}

	public static Response postWithHeaderParam(Map<String, String> headers, String URL) {

		return setLogs().when().headers(headers).post(URL);
	}

	public static Response delete(String URL) {
		return setLogs().when().delete(URL);
	}

	public static Response deleteWithHeaderAndPathParam(Map<String, String> headers, JSONObject jsonObject,
			String URL) {

		return setLogs().when().headers(headers).body(jsonObject).delete(URL);
	}

	public static Response deleteWithHeaderAndPathParamWithoutRequestBody(Map<String, String> headers, String URL)
			throws Exception {
		return setLogs().when().headers(headers).delete(URL);
	}

	public static Response putWithHeaderAndBodyParam(Map<String, String> headers, JSONObject jsonObject, String URL) {

		return RestAssured.given().headers(headers).contentType(getContentType()).request().body(jsonObject).when()
				.put(URL);
	}

	private static ContentType getContentType() {

		return ContentType.JSON;

	}

	public static void verifyContentType(Response response, String type) {
		if (response.getContentType().toLowerCase().contains(type.toLowerCase())) {
			reportRequest("The Content type " + type + " matches the expected content type", "PASS");
		} else {
			reportRequest("The Content type " + type + " does not match the expected content type "
					+ response.getContentType(), "FAIL");
		}
	}

	public static void verifyResponseCode(Response response, int code) {
		if (response.statusCode() == code) {
			reportRequest("The status code " + code + " matches the expected code", "PASS");
		} else {
			reportRequest("The status code " + code + " does not match the expected code" + response.statusCode(),
					"FAIL");
		}
	}

	public static void verifyResponseTime(Response response, long time) {
		if (response.time() <= time) {
			reportRequest("The time taken " + response.time() + " with in the expected time", "PASS");
		} else {
			reportRequest("The time taken " + response.time() + " is greater than expected SLA time " + time, "FAIL");
		}
	}

	public static void verifyContentWithKey(Response response, String key, String expVal) {
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			String actValue = jsonPath.get(key);
			if (actValue.equalsIgnoreCase(expVal)) {
				reportRequest("The JSON response has value '" + expVal + "' as expected - " + actValue, "PASS");
			} else {
				reportRequest("The JSON response :" + actValue + " does not have the value " + expVal + " as expected. "
						+ actValue, "FAIL");
			}
		}
	}

	public static void verifyContentsWithKey(Response response, String key, String expVal) {
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			List<String> actValue = jsonPath.getList(key);
			if (actValue.get(0).equalsIgnoreCase(expVal)) {
				reportRequest("The JSON response has value " + expVal + " as expected. ", "PASS");
			} else {
				reportRequest(
						"The JSON response :" + actValue + " does not have the value " + expVal + " as expected. ",
						"FAIL");
			}
		}
	}

	public static List<String> getContentsWithKey(Response response, String key) {
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return jsonPath.getList(key);
		} else {
			return null;
		}
	}

	public static String getContentWithKey(Response response, String key) {
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return (String) jsonPath.get(key);
		} else {
			return null;
		}
	}

	public static HashMap<String, String> getHeaderMap(String token) {
		HashMap<String, String> alertHeaderMap = new HashMap<String, String>();

		alertHeaderMap.put("Accept", "application/json");
		alertHeaderMap.put("Content-Type", "application/json");
		alertHeaderMap.put("Authorization", token);
		return alertHeaderMap;

	}

	public static void listResponseValidation(List listOne, List listTwo, String elementName) {
		boolean b = listOne.size() == listTwo.size() && listOne.containsAll(listTwo);
		if (b) 
		{
			reportRequest("The actual '" +elementName+"' list " + listTwo + " matches the expected list" + listOne,
					"PASS");
		} 
		else {
			reportRequest("The actual '" +elementName+"' list " + listTwo + " does not match the expected list" + listOne,
					"FAIL");
		}
	}
	
	
	public static String[] getListToStringArray(List<String> list)
	{
	String[] string = new String[list.size()];
	Object[] obj = new Object[list.size()];
	for (int i = 0; i < list.size(); i++) {
		obj[i] = list.get(i);
		string[i]= obj[i].toString();
	}
	return string;
	}
	
	
	public static List<String> getStringArrayToList(String[] string) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < string.length; i++) {

			arrayList.add(string[i]);

		}
		return arrayList;
	}

	
	public File modifyFile(String service) throws ClassNotFoundException, SQLException, IOException {
		
		
		File fileToBeModified = new File("C:\\Users\\Nonadmin\\Desktop\\"+service+".txt");
        File newFile = new File("C:\\Users\\Nonadmin\\Desktop\\"+service+"New.txt");
        String oldContent = "";
         
        BufferedReader reader = null;
        FileWriter writer = null;
		//String query1 = "select * from Users where SURNAME = 'Si Automation' and FORENAME = 'User Six'";
		String query1 = "select * from Users where SURNAME = '"+UserSN+"' and FORENAME = '"+UserFN+"'";
		String query2 = "select * from Encounter where PatientOID in (select oid from Patient where PASID = '"+patID+"')";
		String query3 = "select * from Users where SURNAME = '"+OSMUserSN+"' and FORENAME = '"+OSMUserFN+"'";
		String query4 = "select * from Encounter where PatientOID in (select oid from Patient where PASID = '"+OSMpatID+"')";
		String query5 = "select * from Role where code = 'R8000' and status = 'A'";
		String query6 = "select top 1 * from Team where status = 'A'";
		String query7 = "select top 1 * from Specialty where status = 'A'";
		
		
		
		switch(service) {
		case "Alert":
			//Connection conn = dataBaseConnection();
			ResultSet databaseResult1 = getDatabaseResult(query1);
			String CPId = databaseResult1.getString("OID");
			ResultSet databaseResult2 = getDatabaseResult(query2);
			String EncID = databaseResult2.getString("MainIdentifier");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToBeModified),"UTF-8"));
            String line = reader.readLine();
             
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
                    
           	String[][] replacements = {{"EncounterMI", EncID}, 
                    {"PracOID", CPId}};
            String strOutput = oldContent;
            
          //loop over the array and replace
            for(String[] replacement: replacements) {
            strOutput = strOutput.replace(replacement[0], replacement[1]);
            }
            writer = new FileWriter(newFile);
            
            writer.write(strOutput);

            reader.close();             
            writer.close();
        	break;
		
		case "AllergyIA":
			ResultSet databaseRes1 = getDatabaseResult(query1);
			String AllergyCPId = databaseRes1.getString("OID");
			ResultSet databaseRes2 = getDatabaseResult(query2);
			String AllergyEncID = databaseRes2.getString("MainIdentifier");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToBeModified),"UTF-8"));
            String line1 = reader.readLine();
             
            while (line1 != null) 
            {
                oldContent = oldContent + line1 + System.lineSeparator();
                line1 = reader.readLine();
            }
                    
           	String[][] allergyreplacements = {{"EncounterMI",AllergyEncID}, 
                    {"PracOID", AllergyCPId},{"PATPASID",patID},{"SerURL",serviceURL}};
            String AllergystrOutput = oldContent;
            
          //loop over the array and replace
            for(String[] replacement: allergyreplacements) {
            	AllergystrOutput = AllergystrOutput.replace(replacement[0], replacement[1]);
            }
            writer = new FileWriter(newFile);
            
            writer.write(AllergystrOutput);

            reader.close();             
            writer.close();

			
			break;
		
		case "Problem":
			ResultSet databaseR1 = getDatabaseResult(query3);
			String PblmCPId = databaseR1.getString("OID");
			String PblmUserSN = databaseR1.getString("SURNAME");
			String PblmUserFN = databaseR1.getString("FORENAME");
			ResultSet databaseR2 = getDatabaseResult(query4);
			String PblmEncID = databaseR2.getString("MainIdentifier");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToBeModified),"UTF-8"));
            String l1 = reader.readLine();
             
            while (l1 != null) 
            {
                oldContent = oldContent + l1 + System.lineSeparator();
                l1 = reader.readLine();
            }
                    
           	String[][] pblmreplacements = {{"EncounterMI",PblmEncID}, 
                    {"PracOID", PblmCPId},{"PATPASID",OSMpatID},{"SerURL",serviceURL},{"UserSN",PblmUserSN},{"UserFN",PblmUserFN}};
            String PblmstrOutput = oldContent;
            
          //loop over the array and replace
            for(String[] replacement: pblmreplacements) {
            	PblmstrOutput = PblmstrOutput.replace(replacement[0], replacement[1]);
            }
            writer = new FileWriter(newFile);
            
            writer.write(PblmstrOutput);

            reader.close();             
            writer.close();

			break;

		case "Procedure":
			ResultSet databaseRS1 = getDatabaseResult(query3);
			String ProcedureCPId = databaseRS1.getString("OID");
			String ProcedureUserSN = databaseRS1.getString("SURNAME");
			String ProcedureUserFN = databaseRS1.getString("FORENAME");
			ResultSet databaseRS2 = getDatabaseResult(query4);
			String ProcedureEncID = databaseRS2.getString("MainIdentifier");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToBeModified),"UTF-8"));
            String ln1 = reader.readLine();
             
            while (ln1 != null) 
            {
                oldContent = oldContent + ln1 + System.lineSeparator();
                ln1 = reader.readLine();
            }
                    
           	String[][] procreplacements = {{"EncounterMI",ProcedureEncID}, 
                    {"PracOID", ProcedureCPId},{"PATPASID",OSMpatID},{"SerURL",serviceURL},{"UserSN",ProcedureUserSN},{"UserFN",ProcedureUserFN}};
            String ProcedurestrOutput = oldContent;
            
          //loop over the array and replace
            for(String[] replacement: procreplacements) {
            	ProcedurestrOutput = ProcedurestrOutput.replace(replacement[0], replacement[1]);
            }
            writer = new FileWriter(newFile);
            
            writer.write(ProcedurestrOutput);

            reader.close();             
            writer.close();

			break;

		case "Forms":
			ResultSet dbRes1 = getDatabaseResult(query1);
			String FormsCPId = dbRes1.getString("OID");
			String FormUserSN = dbRes1.getString("SURNAME");
			String FormUserFN = dbRes1.getString("FORENAME");
			ResultSet dbRes2 = getDatabaseResult(query2);
			String FormsEncID = dbRes2.getString("MainIdentifier");
			ResultSet dbRes3 = getDatabaseResult(query5);
			String AuthRole = dbRes3.getString("OID");
			ResultSet dbRes4 = getDatabaseResult(query6);
			String AuthTeam = dbRes4.getString("OID");
			ResultSet dbRes5 = getDatabaseResult(query7);
			String AuthSplty = dbRes5.getString("OID");
			
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToBeModified),"UTF-8"));
            String lne1 = reader.readLine();
             
            while (lne1 != null) 
            {
                oldContent = oldContent + lne1 + System.lineSeparator();
                lne1 = reader.readLine();
            }
                    
            LocalDateTime dateval = LocalDateTime.now();
    		System.out.println(dateval);
    		String dateteime = dateval.toString().split("\\.")[0];
    		String newDate = dateteime+"Z";  		
            
           	String[][] formreplacements = {{"EncounterMI",FormsEncID}, 
                    {"PracOID", FormsCPId},{"PATPASID",patID},{"SerURL",serviceURL},
                    {"UserSN",FormUserSN},{"UserFN",FormUserFN},{"DateTimeZone",newDate},
                    {"AuthRoleOID",AuthRole},{"AuthTeamOID",AuthTeam},{"AuthSpltyOID",AuthSplty}};
           	
            String FormstrOutput = oldContent;
            
          //loop over the array and replace
            for(String[] replacement: formreplacements) {
            	FormstrOutput = FormstrOutput.replace(replacement[0], replacement[1]);
            }
            writer = new FileWriter(newFile);
            
            writer.write(FormstrOutput);

            reader.close();             
            writer.close();
			break;		
		}
		return newFile;
	}

	public ResultSet getDatabaseResult(String sqlStr) throws ClassNotFoundException, SQLException {
		Class.forName(sJdbcDriver); // "com.microsoft.sqlserver.jdbc.SQLServerDriver");		
		Connection conn = DriverManager.getConnection(sSqlServer,sUserName,sPassword); // "jdbc:sqlserver://10.1.72.73:4371;databaseName=LORENZO","ATUser","Mond@y09");
		Statement st = conn.createStatement();
		ResultSet result = st.executeQuery(sqlStr);
		System.out.println(result.next());
		return result;
	}
	
	public Response postWithBodyAsTextFile(File newFile, HashMap<String, String> alertHeaderMap, String url) throws InterruptedException {
        Response resp = RestAssured.given().headers(alertHeaderMap).body(newFile).post(url);
        Thread.sleep(5000);
        System.out.println(resp.statusCode());
        String respBody = resp.getHeader("Location");     
        System.out.println(respBody);
        String[] bits = respBody.split("/");
        newID = bits[bits.length-1];
       
        if (url.contains("questionnaireresponse")) {
            bits = respBody.split("/");
            newID = bits[bits.length-3];
        }
        System.out.println(newID);
        reportStep("Newly created record ID is "+newID+" and the Response boody is"+respBody+"", "PASS");
        return null;
    }
	
	public Response postputWithBodyAsTextFile(File newFile, HashMap<String, String> alertHeaderMap, String url) throws InterruptedException {
        Response resp = RestAssured.given().headers(alertHeaderMap).body(newFile).post(url);
        Thread.sleep(5000);
        System.out.println(resp.statusCode());
        String respBody = resp.getHeader("Location");     
        System.out.println(respBody);
        String[] bits = respBody.split("/");
        newID = bits[bits.length-1];
       
        if (url.contains("questionnaireresponse")) {
            bits = respBody.split("/");
            newID = bits[bits.length-3];
        }
        System.out.println(newID);
        reportStep("Newly created record ID is "+newID+" and the Response boody is"+respBody+"", "PASS");
    
        return null;
    }
	//Experiment:
	public Response postputWithBodyAsTextFile1(File newidFile, HashMap<String, String> alertHeaderMap, String url) throws InterruptedException {
       
        Response resp1 = RestAssured.given().headers(alertHeaderMap).body(newidFile).put(url+"/"+newID);
        System.out.println(resp1);
        System.out.println(resp1.statusCode());
        reportStep("Reaction is successfully removed", "PASS");
        
        return null;
    }
	
	/*public Response postWithBodyAsTextFile(File newFile, HashMap<String, String> alertHeaderMap, String url) {
		Response resp = RestAssured.given().headers(alertHeaderMap).body(newFile).post(url);
		System.out.println(resp.statusCode());
    	String respBody = resp.getHeader("Location");  	
    	System.out.println(respBody);
    	String[] bits = respBody.split("/");
    	String newID = bits[bits.length-1];
    	System.out.println(newID);
    	reportStep("Newly created record ID is "+newID+" and the Response boody is"+respBody+"", "PASS");
		return null;
	}*/


	public RESTAssuredBase() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
			sSqlServer = prop.getProperty("sqlserver");
			sJdbcDriver = prop.getProperty("jdbcdriver");
			sUserName = prop.getProperty("username");
			sPassword = prop.getProperty("password");
			patID = prop.getProperty("PatientID");
			UserFN = prop.getProperty("UserForeName");
			UserSN = prop.getProperty("UserSurName");
			serviceURL = prop.getProperty("serviceURL");
			OSMUserFN = prop.getProperty("OSMUserForeName");
			OSMUserSN = prop.getProperty("OSMUserSurName");
			OSMpatID = prop.getProperty("OSMPatientID");
			LCAUserName = prop.getProperty("LCAusername");
			LCAPassword = prop.getProperty("LCApassword");
			sOpenIDUrl = prop.getProperty("OpenIDURL");
				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public	String apiExecute(String Variable, int JobID, int SuiteID, int CPID, int StepID, HashMap<String, String> alertHeaderMap)
	{
//		HashMap<String, String> result = new HashMap<>();
		String result ="";
		Response response;
		JsonPath jsonPath;

	try {
			response = getWithHeader(alertHeaderMap, "https://api.virtuoso.qa/api/executions/"+JobID+"/suites/"+SuiteID+"/library-checkpoints/"+CPID+"/steps/"+StepID+"");
			jsonPath = response.jsonPath();
			result =  jsonPath.get("item.sideEffects.usedData."+Variable+"");
		} 
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return result;
	}

	public Map<String, String> stringTohashMap(String strVariables)
	{
    Map<String, String> hashMap = new HashMap<String, String>(); 
    strVariables = strVariables.substring(1, strVariables.length() - 1);
    // split the String by a comma 
    String parts[] = strVariables.split(","); 

    // iterate the parts and add them to a HashMap 
    for (String part : parts) { 

   String stuhasMapData[] = part.split(":"); 
   String strhaspMapKey = stuhasMapData[0]; 
   strhaspMapKey = strhaspMapKey.substring(1, strhaspMapKey.length() - 1);
   String strhaspMapValue = stuhasMapData[1]; 
   strhaspMapValue = strhaspMapValue.substring(1, strhaspMapValue.length() - 1);

   hashMap.put(strhaspMapKey, strhaspMapValue);
							}	
	return hashMap; 
	}
	

	public static String getExposedValue(Response response,String CPType, String Expvariable, String ReportVariable) {

	     	String value = "";

			JSONObject data,chkpoint;

			try {

				data = new JSONObject(response.asString());

				 // Get the first dynamic key in the 'journeys' 

		        String dynamicKey = (String) data.getJSONObject("item").getJSONObject("journeys").keys().next();
	 
		        JSONObject dynamicJourneyValue = data.getJSONObject("item").getJSONObject("journeys").getJSONObject(dynamicKey).getJSONObject("lastExecution").getJSONObject("report");

		       // JSONObject chkpoint = data.getJSONObject("item").getJSONObject("journeys").getJSONObject(dynamicKey).getJSONObject("lastExecution").getJSONObject("report");

		        if(CPType == "LCP") {

		        	chkpoint = dynamicJourneyValue.getJSONObject("libraryCheckpointExecutionReports");

		        }else

		        {

		            chkpoint = dynamicJourneyValue.getJSONObject("checkpoints");

		        }

		        Iterator<String> keys = chkpoint.keys();

		        while(keys.hasNext()) {

		            String key = keys.next();

		            if (chkpoint.get(key) instanceof JSONObject) {

		                  JSONObject steps = chkpoint.getJSONObject(key).getJSONObject("steps");

		                  Iterator<String> keys1 = steps.keys();

		                  while(keys1.hasNext()) {

		                	  String CPkey = keys1.next();

		                	  if(steps.get(CPkey) instanceof JSONObject) {

		                		  JSONObject out = steps.getJSONObject(CPkey).getJSONObject("sideEffects").getJSONObject("usedData");

		                		  if(out.has(Expvariable)) {

		                			  value = (String) out.get(Expvariable);
		                			  System.out.println(ReportVariable +": "+out.get(Expvariable));

		                		  }

		                	  }

		                  }

		            }

		        }
	 
			} catch (JSONException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}
	     	return value;

	     }

	
}
