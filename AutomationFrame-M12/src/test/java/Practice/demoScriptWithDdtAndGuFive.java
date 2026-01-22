package Practice;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtility.ExcelFileUtilitie;
import genericUtility.PropertiesFileUtility;

public class demoScriptWithDdtAndGuFive {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		PropertiesFileUtility pu = new PropertiesFileUtility();
		String URL = pu.toReadDataFromPropertyfile("url");
		String BROWSER = pu.toReadDataFromPropertyfile("browser");
		String USERNAME = pu.toReadDataFromPropertyfile("username");
		String PASSWORD = pu.toReadDataFromPropertyfile("password");
		ExcelFileUtilitie eu = new ExcelFileUtilitie();
		String LASTNAME = eu.toReadDataFromExcelFile("Contacts", 1, 2);
		
		String ORGANIZATION = eu.toReadDataFromExcelFile("Organization", 1, 2);
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// step 2 :login to application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		//step 3 :navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		//step 4:click on create contact look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		//step 5:create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		// step 6:select the organization from organization lookup
		String parentid = driver.getWindowHandle();
		String parentidtitle = driver.getTitle();
		System.out.println(parentid + parentidtitle);
		driver.findElement(By.xpath("//img[@alt='Select']")).click();
		Set<String> allid = driver.getWindowHandles();
		allid.remove(parentid);
		for (String id : allid) {
			driver.switchTo().window(id);
			String idtitle = driver.getTitle();
			System.out.println(id + idtitle);
		}
		WebElement a = driver.findElement(By.linkText(ORGANIZATION));

		Actions action = new Actions(driver);
		action.moveToElement(a).perform();
		action.click(a).perform();
        driver.switchTo().window(parentid);
		// step 7:save and verify
     // step 7:save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname + "------passed");
		} else {
			System.out.println(lastname + "----------failed");
		}
		String orgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (orgname.contains(ORGANIZATION)) {
			System.out.println(orgname + "------passed");
		} else {
			System.out.println(orgname + "----------failed");
		}
		// step 7:logout of application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		// step 7:close Browser
		 driver.quit();

	}

}
