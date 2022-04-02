package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.testng.listener.*;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAccountPage;
import utilities.Screenshots;

@Listeners({ExtentITestListenerClassAdapter.class})
public class LoginTest extends Pages.LoginPage{
	Pages.HomePage homePage = new Pages.HomePage();
	Pages.MyAccountPage myAccountPage = new Pages.MyAccountPage();
	Screenshots screenshots = new Screenshots();
	//=======================================================
	@Test
	public void login()
	{
		try {
			homePage.openAutomationPractice();
			homePage.clickSignIn();
			typeEmail("test@test.com");
			typePassword("123456");
			clickSubmit();
			myAccountPage.assertMyAccountTitle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void tackeScreenShot()
	{ 
		screenshots.captureScreenShot("testScreenshot");
	}
}