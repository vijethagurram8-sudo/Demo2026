package organizationTest;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.CreateInformationOrganization;
import elementRepository.CreateOrganization;
import elementRepository.HomePage;
import elementRepository.Organisationpage;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtilitie;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;
@Listeners(genericUtility.Listenerimplimentation.class)
public class ToCreateOrganizationIndustryTest extends BaseClass {
	@Test(groups="regression")
	public void tocreateOrgwithIndustry_004() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getOrganisations().click();

		Organisationpage op = new Organisationpage(driver);
		op.getClickonOrganizationpage().click();

		CreateOrganization co = new CreateOrganization(driver);
		ExcelFileUtilitie eutil = new ExcelFileUtilitie();
		
		JavaUtility jutil	=new JavaUtility();
		String ORGANIZATION = eutil.toReadDataFromExcelFile("Organization", 1, 2)+jutil.toGenerateRandomValue();
        co.getCreatenewOrganozation().sendKeys(ORGANIZATION);
        
		WebDriverUtility wutil = new WebDriverUtility();
		WebElement dropdown1 = co.getIndustryType();
		wutil.toHandleDropDown(dropdown1, "Chemicals");
		co.getTosaveclick().click();
		
		CreateInformationOrganization coif = new CreateInformationOrganization(driver);
		String organization = coif.getCreateinfoorganisation().getText();
		Assert.assertTrue(organization.contains(ORGANIZATION));
	}
}
