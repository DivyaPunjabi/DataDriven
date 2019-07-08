package com.model;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class MercuryToursTest 
{
	public WebDriver driver;
	
  @Test(dataProvider = "getData")
  public void loginwithValidds(String username,String password)
  {
	     driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(username);
	     driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	     driver.findElement(By.xpath("//input[@name='login']")).click();
	     System.out.println("User has login into Mercury Tours application successfully");
	     boolean act_flag= driver.findElement(By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']")).isDisplayed();
	     boolean exp_flag=true;
	     Assert.assertEquals(act_flag, exp_flag);
	     driver.findElement(By.linkText("SIGN-OFF")).click();
	  
  }
  
  @BeforeMethod
  public void getAllCookies() 
  {
	  System.out.println("In getAllCookies method under BeforeMethod annotation");
	  Set<Cookie> cookies=driver.manage().getCookies();
	  for(Cookie cookie:cookies)
	  {
		System.out.println(cookie.getName());  
	  }
  }

  @AfterMethod
  public void captureScreenShot() throws IOException 
  {
	  System.out.println("In CaptureScreenShot method under AfterMethod annotation");
	  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFileToDirectory(src, new File("C:\\Maven\\DataDrivenProject\\ScreenShot\\"));
	  System.out.println("ScreenShot Captured Successfully");
  }


  @DataProvider
  public Object[][] getData() throws IOException 
  {
    ExcelConfigData config=new ExcelConfigData();
    config.readExcel("C:\\Maven\\DataDrivenProject\\LoginDetails.xlsx");
	
    int rowno=config.getRowCount("Sheet1");
    System.out.println("Total number of rows are:" +rowno);
    
    int columnno=config.getColumnCount("Sheet1");
    System.out.println("Total number of columns are:"+columnno);
    
    Object[][] data=new Object[rowno][columnno];
    		
    for(int i=0;i<rowno;i++)
    {
    	for(int j=0;j<columnno;j++)
    	{
    		data[i][j]= config.getData("Sheet1", i, j);
    	}
    }
    return data;
    
    }
  
  @BeforeClass
  public void maximizeBrowser() 
  {
	  System.out.println("In maximizeBrowser method under BeforeClass annotation");
	  driver.manage().window().maximize();
	  System.out.println("Browser has maximized successfully"); 
  }

  @AfterClass
  public void deleteAllCookies() 
  {
	  System.out.println("In deleteAllCookies method under AfterClass annotation");
	  driver.manage().deleteAllCookies();
  }

  @BeforeTest
  public void EnterApplicationUrl() 
  {
	  System.out.println("In EnterApplicationUrl method under BeforeTest annotation");
	  driver.get("http://newtours.demoaut.com/mercurywelcome.php");
	  System.out.println("Application Opened Successfully");
  }

  @AfterTest
  public void dbConnectionClosed() 
  {
	  System.out.println("In dbConnectionClosed method under AfterTest method");
  }

  @BeforeSuite
  public void OpenBrowser() 
  {
	  System.out.println("In OpenBrowser method under BeforeSuite annotation");
	  System.setProperty("webdriver.chrome.driver", "D:\\Testing\\chromedriver.exe");
	  driver=new ChromeDriver();
	  System.out.println("Chrome Browser opened successfully");
	  
  }

  @AfterSuite
  public void closeBrowser() 
  {
	  System.out.println("In closeBrowser method under AfterSuite annotation");
	  driver.close();
  }

}
