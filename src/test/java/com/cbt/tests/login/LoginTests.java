package com.cbt.tests.login;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.cbt.pages.AllOrdersPage;
import com.cbt.pages.LoginPage;
import com.cbt.tests.TestBase;
import com.cbt.utilities.ConfigurationReader;

public class LoginTests extends TestBase {

	@Test(groups= {"smoke"})
	public void positiveloginTest() {
		//today
		//name of our test
		extentLogger=report.createTest("Positive login test");
		//infor()-> to print a message
		extentLogger.info("entering user credentials");
		
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		extentLogger.info("Verifying log out link");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		
		//today
		assertTrue(new AllOrdersPage().logoutLink.isDisplayed());
		// this text will display when assertion passes.
		extentLogger.pass("Verifies log out link displayed");
	}

	@Test(priority = 2)
	public void positiveLoginUsingPageObjectModel() {
		extentLogger=report.createTest("Invalid username test");
		LoginPage loginPage = new LoginPage();
		loginPage.userName.sendKeys(ConfigurationReader.getProperty("username"));
		loginPage.password.sendKeys(ConfigurationReader.getProperty("password"));
		loginPage.loginButton.click();
		//today
		fail("failed to log in");
		extentLogger.pass("Verifies log out link displayed");

	}

	@Test(priority = 1)
	public void invalidUsernameTestUsingPageObjectModel() {
		extentLogger=report.createTest("Invalid username Test");
		LoginPage loginPage = new LoginPage();
		loginPage.userName.sendKeys("invalid");
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
		String errMsg = loginPage.invalidUserNameErrMsg.getText();
       assertEquals(errMsg, "Invalid Login or Password.");
       
       extentLogger.pass("Verifies error message: \"Invalid Login or Password.\"");
	}

	@Test(priority = 2)
	public void anotherpositiveLoginUsingPageObjectModel() {
		extentLogger=report.createTest("Another positive login test 2");
		LoginPage loginPage = new LoginPage();
		loginPage.userName.sendKeys(ConfigurationReader.getProperty("username"));
		loginPage.password.sendKeys(ConfigurationReader.getProperty("password"));
		loginPage.loginButton.click();
		
		throw new SkipException("This test in work progress");
	}
}
