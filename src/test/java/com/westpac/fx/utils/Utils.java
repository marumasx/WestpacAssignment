package com.westpac.fx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.generic.Select;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	public static WebDriver driver;
	public static Properties fx;
	// public static String Browser;

	public Utils() throws IOException {
		fx = new Properties();
		FileInputStream fxfile = new FileInputStream("./src/test/java/fx.properties");
		fx.load(fxfile);
		// Browser= tolleylibrary.getProperty("browser");

	}

	public void kill() {
		System.out.println("Closing Browser");
		driver.manage().deleteAllCookies();
		driver.close();
		driver = null;
	}

	public static WebDriver initializeBrowser(String url, String Browser) {

		if (Browser.equalsIgnoreCase("Chrome")) {
			System.out.println("Opening Chrome Browser Session");
			File chromeDriver = new File(fx.getProperty("ChromeDriver"));
			System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			// chromePrefs.put("download.default_directory", sourceDir);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--disable-geolocation");
			options.addArguments("--disable-extensions");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(cap);
			driver.manage().window().maximize();
			driver.get(url);
			System.out.println("Chrome -->" + driver.getTitle());
		} else if (Browser.equalsIgnoreCase("Firefox")) {
			System.out.println("Opening FF Browser Session");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(url);
		}

		else if (Browser.equalsIgnoreCase("IE")) {
			System.out.println("Opening InternetExplorer Browser Session");
			File IEDriver = new File(fx.getProperty("IEDriver"));
			System.setProperty("webdriver.ie.driver", IEDriver.getAbsolutePath());
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			driver = new InternetExplorerDriver(capabilities);

			driver.manage().window().maximize();
			driver.get(url);
		}

		return driver;
	}

	/* Use this method to mouse over on any element */

	public static void mouseover(WebElement element) {

		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	/*
	 * This method will be used to verify whether an element is displayed and
	 * returns boolean value
	 */

	public static boolean iselementdisplayed(WebElement element) {
		boolean result = element.isDisplayed();
		System.out.println(result);
		return result;

	}

	/*
	 * This method will be used to tell the web driver to wait till the
	 * visibility of mentioned web element
	 */
	public void waitforvisibilityofelement(WebDriver driver, int timeinseconds, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, timeinseconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/*
	 * This method will be used to tell the web driver to wait till the
	 * visibility of mentioned alert
	 */
	public void waitforalert(WebDriver driver, int timeinseconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeinseconds);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	/*
	 * This method will be used to tell the web driver to wait till the state of
	 * mentioned element selected
	 */
	public void waitforelementselectionstate(WebDriver driver, int timeinseconds, WebElement element,
			boolean selected) {

		WebDriverWait wait = new WebDriverWait(driver, timeinseconds);
		wait.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
	}

	/*
	 * This method will be used to tell the web driver to wait till the
	 * mentioned element to be clickable
	 */
	public static void waitforelementtobclickable(WebDriver driver, int timeinseconds, WebElement element,
			boolean selected) {

		WebDriverWait wait = new WebDriverWait(driver, timeinseconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/*
	 * This method will be used to tell the web driver to wait till the
	 * mentioned frame to be visible
	 */
	public void waitforframaetobeavailable(WebDriver driver, int timeinseconds, WebElement element, boolean selected) {

		WebDriverWait wait = new WebDriverWait(driver, timeinseconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));

	}

	/*
	 * This method will be used to tell the web driver to wait till the
	 * mentioned locator is invisible
	 */
	public void waitinvisibilityofelement(WebDriver driver, int timeinseconds, WebElement element, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, timeinseconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

	}

	/*
	 * This method will be used to tell the web driver to wait till the
	 * mentioned locator with text is invisible
	 */
	public void waitforinvisibilityofelementwithtext(WebDriver driver, int timeinseconds, String text, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, timeinseconds);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));

	}

	/*
	 * This method will be used to tell the web driver to wait till the presence
	 * of all the elements located by the mentioned locator
	 *
	 */
	public void waitforpresenceofallelementslocated(WebDriver driver, int timeinseconds, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, timeinseconds);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	/*
	 * This method will be used to tell the web driver to wait till the presence
	 * of element located by the mentioned locator
	 */
	public void waitforpresenceofelementlocated(WebDriver driver, int timeinseconds, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, timeinseconds);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	/*
	 * This method will be used to tell the web driver to wait till the text to
	 * be present in the mentioned element
	 * 
	 */
	public void waitfortexttobepresent(WebDriver driver, int timeinseconds, WebElement element, String text) {

		WebDriverWait wait = new WebDriverWait(driver, timeinseconds);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));

	}

	/*
	 * This method will be used to tell the web driver to wait till the
	 * mentioned title visible
	 * 
	 * 
	 */
	public void waitfortitle(WebDriver driver, int timeinseconds, String title) {

		WebDriverWait wait = new WebDriverWait(driver, timeinseconds);
		wait.until(ExpectedConditions.titleIs(title));

	}

	/*
	 * This method will be used to tell the web driver to wait till the
	 * mentioned title matches
	 * 
	 * 
	 */
	public void waitfortitlecontains(WebDriver driver, int timeinseconds, String title) {

		WebDriverWait wait = new WebDriverWait(driver, timeinseconds);
		wait.until(ExpectedConditions.titleContains(title));

	}

	/*
	 * This method will returns the currently opened application's URL
	 * 
	 * 
	 */
	public String geturl() {
		String url = driver.getCurrentUrl();
		return url;

	}

	/*
	 * 
	 * This method will generate random alpha-numeric string
	 * 
	 */
	public String createrandomstring(int num) {
		String randomstring = RandomStringUtils.randomAlphanumeric(num);
		return randomstring;
	}

	/*
	 * 
	 * This method will be used to select elements from Dropdown box
	 * 
	 */
	public static void selectFromDropdown(WebElement element, String index, String value, String visibletext) {
		org.openqa.selenium.support.ui.Select se = new org.openqa.selenium.support.ui.Select(element);

		if (value == null && visibletext == null) {
			int index1 = Integer.parseInt(index);
			se.selectByIndex(index1);
		} else if (index == null && value == null) {
			se.selectByVisibleText(visibletext);
		} else if (index == null && visibletext == null) {
			se.selectByValue(value);
		}

	}
	/*
	 * 
	 * This method will be used to tell the webdriver to wait for particular time and 
	 * if it finds the specified element before the mentioned time also it will continue with the execution 
	 * 
	 */
	public static void impicitwait(int TimeOut) {
		driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
	}

}
