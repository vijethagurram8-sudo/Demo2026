package Practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import elementRepository.CreateInformationOrganization;
import elementRepository.CreateOrganization;
import elementRepository.HomePage;
import elementRepository.LoginPage;
import elementRepository.Organisationpage;
import genericUtility.ExcelFileUtilitie;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class PomTestScriptFour {

	public static void main(String[] args) throws IOException {

		// commondata PropertiesFileUtility class object
		PropertiesFileUtility putil = new PropertiesFileUtility();
		// specificdata ExcelFileUtilitie class object
		ExcelFileUtilitie eutil = new ExcelFileUtilitie();
		// for driver related actions WebDriverUtility class object
		WebDriverUtility wutil = new WebDriverUtility();
		// to take the data from propertie file object ref through
		// methods with string key
		String URL = putil.toReadDataFromPropertyfile("url");
		String BROWSER = putil.toReadDataFromPropertyfile("browser");
		String USERNAME = putil.toReadDataFromPropertyfile("username");
		String PASSWORD = putil.toReadDataFromPropertyfile("password");
		// to take the data from excel file class objects call methods
		// sheet row cell
		String ORGANIZATION = eutil.toReadDataFromExcelFile("Organization", 1, 2);
		// STEP 1 :launch browser
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		wutil.tomaximize(driver);// webdriver utility
		wutil.toImplicitWait(driver);// webdriver utility
		// step:2 login to application with valid credentials
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		// step3:Navigate to homepage and organisation
		HomePage hp = new HomePage(driver);
		hp.getOrganisations().click();
		// step 4:organization look up image click
		Organisationpage op = new Organisationpage(driver);
		op.getClickonOrganizationpage().click();
		// step 5:organization page dropdown feilds
		Random r = new Random();
		int randomvalue = r.nextInt(1000);
		CreateOrganization co = new CreateOrganization(driver);
		co.getCreatenewOrganozation().sendKeys(ORGANIZATION + randomvalue);

		WebElement dropdown1 = co.getIndustryType();
		wutil.toHandleDropDown(dropdown1, "Energy");
		WebElement dropdown2 = co.getAccountType();
		wutil.toHandleDropDown(dropdown2, "Customer");
		co.getTosaveclick().click();

		// step 6:
		CreateInformationOrganization coif = new CreateInformationOrganization(driver);
		String organization = coif.getCreateinfoorganisation().getText();
		if (organization.contains(ORGANIZATION + randomvalue)) {
			System.out.println(organization + "-------passed");
		} else {
			System.out.println(organization + "-------failed");
		}
		
	 	// STEP 7:LOGOUT OF APPLICATION
		wutil.toMouseHover(driver, hp.getLogoutelement());
		hp.getSignout().click();
}
		
	}

	
	

