package org.example.TestCases;

import org.example.Pages.ApproveApplication;
import org.example.Pages.DeskHome;
import org.example.Pages.EventApply;
import org.example.Pages.EventForm;
import org.example.Pages.Login;
import org.example.Pages.PortalHome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testcase {
    WebDriver driver;
    String eventId = "";
    String activityName = "First Automation Testing Projeect";
    @BeforeSuite
    public void createDriver(){
        WebDriverManager.chromedriver().setup();
        // Set Chrome options to enable incognito mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        // Initialize WebDriver with Chrome options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    // Login as Admin;
    // @Test
    public void loginAdmin(){
        boolean status = false;
        Login login = new Login(driver);

        // Navigate to Login URL
        login.navigateToLoginUrl();

        // Login the Admin
        status = login.login("vikram@chezuba.net", "Vikram@chezuba2024");

        Assert.assertTrue(status,"Error in Login");
        PortalHome portalHome = new PortalHome(driver);
        portalHome.logoutFromPortal();

        Assert.assertEquals(driver.getCurrentUrl(), "https://siteone.chezuba-test.net/?cmd=web_logout");
    }

    // Logic to switch to desk
    public void switchToDesk() throws InterruptedException{
        PortalHome portalHome = new PortalHome(driver);
        portalHome.switchToDesk();
        Assert.assertEquals(driver.getCurrentUrl(), "https://siteone.chezuba-test.net/app/reports-and-dashboards");
    }

    @Test(priority=1)
    public void createActivityEvent() throws InterruptedException{
        boolean status = false;
        Login login = new Login(driver);

        // Navigate to Login URL
        login.navigateToLoginUrl();

        // Login the Admin
        status = login.login("vikram+001@chezuba.net", "Vikram@chezuba2024");

        Assert.assertTrue(status,"Error in Login");

        // Switch to desk
        switchToDesk();

        EventForm eventForm = new EventForm(driver);
        eventForm.searchNewEvent("New Activity Event");

        System.out.println(driver.getCurrentUrl());

        status = eventForm.createEvent(this.activityName, "Garba Dance", "2", "Mathura Ji", "01234567879", "chezu@teju.com", "Ramlila maidan", "23","+05:30 Asia/Kolkata");

        Assert.assertTrue(status, "Not able to create the Event");

        Thread.sleep(3000);

        this.eventId = eventForm.eventId;
        

        System.out.println(driver.getCurrentUrl());

        // verify the status
        WebElement eventStatus = driver.findElement(By.xpath("//span[@class='indicator-pill whitespace-nowrap gray']"));
        System.out.println(eventStatus.getText());
        Assert.assertEquals(eventStatus.getText(), "Draft");
        WebElement actionButton = driver.findElement(By.xpath("//*[@id=\"page-Activity Event\"]/div[1]/div/div/div[2]/div[3]/div[2]/button"));

        
        actionButton.click();
        Thread.sleep(2000);

        // click on publich button
        WebElement publishButton = driver.findElement(By.xpath("//*[@id=\"page-Activity Event\"]/div[1]/div/div/div[2]/div[3]/div[2]/ul/li[1]/a"));
        publishButton.click();

        DeskHome deskHome = new DeskHome(driver);
        deskHome.navigateToDeskHomePage();
        deskHome.deskLogout();

        Assert.assertEquals(driver.getCurrentUrl(), "https://siteone.chezuba-test.net/#login");

        
    }

    // @Test
    public void loginPortalUser(){
        boolean status = false;
        Login login = new Login(driver);

        // Navigate to Login URL
        login.navigateToLoginUrl();

        // Login the Admin
        status = login.login("vikram+002@chezuba.net", "Vikram@chezuba2024");

        Assert.assertTrue(status,"Error in Login");
        PortalHome portalHome = new PortalHome(driver);
        portalHome.logoutFromPortal();

        Assert.assertEquals(driver.getCurrentUrl(), "https://siteone.chezuba-test.net/?cmd=web_logout");
    }

    @Test(priority = 2)
    public void applyForActivity() throws InterruptedException{
        boolean status = false;
        Login login = new Login(driver);

        // Navigate to Login URL
        login.navigateToLoginUrl();

        // Login the Admin
        status = login.login("vikram+002@chezuba.net", "Vikram@chezuba2024");

        Assert.assertTrue(status,"Error in Login");

        EventForm eventForm = new EventForm(driver);
        this.eventId = eventForm.eventId;
        System.out.println(this.eventId);
        EventApply eventApply = new EventApply(driver);
        eventApply.navigateToActivityCalanderPage();
        eventApply.applyActivityEvent(this.activityName);

        WebElement waitingText = driver.findElement(By.xpath("//span[@class='text-muted']"));
        Assert.assertEquals(waitingText.getText(), "Wait for the approval to participate in this activity");
        
        PortalHome portalHome = new PortalHome(driver);
        portalHome.nagigateToPortalHomePage();
        portalHome.logoutFromPortal();
    }

    @Test(priority=3)
    public void approveActivityApplication() throws InterruptedException{
        boolean status;

        Login login = new Login(driver);

        // Navigate to Login URL
        login.navigateToLoginUrl();

        // Login the Admin
        status = login.login("vikram+001@chezuba.net", "Vikram@chezuba2024");

        Assert.assertTrue(status,"Error in Login");

        // Switch to desk
        switchToDesk();

        ApproveApplication approveApplication = new ApproveApplication(driver);
        approveApplication.navigateToActivityApplicaitonPage();
        approveApplication.approveEventApplication(eventId);

        WebElement applicationStatus = driver.findElement(By.xpath("//*[@id=\'page-Activity Application\']/div[1]/div/div/div[2]/div[3]/div[2]/ul/li[1]"));

        System.out.println(applicationStatus.getText());

    }
    

    @AfterClass
    public  void quitTest(){
        driver.quit();
    }
}
