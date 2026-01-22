package Practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import elementRepository.ContactPage;
import elementRepository.CreateInformation;
import elementRepository.CreateOrganization;
import elementRepository.CreatenewContact;
import elementRepository.HomePage;
import elementRepository.LoginPage;
import genericUtility.ExcelFileUtilitie;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class PomTestScript {

	public static void main(String[] args) throws IOException {
		// commondata PropertiesFileUtility class object
		PropertiesFileUtility putil = new PropertiesFileUtility();
		// specificdata ExcelFileUtilitie class object
		ExcelFileUtilitie eutil = new ExcelFileUtilitie();
		// for driver related actions WebDriverUtility class object
		WebDriverUtility wutil = new WebDriverUtility();
		// to take the data from propertie file object ref through
		//methods with string key
		String URL = putil.toReadDataFromPropertyfile("url");
		String BROWSER = putil.toReadDataFromPropertyfile("browser");
		String USERNAME = putil.toReadDataFromPropertyfile("username");
		String PASSWORD = putil.toReadDataFromPropertyfile("password");
		// to take the data from excel file class objects call methods
        //sheet row cell
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
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
		// step3:Navigate to contacts link
		HomePage hp = new HomePage(driver);
		hp.getContacts().click();
		//step 4:
		 ContactPage cp = new ContactPage(driver);
		 cp.getClickcontactimage().click();
		// STEP 5:
		CreatenewContact cnc = new CreatenewContact(driver);
		cnc.getCreatenewcontactlastname().sendKeys(LASTNAME);
		cnc.getTosaveclick().click();
		// step 6:
		CreateInformation cif = new CreateInformation(driver);
		String lastname = cif.getCreateinfo().getText();
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname + "-------passed");
		} else {
			System.out.println(lastname + "-------failed");
		}
		// STEP 7:LOGOUT OF APPLICATION
		wutil.toMouseHover(driver, hp.getLogoutelement());
		hp.getSignout().click();
	}
	}
