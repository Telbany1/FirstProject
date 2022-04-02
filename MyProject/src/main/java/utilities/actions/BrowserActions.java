package utilities.actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BrowserActions extends TestBase{

    @SuppressWarnings("deprecation")
	public void navigateToPage(String url) {
    	driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        System.out.println(url);
        driver.navigate().to(url);
    }

    public void maximizeWindow() {
    	driver.manage().window().maximize();
    }

    public void relaunchBrowser()
    {
    	driver.quit();
    	launchBrowser();
    }

    public void openNewTab()
    {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void closeBrowser() {
    	driver.close();
    }

    public void quitBrowser() {
    	quitDriver();
    }
 //===========================================================================================
    public void refreshPage() {
    	driver.navigate().refresh();
    }
    
    public void navigateBack() {
    	driver.navigate().back();
    }

    public void navigateForward() {
    	driver.navigate().forward();
    }

    public void restoreView() {
    	driver.manage().window().maximize();
    	driver.navigate().refresh();
    }


    //Scrolling
    public void scrollDownToView(WebElement element) {
        ((JavascriptExecutor) driver).
                executeScript("arguments[0].scrollIntoView({behavior:'smooth',block: 'center'});",
                        element);
    }

    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    public void zoomOutOrIn(double percentageIndex) {
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='" + percentageIndex + "'");
    }

    public void scrollVertically(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ")", "");

    }

    public void scrollHorizontally(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + pixels + ",0)");
    }

    public void scrollDownToBottomOfPage() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    //Alerts
    public void approveAlert() {
    	driver.switchTo().alert().accept();
    }

    public boolean checkIfAlertIsPresent() {
        try {
        	driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    //check if window is scrolled up or not (Used to test the back to top button)
    public boolean isWindowScrolledUp() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        Long value = (Long) executor.executeScript("return window.pageYOffset;");
        if (value == 0) {
            return true;
        } else {
            return false;
        }
    }

    //Get Browser Title
    public String getBrowserTitle() {
        return driver.getTitle();
    }

    //Check if window is scrolled down or not
    public boolean isWindowScrolledDown() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        Object currValueInnerHeightObj = executor.executeScript("return window.innerHeight");
        Object currValueYOffsetObj = executor.executeScript("return window.pageYOffset");
        Object docHeightObj = executor.executeScript("return document.body.scrollHeight");
        Long currValue = 0L;
        Long docHeight = 0L;

        System.out.println(currValueInnerHeightObj.getClass().toString());
        if (currValueInnerHeightObj.getClass().toString().toLowerCase().contains("long")) {
            currValue = (Long) currValueInnerHeightObj;
        } else {
            currValue = Math.round((Double) currValueInnerHeightObj);
        }

        if (currValueYOffsetObj.getClass().toString().toLowerCase().contains("long")) {
            currValue += (Long) currValueYOffsetObj;
        } else {
            currValue += Math.round((Double) currValueYOffsetObj);
        }

        if (docHeightObj.getClass().toString().toLowerCase().contains("long")) {
            docHeight = (Long) docHeightObj;
        } else {
            docHeight = Math.round((Double) docHeightObj);
        }
        currValue++;
        System.out.println();
        System.out.println("currValue: " + currValue);
        System.out.println("docHeight: " + docHeight);
        return currValue >= docHeight;
    }

    public String getNetworkTabDetails() {
        String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        String netData = ((JavascriptExecutor) driver).executeScript(scriptToExecute).toString();
        System.out.println(netData);
        return  netData;
    }
}