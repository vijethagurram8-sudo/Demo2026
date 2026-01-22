package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	 public HomePage(WebDriver driver) {
		 //constructor
		 PageFactory.initElements(driver,this);
		 //class page factory,
	 }
		 @FindBy(linkText="Contacts")
		 private WebElement contacts;
		 
		 @FindBy(linkText="Organizations")
		 private WebElement organisations;
		 
		 @FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
		 private WebElement logoutelement;
		 
		 @FindBy(linkText="Sign Out")
		 private WebElement signout;
		public WebElement getContacts() {
			return contacts;
		}
		public WebElement getOrganisations() {
			return organisations;
		}
		public WebElement getLogoutelement() {
			return logoutelement;
		}
		public WebElement getSignout() {
			return signout;
		}
		 
	 }

