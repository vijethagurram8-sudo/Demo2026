package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptFive {

	public static void main(String[] args) throws IOException {
		// Read data from excel file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		// step 1:launch Browser
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		FileInputStream fisw = new FileInputStream(".\\src\\test\\resources\\TestDataAdvance.xlsx");
		Workbook wb = WorkbookFactory.create(fisw);
		String LASTNAME = wb.getSheet("Contacts").getRow(1).getCell(2).toString();
		String LASTNAME1 = wb.getSheet("Contacts").getRow(2).getCell(2).toString();
		
		String ORGANIZATION = wb.getSheet("Organization").getRow(1).getCell(2).toString();
		String ORGANIZATION1 = wb.getSheet("Organization").getRow(2).getCell(2).toString();
		
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
