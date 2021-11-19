package dailer;

import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestDailerApp {
	
	static AndroidDriver<MobileElement> driver;
	
 @Test(dataProvider = "contacts")
  
  public void ParamAddContact(String name,String phone) {
	 System.out.println(name+"-"+phone);
  }
  
  @BeforeMethod
  
  public void beforeMethod() {
  }

  @AfterMethod
  
  public void afterMethod() {
  }


  @DataProvider
  
  public String[][] contacts() {
    return new String[][] {
      new String[] { "Priya", "832783" },
      new String[] { "Beh", "898989" },
    };
  }
  @BeforeClass
  public void StartApp() throws MalformedURLException {
	  URL url = new URL("http://127.0.0.1:4723/wd/hub");
		// Desired Capability
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "9");
		cap.setCapability("appPackage", "com.google.android.dialer");
		cap.setCapability("appActivity", "com.google.android.dialer.extensions.GoogleDialtactsActivity");

		// AndroidDriver
		driver = new AndroidDriver<MobileElement>(url, cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		SessionId sessionId = driver.getSessionId();
		System.out.println(sessionId);
	  
  }
@Test
public void testAddContact() {
	System.out.println();
}
@Test
public void testExcelData() throws IOException {
	//Reading through excel
	
	FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\Simplilearn Automation\\Phase4\\ReadExcelData.xlsx");
    @SuppressWarnings("resource")
	XSSFWorkbook wbook = new XSSFWorkbook(fis);
    XSSFSheet sheet = wbook.getSheetAt(0);
    int rows =sheet.getLastRowNum();
    for (int row =1;row<rows;row++) {
    	String name = sheet.getRow(1).getCell(0).getStringCellValue();
		String phone = sheet.getRow(1).getCell(1).getStringCellValue();
		
		System.out.println("Test: Name from xlsx is ["+name+"]");
		System.out.println("Test: Phone from xlsx is ["+phone+"]");


    }
}
  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
