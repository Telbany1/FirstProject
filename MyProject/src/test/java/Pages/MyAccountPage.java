package Pages;

import org.openqa.selenium.By;

import utilities.Assertions;
import utilities.actions.ElementActions;

public class MyAccountPage extends ElementActions{
	Assertions assertions = new Assertions();
	//=============================Locators==================================
    By myAccountTitle = By.cssSelector(".page-heading");
    //=============================Strings==================================
    String myAccountTitleText = "MY ACCOUNT";
    //===================================Actions===================================
    //-----------------------------------Assertions--------------------------------
    
    //------------------------------Text Assertions----------------------------------
    public void assertMyAccountTitle()
    {
    	assertions.assertElementText(myAccountTitle, myAccountTitleText);
    }
}