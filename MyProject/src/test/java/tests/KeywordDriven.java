package tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAccountPage;
import utilities.Paths;
import utilities.Screenshots;
import utilities.readers.ExcelReader;

public class KeywordDriven extends Pages.LoginPage{
	Pages.HomePage homePage = new Pages.HomePage();
	Pages.MyAccountPage myAccountPage = new Pages.MyAccountPage();
	ExcelReader excelReader = new ExcelReader();
	Screenshots screenshots = new Screenshots();
	String keyword = "not assigned";
	int row = 1;
	//=======================================================
	@BeforeClass()
	public void loadLoginData()
	{
		String filePath = Paths.KEYWORD_DATA;
		try {
			excelReader.setPath(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void login()
	{
		while(!keyword.equals("EndOfTest"))
		{
			try {
				keyword = ExcelReader.read(row, 0);
				if(keyword.equals("OpenWebSite"))
				{
					homePage.openAutomationPractice();
				}
				else if(keyword.equals("clickSignIn"))
				{
					homePage.clickSignIn();
				}
				else if(keyword.equals("typeEmail"))
				{
					typeEmail(excelReader.read(row, 1));
				}
				else if(keyword.equals("typePassword"))
				{
					typePassword(excelReader.read(row, 1));
				}
				else if(keyword.equals("typePassword"))
				{
					typePassword(excelReader.read(row, 1));
				}
				else if(keyword.equals("clickSubmitLogin"))
				{
					clickSubmit();
				}
				else if(keyword.equals("validateMyAccount"))
				{
					myAccountPage.assertMyAccountTitle();
				}
				else if(keyword.equals("takeScreenshot"))
				{
					screenshots.captureScreenShot("testScreenshot");
				}
				row++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}