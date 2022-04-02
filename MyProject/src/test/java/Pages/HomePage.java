package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.ConfigUtil;
import utilities.TestBase;
import utilities.actions.ElementActions;

public class HomePage extends ElementActions{
	//=============================Locators==================================
    static By signIn = By.cssSelector("a[title='Log in to your customer account']");
    //=============================Strings==================================
   
    //===================================Actions===================================
    public static void openAutomationPractice() {
        System.out.println(ConfigUtil.Web_URL);
        driver.navigate().to(ConfigUtil.Web_URL);
    }
    
    public static void clickSignIn()
    {
    	getElement(signIn).click();
    }
    //-----------------------------------Assertions--------------------------------
   
    //------------------------------Text Assertions----------------------------------
}