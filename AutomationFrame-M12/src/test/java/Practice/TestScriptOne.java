package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestScriptOne {

	public static void main(String[] args) {
		// step 1:launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//step 2:login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		//step 3:navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		//step 4:click on create contact look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		//step 5:create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("vijetha");
		//step 6:save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(lastname.contains("vijetha")) {
			System.out.println(lastname +"------passed");
			}else {
				System.out.println(lastname +"----------failed");
			}
		//step 7:logout of application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		//step 7:close Browser
		driver.quit();
	}

}
