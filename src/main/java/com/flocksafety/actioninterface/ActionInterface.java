package com.flocksafety.actioninterface;


import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ActionInterface {
	
	//Added all user actions abstract methods to achieve Abstraction  
	public void scrollByVisibilityOfElement(By locator) throws InterruptedException;
	public void actionClick(By locator);
	public boolean isDisplayed(By locator);
	public boolean type(By locator, String text);
	public boolean findElement(By locator);
	public boolean isSelected(By locator);
	public boolean isEnabled(By locator);
	public boolean selectBySendkeys(By locator, String value);
	public boolean selectByIndex(By locator, int index);
	public boolean selectByValue(By locator,String value);
	public boolean selectByVisibleText(By locator, String visibletext);
	public boolean mouseHoverByJavaScript(By locator);
	 public void JSClick(By locator);
	public boolean switchToFrameByLocator(By locator);
	public boolean switchToFrameByIndex(int index);
	public boolean switchToFrameById(String idValue);
	public boolean switchToFrameByName(String nameValue);
	public boolean switchToDefaultFrame();
	public void mouseOverElement(By locator);
	public boolean moveToElement(By locator);
	public boolean mouseover(By locator);
	public boolean draggable(By source_locator, int x, int y);
	public boolean draganddrop(By source_locator, By target_locator);
	public boolean slider(By locator, int x, int y);
	public boolean rightclick(By locator);
	public boolean switchWindowByTitle(String windowTitle, int count);
	public boolean switchToNewWindow();
	public boolean switchToOldWindow();
	public int getColumncount(WebElement row);
	public int getRowCount(WebElement table);
	public boolean Alert();
	public boolean launchUrl(String url);
	public boolean isAlertPresent();
	public String getCurrentURL();
	public String getTitle();
	public void click(By locator);
	public void clickElement(By locator)throws InterruptedException;
	public void fluentWait(By locator);
	public void implicitWait();
	public void pageLoadTimeOut();
	public String screenShot(WebDriver driver, String filename);
	public String getCurrentTime();
	public String getCurrentDate(int todayDate, int nextDate, int daysToSkip, String dateFormat);
	public String isFileDownloaded(String fileText, String fileExtension, int timeOut);
	public boolean isAttribtuePresent(By locator, String attribute);
	public String getAlertText();
	public void explicitWaitElementClickable(By locator);
	public void explicitWait(By locator);
	public void waitForElement(By locator, String elementText) throws InterruptedException;
	public void waitUntilElementVisible(By locator) throws InterruptedException;
	public void waitforPageLoad() throws InterruptedException;
	public void randomId();
	public void TextArea(String label, String text) throws InterruptedException;
	public void saveURL(String record);
	public void elementPresent(By locator) throws InterruptedException ;
	public void scrollByPosition(int position);
	public void gotoRecordURL(String env, String object, String sfdcId);
	public void gotoRecordUsingRelatedListURL(String env, String object);
}
