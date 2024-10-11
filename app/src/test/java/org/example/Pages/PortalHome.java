package org.example.Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PortalHome {
    WebDriver driver;
    String portal_home_page_url = "https://siteone.chezuba-test.net/";
    String desk_home_page_url = "https://siteone.chezuba-test.net/app";
    String logout_url = "https://siteone.chezuba-test.net/?cmd=web_logout";

    @FindBy(id="website-post-login") WebElement profileAvatat;

    @FindBy(className="switch-to-desk") WebElement switch_to_desk_button;

    @FindBy(xpath="//a[@href='/?cmd=web_logout']") WebElement logout_button;

    @FindBy(className="ce-block__content") WebElement card;

    @FindBy(className="app-logo") WebElement appLogo;

    public PortalHome(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);

    }

    public void nagigateToPortalHomePage(){
        if(!driver.getCurrentUrl().equals(this.portal_home_page_url));
        driver.get(portal_home_page_url);
    }

    public void logoutFromPortal(){
        try {
            profileAvatat.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(logout_button));

            //click on logout button
            logout_button.click();

            wait.until(ExpectedConditions.urlContains(logout_url));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void switchToDesk() throws InterruptedException{
        profileAvatat.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(switch_to_desk_button));
        //click on switch-to-desk button
        switch_to_desk_button.click();
        wait.until(ExpectedConditions.urlContains(desk_home_page_url));
        //click on the logo
        appLogo.click();
        wait.until(ExpectedConditions.visibilityOf(card));
        // Thread.sleep(5000);
    }
}
