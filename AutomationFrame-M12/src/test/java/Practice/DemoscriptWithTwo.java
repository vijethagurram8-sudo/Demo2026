package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoscriptWithTwo {

	public static void main(String[] args) throws IOException {
		//Read data from excel file
				FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
				Properties prop = new Properties();
				prop.load(fis);
				String BROWSER = prop.getProperty("browser");
				String URL = prop.getProperty("url");
			    //step 1:launch Browser
				String USERNAME = prop.getProperty("username");
				String PASSWORD = prop.getProperty("password");
				FileInputStream fisw = new FileInputStream(".\\src\\test\\resources\\TestDataAdvance.xlsx");
				Workbook wb = WorkbookFactory.create(fisw);
				String ORGANIZATION = wb.getSheet("Organization").getRow(1).getCell(2).toString();
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
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				//step 2 :login to application with valid credentials
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
				Actions action = new Actions(driver);
				action.moveToElement(logout).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				//step 7:close Browser
				driver.quit();
				

	}

}
