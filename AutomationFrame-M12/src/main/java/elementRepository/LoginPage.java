package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	//constructor
	 public LoginPage(WebDriver driver) {
		 PageFactory.initElements(driver,this);//call object curret page 
	 }
	 @FindBy(name="user_name")
	 private WebElement usernameTextfield;
	 
	@FindAll({ @FindBy(name="user_password"), @FindBy(xpath="//input[@type='password']")})
	 private WebElement passwordTextfield;
	
	@FindBy(id="submitButton")
	 private WebElement loginButton;
	// right click source get generic getters 
	public WebElement getUsernameTextfield() {
		return usernameTextfield;
	}
	public WebElement getPasswordTextfield() {
		return passwordTextfield;
	}
	public WebElement getLoginButton() {
		return loginButton;
	}
	
	 
}
