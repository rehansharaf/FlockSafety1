
package com.flocksafety.actiondriver;

import static org.testng.Assert.fail;

import java.io.File;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flocksafety.actioninterface.ActionInterface;
import com.flocksafety.api.CRUDOperation;
import com.flocksafety.api.SOQLqueryBuilder;
import com.flocksafety.base.TestBase;

/**
 * @author Imran 10/19/2024
 *
 */
public class Action extends TestBase implements ActionInterface {

	long timeOut = 120;
	WebDriver driver;
	JavascriptExecutor js; 
	
	CRUDOperation 		crudOperation;
	SOQLqueryBuilder 	soqlQueryBuilder;
	
	public Action(WebDriver driver) {
	 
		this.driver = driver;
		js = (JavascriptExecutor) driver;
		crudOperation = new CRUDOperation();
		soqlQueryBuilder = new SOQLqueryBuilder();
	}
	
	
	//************************ General Functionality*****************************
	
	
	@Override
	public void randomId() {
		Integer Id = (int) (System.currentTimeMillis() / 1000);
		String rndId = Integer.toString(Id);
		rndNum.put("randomId", rndId);
	}
	
	@Override
	public void scrollByVisibilityOfElement(By locator) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
		Thread.sleep(1000);
	}
	
	@Override
	public  void scrollByPosition(int position) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,"+position+")");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Override
	public void actionClick(By locator) {

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(locator)).click().build().perform();

	}

	@Override
	public boolean findElement(By locator) {
		boolean flag = false;
		try {
			driver.findElement(locator).isDisplayed();
			flag = true;
		} catch (Exception e) {
			// System.out.println("Location not found: "+locatorName);
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully Found element at");

			} else {
				System.out.println("Unable to locate element at");
			}
		}
		return flag;
	}

	@Override
	public boolean isDisplayed(By locator) {
		boolean flag = false;
		flag = findElement(locator);
		if (flag) {
			flag = driver.findElement(locator).isDisplayed();
			if (flag) {
				System.out.println("The element is Displayed");
			} else {
				System.out.println("The element is not Displayed");
			}
		} else {
			System.out.println("Not displayed ");
		}
		return flag;
	}

	@Override
	public boolean isSelected(By locator) {
		boolean flag = false;
		flag = findElement(locator);
		if (flag) {
			flag = driver.findElement(locator).isSelected();
			if (flag) {
				System.out.println("The element is Selected");
			} else {
				System.out.println("The element is not Selected");
			}
		} else {
			System.out.println("Not selected ");
		}
		return flag;
	}

	@Override
	public boolean isEnabled(By locator) {
		boolean flag = false;
		flag = findElement(locator);
		if (flag) {
			flag = driver.findElement(locator).isEnabled();
			if (flag) {
				System.out.println("The element is Enabled");
			} else {
				System.out.println("The element is not Enabled");
			}
		} else {
			System.out.println("Not Enabled ");
		}
		return flag;
	}

	/**
	 * Type text at location
	 * 
	 * @param locatorName
	 * @param text
	 * @return - true/false
	 */
	@Override
	public boolean type(By locator, String text) {
		
		String errorMessage = null;
		boolean flag = false;
		try {
			flag = driver.findElement(locator).isDisplayed();
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(text);
			// logger.info("Entered text :"+text);
			flag = true;
		} catch (Exception e) {
			System.out.println("Location Not found");
			errorMessage = e.getMessage();
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully entered value");
			} else {
				System.out.println("Unable to enter value :"+errorMessage);
			}

		}
		return flag;
	}

	@Override
	public boolean selectBySendkeys(By locator, String value) {
		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Select value from the DropDown");		
			} else {
				System.out.println("Not Selected value from the DropDown");
				// throw new ElementNotFoundException("", "", "")
			}
		}
	}

	/**
	 * select value from DropDown by using selectByIndex
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param index       : Index of value wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 * 
	 */
	@Override
	public boolean selectByIndex(By locator, int index) {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Index");
			} else {
				System.out.println("Option not selected by Index");
			}
		}
	}

	/**
	 * select value from DD by using value
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param value       : Value wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 */

	@Override
	public boolean selectByValue(By locator,String value) {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Value");
			} else {
				System.out.println("Option not selected by Value");
			}
		}
	}

	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param visibletext : VisibleText wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 */

	@Override
	public boolean selectByVisibleText(By locator, String visibletext) {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by VisibleText");
			} else {
				System.out.println("Option not selected by VisibleText");
			}
		}
	}

	@Override
	public boolean mouseHoverByJavaScript(By locator) {
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("MouseOver Action is performed");
			} else {
				System.out.println("MouseOver Action is not performed");
			}
		}
	}

	
	
	 public void clickElement(By locator) throws InterruptedException { 
	    	
	    	waitUntilElementVisible(locator);
	    	js.executeScript("arguments[0].click();", driver.findElement(locator)); 
	    	    
	    }
	
	
	    
	 @Override 
	 public  void waitforPageLoad() throws InterruptedException { 
	 			ExpectedCondition<Boolean> pageLoadCondition = new
	                ExpectedCondition<Boolean>() {
	                    @Override
						public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                    }
	                };
	        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	        wait.until(pageLoadCondition);
	    }
	
	
	@Override
	public void JSClick(By locator) {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(locator));
			// driver.executeAsyncScript("arguments[0].click();", element);

			flag = true;

		}

		catch (Exception e) {
			throw e;

		} finally {
			if (flag) {
				System.out.println("Click Action is performed");
			} else if (!flag) {
				System.out.println("Click Action is not performed");
			}
		}
		//return flag;
	}
	
	@Override
	public boolean switchToFrameByLocator(By locator) {
		

		boolean flag = false;
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
			driver.switchTo().frame(driver.findElement(locator));
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with locator \"" + locator + "\" is selected");
			} else {
				System.out.println("Frame with locator \"" + locator + "\" is not selected");
			}
		}
	
	}

	@Override
	public boolean switchToFrameByIndex(int index) {
		boolean flag = false;
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
			driver.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with index \"" + index + "\" is selected");
			} else {
				System.out.println("Frame with index \"" + index + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame ID.
	 * 
	 * @param idValue : Frame ID wish to switch
	 * 
	 */
	@Override
	public boolean switchToFrameById(String idValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with Id \"" + idValue + "\" is selected");
			} else {
				System.out.println("Frame with Id \"" + idValue + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue : Frame Name wish to switch
	 * 
	 */
	@Override
	public boolean switchToFrameByName(String nameValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with Name \"" + nameValue + "\" is selected");
			} else if (!flag) {
				System.out.println("Frame with Name \"" + nameValue + "\" is not selected");
			}
		}
	}

	@Override
	public boolean switchToDefaultFrame() {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				// SuccessReport("SelectFrame ","Frame with Name is selected");
			} else if (!flag) {
				// failureReport("SelectFrame ","The Frame is not selected");
			}
		}
	}

	@Override
	public void mouseOverElement(By locator) {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(driver.findElement(locator)).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				System.out.println(" MouserOver Action is performed on ");
			} else {
				System.out.println("MouseOver action is not performed on");
			}
		}
	}

	@Override
	public boolean moveToElement(By locator) {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", locator);
			Actions actions = new Actions(driver);
			// actions.moveToElement(driver.findElement(locator)).build().perform();
			actions.moveToElement(driver.findElement(locator)).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean mouseover(By locator) {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(driver.findElement(locator)).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			/*
			 * if (flag) {
			 * SuccessReport("MouseOver ","MouserOver Action is performed on \""+locatorName
			 * +"\""); } else {
			 * failureReport("MouseOver","MouseOver action is not performed on \""
			 * +locatorName+"\""); }
			 */
		}
	}
	@Override
	public boolean draggable(By source_locator, int x, int y) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDropBy(driver.findElement(source_locator), x, y).build().perform();
			Thread.sleep(5000);
			flag = true;
			return true;

		} catch (Exception e) {
		
			return false;
			
		} finally {
			if (flag) {
				System.out.println("Draggable Action is performed on \""+source_locator+"\"");			
			} else if(!flag) {
				System.out.println("Draggable action is not performed on \""+source_locator+"\"");
			}
		}
	}
	@Override
	public boolean draganddrop(By source_locator, By target_locator) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDrop(driver.findElement(source_locator), driver.findElement(target_locator)).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("DragAndDrop Action is performed");
			} else if(!flag) {
				System.out.println("DragAndDrop Action is not performed");
			}
		}
	}
	
	@Override
	public boolean slider(By locator, int x, int y) {
		boolean flag = false;
		try {
			// new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
			// .perform();
			new Actions(driver).dragAndDropBy(driver.findElement(locator), x, y).build().perform();// 150,0
			Thread.sleep(5000);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Slider Action is performed");
			} else {
				System.out.println("Slider Action is not performed");
			}
		}
	}
	
	@Override
	public boolean rightclick(By locator) {
		boolean flag = false;
		try {
			Actions clicker = new Actions(driver);
			clicker.contextClick(driver.findElement(locator)).perform();
			flag = true;
			return true;
			// driver.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("RightClick Action is performed");
			} else {
				System.out.println("RightClick Action is not performed");
			}
		}
	}
	
	@Override
	public boolean switchWindowByTitle(String windowTitle, int count) {
		boolean flag = false;
		try {
			Set<String> windowList = driver.getWindowHandles();

			String[] array = windowList.toArray(new String[0]);

			driver.switchTo().window(array[count-1]);

			if (driver.getTitle().contains(windowTitle)){
				flag = true;
			}else{
				flag = false;
			}
			return flag;
		} catch (Exception e) {
			//flag = true;
			return false;
		} finally {
			if (flag) {
				System.out.println("Navigated to the window with title");
			} else {
				System.out.println("The Window with title is not Selected");
			}
		}
	}
	@Override
	public boolean switchToNewWindow() {
		boolean flag = false;
		try {

			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[1].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Window is Navigated with title");				
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}
	@Override
	public boolean switchToOldWindow() {
		boolean flag = false;
		try {

			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[0].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Focus navigated to the window with title");			
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}
	@Override
	public int getColumncount(WebElement row) {
		List<WebElement> columns = row.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;
	}
	
	@Override
	public int getRowCount(WebElement table) {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}
	
	
	/**
	 * Verify alert present or not
	 * 
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 * 
	 */
	@Override
	public boolean Alert() {
		boolean presentFlag = false;
		Alert alert = null;

		try {
			// Check the presence of alert
			alert = driver.switchTo().alert();
			// if present consume the alert
			alert.accept();
			presentFlag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (!presentFlag) {
				System.out.println("The Alert is handled successfully");		
			} else{
				System.out.println("There was no alert to handle");
			}
		}

		return presentFlag;
	}
	@Override
	public boolean launchUrl(String url) {
		boolean flag = false;
		try {
			driver.navigate().to(url);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Successfully launched \""+url+"\"");				
			} else {
				System.out.println("Failed to launch \""+url+"\"");
			}
		}
	}
	
	@Override
	public boolean isAlertPresent() 
	{ 
		try 
		{ 
			driver.switchTo().alert(); 
			return true; 
		}   // try 
		catch (NoAlertPresentException Ex) 
		{ 
			return false; 
		}   // catch 
	}
	
	@Override
	public String getAlertText() 
	{
		String presentText = "";
		Alert alert = null;

		try {
			// Check the presence of alert
			alert = driver.switchTo().alert();
			// if present consume the alert
			presentText = alert.getText();
			alert.accept();
			
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} 

		return presentText;
	}
	
	@Override
	public String getTitle() {
		boolean flag = false;

		String text = driver.getTitle();
		if (flag) {
			System.out.println("Title of the page is: \""+text+"\"");
		}
		return text;
	}
	
	@Override
	public String getCurrentURL()  {
		boolean flag = false;

		String text = driver.getCurrentUrl();
		if (flag) {
			System.out.println("Current URL is: \""+text+"\"");
		}
		return text;
	}
	
	@Override
	public void click(By locator) {

		boolean flag = false;
		String errorMessage = null;
		driver.findElement(locator).click();
		flag = true;
		
		/*
		try {
			driver.findElement(locator).click();
			flag = true;
			//return true;
		} catch (Exception e) {
			errorMessage = e.getMessage();
			//return false;
		} 
		*/
	}
	
	@Override
	public void fluentWait(By locator) {
	    Wait<WebDriver> wait = null;
	    try {
	        wait = new FluentWait<WebDriver>((WebDriver) driver)
	        		.withTimeout(Duration.ofSeconds(20))
	        	    .pollingEvery(Duration.ofSeconds(2))
	        	    .ignoring(Exception.class);
	        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
	        driver.findElement(locator).click();
	    }catch(Exception e) {
	    }
	}
	
	
	@Override
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@Override
	public void explicitWaitElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
	}
	
	@Override
	public void explicitWait(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	@Override
	public void pageLoadTimeOut() {
		driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
	}
	
	
	@Override
	public synchronized String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		
		// This new path for jenkins
//		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename + "_"
//				+ dateName + ".png";
		
		String base64Img = null;
		try {
			base64Img = convertImgToBase64(destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return  base64Img;
	}
	
	
	public String convertImgToBase64(String imgPath) throws IOException {
		
		byte[] file = FileUtils.readFileToByteArray(new File(imgPath));
        String base64Img = new String(Base64.encodeBase64String(file));
		return base64Img;
	}
	
	
	@Override
	public String getCurrentTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}
	
	
	@Override
	 public String isFileDownloaded(String fileText, String fileExtension, int timeOut) {
	        String folderName = System.getProperty("user.dir")+"\\src\\test\\resources\\downloadeddata\\";
	        File[] listOfFiles;
	        int waitTillSeconds = timeOut;
	        boolean fileDownloaded = false;
	        String filePath = null; 

	        long waitTillTime = Instant.now().getEpochSecond() + waitTillSeconds;
	        while (Instant.now().getEpochSecond() < waitTillTime) {
	            listOfFiles = new File(folderName).listFiles();
	            for (File file : listOfFiles) {
	                String fileName = file.getName().toLowerCase();
	                if (!fileName.contains(".tmp")) {
	                	if(!fileName.contains(".part")) {
	                		if(!fileName.contains(".crdownload")) {
	                			fileDownloaded = true;
	                			filePath = file.getAbsolutePath();
	                			break;
	                		}
	                	}
	                }
	            }
	            if (fileDownloaded) {
	                break;
	            }
	        }
	        return filePath;
	}
	
	

	@Override
	public String getCurrentDate(int todayDate, int nextDate, int daysToSkip, String dateFormat) {
	
	      if(todayDate == 1) {
	    	
	    	  LocalDate dateObj = LocalDate.now();
		  	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		  	  return dateObj.format(formatter);
		  	  
	      }else {
	
	    	  Date dt = new Date();
			  Calendar c = Calendar.getInstance(); 
			  c.setTime(dt); 
			  c.add(Calendar.DATE, daysToSkip);
			  dt = c.getTime();
			  return new SimpleDateFormat(dateFormat).format(dt);

	      }
	  	  
        	
	}
	
	
	@Override
	public boolean isAttribtuePresent(By locator, String attribute) {
	    Boolean result = false;
	    try {
	        String value = driver.findElement(locator).getAttribute(attribute);
	        if (value != null){
	            result = true;
	        }
	    } catch (Exception e) {}

	    return result;
	}
	
	
	public void waitUntilElementVisible(By locator) {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
	}
	
	
	@Override
	public void waitForElement(By locator, String elementText) throws InterruptedException {
	   	  		
	   	  for (int second = 0;; second++) {
	   		     if (second >= 40) fail("timeout");
	   		     try { 
	   		    	
	   		    	 Thread.sleep(1000);
	   		    	 String g1 = driver.findElement(locator).getText();
	   		    	 elementText = elementText.trim();
	   		    	 g1 = g1.trim();
	   		    	
	   		    	 if (elementText.equals(g1))
	   		    	 
	   					 break;		    	 
	   		    	 
	   		     } 
	   		     catch (Exception e){
	   		    	 System.out.println(e.getMessage());
	   		     }
	   		     Thread.sleep(1000);
	   	  	
	   	  }
	   	  
	     }

	
	@Override
	public void elementPresent(By locator) throws InterruptedException {
	   	  
	   	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	   	  for (int second = 0;; second++) {
	   		     if (second >= 40) fail("timeout");
	   		     try { 
	   		    	
	   		    	 Thread.sleep(3000);
	   		    	 	   		    	
	   		    	 if (isDisplayed(locator)) 
	   		    	 	 break;		    	 
	   		     } 
	   		     catch (Exception e){
	   		    	 System.out.println(e.getMessage());
	   		     }
	   		     Thread.sleep(1000);
	   	  	
	   	  }
	   	  
	     }
	
	

	//***********************General API Functions*****************************
	
	
	
	
	
	
	
	
	
	
	
	

	//***********************General Page Functions*****************************
	
	public void TextArea(String label, String text) throws InterruptedException {
		WebElement field = driver.findElement(By.xpath("//label[contains(text(),'"+label+"')]/..//textarea"));
		field.sendKeys(text);
	}
	
	public void clickRecordinRelatedList() throws InterruptedException {
		System.out.println(rndNum.get("randomId"));
		url.put("RelatedListURL", getCurrentURL());
		By field = By.xpath("//a[contains(@title, '"+ TestBase.rndNum.get("randomId")+"')]");
		for (int i=0; i<3; i++) {
			if (isDisplayed(field)) {}
			else {driver.navigate().to(url.get("RelatedListURL")); i++;Thread.sleep(5000);}
		}
		JSClick(field);
	}
	
	public void saveURL(String record) {
		String URL = driver.getCurrentUrl();
		url.put(record, URL);
	}
	
	public void gotoRecordURL(String env, String object, String sfdcId) {
		driver.navigate().to("https://flocksafety--"+env+".sandbox.lightning.force.com/lightning/r/"+object+"/"+sfdcId+"/view");
	}
	@Override
	public void gotoRecordUsingRelatedListURL(String env, String object) {
		//driver.navigate().to("https://flocksafety--"+env+".sandbox.lightning.force.com/lightning/r/"+object+"/"+sfdcId+"/view");
		driver.navigate().to("https://flocksafety--"+env+".sandbox.lightning.force.com/lightning/o/"+object+"/list?filterName=__Recent");
	}
	
	
}
