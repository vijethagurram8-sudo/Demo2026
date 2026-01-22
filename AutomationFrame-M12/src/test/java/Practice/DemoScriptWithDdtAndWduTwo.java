package Practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtilitie;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithDdtAndWduTwo {

	public static void main(String[] args) throws IOException {
		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtilitie eutil = new ExcelFileUtilitie();
		WebDriverUtility wutil = new WebDriverUtility();
		 String URL = putil.toReadDataFromPropertyfile("url");
		 String BROWSER = putil.toReadDataFromPropertyfile("browser");
		 String USERNAME = putil.toReadDataFromPropertyfile("username");
		 String PASSWORD = putil.toReadDataFromPropertyfile("password");
		//to read data from excelfile
		// String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		 String ORGANIZATION = eutil.toReadDataFromExcelFile("Organization", 1, 2);
		 //STEP 1 :launch browser
		 WebDriver driver = null;
		 if(BROWSER.equals("chrome")) {
			 driver=new ChromeDriver();
			  }
		 else if(BROWSER.equals("edge")) {
			 driver=new EdgeDriver();
			  }
		 else if(BROWSER.equals("firefox")) {
			 driver=new FirefoxDriver();
			  }
		 
		 wutil.tomaximize(driver);//webdriver utility
		 wutil.toImplicitWait(driver);//webdriver utility
		 //step:2 login to application with valid credentials 
		 driver.get(URL);
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			//step 3:navigate to organization link
			driver.findElement(By.linkText("Organizations")).click();
			//step 4:Click on create Organization look up image
			driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
			//generates random number
			Random r = new Random();
			int randomvalue = r.nextInt(1000);
			//step 5:create organization with mandatory fields
			driver.findElement(By.name("accountname")).sendKeys(ORGANIZATION+randomvalue);
			//step 6:save and verify
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			String orgname=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(orgname.contains(ORGANIZATION+randomvalue)) {
				System.out.println(orgname +"------passed");
				}else {
					System.out.println(orgname +"----------failed");
				}
			//step 7:Logout of application
			WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			wutil.toMouseHover(driver, logout);
			driver.findElement(By.linkText("Sign Out")).click();
			//step 8:close Browser
			driver.quit();
	}
	}


