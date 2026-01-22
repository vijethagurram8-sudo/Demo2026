package contactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.ContactPage;
import elementRepository.CreateInformation;
import elementRepository.CreatenewContact;
import elementRepository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtilitie;
@Listeners(genericUtility.Listenerimplimentation.class)
public class ToCreateContactTest extends BaseClass {
	@Test(groups="smoke")
	public void toCreatecontact_001() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getContacts().click();
		
		ContactPage cp = new ContactPage(driver);
		cp.getClickcontactimage().click();

		ExcelFileUtilitie eutil = new ExcelFileUtilitie();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 2, 2);
		
		CreatenewContact ccp = new CreatenewContact(driver);
		ccp.getCreatenewcontactlastname().sendKeys(LASTNAME);
		ccp.getTosaveclick().click();
		//fail the test script  call the Assert.fail(); method
		Assert.fail();
		CreateInformation cip = new CreateInformation(driver);
		String lastname = cip.getCreateinfo().getText();
		/*if(latname.contains(LASTNAME)){
		 * System.out.println(lastname+"passed");
		 * }else{
		 * System.out.println(lastname+"failed");
		 */
		Assert.assertTrue(lastname.contains(LASTNAME));
		
}

}
