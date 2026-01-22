package organizationTest;


import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.ContactPage;
import elementRepository.CreateInformation;
import elementRepository.CreateInformationOrganization;
import elementRepository.CreateOrganization;
import elementRepository.CreatenewContact;
import elementRepository.HomePage;
import elementRepository.Organisationpage;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtilitie;
import genericUtility.JavaUtility;
@Listeners(genericUtility.Listenerimplimentation.class)
public class TocreateOrganizationTest extends BaseClass {
	
		@Test(groups="regression")
		public void toCreateoganization_002() throws EncryptedDocumentException, IOException {
			
			
			HomePage hp = new HomePage(driver);
			hp.getOrganisations().click();
			
			Organisationpage op=new Organisationpage(driver);
			op.getClickonOrganizationpage().click();
			
			 CreateOrganization co = new CreateOrganization(driver);
			ExcelFileUtilitie eutil = new ExcelFileUtilitie();

		//	JavaUtility 
			JavaUtility jutil	=new JavaUtility();
			String ORGANIZATION = eutil.toReadDataFromExcelFile("Organization", 1, 2)+jutil.toGenerateRandomValue();
	         co.getCreatenewOrganozation().sendKeys(ORGANIZATION);
	         co.getTosaveclick().click();
	         
	 		
	         CreateInformationOrganization coif = new CreateInformationOrganization(driver);
	 		String organization = coif.getCreateinfoorganisation().getText();
	 		Assert.assertTrue(organization.contains(ORGANIZATION));

		}	

}


