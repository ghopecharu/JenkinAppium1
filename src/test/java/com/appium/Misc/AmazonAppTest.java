package com.appium.Misc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class AmazonAppTest {
	// Using WebView Tester -opening google site in webview Functionality

	static AndroidDriver<MobileElement> driver1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws MalformedURLException, InterruptedException {
		// URL
		  System.out.println("Creted Driver instance");
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		// Desired Capability
		DesiredCapabilities cap = new DesiredCapabilities();
		//cap.setCapability("deviceName", "Android");
       // cap.setCapability("app", "/opt/amazon-shopping-22-16-0-100.apk");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "9");
		cap.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		cap.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

		// AndroidDriver
		driver1 = new AndroidDriver<MobileElement>(url, cap);
		driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		SessionId sessionId = driver1.getSessionId();
		System.out.println(sessionId);
		Thread.sleep(1000);
	}
	@Test
    public void testSearch() throws InterruptedException {
        driver1.findElement(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button")).click();
        Thread.sleep(2000);
        driver1.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")).click();

       Thread.sleep(2000);
        
      driver1.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")).sendKeys("panasonic earphones");
        Thread.sleep(2000);
        
        List<MobileElement> searchDd = driver1.findElements(By.id("com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text"));
        System.out.println(searchDd.size());
        searchDd.get(1).click();
        
        MobileElement parentSearch = driver1.findElement(By.xpath("//android.view.View[@resource-id='search']"));
        
        List<MobileElement> searchProds = parentSearch.findElements(By.xpath(".//android.view.View"));
        System.out.println(searchProds.size());
        
     
        searchProds = driver1.findElements(By.xpath("//android.view.View[@resource-id='search']//android.view.View"));
        System.out.println(searchProds.size());
        
        List<MobileElement> lstViews = driver1.findElements(By.xpath("//android.widget.ToggleButton[@resource-id='s-all-filters']//parent::android.view.View/following-sibling::android.view.View"));
        lstViews = driver1.findElements(By.xpath("//android.widget.ToggleButton[@resource-id='s-all-filters']//parent::android.view.View//following-sibling::android.view.View"));
        for (MobileElement view : lstViews) {
            List<MobileElement> lstChild = view.findElements(By.xpath(".//android.view.View"));
            if (lstChild.size()>2) {
                String strText = lstChild.get(3).getAttribute("content-desc");
                System.out.println(strText);
                
            }
        }
        
	}
	}