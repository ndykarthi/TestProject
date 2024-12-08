package com.dedalus.genericappmethods_windows;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dedalus.restasssured.RESTAssuredBase;
import com.dedalus.utilities.Reporter;

import io.appium.java_client.MobileElement;



public class GenericAppMethods extends Reporter {

  public RemoteWebDriver driver;

  public String sBinaryPath;
  public String sSqlServer;
  public String sJdbcDriver;
  public String sUserName;
  public String sPassword;
  public String sUserNameApp;
  public String sPasswordApp;
  public Properties prop;
  public String sApiUrl;
  public String url;
  public CloseableHttpResponse closeableHttpResponse;
  public String sPin;
  public String sCAUKdriver;
  public String slcmdriver;
  public String sCAUKBinaryPath;
  public String sCAUKPin;
  public String sChromedriverPath;
 // public String sConnectionstringURL;
  public String dataFileName, dataFileType;
  public WebDriverWait wait;
  public String LZOUserNameApp;
  public String LZOPasswordApp;
  public String WebPASUserNameApp;
  public String WebPASPasswordApp;
  public String SORLicence;
  public String SORCopyright;

  public GenericAppMethods() {
    prop = new Properties();
    try {
      prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
      sBinaryPath = prop.getProperty("binarypath");
      sSqlServer = prop.getProperty("sqlserver");
      sJdbcDriver = prop.getProperty("jdbcdriver");
      sUserName = prop.getProperty("username");
      sPassword = prop.getProperty("password");
      sApiUrl = prop.getProperty("apiURL");
      sPin = prop.getProperty("pin");
      sCAUKdriver = prop.getProperty("CAUKdriver");
      slcmdriver = prop.getProperty("lcmdriver");
      sCAUKBinaryPath = prop.getProperty("CAUKbinarypath");
      sCAUKPin = prop.getProperty("CAUKPin");
      sUserNameApp = prop.getProperty("usernameApp");
      sPasswordApp = prop.getProperty("passwordApp");
      sChromedriverPath = prop.getProperty("ChromedriverPath");
   //   sConnectionstringURL= prop.getProperty("ConnectionstringURL");
      LZOUserNameApp = prop.getProperty("LZOUsernameApp");
		LZOPasswordApp = prop.getProperty("LZOPasswordApp");
		WebPASUserNameApp = prop.getProperty("WebPASUsernameApp");
		WebPASPasswordApp = prop.getProperty("WebPASPasswordApp");
		 SORLicence = prop.getProperty("SORLicence");
		 SORCopyright = prop.getProperty("SORCopyright");

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void startAppWithDebug(String applicationName) throws FileNotFoundException, IOException, InterruptedException {
    try {
      Runtime rt = Runtime.getRuntime();
      // Process proc = rt.exec("taskkill /im chrome.exe /f /t");
      Process proc1 = rt.exec("taskkill /im ClinicalAide.exe /f /t");
      // Thread.sleep(5000);
      System.setProperty("webdriver.chrome.driver", sChromedriverPath);
      ChromeOptions chromeoptions = new ChromeOptions();
      chromeoptions.setBinary(sCAUKBinaryPath);
      driver = new ChromeDriver(chromeoptions);
      Thread.sleep(6000);
      driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
      reportStep("The application launched successfully", "PASS");
    } catch (WebDriverException e) {
      reportStep("The application launched successfully", "FAIL");
    }
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.numberOfWindowsToBe(2));

    String Parent_window = driver.getWindowHandle();
    Set<String> handles = driver.getWindowHandles();
    //System.out.println(handles);
    for (String handle : handles) {
      driver.switchTo().window(handle);
      //System.out.println(driver.switchTo().window(handle));
      if (driver.getTitle().equalsIgnoreCase("ClinicalAide")) {
        System.out.println("true");
      } else {
        System.out.println("false");
        driver.close();
      }
    }
    // Adding Connection
    driver.findElement(By.xpath("//span[@class='wifisymbol']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//ion-button[contains(text(),'Add Connection')]")).click();
    Thread.sleep(2000);
    WebElement url = driver.findElement(By.xpath("//input[@placeholder='URL ']"));
    // url.sendKeys("./assets/config/config.lorenzo-sit-openid.json");
    url.sendKeys("./assets/config/config.lorenzo-sit1058-openid.json");

    Thread.sleep(2000);
    driver.findElement(By.xpath("//span[@class='alert-button-inner sc-ion-alert-md'][text()='Add']")).click();
    Thread.sleep(2000);
    WebElement back = driver.findElement(By.xpath("//ion-back-button/button/span"));
    actionClick(back);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//ion-button[contains(text(),'Login')]")).click();
    Thread.sleep(10000);
    Thread.sleep(2000);

    Set<String> lastWindow = driver.getWindowHandles();
   // System.out.println(lastWindow);

    int l = lastWindow.size() - 1;
    int inc = -1;
    for (String win : lastWindow) {
      inc++;
      if (inc == l) {
        driver.switchTo().window(win);
        System.out.println(win);
      }
    }
    Thread.sleep(5000);
   // System.out.println(driver.findElement(By.xpath("//*[contains(text(),'name')]")));
    WebElement userName1 = driver.findElement(By.xpath("//*[contains(text(),'name')]"));
    explicitWait(userName1);
    Actions act = new Actions(driver);
    act.moveToElement(userName1);
    WebElement name = driver.findElement(By.id("UserName"));
    explicitWait(name);
    name.sendKeys(sUserNameApp);
    WebElement pWord = driver.findElement(By.id("Password"));
    explicitWait(pWord);
    pWord.clear();
    pWord.sendKeys(sPasswordApp);
    explicitWait(driver.findElementByClassName("btn-login"));
    driver.findElementByClassName("btn-login").click();
    Set<String> allWindows = driver.getWindowHandles();
    //System.out.println(driver.getWindowHandles());
    for (String currentWindow : allWindows) {
      driver.switchTo().window(currentWindow);
      System.out.println(driver.getTitle());
    }
  }

  

  public void startApp_kart(String SOR) throws FileNotFoundException, IOException, InterruptedException {
   
      try {

        Runtime rt = Runtime.getRuntime();

        // Process proc = rt.exec("taskkill /im chrome.exe /f /t");

        Process proc1 = rt.exec("taskkill /im ClinicalAide.exe /f /t");

        // Thread.sleep(5000);

        System.setProperty("webdriver.chrome.driver", sChromedriverPath);

        ChromeOptions chromeoptions = new ChromeOptions();

        chromeoptions.setBinary(sCAUKBinaryPath);

        driver = new ChromeDriver(chromeoptions);

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

//        reportStep("The application launched successfully", "PASS");

      } catch (WebDriverException e) {

        reportStep("The application launched successfully", "FAIL");

      }

      Thread.sleep(4000);
   
      String Parent_window = driver.getWindowHandle();

      Set<String> handles = driver.getWindowHandles();

      //System.out.println(handles);

      for (String handle : handles) {

        driver.switchTo().window(handle);

        //System.out.println(driver.switchTo().window(handle));

        if (driver.getTitle().equalsIgnoreCase("ClinicalAide")) {

          System.out.println("true");

        } else {

          System.out.println("false");

          driver.close();

        }

      }

      Thread.sleep(10000);

      driver.findElement(By.xpath("//span[@class='wifisymbol']")).click();

      Thread.sleep(5000);

      driver.findElement(By.xpath("//ion-button[contains(text(),'Add Connection')]")).click();

      Thread.sleep(2000);

      WebElement url = driver.findElement(By.xpath("//input[@placeholder='URL ']"));

      // url.sendKeys("./assets/config/config.lorenzo-sit1058-openid.json");

      //url.sendKeys("./assets/config/config.lorenzo-SIT-RLY-Cloud.json");

      //url.sendKeys("./assets/config/config.lorenzo-SIT-dxcappchng6049.json");

      //
   
      if(SOR.equals("LORENZO"))

      {

//      	url.sendKeys(sConnectionstringURL);

      }

      else if (SOR.equals("WEBPAS"))

      {

      	url.sendKeys("./assets/config/config.webpas-koolaid-openid.json");

      }

      Thread.sleep(2000);

      driver.findElement(By.xpath("//span[@class='alert-button-inner sc-ion-alert-md'][text()='Add']")).click();

      Thread.sleep(2000);

  ///    WebElement back = driver.findElement(By.xpath("//ion-back-button/button/span"));

      WebElement back = driver.findElement(By.xpath("//ion-back-button"));

      actionClick(back);
   
      if(SOR.equals("LORENZO"))

      {   

      Set<String> windowHandles2 = driver.getWindowHandles();

      String firstWinHandle2 = driver.getWindowHandle();

      System.out.println(firstWinHandle2);
   
      driver.findElement(By.xpath("//ion-button[contains(text(),'Login')]")).click();

      Thread.sleep(12000);

      Set<String> windowHandles = driver.getWindowHandles();

      String firstWinHandle = driver.getWindowHandle();

      windowHandles.remove(firstWinHandle);
   
      String winHandle = windowHandles.iterator().next();

      if (winHandle != firstWinHandle) {

        String secondWinHandle = winHandle;

        driver.switchTo().window(secondWinHandle);

        /* System.out.println(driver.getTitle()); */

       // System.out.println(driver.findElement(By.xpath("//*[contains(text(),'name')]")));

        WebElement userName1 = driver.findElement(By.xpath("//*[contains(text(),'name')]"));

        explicitWait(userName1);

        Actions act = new Actions(driver);

        act.moveToElement(userName1);

        WebElement name = driver.findElement(By.id("UserName"));

        explicitWait(name);

        name.sendKeys(sUserNameApp);

        WebElement pWord = driver.findElement(By.id("Password"));

        explicitWait(pWord);

        pWord.clear();

        pWord.sendKeys(sPasswordApp);

        explicitWait(driver.findElementByClassName("btn-login"));

        driver.findElementByClassName("btn-login").click();

        Set<String> allWindows = driver.getWindowHandles();
   
        for (String currentWindow : allWindows) {

          driver.switchTo().window(currentWindow);

          System.out.println(driver.getTitle());

        }

        Thread.sleep(10000);

      }

      }

      if (SOR.equals("WEBPAS"))

      {

      	driver.findElement(By.xpath("//ion-button[contains(text(),'Login')]")).click();

          Thread.sleep(12000);

          Set<String> windowHandles = driver.getWindowHandles();

          String firstWinHandle = driver.getWindowHandle();

          windowHandles.remove(firstWinHandle);

          String winHandle = windowHandles.iterator().next();

          if (winHandle != firstWinHandle) {

            String secondWinHandle = winHandle;

            driver.switchTo().window(secondWinHandle);

      	driver.findElementByXPath("//input[@name='loginfmt']").sendKeys("karthi@dc4hapimpilot.onmicrosoft.com");

  		driver.findElementByXPath("//input[@value='Next']").click();

  		Thread.sleep(3000);

  		driver.findElementByXPath("//input[@name='passwd']").sendKeys("kbca@webpas0");

  		driver.findElementByXPath("//input[@value='Sign in']").click();

  		Thread.sleep(3000);

  		driver.findElementByXPath("//input[@value='Yes']").click();

  		Thread.sleep(10000);

//  		reportStep("Connection established", "PASS");
   
  		Thread.sleep(2000);

          }

      	/*

      	 WebElement userName1 = driver.findElement(By.xpath("//*[@id='username']"));

           explicitWait(userName1);

           Actions act = new Actions(driver);

           act.moveToElement(userName1);

           userName1.click();

           act.click().sendKeys("karthi").build().perform();

//          WebElement name = driver.findElement(By.id("username"));

//          explicitWait(name);

//           userName1.sendKeys("karthi");

           WebElement pWord = driver.findElement(By.id("password"));

           act.moveToElement(pWord);

           act.click().sendKeys("kbca@webpas0").build().perform();
   
           explicitWait(driver.findElementByXPath("//*[contains(text(),'Login')]"));

           WebElement lgn = driver.findElementByXPath("//*[contains(text(),'Login')]");

           act.moveToElement(lgn);

           act.click().build().perform();*/

      }

    }

  

  public void startApp(String SOR,String SORConfigURL) throws FileNotFoundException, IOException, InterruptedException {

    try {
      Runtime rt = Runtime.getRuntime();
      // Process proc = rt.exec("taskkill /im chrome.exe /f /t");
      Process proc1 = rt.exec("taskkill /im ClinicalAide.exe /f /t");
      // Thread.sleep(5000);
      System.setProperty("webdriver.chrome.driver", sChromedriverPath);
      ChromeOptions chromeoptions = new ChromeOptions();
      chromeoptions.setBinary(sCAUKBinaryPath);
      driver = new ChromeDriver(chromeoptions);
      driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//      reportStep("The application launched successfully", "PASS");
    } catch (WebDriverException e) {
      reportStep("The application launched successfully", "FAIL");
    }
    Thread.sleep(4000);

    String Parent_window = driver.getWindowHandle();
    Set<String> handles = driver.getWindowHandles();
    //System.out.println(handles);
    for (String handle : handles) {
      driver.switchTo().window(handle);
      //System.out.println(driver.switchTo().window(handle));
      if (driver.getTitle().equalsIgnoreCase("ClinicalAide")) {
        System.out.println("true");
      } else {
        System.out.println("false");
        driver.close();
      }
    }
    Thread.sleep(10000);
    driver.findElement(By.xpath("//span[@class='wifisymbol']")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//ion-button[contains(text(),'Add Connection')]")).click();
    Thread.sleep(2000);
    WebElement url = driver.findElement(By.xpath("//input[@placeholder='URL ']"));
    // url.sendKeys("./assets/config/config.lorenzo-sit1058-openid.json");
    //url.sendKeys("./assets/config/config.lorenzo-SIT-RLY-Cloud.json");
    //url.sendKeys("./assets/config/config.lorenzo-SIT-dxcappchng6049.json");
    
    //

    if(SOR.equals("LORENZO"))
    {
    	url.sendKeys(SORConfigURL);
    	sUserNameApp = LZOUserNameApp;
    	sPasswordApp = 	LZOPasswordApp;	
    }
    else if (SOR.equals("WEBPAS"))
    {
    //	url.sendKeys("./assets/config/config.webpas-koolaid.json");
    //	url.sendKeys("./assets/config/config.webpas-koolaid-openid.json");
    	url.sendKeys(SORConfigURL);
    	sUserNameApp = WebPASUserNameApp;
    	sPasswordApp = 	WebPASPasswordApp;
    }
    Thread.sleep(2000);
    driver.findElement(By.xpath("//span[@class='alert-button-inner sc-ion-alert-md'][text()='Add']")).click();
    Thread.sleep(2000);
///    WebElement back = driver.findElement(By.xpath("//ion-back-button/button/span"));
    WebElement back = driver.findElement(By.xpath("//ion-back-button"));
    actionClick(back);

    if(SOR.equals("LORENZO"))
    {   
    Set<String> windowHandles2 = driver.getWindowHandles();
    String firstWinHandle2 = driver.getWindowHandle();
    System.out.println(firstWinHandle2);

    driver.findElement(By.xpath("//ion-button[contains(text(),'Login')]")).click();
    Thread.sleep(12000);
    Set<String> windowHandles = driver.getWindowHandles();
    String firstWinHandle = driver.getWindowHandle();
    windowHandles.remove(firstWinHandle);

    String winHandle = windowHandles.iterator().next();
    if (winHandle != firstWinHandle) {
      String secondWinHandle = winHandle;
      driver.switchTo().window(secondWinHandle);
      /* System.out.println(driver.getTitle()); */
     // System.out.println(driver.findElement(By.xpath("//*[contains(text(),'name')]")));
      WebElement userName1 = driver.findElement(By.xpath("//*[contains(text(),'name')]"));
      explicitWait(userName1);
      Actions act = new Actions(driver);
      act.moveToElement(userName1);
      WebElement name = driver.findElement(By.id("UserName"));
      explicitWait(name);
      name.sendKeys(sUserNameApp);
      WebElement pWord = driver.findElement(By.id("Password"));
      explicitWait(pWord);
      pWord.clear();
      pWord.sendKeys(sPasswordApp);
      explicitWait(driver.findElementByClassName("btn-login"));
      driver.findElementByClassName("btn-login").click();
      Set<String> allWindows = driver.getWindowHandles();

      for (String currentWindow : allWindows) {
        driver.switchTo().window(currentWindow);
        System.out.println(driver.getTitle());
      }
      Thread.sleep(10000);
    }
    }
    
    if (SOR.equals("WEBPAS"))
    {      
         	driver.findElement(By.xpath("//ion-button[contains(text(),'Login')]")).click();
             Thread.sleep(5000);
             Set<String> windowHandles = driver.getWindowHandles();
             ArrayList<String> tabs = new ArrayList<>(windowHandles);
             driver.switchTo().window(tabs.get(1)); 
         	driver.findElementByXPath("//input[@name='loginfmt']").sendKeys(sUserNameApp);
     		driver.findElementByXPath("//input[@value='Next']").click();
     		Thread.sleep(2000);
     		driver.findElementByXPath("//input[@name='passwd']").sendKeys(sPasswordApp);
     		driver.findElementByXPath("//input[@value='Sign in']").click();
     		Thread.sleep(2000);
     		driver.findElementByXPath("//input[@value='Yes']").click();
     		Thread.sleep(5000);
     		driver.switchTo().window(tabs.get(0));   
     }
   }

  public void startApp1(String applicationName) throws FileNotFoundException, IOException, InterruptedException {

    try {
      Runtime rt = Runtime.getRuntime();
      // Process proc = rt.exec("taskkill /im chrome.exe /f /t");
      Process proc1 = rt.exec("taskkill /im ClinicalAide.exe /f /t");
      // Thread.sleep(5000);

      // ***parallel run*****
      /*
       * DesiredCapabilities cap=new DesiredCapabilities(); cap.setCapability("applicationName",
       * applicationName); cap.setCapability("task kill", proc1); ChromeOptions options = new
       * ChromeOptions(); options.setBinary("C:\\Program Files\\ClinicalAide\\ClinicalAide.exe");
       * options.merge(cap); driver = new RemoteWebDriver(new URL("http://172.26.6.17:4444/wd/hub"),
       * options); driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       */
      // ***parallel run end *****

      System.setProperty("webdriver.chrome.driver", sChromedriverPath);
      ChromeOptions chromeoptions = new ChromeOptions();
      chromeoptions.setBinary(sCAUKBinaryPath);
      driver = new ChromeDriver(chromeoptions);
      driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
      reportStep("The application launched successfully", "PASS");
    } catch (WebDriverException e) {
      reportStep("The application launched successfully", "FAIL");
    }

    WebDriverWait wait = new WebDriverWait(driver, 30);

    System.out.println(driver.getTitle());
    driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
    Thread.sleep(6000);
    Set<String> windowHandles1 = driver.getWindowHandles();
    String firstWinHandle1 = driver.getWindowHandle();
    windowHandles1.remove(firstWinHandle1);
    String winHandle1 = windowHandles1.iterator().next();
    if (winHandle1 != firstWinHandle1) {
      String secondWinHandle1 = winHandle1;
      driver.switchTo().window(secondWinHandle1);
      System.out.println(driver.getTitle());
      /* System.out.println(driver.getTitle()); */
     // System.out.println(driver.findElement(By.xpath("//*[contains(text(),'name')]")));
      WebElement userName1 = driver.findElement(By.xpath("//*[contains(text(),'name')]"));
      explicitWait(userName1);
      // Thread.sleep(6000);
      Actions act = new Actions(driver);
      act.moveToElement(userName1);
      // act.sendKeys("sram").build().perform();
      // Thread.sleep(4000);
      WebElement name = driver.findElement(By.id("UserName"));
      // System.out.println(name);
      // Thread.sleep(3000);
      // name.clear();
      // Thread.sleep(3000);
      explicitWait(name);
      name.sendKeys(sUserNameApp);
      WebElement pWord = driver.findElement(By.id("Password"));
      explicitWait(pWord);
      // System.out.println(pWord);
      // Thread.sleep(3000);
      pWord.clear();
      // Thread.sleep(3000);
      pWord.sendKeys(sPasswordApp);
      // Thread.sleep(5000);
      explicitWait(driver.findElementByClassName("btn-login"));
      driver.findElementByClassName("btn-login").click();
      // Thread.sleep(5000);
      // String firstWinHandles = driver.getWindowHandle();
      // driver.switchTo().window("0");
      Set<String> allWindows = driver.getWindowHandles();
     // System.out.println(driver.getWindowHandles());

      for (String currentWindow : allWindows) {
        driver.switchTo().window(currentWindow);
        System.out.println(driver.getTitle());

      }

    }
  }


  public void type(WebElement ele, String data, String field) {
    try {
      ele.clear();
      ele.sendKeys(data);
      // String x = ""+ele;
      // System.out.println(ele.getAttribute("value"));
      reportStep("The data: " + data + " entered successfully in the field :" + field, "PASS");
    } catch (InvalidElementStateException e) {
      reportStep("The data: " + data + " could not be entered in the field :" + field, "FAIL");
    } catch (WebDriverException e) {
      reportStep("Unknown exception occured while entering " + data + " in the field :" + field, "FAIL");
    }
  }

  public void typeKeys(WebElement ele, Keys act) {
    try {
      // ele.clear();
      ele.sendKeys(act);
      // String x = ""+ele;

      String x = "" + ele;
      reportStep("The data: " + act + " entered successfully in the field :" + ele, "PASS");
    } catch (InvalidElementStateException e) {
      reportStep("The data: " + act + " could not be entered in the field :" + ele, "FAIL");
    } catch (WebDriverException e) {
      reportStep("Unknown exception occured while entering " + act + " in the field :" + ele, "FAIL");
    }
  }

  public void click(WebElement ele) {
    String text = "";
    try {
      WebDriverWait wait = new WebDriverWait(driver, 20);
      wait.until(ExpectedConditions.elementToBeClickable(ele));
      // text = ele.getText();
      ele.click();
      // reportStep("The element :" + text + " is clicked.", "PASS");
    } catch (InvalidElementStateException e) {
      reportStep("The element: " + text + " could not be clicked", "FAIL");
    } catch (WebDriverException e) {
      reportStep("Unknown exception occured while clicking in the field :", "FAIL");
    }
  }
  
  public void click(WebElement ele,String field) {
//	    String text = "";
	    try {
	      WebDriverWait wait = new WebDriverWait(driver, 20);
	      wait.until(ExpectedConditions.elementToBeClickable(ele));
	      // text = ele.getText();
	      ele.click();
	       reportStep("The element :" + field + " is clicked.", "PASS");
	    } catch (InvalidElementStateException e) {
	      reportStep("The element: " + field + " could not be clicked", "FAIL");
	    } catch (WebDriverException e) {
	      reportStep("Unknown exception occured while clicking in the field :", "FAIL");
	    }
	  }

  public void actionClick(WebElement ele) {
    String text = "";
    try {
      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(ExpectedConditions.elementToBeClickable(ele));
      text = ele.getText();
      // JavascriptExecutor js = (JavascriptExecutor)driver;
      // js.executeScript("arguments[0].scrollIntoView(true);", ele);
      Actions action = new Actions(driver);
      action.moveToElement(ele).click().perform();
      reportStep("The element :" + text + "  is clicked.", "PASS");
      takeSnap();
    } catch (InvalidElementStateException e) {
      reportStep("The element: " + text + " could not be clicked", "FAIL");
    } catch (WebDriverException e) {
      reportStep("The element " + ele + "is not available", "FAIL");
    }
  }
     public static boolean isElementDisplayed(WebElement element) {
    	 boolean b=false;
    	 try {
    		 b=element.isDisplayed();
    		 b=true;
    	 }
    	 catch(NoSuchElementException ne)
    	 {
    		 
    		 System.out.println("Element is not found" +ne);
    		 
    	 }
    	 return b;
     }
  public void actionClick1(WebElement ele1, WebElement ele2) throws InterruptedException {
	   	    try {
                 for(int i=0;i<=6;i++) {
                	 ele1.click();
                	 Thread.sleep(2000);
                	 
                	 if(!isElementDisplayed(ele2))
                	 {
                		 ele1.click();
                		
                	 }
                	 else
                	 {
                		 System.out.println("Element is displayed");
                		 break;
                	 }
                	 
                 }
	    	
	      takeSnap();
	    } catch (InvalidElementStateException e) {
	      reportStep("The element: could not be clicked", "FAIL");
	    } catch (WebDriverException e) {
	      reportStep("The element is not available", "FAIL");
	    }
	  }


  public void javascriptClick(WebElement ele) throws InterruptedException {
    String text = "";
    try {
      // WebDriverWait wait = new WebDriverWait(driver, 30);
      // wait.until(ExpectedConditions.elementToBeClickable(ele));
      //text = ele.getText();
    	Thread.sleep(1000);
      JavascriptExecutor js = driver;
      js.executeScript("arguments[0].scrollIntoView(true);", ele);
      // Thread.sleep(3000);
      js.executeScript("arguments[0].click();", ele);
      Thread.sleep(2000);
      reportStep("The element :" + text + "  is clicked.", "PASS");
      takeSnap();
    } catch (InvalidElementStateException e) {
      reportStep("The element: " + text + " could not be clicked", "FAIL");
    } catch (WebDriverException e) {
      reportStep("The element " + ele + "is not available", "FAIL");
    }
  }

  public void javascriptClick(WebElement ele, String data) {
    String text = "";
    try {
      // WebDriverWait wait = new WebDriverWait(driver, 30);
      // wait.until(ExpectedConditions.elementToBeClickable(ele));
      text = ele.getText();

      JavascriptExecutor js = driver;
      js.executeScript("arguments[0].scrollIntoView(true);", ele);
      js.executeScript("arguments[0].click();", ele);
      reportStep("The element :" + text + "  is clicked.", "PASS");
      takeSnap();
    } catch (InvalidElementStateException e) {
      reportStep("The element: " + text + " could not be clicked", "FAIL");
    } catch (WebDriverException e) {
      reportStep("The element " + ele + "is not available", "FAIL");
    }
  }

  public void javascriptScroll(List<WebElement> ele) throws InterruptedException {
    String text = "";
    try {
      // WebDriverWait wait = new WebDriverWait(driver, 30);
      // wait.until(ExpectedConditions.elementToBeClickable(ele));
      // text = ele.getText();
      Thread.sleep(2000);
      // OLD JavascriptExecutor js = driver;
     //new 
      JavascriptExecutor js = (JavascriptExecutor)driver;
      js.executeScript("arguments[0].scrollIntoView(true);", ele);
      Thread.sleep(2000);
    } catch (InvalidElementStateException e) {
      reportStep("The element: " + text + " could not be clicked", "FAIL");
    } catch (WebDriverException e) {
      reportStep("The element " + ele + "is not available", "FAIL");
    }
  }



  public void javascriptScroll1 (WebElement getgoalnameInmessagetext) {
    String text = "";
    try {
      // WebDriverWait wait = new WebDriverWait(driver, 30);
      // wait.until(ExpectedConditions.elementToBeClickable(ele));
      // text = ele.getText();

      JavascriptExecutor js = driver;
      js.executeScript("arguments[0].scrollIntoView(true);", getgoalnameInmessagetext);

    } catch (InvalidElementStateException e) {
      reportStep("The element: " + text + " could not be clicked", "FAIL");
    } catch (WebDriverException e) {
      reportStep("The element " + getgoalnameInmessagetext + "is not available", "FAIL");
    }
  }
  
 

  @Override
  public long takeSnap() {
    long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
    try {
      FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE), new File("./reports/images/" + number + ".jpg"));
    } catch (WebDriverException e) {
      System.out.println("The browser has been closed.");
    } catch (IOException e) {
      System.out.println("The snapshot could not be taken");
    }
    return number;
  }

  public CloseableHttpResponse postWithHeaders(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException

  {
    CloseableHttpResponse closeableHttpResponse = null;
    // try {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(url);
    httpPost.setEntity(new StringEntity(entityString));
    for (Map.Entry<String, String> entry : headerMap.entrySet()) {
      httpPost.addHeader(entry.getKey(), entry.getValue());
    }

    closeableHttpResponse = httpClient.execute(httpPost);
    // reportStep("The" + url + " successfully returned the json response", "PASS");
    // } catch(Exception e)
    // {
    // reportStep("The" + url + " NOT returned the json response", "FAIL");
    // }

    return closeableHttpResponse;
  }


  public void SwitchWiFi(String Action) throws InterruptedException {
    Runtime rt = Runtime.getRuntime();
    try {
      String[] command = {"cmd.exe", "/C", "Start", "C:\\Users\\Nonadmin\\Desktop\\WiFi" + Action + ".bat"};
      rt.exec(command);
      Thread.sleep(11000);

    } catch (final IOException e) {
      throw new RuntimeException("Failed to run the bat file");
    }

  }

  public void closeApp() {
    driver.quit();
  }

  public void closecurrentWindow() {
    driver.close();
  }


  public void verifyExists(WebElement ele) {
    boolean text = false;
    try {
      text = driver.findElementByXPath("//div[@class='patients-page']").isDisplayed();
      if (text = true) {
        reportStep("The element :" + ele + "  is visible.", "PASS");
      } else {
        reportStep("The element: " + ele + " is not visible", "FAIL");
      }
      takeSnap();
    } catch (InvalidElementStateException e) {
      reportStep("The element: " + ele + " is not visible", "FAIL");
    } catch (WebDriverException e) {
      reportStep("The element " + ele + "is not available", "FAIL");
    }
  }



  public void verifyNotExists(WebElement ele) {
    boolean text = false;
    try {
      text = ele.isDisplayed();
      if (text = false) {
        reportStep("The element :" + ele + "  is not visible.", "PASS");
      } else {
        reportStep("The element: " + ele + " is  visible", "FAIL");
      }
      takeSnap();
    } catch (InvalidElementStateException e) {
      reportStep("The element: " + ele + " is visible", "FAIL");
    } catch (WebDriverException e) {
      reportStep("The element " + ele + "is available", "FAIL");
    }
  }

  public void verifyTextDisplay(WebElement ele) {
    boolean text = false;
    try {
      text = ele.isDisplayed();
      if (text = true) {
        reportStep("The element :" + ele + "  is visible.", "PASS");
      } else {
        reportStep("The element: " + ele + " is not visible", "FAIL");
      }
      takeSnap();
    } catch (InvalidElementStateException e) {
      reportStep("The element: " + ele + " is not visible", "FAIL");
    } catch (WebDriverException e) {
      reportStep("The element " + ele + "is not available", "FAIL");
    }
  }


  public static void enterPin(String pin) throws AWTException {
    Robot robot = new Robot();
    String[] split = pin.split("");

    for (int i = 0; i < split.length; i++) {
      if (Integer.parseInt(split[i]) == 0) {
        robot.keyPress(KeyEvent.VK_0);
        robot.keyRelease(KeyEvent.VK_0);
      } else if (Integer.parseInt(split[i]) == 1) {
        robot.keyPress(KeyEvent.VK_1);
        robot.keyRelease(KeyEvent.VK_1);
      } else if (Integer.parseInt(split[i]) == 2) {
        robot.keyPress(KeyEvent.VK_2);
        robot.keyRelease(KeyEvent.VK_2);
      } else if (Integer.parseInt(split[i]) == 3) {
        robot.keyPress(KeyEvent.VK_3);
        robot.keyRelease(KeyEvent.VK_3);
      } else if (Integer.parseInt(split[i]) == 4) {
        robot.keyPress(KeyEvent.VK_4);
        robot.keyRelease(KeyEvent.VK_4);
      } else if (Integer.parseInt(split[i]) == 5) {
        robot.keyPress(KeyEvent.VK_5);
        robot.keyRelease(KeyEvent.VK_5);
      } else if (Integer.parseInt(split[i]) == 6) {
        robot.keyPress(KeyEvent.VK_6);
        robot.keyRelease(KeyEvent.VK_6);
      } else if (Integer.parseInt(split[i]) == 7) {
        robot.keyPress(KeyEvent.VK_7);
        robot.keyRelease(KeyEvent.VK_7);
      } else if (Integer.parseInt(split[i]) == 8) {
        robot.keyPress(KeyEvent.VK_8);
        robot.keyRelease(KeyEvent.VK_8);
      } else if (Integer.parseInt(split[i]) == 9) {
        robot.keyPress(KeyEvent.VK_9);
        robot.keyRelease(KeyEvent.VK_9);
      }

    }

  }



  public static HashMap<String, String> getHeaderMap(String token) {
    HashMap<String, String> alertHeaderMap = new HashMap<String, String>();

    alertHeaderMap.put("Accept", "application/json+fhir");
    alertHeaderMap.put("Content-Type", "application/json+fhir");
    alertHeaderMap.put("Authorization", token);
    return alertHeaderMap;

  }

  public void DBValidation(String DBValue, String AppValue) {
    if (DBValue.equals(AppValue)) {
      reportStep("The values in Lorenzo Database " + DBValue + " and CAUK application " + AppValue + " are equal", "PASS", false);
    } else {
      reportStep("The values in Lorenzo Database " + DBValue + " and CAUK application " + AppValue + " are not equal", "FAIL", false);
    }
  }


  public void DBValidation(String DBValue, String AppValue, String field) {
    if (DBValue.contains(AppValue)) {
      reportStep("The values of " + field + " in Lorenzo Database " + DBValue + " and  application " + AppValue + " are equal", "PASS", false);
    } else {
      reportStep("The values of " + field + " in Lorenzo Database " + DBValue + " and  application " + AppValue + " are not equal", "FAIL", false);
    }
  }

  public void DBValidationWithNullCheck(String DBValue, String AppValue, String field) {
	    if (DBValue.equals(AppValue)) {
	      reportStep("The values of " + field + " in Lorenzo Database " + DBValue + " and  application " + AppValue + " are equal", "PASS", false);
	    } else {
	      reportStep("The values of " + field + " in Lorenzo Database " + DBValue + " and  application " + AppValue + " are not equal", "FAIL", false);
	    }
	  }
  public void DBValidationcontains(String DBValue, String AppValue, String field) {
    if (DBValue.equalsIgnoreCase(AppValue)) {
      reportStep("The values of " + field + " in Lorenzo Database " + DBValue + " and  application " + AppValue + " are equal", "PASS", false);
    } else {
      reportStep("The values of " + field + " in Lorenzo Database " + DBValue + " and  application " + AppValue + " are not equal", "FAIL", false);
    }
  }
/*
  public String getAccessToken(String applicationName) throws ClassNotFoundException, SQLException, InterruptedException {
    RemoteWebDriver webdriver;
    EventFiringWebDriver driver;

    String LoginName = null;
    RESTAssuredBase rest = new RESTAssuredBase();
    System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
    webdriver = new ChromeDriver();
    driver = new EventFiringWebDriver(webdriver);
    // driver.register(this);
    driver.manage().window().maximize();
    String CAUKUserName, CAUKPassword, opendID;
    CAUKUserName = rest.CAUKUserName;
    CAUKPassword = rest.CAUKPassword;
    opendID = rest.sOpenIDUrl;
    driver.get(opendID + "/OIDCPortal/Logon?response_type=id_token%20token&client_id=7198868561693907585&redirect_uri=communityaide://authorise&scope=openid%20profile%20LorenzoOIDCRoles%20LorenzoOIDCResources&state=sdfsdfsd&nonce=lakshmk&grant_types=implicit&pin_feature=1");
    Thread.sleep(5000);
    driver.findElement(By.id("UserName")).sendKeys(CAUKUserName);
    Thread.sleep(5000);
    driver.findElement(By.id("Password")).sendKeys(CAUKPassword);
    LoginName = CAUKUserName;
    Thread.sleep(5000);
    driver.findElement(By.className("btn-login")).click();
    Thread.sleep(5000);
    DataBaseUtility dbutil = new DataBaseUtility();
    ResultSet databaseResult = dbutil.getDatabaseResult("select Status, * from SecuritySession where LoginName = '" + LoginName + "' and Status = 'A' order by lastaccesseddttm desc");
    String token = databaseResult.getString("sessionkey");
    System.out.println(token);
    return token;
  }
*/
  /*
   * @Override public void beforeAlertAccept(WebDriver driver) { // TODO Auto-generated method stub
   *
   * }
   *
   * @Override public void afterAlertAccept(WebDriver driver) { // TODO Auto-generated method stub
   *
   * }
   *
   * @Override public void afterAlertDismiss(WebDriver driver) { // TODO Auto-generated method stub
   *
   * }
   *
   * @Override public void beforeAlertDismiss(WebDriver driver) { // TODO Auto-generated method stub
   *
   * }
   *
   * @Override public void beforeNavigateTo(String url, WebDriver driver) { // TODO Auto-generated
   * method stub
   *
   * }
   *
   * @Override public void afterNavigateTo(String url, WebDriver driver) { // TODO Auto-generated
   * method stub
   *
   * }
   *
   * @Override public void beforeNavigateBack(WebDriver driver) { // TODO Auto-generated method stub
   *
   * }
   *
   * @Override public void afterNavigateBack(WebDriver driver) { // TODO Auto-generated method stub
   *
   * }
   *
   * @Override public void beforeNavigateForward(WebDriver driver) { // TODO Auto-generated method
   * stub
   *
   * }
   *
   * @Override public void afterNavigateForward(WebDriver driver) { // TODO Auto-generated method
   * stub
   *
   * }
   *
   * @Override public void beforeNavigateRefresh(WebDriver driver) { // TODO Auto-generated method
   * stub
   *
   * }
   *
   * @Override public void afterNavigateRefresh(WebDriver driver) { // TODO Auto-generated method
   * stub
   *
   * }
   *
   * @Override public void beforeFindBy(By by, WebElement element, WebDriver driver) { // TODO
   * Auto-generated method stub
   *
   * }
   *
   * @Override public void afterFindBy(By by, WebElement element, WebDriver driver) { // TODO
   * Auto-generated method stub
   *
   * }
   *
   * @Override public void beforeClickOn(WebElement element, WebDriver driver) { // TODO
   * Auto-generated method stub
   *
   * }
   *
   * @Override public void afterClickOn(WebElement element, WebDriver driver) { // TODO
   * Auto-generated method stub
   *
   * }
   *
   * @Override public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[]
   * keysToSend) { // TODO Auto-generated method stub
   *
   * }
   *
   * @Override public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[]
   * keysToSend) { // TODO Auto-generated method stub
   *
   * }
   *
   * @Override public void beforeScript(String script, WebDriver driver) { // TODO Auto-generated
   * method stub
   *
   * }
   *
   * @Override public void afterScript(String script, WebDriver driver) { // TODO Auto-generated
   * method stub
   *
   * }
   *
   * @Override public void onException(Throwable throwable, WebDriver driver) { // TODO
   * Auto-generated method stub
   *
   * }
   */

  public void explicitWait(WebElement ele) {
    wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOf(ele));
    wait.until(ExpectedConditions.elementToBeClickable(ele));
  }

  public void javascriptclick(WebElement Element) throws InterruptedException {
    Thread.sleep(1000);
    JavascriptExecutor js = driver;
    js.executeScript("arguments[0].click();", Element);
  }

  public void DBValidationNoValuePresent(String Fieldname) {

    reportStep("There is no value present in DB for field " + Fieldname + " ", "PASS");

  }

  public void refresh() {
    // driver.navigate().refresh();
    driver.executeScript("location.reload()");
  }

  public static boolean isSorted(ArrayList<Long> al) {

    boolean result = true;
    for (int i = 0; i < al.size() - 1; i++) {

      if (al.get(i) <= al.get(i + 1)) {
        result = false;
        break;
      }
    }
    return result;
  }

  public String getText(List<WebElement> ele, String fieldName) throws InterruptedException {
    String text = null;
    List<WebElement> value = ele;
    int valueresult = value.size();
    if (valueresult == 0) {
      System.out.println("There is no text present");
      reportStep("There is no text present", "PASS");
    } else {
      try {
        text = ele.get(0).getText();
       
        System.out.println(text);
        reportStep("The element :" + text + "  is found for Field: " + fieldName, "PASS");

      } catch (InvalidElementStateException e) {
        reportStep("The element: " + text + " could not be found Field: " + fieldName, "FAIL");
      } catch (WebDriverException e) {
        reportStep("Unknown exception occured while getting text:", "FAIL");
      }
      takeSnap();
    }
    return text;
  }
  
  public String getAttribute(List<WebElement> ele, String fieldName) throws InterruptedException {
	    String text = null;
	    List<WebElement> value = ele;
	    int valueresult = value.size();
	    if (valueresult == 0) {
	      System.out.println("There is no text present");
	      reportStep("There is no text present", "PASS");
	    } else {
	      try {	       
	        text=ele.get(0).getAttribute("innerHTML");
	        System.out.println(text);
	        reportStep("The element :" + text + "  is found for Field: " + fieldName, "PASS");

	      } catch (InvalidElementStateException e) {
	        reportStep("The element: " + text + " could not be found Field: " + fieldName, "FAIL");
	      } catch (WebDriverException e) {
	        reportStep("Unknown exception occured while getting text:", "FAIL");
	      }
	      takeSnap();
	    }
	    return text;
	  }
  
  public String getAttribute(WebElement ele, String fieldName) throws InterruptedException {
	    String text = "";
	    try {
	      text = ele.getAttribute("innerHTML");
	      System.out.println(text);
	      reportStep("The element :" + text + "  is found for Field: " + fieldName, "PASS");
	    } catch (InvalidElementStateException e) {
	      reportStep("The element: " + text + " could not be found Field: " + fieldName, "FAIL");
	    } catch (WebDriverException e) {
	      reportStep("Unknown exception occured while getting text:", "FAIL");
	    }
	    takeSnap();
	    return text;
	  }

  public void enterText(WebElement ele, String data) throws InterruptedException {
    try {
      Thread.sleep(5000);
      javascriptClick(ele);
      ele.sendKeys(data);
      reportStep("The element :" + data + "  is found.", "PASS");
    } catch (InvalidElementStateException e) {
      reportStep("The element: " + data + " could not be found", "FAIL");
    } catch (WebDriverException e) {
      reportStep("Unknown exception occured while getting text:", "FAIL");
    }

  }

  public static String removeWord(String string, String word) {
    if (string.contains(word)) {
      String tempWord = word + " ";
      string = string.replaceAll(tempWord, "");
      tempWord = " " + word;
      string = string.replaceAll(tempWord, "");
    }
    return string;
  }

  public String getText(WebElement ele, String fieldName) throws InterruptedException {
    String text = "";
    try {
      text = ele.getText();
      //System.out.println(text);
      reportStep("The element :" + text + "  is found for Field: " + fieldName, "PASS");
    } catch (InvalidElementStateException e) {
      reportStep("The element: " + text + " could not be found Field: " + fieldName, "FAIL");
    } catch (WebDriverException e) {
     // reportStep("Unknown exception occured while getting text:", "FAIL");
    }
   // takeSnap();
    return text;
  }

  public String getIcon(WebElement ele, String data) throws InterruptedException {
	  String Icon = "";
    try {
    	Icon = ele.getAttribute("aria-label");
        takeSnap();
    } catch (InvalidElementStateException e) {
        reportStep("The element: " + Icon + " could not be found Field: " + data, "FAIL");
      } catch (WebDriverException e) {
        reportStep("Unknown exception occured while getting text:", "FAIL");
      }
	return Icon;
	
  }


  public void LoginCSP() throws FileNotFoundException, IOException, InterruptedException {
    try {
      System.setProperty("webdriver.chrome.driver", sChromedriverPath);
      // ChromeOptions option = new ChromeOptions();
      driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.get("https://uk1sbapersshdsit.persona.dhp.dxc.technology/");
      Thread.sleep(5000);
      driver.findElement(By.xpath("//input[@id='UserName']")).sendKeys("dcare13");
      driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Qwerty@3");
      driver.findElement(By.xpath("//input[@value='Login']")).click();
      Thread.sleep(5000);
      driver.findElement(By.xpath("//homepage-card//span[contains(text(),'OP Clinic')]")).click();
      driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Zydus clinic");

      driver.findElement(By.xpath("(//div[contains(@class,'datePickerContainer')]//input)[2]")).sendKeys(Keys.CONTROL + "a" + Keys.CONTROL);
      driver.findElement(By.xpath("(//div[contains(@class,'datePickerContainer')]//input)[2]")).sendKeys("09-Feb-2021");

      // await element(by.xpath("//button[@class='mdi mdi-calendar-range
      // calendar-icon-focus mat-icon-button']")).click();
      Thread.sleep(5000);
      driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    } catch (WebDriverException e) {
      reportStep("The application is not launched successfully", "FAIL");
    }
  }

  public void startApp_WD(String applicationName) throws FileNotFoundException, IOException, InterruptedException {

    try {
      Runtime rt = Runtime.getRuntime();
      // Process proc = rt.exec("taskkill /im chrome.exe /f /t");
      Process proc1 = rt.exec("taskkill /im ClinicalAide.exe /f /t");
      // Thread.sleep(5000);
      System.setProperty("webdriver.chrome.driver", sChromedriverPath);
      ChromeOptions chromeoptions = new ChromeOptions();
      chromeoptions.setBinary(sCAUKBinaryPath);
      driver = new ChromeDriver(chromeoptions);
      driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
      reportStep("The application launched successfully", "PASS");
    } catch (WebDriverException e) {
      reportStep("The application launched successfully", "FAIL");
    }

    Set<String> windowHandles1 = driver.getWindowHandles();
    String firstWinHandle1 = driver.getWindowHandle();
    System.out.println(firstWinHandle1);

    driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
    Thread.sleep(12000);
    Set<String> windowHandles = driver.getWindowHandles();
    String firstWinHandle = driver.getWindowHandle();
    windowHandles.remove(firstWinHandle);

    String winHandle = windowHandles.iterator().next();
    if (winHandle != firstWinHandle) {
      String secondWinHandle = winHandle;
      driver.switchTo().window(secondWinHandle);
      /* System.out.println(driver.getTitle()); */
     // System.out.println(driver.findElement(By.xpath("//*[contains(text(),'name')]")));
      WebElement userName1 = driver.findElement(By.xpath("//*[contains(text(),'name')]"));
      explicitWait(userName1);
      // Thread.sleep(6000);
      Actions act = new Actions(driver);
      act.moveToElement(userName1);
      // act.sendKeys("sram").build().perform();
      // Thread.sleep(4000);
      WebElement name = driver.findElement(By.id("UserName"));
      // System.out.println(name);
      // Thread.sleep(3000);
      // name.clear();
      // Thread.sleep(3000);
      explicitWait(name);
      name.sendKeys(sUserNameApp);
      WebElement pWord = driver.findElement(By.id("Password"));
      explicitWait(pWord);
      // System.out.println(pWord);
      // Thread.sleep(3000);
      pWord.clear();
      // Thread.sleep(3000);
      pWord.sendKeys(sPasswordApp);
      // Thread.sleep(5000);
      explicitWait(driver.findElementByClassName("btn-login"));
      driver.findElementByClassName("btn-login").click();
      // Thread.sleep(5000);
      // String firstWinHandles = driver.getWindowHandle();
      // driver.switchTo().window("0");
      Set<String> allWindows = driver.getWindowHandles();
      //System.out.println(driver.getWindowHandles());

      for (String currentWindow : allWindows) {
        driver.switchTo().window(currentWindow);
        System.out.println(driver.getTitle());
      }
    }
  }
  
  public void Select_Date(String day) throws InterruptedException {
      String activeday = driver.findElement(By.xpath("(//button[@class='picker-opt picker-opt-selected'])[1]"))
              .getText();
      if (Integer.parseInt(activeday) <= Integer.parseInt(day)) {
          for (int i = Integer.parseInt(activeday); i <= Integer.parseInt(day); i++) {
              String a = new DecimalFormat("00").format(i);
              i = Integer.parseInt(a);
              WebElement ele = driver.findElement(By.xpath(
                      "(//div[contains(@class,'picker-opts')])[1]//button[contains(@class,'picker-opt') and contains(text(), '"
                              + a + "')]"));
              explicitWait(ele);
              ele.click();
              Thread.sleep(1000);
          }
          reportStep("The element :" + day + "  is Clicked.", "PASS");
      }else {
          for (int i = Integer.parseInt(activeday); i >= Integer.parseInt(day); i--) {
              String a = new DecimalFormat("00").format(i);
              i = Integer.parseInt(a);
              WebElement ele = driver.findElement(By.xpath(
                      "(//div[contains(@class,'picker-opts')])[1]//button[contains(@class,'picker-opt') and contains(text(), '"
                              + a + "')]"));
              explicitWait(ele);
              ele.click();
              Thread.sleep(1000);
          }
          reportStep("The element :" + day + "  is Clicked.", "PASS");
      }
  }
	
	public void Select_Month(String Month) throws InterruptedException {
      String activemonthtext = driver.findElement(By.xpath("(//button[@class='picker-opt picker-opt-selected'])[2]"))
              .getText();
      WebElement activemonthind = driver
              .findElement(By.xpath("(//button[@class='picker-opt picker-opt-selected'])[2]"));
      String activemonthindex = activemonthind.getAttribute("opt-index");
      if (Integer.parseInt(activemonthindex) <= Integer.parseInt(Month)) {
          for (int i = Integer.parseInt(activemonthindex); i <= Integer.parseInt(Month); i++) {
              // String a = new DecimalFormat("00").format(i);
              // i = Integer.parseInt(a);
              WebElement ele = driver.findElement(By.xpath(
                      "(//div[contains(@class,'picker-opts')])[2]//button[contains(@class,'picker-opt') and @opt-index='"
                              + i + "']"));
              explicitWait(ele);
              ele.click();
              Thread.sleep(1000);
          }
          reportStep("The element :" + Month + "  is Clicked.", "PASS");
      } else {
          for (int i = Integer.parseInt(activemonthindex); i >= Integer.parseInt(Month); i--) {
              WebElement ele = driver.findElement(By.xpath(
                      "(//div[contains(@class,'picker-opts')])[2]//button[contains(@class,'picker-opt') and @opt-index='"
                              + i + "']"));
              explicitWait(ele);
              ele.click();
              Thread.sleep(1000);
          }
          reportStep("The element :" + Month + "  is Clicked.", "PASS");
      }
  }
	
	
	
	public void Select_Year(String Year) throws InterruptedException {
      String activeyear = driver.findElement(By.xpath("(//button[@class='picker-opt picker-opt-selected'])[3]"))
              .getText();
      if (Integer.parseInt(activeyear) <= Integer.parseInt(Year)) {
          for (int i = Integer.parseInt(activeyear); i <= Integer.parseInt(Year); i++) {
              WebElement ele = driver.findElement(By.xpath(
                      "(//div[contains(@class,'picker-opts')])[3]//button[contains(@class,'picker-opt') and contains(text(), '"
                              + i + "')]"));
              explicitWait(ele);
              ele.click();
              Thread.sleep(1000);
          }
          reportStep("The element :" + Year + "  is Clicked.", "PASS");
      } else {



          for (int i = Integer.parseInt(activeyear); i >= Integer.parseInt(Year); i--) {
              WebElement ele = driver.findElement(By.xpath(
                      "(//div[contains(@class,'picker-opts')])[3]//button[contains(@class,'picker-opt') and contains(text(), '"
                              + +i + "')]"));
              explicitWait(ele);
              ele.click();
              Thread.sleep(1000);
          }
          reportStep("The element :" + Year + "  is Clicked.", "PASS");
      }
  }
	
	public void DBcontainsValidation(String Value1,String Value2,String field) {
		 if(Value1.contains(Value2)) {
	         reportStep("The values of "+field+ " in Lorenzo Database "+Value2+ " and  application "+Value1+" are equal", "PASS",false);
	    }
	    else
	    {
	         reportStep("The values of "+field+ " in Lorenzo Database "+Value2+ " and  application "+Value1+" are not equal", "FAIL",false);
	    }  
	}
	public void Clear(WebElement ele) {
        ele.sendKeys(Keys.CONTROL + "a");
        ele.sendKeys(Keys.DELETE);
    }
	
	public String getFirstLetters(String text)
	{
	  String firstLetters = "";
	  text = text.replaceAll("[.,]", ""); // Replace dots, etc (optional)
	  for(String s : text.split(" "))
	  {
	    firstLetters += s.charAt(0);
	  }
	  return firstLetters;
	}
	
	
	
	public void Loginapp(String applicationName,String username,String Password) throws FileNotFoundException, IOException, InterruptedException {

		   
	    driver.findElement(By.xpath("//ion-button[contains(text(),'Login')]")).click();
	    Thread.sleep(12000);
	    Set<String> windowHandles = driver.getWindowHandles();
	    String firstWinHandle = driver.getWindowHandle();
	    windowHandles.remove(firstWinHandle);

	    String winHandle = windowHandles.iterator().next();
	    if (winHandle != firstWinHandle) {
	      String secondWinHandle = winHandle;
	      driver.switchTo().window(secondWinHandle);
	      /* System.out.println(driver.getTitle()); */
	      System.out.println(driver.findElement(By.xpath("//*[contains(text(),'name')]")));
	      WebElement userName1 = driver.findElement(By.xpath("//*[contains(text(),'name')]"));
	      explicitWait(userName1);
	      // Thread.sleep(6000);
	      Actions act = new Actions(driver);
	      act.moveToElement(userName1);
	      // act.sendKeys("sram").build().perform();
	      // Thread.sleep(4000);
	      WebElement name = driver.findElement(By.id("UserName"));
	      // System.out.println(name);
	      // Thread.sleep(3000);
	      // name.clear();
	      // Thread.sleep(3000);
	      explicitWait(name);
	      name.sendKeys(username);
	      WebElement pWord = driver.findElement(By.id("Password"));
	      explicitWait(pWord);
	      // System.out.println(pWord);
	      // Thread.sleep(3000);
	      pWord.clear();
	      // Thread.sleep(3000);
	      pWord.sendKeys(Password);
	      // Thread.sleep(5000);
	      explicitWait(driver.findElementByClassName("btn-login"));
	      driver.findElementByClassName("btn-login").click();
	      // Thread.sleep(5000);
	      // String firstWinHandles = driver.getWindowHandle();
	      // driver.switchTo().window("0");
	      Set<String> allWindows = driver.getWindowHandles();
	      System.out.println(driver.getWindowHandles());
	      Thread.sleep(12000);
	      for (String currentWindow : allWindows) {
	        driver.switchTo().window(currentWindow);
	        System.out.println(driver.getTitle());
	      }
	    }
	    
	  }
	public void AssertStringContains(String Str1,String Str2,String field) {
		if(Str1.contains(Str2)) {
			reportStep("The values of "+field+ " : "+Str1+ " and "+Str2+" are equal", "PASS",false);
		}
		else
		{
			reportStep("The values of "+field+ " : "+Str1+ " and  "+Str2+" are not equal", "FAIL",false);
			System.out.println("The values of "+field+ " : "+Str1+ " and  "+Str2+" are not equal");
		}
	}
	
	public void AssertStringEquals(String Str1,String Str2,String field) {
		if(Str1.equals(Str2)) {
			reportStep("The values of "+field+ " : "+Str1+ " and "+Str2+" are equal", "PASS",false);
		}
		else
		{
			reportStep("The values of "+field+ " : "+Str1+ " and  "+Str2+" are not equal", "FAIL",false);
			System.out.println("The values of "+field+ " : "+Str1+ " and  "+Str2+" are not equal");
		}
	}
	
	public void javascriptScroll (WebElement element,String field) {
	    String text = "";
	    try {
	      // WebDriverWait wait = new WebDriverWait(driver, 30);
	      // wait.until(ExpectedConditions.elementToBeClickable(ele));
	      // text = ele.getText();

	      JavascriptExecutor js = driver;
	      js.executeScript("arguments[0].scrollIntoView(true);", element);
	      reportStep(field + " is found", "PASS");
	    } catch (InvalidElementStateException e) {
	      reportStep(field + " is not found", "FAIL");
	    } catch (WebDriverException e) {
	      reportStep("The element " + field + "is not available", "FAIL");
	    }
	  }
	
	public static String replaceLast(String string) {
	 	String find = "-";
	 	String replace = ":";
        int lastIndex = string.lastIndexOf(find);
        if (lastIndex == -1) {
            return string;
        }
        String beginString = string.substring(0, lastIndex);
        String endString = string.substring(lastIndex + find.length());
        return beginString + replace + endString;
    }
	
	public void VerifyElementNotPresent(List<WebElement> EleName , String field) {
        try {
            List<WebElement> value = EleName;
            int valueresult = value.size();
            if (valueresult == 0) {
                reportStep("The element :" + field + "  is not displayed.", "PASS");
                takeSnap();
            }
            else
            {
            	reportStep("The element :" + field + "  is  displayed.", "FAIL");;
            }
        } catch (InvalidElementStateException e) {
            reportStep("The element: " + field + " is available", "FAIL");
        } catch (WebDriverException e) {
            reportStep("The element " + field + "is available", "FAIL");
        }
    }

	public void jsclick(WebElement Element,String field) throws InterruptedException {
	    try {
	    	JavascriptExecutor js = driver;
	        js.executeScript("arguments[0].scrollTop = arguments[1];", Element, 100);
	        js.executeScript("arguments[0].click();", Element);
	        reportStep(field+" is clicked", "PASS");
	        takeSnap();
	    } catch (InvalidElementStateException e) {
			reportStep(field+"  could not be found", "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while clicking", "FAIL");
		}
	    
	  }
	
	public void VerifyPatientDetails_SearchList(String Surname,String Forename,String DOB, String NHS, String Address) throws InterruptedException {
		try {
			String PatName = driver.findElementByXPath("//ion-item-sliding//h2").getText();
			String PatDOB = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'Born')]/parent::span").getText();
			String PatNHS = driver.findElementByXPath("//ion-item-sliding//span[contains(text(),'NHS')]/parent::span").getText();
			String PatAdd = driver.findElementByXPath("//ion-item-sliding//p[2]/span").getText();
			System.out.println("PatDOB: "+PatDOB+" and "+DOB); 
			System.out.println("PatNHS: "+PatNHS+" and "+NHS); 
			System.out.println("PatAdd: "+PatAdd+" and "+Address);
			AssertStringContains((Surname.toUpperCase()+", "+Forename), PatName, "Patient Name in Search list");
			AssertStringEquals(("Born "+DOB), PatDOB, "Patient DOB in Search list");
			AssertStringEquals(("NHS "+NHS), PatNHS, "Patient NHS in Search list");
			AssertStringEquals(Address, PatAdd, "Patient Address in Search list");
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	
	public void VerifyPatientDetails_SearchListMore(String Surname,String Forename, String Gender,String DOB, String NHS, String PASID) throws InterruptedException {
		try {
			WebElement PatDetMoreIcon = driver.findElementByXPath("//ion-item-sliding//ion-icon[contains(@name,'ellipsis')]");
			jsclick(PatDetMoreIcon, "Patient Details More Icon");
			String PatName = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(@class,'Title')]").getText();
			String PatDOB = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Born')]/following-sibling::span").getText();
			String PatNHS = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'NHS')]/following-sibling::span").getText();
			String PatGender = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'Gender')]/following-sibling::span").getText();
			String PatPASID = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/ancestor::ion-row[1]/following-sibling::ion-row//span[contains(text(),'PAS')]/following-sibling::span").getText();
			WebElement closeicon = driver.findElementByXPath("//span[contains(text(),'Patient Details')]/parent::ion-col/following-sibling::ion-col/ion-icon[@name='close']");
			jsclick(closeicon, "Patient details close icon");
			if((Gender.equals("Not known"))||(Gender.equals("Indeterminate"))) {
				Gender = "Other";
			}
			AssertStringContains((Surname.toUpperCase()+", "+Forename), PatName, "Patient Name in Patient Details pop up");
			AssertStringEquals(DOB, PatDOB, "Patient DOB in Patient Details pop up");
			AssertStringEquals(Gender, PatGender, "Patient Gender in Patient Details pop up");
			AssertStringEquals(NHS, PatNHS, "Patient NHS in Patient Details pop up");
			AssertStringEquals(PASID, PatPASID, "Patient PASID in Patient Details pop up");
			
		}catch (InvalidElementStateException e) {
  			reportStep("The element could not be clicked", "FAIL");
  		} catch (WebDriverException e) {
  			reportStep("The element is not available", "FAIL");
  		}
	}
	
	public void enterText(WebElement ele, String data, String field) throws InterruptedException {
        try {
            Thread.sleep(4000);
            ele.clear();
            ele.sendKeys(data);
            reportStep("The data: " + data + " entered successfully in the field :" + field, "PASS");
            takeSnap();
        } catch (InvalidElementStateException e) {
            reportStep("The data: " + data + " could not be entered in the field :" + field, "FAIL");
        } catch (WebDriverException e) {
            reportStep("Unknown exception occured while entering " + data + " in the field :" + field, "FAIL");
        }
	}
	
	public String getIconName(WebElement ele, String data) throws InterruptedException {
		  String Icon = "";
	    try {
	    	Icon = ele.getAttribute("name");
	        takeSnap();
	    } catch (InvalidElementStateException e) {
	        reportStep("The element: " + Icon + " could not be found Field: " + data, "FAIL");
	      } catch (WebDriverException e) {
	        reportStep("Unknown exception occured while getting text:", "FAIL");
	      }
		return Icon;
	  }
	
	public String convertCamelCase(String input) {
		  String[] words = input.split("\\s");
		  StringBuilder result = new StringBuilder();
	        for (String word : words) {
	            result.append(Character.toTitleCase(word.charAt(0)))
	                  .append(word.substring(1).toLowerCase())
	                  .append(" ");
	        }
	        return result.toString().trim();
	  }
	
	 public String getIconClass(WebElement ele, String data) throws InterruptedException {
		  String Icon = "";
	    try {
	    	Icon = ele.getAttribute("class");
	        takeSnap();
	    } catch (InvalidElementStateException e) {
	        reportStep("The element: " + Icon + " could not be found Field: " + data, "FAIL");
	      } catch (WebDriverException e) {
	        reportStep("Unknown exception occured while getting text:", "FAIL");
	      }
		return Icon;	
	  }
	 
	 public String getIconSrc(WebElement ele, String data) throws InterruptedException {
		  String Icon = "";
	    try {
	    	Icon = ele.getAttribute("src");
	        takeSnap();
	    } catch (InvalidElementStateException e) {
	        reportStep("The element: " + Icon + " could not be found Field: " + data, "FAIL");
	      } catch (WebDriverException e) {
	        reportStep("Unknown exception occured while getting text:", "FAIL");
	      }
		return Icon;	
	  }
	
	 public void checkCopyright() throws InterruptedException {
			try {
			      Runtime rt = Runtime.getRuntime();
			      Process proc1 = rt.exec("taskkill /im ClinicalAide.exe /f /t");
			      System.setProperty("webdriver.chrome.driver", sChromedriverPath);
			      ChromeOptions chromeoptions = new ChromeOptions();
			      chromeoptions.setBinary(sCAUKBinaryPath);
			      driver = new ChromeDriver(chromeoptions);
		//	      driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			    } catch (WebDriverException | IOException e) {
			      reportStep("The application launched successfully", "FAIL");
			    }
			    Thread.sleep(4000);
			    
			    Set<String> handles = driver.getWindowHandles();
			    for (String handle : handles) {
			      driver.switchTo().window(handle);
			    }
			    
			    String Copyright = driver.findElementByXPath("//p[@class='copyright']").getText();
			    String licence = driver.findElementByXPath("//p[@class='licence']").getText();
			    WebElement ele = driver.findElementByXPath("//div[@class='app-name']/img");  
			    
			    String logo = getIconClass(ele, "Logo Class");
			    String logoName = getIconSrc(ele, "Logo Name");
			    
			    AssertStringEquals(Copyright, SORCopyright, "Copyright text");
			    AssertStringEquals(licence, SORLicence, "Licence text");
			    AssertStringContains(logoName, "dedalus", "Logo Name");
			    AssertStringEquals(logo, "dxc-logo", "Logo Class");
		}
	 
	 
	// Method to calculate time difference in minutes
     public static String CalculateTimeDifferenceinMinutes(String date1, String date2) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
         // Parsing the date strings into LocalDateTime
         LocalDateTime dateTime1 = LocalDateTime.parse(date1, formatter);
         LocalDateTime dateTime2 = LocalDateTime.parse(date2, formatter);
         // Calculating the difference in minutes
         Duration duration = Duration.between(dateTime1, dateTime2);
         long minutesDifference = duration.toMinutes();
         // Formatting the output as desired
         return minutesDifference + "m";
     }
	 
}
