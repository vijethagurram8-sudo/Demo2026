package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * this class is consist of
 */

public class WebDriverUtility {
	/**
	 * this method is used to maximize browser provided driver
	 */
	public void tomaximize(WebDriver driver) {

		driver.manage().window().maximize();
	}

	/**
	 * this method is used to maximize browser provided driver
	 */
	public void tominimize(WebDriver driver) {

		driver.manage().window().minimize();
	}

	/**
	 * this method is used to wait until the element loaded in webpage
	 */
	public void toImplicitWait(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	/**
	 * this method will wait until the element is clickable provided driver and
	 * element
	 * 
	 * @param driver
	 * @param element
	 */
	public void elementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * this method will wait until the element is clickable provided driver and
	 * element
	 * 
	 * @param driver
	 * @param element
	 */
	public void visibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * this method is used to handle dropdown using index provided with dropdown
	 * element and index
	 * 
	 * @param element
	 * @param index
	 */
	public void toHandleDropDown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * this method is used to handle dropdown using value provided with dropdown
	 * element and value
	 * 
	 * @param element
	 * @param value
	 */
	public void toHandleDropDown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * this method is used to handle dropdown using visibletext provided with
	 * dropdown element and visibletext
	 * 
	 * @param text
	 * @param element
	 */
	public void toHandleDropDown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * this method is used to
	 * 
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);

	}

	/**
	 * 
	 * @param driver
	 * @param name_id
	 */
	public void toHandleFrame(WebDriver driver, String name_id) {
		driver.switchTo().frame(name_id);
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void toHandleFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * 
	 * @param driver
	 */
	public void tobackToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}

	/**
	 * 
	 * @param driver
	 */
	public void toBackToDefaultFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void toMouseHover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * this method is used to perform rightclick on element provided with driver and
	 * element
	 * 
	 * @param driver
	 * @param element
	 */

	public void toRightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	/**
	 * this method is used to perform doublelclick on element provided driver and
	 * element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toDoubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * this metod is used to perform drog and drop action on element provided driver
	 * and element
	 * 
	 * @param driver
	 * @param source
	 * @param target
	 */
	public void toDragAndDrop(WebDriver driver, WebElement source, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).perform();
	}

	/**
	 * this method is used to handle alert popup by accepting it provide driver
	 * 
	 * @param driver
	 */
	public void toHandleAlertPopupByAccept(WebDriver driver) {
		Alert alertpopup = driver.switchTo().alert();
		alertpopup.accept();
	}

	/**
	 * this method is used to handle alert popup by dissmissing it provided driver
	 * 
	 * @param driver
	 */
	public void toHandleAlertPopupByDissmiss(WebDriver driver) {
		Alert alertpopup = driver.switchTo().alert();
		alertpopup.dismiss();
	}

	/**
	 *imp here return type String" this method is used to capture message in alert popup and then accept it
	 * provided driver
	 * 
	 * @param driver
	 * @return
	 */
	public String toCaptureAlertMessageAndAccept(WebDriver driver) {
		Alert alertpopup = driver.switchTo().alert();
		String alertMessage = alertpopup.getText();
		alertpopup.accept();
		return alertMessage;
	}
	/*   
	 * javascript excuiter
	 */
	public void toscroll(WebDriver driver) {
	JavascriptExecutor js =(JavascriptExecutor) driver;	
 	js.executeScript("window.scrollBy(0,500)");
	}
  	/**
	 * this method is used to take screenshot of an entire webpage
	 *  provided driver and screenshot name/ file folder right clickon project new foder errorshots
	 * @param driver
	 * @param screenshotname
	 * @throws IOException
	 */

	public String toTakeScreenshot(WebDriver driver, String screenshotname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorshots/" + screenshotname + ".png");
		FileHandler.copy(temp, src);
		return src.getAbsolutePath();//Extent Reports.getting path where screenshots
	}
	/**
	 * this method is used to transfer driver control to window provided 
	 * driver and perticular window title
	 * @param driver
	 * @param partialtitle
	 */
	
	public void toSwitchwindow(WebDriver driver, String partialtitle) {
		//step 1:capture all session ids
		Set<String> allids = driver.getWindowHandles();
		//step 2: transfer to every window and capture title
		for(String id:allids) {
			String title = driver.switchTo().window(id).getTitle();
			//compair the title captured with partialtitle given
			if(title.contains(partialtitle)) {
				break;
			}
			
		}
		
	}
}
