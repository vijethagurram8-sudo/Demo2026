package Practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TestScriptFour {

	public static void main(String[] args) {
		// step 1:launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// step 2:login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		// step 3:navigate to Organization link
		driver.findElement(By.linkText("Organizations")).click();
		// step 4:Click on create Organization look up image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		Random r = new Random();
		int randomvalue = r.nextInt(1000);
		// step 5:create organization with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys("jspider" + randomvalue);

		// step 6:select "energy"in the industry dropdown
		WebElement dropdown = driver.findElement(By.name("industry"));
		Select dropdownfield = new Select(dropdown);
		dropdownfield.selectByValue("Energy");
		// step 7:select "customer" in the type dropdown
		WebElement dropdown2 = driver.findElement(By.name("accounttype"));
		Select dropdownfield2 = new Select(dropdown2);
		dropdownfield2.selectByValue("Customer");
		// step 6:save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String orgname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgname.contains("jspider" + randomvalue)) {
			System.out.println(orgname + "------passed");
		} else {
			System.out.println(orgname + "----------failed");
		}
		String dropdownname = driver.findElement(By.id("dtlview_Industry")).getText();
		if (dropdownname.contains("Energy")) {
			System.out.println(dropdownname + "------passed");
		} else {
			System.out.println(dropdownname + "----------failed");
		}
		String dropdown21 = driver.findElement(By.id("dtlview_Type")).getText();
		if (dropdown21.contains("Customer")) {
			System.out.println(dropdown21 + "------passed");
		} else {
			System.out.println(dropdown21 + "----------failed");
		}
		// step 7:Logout of application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		// step 7:close Browser
		driver.quit();

	}

}
