package com.dedalus.genericappmethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import com.dedalus.utilities.Reporter;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;

public class GenericAppMethods extends Reporter implements WebDriverEventListener {

	public AppiumDriver<MobileElement> driver;
	public Properties prop;
	public WebDriverWait wait;
	
	public String LZOUserNameApp;
	public String LZOPasswordApp;
	public String WebPASUserNameApp;
	public String WebPASPasswordApp;
	public String sConfigURL;
	public String sLambdaUserName;
	public String sLambdaAccessKey;
	public String sLambdaGridURL;
	public String sLZOAppIDiOS;
	public String sLZOAppIDAndroid;
	public String sWebPASAppIDiOS;
	public String sWebPASAppIDAndroid;
	public String stunnelName;
	public String sSqlServer;
	public String sJdbcDriver;
	public String sUserName;
	public String sPassword;
	public String SORLicence;
	public String SORCopyright;

	JavascriptExecutor js;

	public GenericAppMethods() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
			LZOUserNameApp = prop.getProperty("LZOUsernameApp");
			LZOPasswordApp = prop.getProperty("LZOPasswordApp");
			WebPASUserNameApp = prop.getProperty("WebPASUsernameApp");
			WebPASPasswordApp = prop.getProperty("WebPASPasswordApp");
			sConfigURL = prop.getProperty("configURL");
			sLambdaUserName = prop.getProperty("LambdaUserName");
			sLambdaAccessKey = prop.getProperty("AccessKey");
			sLambdaGridURL = prop.getProperty("GridURL");
			sLZOAppIDiOS = prop.getProperty("LZOAppIDiOS");
			sLZOAppIDAndroid = prop.getProperty("LZOAppIDAndroid");
			sWebPASAppIDiOS = prop.getProperty("WebPASAppIDiOS");
			sWebPASAppIDAndroid = prop.getProperty("WebPASAppIDAndroid");
			stunnelName = prop.getProperty("tunnelName");
			 sSqlServer = prop.getProperty("sqlserver");
		      sJdbcDriver = prop.getProperty("jdbcdriver");
		      sUserName = prop.getProperty("username");
		      sPassword = prop.getProperty("password");
		      SORLicence = prop.getProperty("SORLicence");
		      SORCopyright = prop.getProperty("SORCopyright");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void switchToWebView() throws InterruptedException {
		 System.out.println(driver.getContext());
		 Set<String> contexts1 =  driver.getContextHandles();
		 String lastestContextView = (String) contexts1.toArray()[contexts1.size()-1];
	        for (String context : contexts1) {
	            if (context.contains("WEBVIEW")) {
	                driver.context(lastestContextView);
	                Thread.sleep(5000);
	                break;
	            }
	        }
	}
	
	
	public void actionClick(MobileElement ele) {
	    String text = "";
	    try {
	      WebDriverWait wait = new WebDriverWait(driver, 30);
	      wait.until(ExpectedConditions.elementToBeClickable(ele));
	      text = ele.getText();
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

	
	 public String getText(List<MobileElement> ele, String fieldName) throws InterruptedException {
		    String text = null;
		    List<MobileElement> value = ele;
		    int valueresult = value.size();
		    if (valueresult == 0) {
		      System.out.println("There is no text present for field : "+ fieldName);
		      reportStep("There is no text present", "PASS");
		    } else {
		      try {
		        text = ele.get(0).getText();
		       
		        System.out.println(fieldName+": "+text);
		        reportStep("The element :" + text + "  is found for Field: " + fieldName, "PASS");

		      } catch (InvalidElementStateException e) {
		        reportStep("The element: " + text + " could not be found Field: " + fieldName, "FAIL");
		      } catch (WebDriverException e) {
		        reportStep("Unknown exception occured while getting text for field : "+fieldName, "FAIL");
		      }
		      takeSnap();
		    }
		    return text;
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

	public void click(MobileElement ele,String field) {
		//String text = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			//text = ele.getText();
			ele.click();
			reportStep("The element :" + field + "  is clicked.", "PASS");
		} catch (InvalidElementStateException e) {
			reportStep("The element: " + field + " could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while clicking in the field :", "FAIL");
		}
	}

	@Override
	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
					new File("./reports/images/" + number + ".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
		
	}

	public void verifyTextDisplay(WebElement ele) {
		boolean text = false;
		try {
			text = ele.isDisplayed();
			if(text=true) {
				reportStep("The element :" + ele + "  is visible.", "PASS");
			}
			else {
				reportStep("The element: " + ele + " is not visible", "FAIL");
			}
			takeSnap();
		} catch (InvalidElementStateException e) {
			reportStep("The element: " + ele + " is not visible", "FAIL");
		} catch (WebDriverException e) {
			reportStep("The element "+ele+ "is not available", "FAIL");
		}
	}


	public void DBValidation(String DBValue,String AppValue) {
		 if(DBValue.equals(AppValue)) {
	         reportStep("The values in Lorenzo Database "+DBValue+ " and NA application "+AppValue+" are equal", "PASS",false);
	    }
	    else
	    {
	        reportStep("The values in Lorenzo Database "+DBValue+ " and NA application "+AppValue+" are not equal", "FAIL",false);
	    }
	}

	public void DBValidation(String DBValue,String AppValue,String field) {
		 if(DBValue.equals(AppValue)) {
	         reportStep("The values of "+field+ " in Lorenzo Database "+DBValue+ " and NA application "+AppValue+" are equal", "PASS",false);
	    }
	    else
	    {
	         reportStep("The values of "+field+ " in Lorenzo Database "+DBValue+ " and NA application "+AppValue+" are not equal", "FAIL",false);
	    }
	}

	public void DBValidationcontains(String DBValue,String AppValue,String field) {
		 if(DBValue.equalsIgnoreCase(AppValue)) {
	         reportStep("The values of "+field+ " in Lorenzo Database "+DBValue+ " and NA application "+AppValue+" are equal", "PASS",false);
	    }
	    else
	    {
	         reportStep("The values of "+field+ " in Lorenzo Database "+DBValue+ " and NA application "+AppValue+" are not equal", "FAIL",false);
	    }
	}
//-------------------------------------------------------------------------------
	public void explicitWait(MobileElement ele)
	{
	wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOf(ele));
	wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	//-------------------------------------------------------------------------------
	public void javascriptclick(WebElement Element) throws InterruptedException
    {
		Thread.sleep(1000);
        JavascriptExecutor js = driver;
        js.executeScript("arguments[0].click();", Element);
    }

	public void jsclick(MobileElement Element,String field) throws InterruptedException {
    try {
    	JavascriptExecutor js = driver;
        js.executeScript("arguments[0].scrollTop = arguments[1];", Element, 100);
        js.executeScript("arguments[0].click();", Element);
        reportStep(field+" is clicked", "PASS");
    } catch (InvalidElementStateException e) {
		reportStep(field+"  could not be found", "FAIL");
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while clicking", "FAIL");
	}
    
  }

	public void refresh() {
		 //driver.navigate().refresh();
		 driver.executeScript("location.reload()");
	}

	public static boolean isSorted(ArrayList<Long> al) {
	    boolean result = true;
	    for(int i = 0; i < al.size()-1; i++) {

	        if(al.get(i) <= al.get(i+1)) {
	            result = false;
	            break;
	        }
	    }
	    return result;
	}

	 
	public void enterText(WebElement ele, String data) throws InterruptedException {
		try {
		Thread.sleep(5000);
		//javascriptClick(ele);
		ele.sendKeys(data);
		reportStep("Text :" + data + "  is entered.", "PASS");
		} catch (InvalidElementStateException e) {
			reportStep("The element: " + data + " could not be found", "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while getting text:", "FAIL");
		}
	}
	public void javascriptClick(MobileElement ele) {
		String text = "";
		try {
			//text = ele.getText();


			js.executeScript("arguments[0].scrollIntoView(true);", ele);
			js.executeScript("arguments[0].click();", ele);
			reportStep("The element :" + text + "  is clicked.", "PASS");
			takeSnap();
		} catch (InvalidElementStateException e) {
			reportStep("The element: " + text + " could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("The element "+ele+ "is not available", "FAIL");
		}
	}

	public void javascriptenterText(String data, WebElement ele) throws InterruptedException {
		js.executeScript("arguments[0].value='';", ele);
		js.executeScript("arguments[0].value='"+data+"';", ele);
		takeSnap();
		Thread.sleep(1000);

		}

	    public void actionClick(WebElement ele, String Field) {
        String text = "";
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            text = ele.getText();
            Actions action = new Actions(driver);
            action.moveToElement(ele).click().perform();
            reportStep("The element :" + text + "  is clicked for the fieldname:"+Field, "PASS");
            takeSnap();
        } catch (InvalidElementStateException e) {
            reportStep("The element: " + text + " could not be clickedfor the fieldname:"+Field, "FAIL");
        } catch (WebDriverException e) {
            reportStep("The element "+ele+ "is not available for the fieldname:"+Field, "FAIL");
        }
    }
//------------------------------------------------------------------------------------------------------
    public void enterText(MobileElement ele, String data, String field) throws InterruptedException {
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

//-------------------------------------------------------------------------------------------------------------
	
	public String getText(MobileElement ele,String fieldName) throws InterruptedException {
		   String text = "";
		   try {  
			javascriptScroll(ele,fieldName);
			text = ele.getText();
			System.out.println(text);
			reportStep("The element :" + text + "  is found for Field: "+ fieldName, "PASS");
				} catch (InvalidElementStateException e) {
					reportStep("The element: " + text + " could not be found Field: "+ fieldName, "FAIL");
				} catch (WebDriverException e) {
					reportStep("Unknown exception occured while getting text:", "FAIL");
				}
		    takeSnap();
			return text;
	}	
	
	public boolean getAttributeValue(WebElement ele, String attribute) throws InterruptedException {
		boolean attValue = ele.getAttribute(attribute) != null;
		takeSnap();
		return attValue;
	}

	public void actionClickIcon(MobileElement ele,String text) {
		//String text = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			//text = ele.getText();
			Actions action = new Actions(driver);
			action.moveToElement(ele).click().perform();
			reportStep(text + "  is clicked.", "PASS");
			takeSnap();
		} catch (InvalidElementStateException e) {
			reportStep("The element: " + text + " could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("The element "+ele+ "is not available", "FAIL");
		}
	}

	public void VerifyImageIsEnabled(WebElement ele,String field) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			boolean val = ele.isEnabled();
			if(val) {
				reportStep("The element :" + field + "  is enabled.", "PASS");
			}
			else {
				reportStep("The element :" + field + "  is not enabled.", "FAIL");
			}
			takeSnap();
		} catch (InvalidElementStateException e) {
			reportStep("The element: " + field + " is not available", "FAIL");
		} catch (WebDriverException e) {
			reportStep("The element "+field+ "is not available", "FAIL");
		}
	}

	
		@Override
		public void afterAlertAccept(WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterAlertDismiss(WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeAlertDismiss(WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeNavigateTo(String url, WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterNavigateTo(String url, WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeNavigateBack(WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterNavigateBack(WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeNavigateForward(WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterNavigateForward(WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeNavigateRefresh(WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterNavigateRefresh(WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeFindBy(By by, WebElement element, WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterFindBy(By by, WebElement element, WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeClickOn(WebElement element, WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterClickOn(WebElement element, WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeScript(String script, WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterScript(String script, WebDriver driver) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onException(Throwable throwable, WebDriver driver) {
			// TODO Auto-generated method stub

		}

		public String getIcon(MobileElement ele, String data) throws InterruptedException {
			String Icon = "";
		    try {
		    	//Icon = ele.getAttribute("aria-label");
		    	Icon = ele.getAttribute("name");
		        takeSnap();
		    } catch (InvalidElementStateException e) {
		        reportStep("The element: " + Icon + " could not be found Field: " + data, "FAIL");
		      } catch (WebDriverException e) {
		        reportStep("Unknown exception occured while getting text:", "FAIL");
		      }
			return Icon;
	    }

		public void DBValidationNoValuePresent(String Fieldname) {
			reportStep("There is no value present in DB for field " + Fieldname + " ", "PASS");
		}

		 public void VerifyImage(WebElement ele,String field) {
		        try {
		            WebDriverWait wait = new WebDriverWait(driver, 30);
		            wait.until(ExpectedConditions.elementToBeClickable(ele));
		            boolean val = ele.isDisplayed();
		            if(val) {
		                reportStep("The element :" + field + "  is displayed.", "PASS");
		            }
		            else {
		                reportStep("The element :" + field + "  is not displayed.", "FAIL");
		            }
		            takeSnap();
		        } catch (InvalidElementStateException e) {
		            reportStep("The element: " + field + " is not available", "FAIL");
		        } catch (WebDriverException e) {
		            reportStep("The element "+field+ "is not available", "FAIL");
		        }
		    }


			public void click(WebElement ele, String Field) throws InterruptedException {
				Thread.sleep(8000);
				try {
					WebDriverWait wait = new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.elementToBeClickable(ele));
					ele.click();
					reportStep("The element :" + Field + "  is clicked.", "PASS");
					takeSnap();
				} catch (InvalidElementStateException e) {
					reportStep("The element: " + Field + " could not be clicked", "FAIL");
				} catch (WebDriverException e) {
					reportStep("Unknown exception occured while clicking in the field :", "FAIL");
				}
			}

			public void VerifyFavoritePatient(String patient) {
				try {
					WebElement ele = driver.findElement(By.xpath("//h3/span[contains(text(),'"+patient+"')]/ancestor::ion-label/parent::div/following-sibling::div//ion-icon[@name='star']"));
					boolean flag = ele.isDisplayed();
					if(flag=true) {
						reportStep(patient+" is displayed with the favourite icon", "PASS", false);
					}
					takeSnap();
				}catch (InvalidElementStateException e) {
					reportStep("The patient is not available", "FAIL");
				} catch (WebDriverException e) {
		            reportStep("The patient is not available", "FAIL");
		        }
			}
		
			public void VerifyDefaultTab(WebElement ele, String field) {
				try {
					WebDriverWait wait = new WebDriverWait(driver, 30);
		            wait.until(ExpectedConditions.elementToBeClickable(ele));
		            String activeTab = ele.getText();
		            if(activeTab.equalsIgnoreCase(field)) {
		            	reportStep(field+" is selected by default", "PASS", false);
		            }
		            else {
		            	reportStep(field+" is not selected by default", "FAIL", false);
		            }
					takeSnap();
				} catch (InvalidElementStateException e) {
				reportStep("The element: " + field + " is not available", "FAIL");
				} catch (WebDriverException e) {
	            reportStep("The element "+field+ "is not available", "FAIL");
	        }
		}

		public void VerifyElementNotPresent(List<MobileElement> list ) {
		        try {
		            List<MobileElement> value = list;
		            int valueresult = value.size();
		            if (valueresult == 0) {
		                reportStep("The element :" + list + "  is not displayed.", "PASS");
		                takeSnap();
		            }
		        } catch (InvalidElementStateException e) {
		            reportStep("The element: " + list + " is available", "FAIL");
		        } catch (WebDriverException e) {
		            reportStep("The element " + list + "is available", "FAIL");
		        }
		    }

			public void Clear(WebElement ele) {
	             ele.sendKeys(Keys.CONTROL + "a");
	             ele.sendKeys(Keys.DELETE);
	         }

			public String getStringAttribute(WebElement ele, String attribute) throws InterruptedException {
				String attValue = "";	
				try {
					attValue = ele.getAttribute(attribute);
					takeSnap();
					return attValue;
					
				} catch (InvalidElementStateException e) {
				reportStep("The element is not available", "FAIL");
				} catch (WebDriverException e) {
	            reportStep("The element is not available", "FAIL");
	        }
				return attValue;
				
			}
	
			
			public void actionClickMobile(MobileElement ele) {
				String text = "";
				try {
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.elementToBeClickable(ele));
					text = ele.getText();
					//JavascriptExecutor js = (JavascriptExecutor)driver;
					//js.executeScript("arguments[0].scrollIntoView(true);", ele);
					Actions action = new Actions(driver);
					action.moveToElement(ele).click().perform();
					reportStep("The element :" + text + "  is clicked.", "PASS");
					takeSnap();
				} catch (InvalidElementStateException e) {
					reportStep("The element: " + text + " could not be clicked", "FAIL");
				} catch (WebDriverException e) {
					reportStep("The element "+ele+ "is not available", "FAIL");
				}
			}

			@Override
			public void beforeSwitchToWindow(String windowName, WebDriver driver) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterSwitchToWindow(String windowName, WebDriver driver) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <X> void beforeGetScreenshotAs(OutputType<X> target) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeGetText(WebElement element, WebDriver driver) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterGetText(WebElement element, WebDriver driver, String text) {
				// TODO Auto-generated method stub	
			}


			@Override
			public void beforeAlertAccept(WebDriver driver) {
				// TODO Auto-generated method stub
				
			}		
	
			

			public void startCAUKApp(String SOR,String strplatformName, String strdeviceName, String strplatformVersion,String strdeviceOrientation,String ConfigURL) throws MalformedURLException, InterruptedException {

				String strAppID = "";
				String sUserNameApp = "";
				String sPasswordApp = "";
				

				try{
					if(SOR.equals("LORENZO") && (strplatformName.equals("iOS"))  ){
						strAppID = 	sLZOAppIDiOS;
					}else if (SOR.equals("LORENZO") && (strplatformName.equals("Android")) ){
						strAppID = 	sLZOAppIDAndroid;
						ConfigURL = "./assets/config/config.lorenzo-SIT-RMY-Cloud.json";
					}else if (SOR.equals("WEBPAS") && (strplatformName.equals("iOS")) ){
						strAppID = 	sWebPASAppIDiOS;
						ConfigURL = "./assets/config/config.webpas-koolaid-openid-4assure.json";
					}else if (SOR.equals("WEBPAS") && (strplatformName.equals("Android")) ){
						strAppID = 	sWebPASAppIDAndroid;	
						ConfigURL = "./assets/config/config.webpas-koolaid-openid-4assure.json";
					}
					 if(SOR.equals("LORENZO"))
					    {
					    	sUserNameApp = LZOUserNameApp;
					    	sPasswordApp = 	LZOPasswordApp;	
					    }
					    else if (SOR.equals("WEBPAS"))
					    {
					    	sUserNameApp = WebPASUserNameApp;
					    	sPasswordApp = 	WebPASPasswordApp;
					    }

					DesiredCapabilities capabilities = new DesiredCapabilities();
				    capabilities.setCapability("platformName", strplatformName);
				    capabilities.setCapability("build", strdeviceName+" "+strplatformVersion);
				    capabilities.setCapability("deviceName", strdeviceName);
				    capabilities.setCapability("platformVersion", strplatformVersion);  
				    capabilities.setCapability("isRealMobile", true);
				    capabilities.setCapability("app", strAppID); 
				    capabilities.setCapability("deviceOrientation", strdeviceOrientation);
				    capabilities.setCapability("console", true);
				    capabilities.setCapability("network", true);
				    capabilities.setCapability("visual", true);
				    capabilities.setCapability("devicelog", true);
				    capabilities.setCapability("autoWebview", false);
				    if(strplatformName.equals("iOS")) {
				    	 capabilities.setCapability("autoWebview", true);
				    }
				    
				    if(strdeviceOrientation.equals("LANDSCAPE")) {
				    	capabilities.setCapability("unicodeKeyboard", true);
				    //	capabilities.setCapability("resetKeyboard", true);
				    }
	    
					 if(SOR.equals("LORENZO"))
					    {
					    capabilities.setCapability("tunnel", true);
				        capabilities.setCapability("tunnelName", "Assurance");
				        capabilities.setCapability("timezone","UTC+01:00"); 
					    }
					 
					 if(SOR.equals("WEBPAS"))
					    {
				        capabilities.setCapability("timezone","UTC+11:00"); // need to update for WebPAS
					    }
			        
			        String hub = "https://"+ sLambdaUserName + ":" + sLambdaAccessKey + sLambdaGridURL;
			        driver = new AppiumDriver<MobileElement>(new URL(hub), capabilities);
					Thread.sleep(10000);
				    }catch(Exception e) {
				    	e.printStackTrace();
				    }		
				switchToWebView();
				//MobileElement WifiIcon = driver.findElementByXPath("//ion-icon[@aria-label='wifi']/parent::span/parent::ion-button");
				MobileElement WifiIcon = driver.findElementByXPath("//ion-icon[@name='wifi']/parent::span/parent::ion-button");
				click(WifiIcon, "Wifi Icon");
				Thread.sleep(3000);
				//MobileElement addConnection = driver.findElementByXPath("//ion-icon[@aria-label='ellipsis vertical outline']/parent::ion-button");
				MobileElement addConnection = driver.findElementByXPath("//ion-icon[@name='ellipsis-vertical-outline']/parent::ion-button");
				click(addConnection, "Add connections");
				Thread.sleep(3000);
				MobileElement enterManually = driver.findElementByXPath("//span[text()='Enter manually']/parent::button");
				click(enterManually, "Enter URL manually option");
				Thread.sleep(2000);
				MobileElement urlInput = driver.findElementByXPath("//input[@type='url']");
				click(urlInput, "URL");
	//			MobileElement addConn = driver.findElementByXPath("//h2[contains(text(),'Add Connection')]");
	//			click(addConn, "Add Connection");
				enterText(urlInput,ConfigURL);
				MobileElement addButton = driver.findElementByXPath("//span[text()='Add']/parent::button");
				click(addButton, "Add button");
				Thread.sleep(3000);
				MobileElement backButton = driver.findElementByXPath("//ion-back-button");
				click(backButton, "Back button");
				Thread.sleep(3000);
				MobileElement openIDLogin = driver.findElementByXPath("//*[contains(@class,'buttonchange')]");
				click(openIDLogin, "Navigate to openID page");
				Thread.sleep(15000);
				reportStep("Connection string entered", "PASS");
				if(SOR.equals("LORENZO")) {		
					if(strplatformName.equals("Android")){
						switchToNativeView();
						driver.findElementByXPath("//*[@resource-id='UserName']").sendKeys(sUserNameApp);
						driver.findElementByXPath("//*[@resource-id='Password']").sendKeys(sPasswordApp);
						Thread.sleep(2000);
						driver.findElementByXPath("//*[@resource-id='btnSubmit']").click();
					}
					else {
						driver.findElementByXPath("//*[@id='UserName']").sendKeys(sUserNameApp);
						driver.findElementByXPath("//*[@id='Password']").sendKeys(sPasswordApp);
						Thread.sleep(2000);
						driver.findElementByXPath("//*[@id='btnSubmit']").click();
					}
				}else if(SOR.equals("WEBPAS")) {
					if(strplatformName.equals("iOS")){
						switchToWebView();
						Thread.sleep(3000);
						driver.findElementByXPath("//input[@name='loginfmt']").sendKeys(WebPASUserNameApp);
						driver.findElementByXPath("//input[@value='Next']").click();
						Thread.sleep(3000);
						driver.findElementByXPath("//input[@name='passwd']").sendKeys(WebPASPasswordApp);
						driver.findElementByXPath("//input[@value='Sign in']").click();
						Thread.sleep(3000);
						List<MobileElement> authScreen = driver.findElements(By.id("btnAskLater"));
						clickIfPresent(authScreen);
						Thread.sleep(3000);
						driver.findElementByXPath("//input[@value='No']").click();
					}else {
						switchToNativeView();
						Thread.sleep(30000);
						driver.findElementByXPath("//*[@class='android.widget.EditText']").click();
						driver.findElementByXPath("//*[@class='android.widget.EditText']").sendKeys(WebPASUserNameApp);
						driver.findElementByXPath("//*[@class='android.widget.Button']").click();
						Thread.sleep(30000);
						switchToNativeView();
						driver.findElementByXPath("//*[@class='android.widget.EditText']").click();
						driver.findElementByXPath("//*[@class='android.widget.EditText']").sendKeys(WebPASPasswordApp);
						driver.findElementByXPath("//*[@class='android.widget.Button']").click();
						Thread.sleep(3000);
						switchToNativeView();
						driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "go"));
					}
				}
				Thread.sleep(10000);
				reportStep("Connection established", "PASS");
				switchToWebView();
				Thread.sleep(2000);
			}

	
			
		
				
	public void switchToNativeView() throws InterruptedException {
				Set<String> contexts1 =  driver.getContextHandles();
				String lastestContextView = (String) contexts1.toArray()[contexts1.size()-1];
				for (String context : contexts1) {
					if (context.contains("NATIVE")) {
						driver.context("NATIVE_APP");
						Thread.sleep(5000);
						break;
					}
				}
			}
			
			
			public void javascriptScroll (MobileElement element,String field) {
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
			
			public void clickIfPresent(List<MobileElement> elements) {
			    if (!elements.isEmpty()) {
			        elements.get(0).click();
			        System.out.println("Clicked");
			        reportStep("Element present in screen, Clicked", "PASS");
			    }
			    else {
			    	System.out.println("Skipped");
			    	reportStep("Element not present in screen, Skipped", "PASS");
			    }
			}
			
			public static String padLeft(String s, int n, char c) {
			    return String.format("%"+n+"s", s).replace(' ', c);
			}
			
			
			public String getIconName(MobileElement ele, String data) throws InterruptedException {
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
			
			 public String getIconClass(MobileElement ele, String data) throws InterruptedException {
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
			 
			 public String getIconSrc(MobileElement ele, String data) throws InterruptedException {
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
			 
			 public void checkCopyright(String SOR,String strplatformName, String strdeviceName, String strplatformVersion,String strdeviceOrientation) throws InterruptedException {
					
					String strAppID = "";
					try{
						if (SOR.equals("WEBPAS") && (strplatformName.equals("iOS")) ){
							strAppID = 	sWebPASAppIDiOS;	
						}else if (SOR.equals("WEBPAS") && (strplatformName.equals("Android")) ){
							strAppID = 	sWebPASAppIDAndroid;	
						}
			
						DesiredCapabilities capabilities = new DesiredCapabilities();
					    capabilities.setCapability("platformName", strplatformName);
					    capabilities.setCapability("build", strdeviceName+" "+strplatformVersion);
					    capabilities.setCapability("deviceName", strdeviceName);
					    capabilities.setCapability("platformVersion", strplatformVersion);  
					    capabilities.setCapability("isRealMobile", true);
					    capabilities.setCapability("app", strAppID); 
					    capabilities.setCapability("deviceOrientation", strdeviceOrientation);
					    capabilities.setCapability("console", true);
					    capabilities.setCapability("network", true);
					    capabilities.setCapability("visual", true);
					    capabilities.setCapability("devicelog", true);
					    capabilities.setCapability("autoWebview", false);
					    if(strplatformName.equals("iOS")) {
					    	 capabilities.setCapability("autoWebview", true);
					    }
					    
					    if(strdeviceOrientation.equals("LANDSCAPE")) {
					    	capabilities.setCapability("unicodeKeyboard", true);
					    //	capabilities.setCapability("resetKeyboard", true);
					    }
		    
				        String hub = "https://"+ sLambdaUserName + ":" + sLambdaAccessKey + sLambdaGridURL;
				        driver = new AppiumDriver<MobileElement>(new URL(hub), capabilities);
						Thread.sleep(10000);
					    }catch(Exception e) {
					    	e.printStackTrace();
					    }		
					    switchToWebView();
					    String Copyright = driver.findElementByXPath("//p[@class='copyright']").getText();
					    String licence = driver.findElementByXPath("//p[@class='licence']").getText();
					    MobileElement ele = driver.findElementByXPath("//div[@class='app-name']/img");  
					    
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
