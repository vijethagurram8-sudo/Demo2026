package Practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestScriptfive {

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
		// step 3:navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		// step 4:click on create contact look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		// step 5:create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("vijetha");
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
		WebElement a = driver.findElement(By.linkText("jspider"));

		Actions action = new Actions(driver);
		action.moveToElement(a).perform();
		action.click(a).perform();
        driver.switchTo().window(parentid);
		// step 7:save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (lastname.contains("vijetha")) {
			System.out.println(lastname + "------passed");
		} else {
			System.out.println(lastname + "----------failed");
		}
		String orgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (orgname.contains("jspider")) {
			System.out.println(orgname + "------passed");
		} else {
			System.out.println(orgname + "----------failed");
		}
		// step 8:logout of application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		// step 9:close Browser
		 driver.quit();
	}
}
