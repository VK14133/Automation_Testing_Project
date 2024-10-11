package org.example.Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    String login_url = "https://siteone.chezuba-test.net/#login";
    String home_page_url = "https://siteone.chezuba-test.net/";
    String portal_home_page_url = "https://siteone.chezuba-test.net/";

    WebDriver driver;

    @FindBy(id="login_email") WebElement emailTextBox;

    @FindBy(id="login_password") WebElement passwordTestBox;

    @FindBy(className="btn-login") WebElement loginButton;

    @FindBy(className="user-email") WebElement adminEmail;

    public Login(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
    }

    public void navigateToLoginUrl(){
        if(!driver.getCurrentUrl().equals(this.login_url)){
            driver.get(login_url);
        }
    }

    public void nagigateToPortalHomePage(){
        if(!driver.getCurrentUrl().equals(this.portal_home_page_url));
        driver.get(portal_home_page_url);
    }

    public boolean login(String email, String password){
        try {
            emailTextBox.sendKeys(email);
            passwordTestBox.sendKeys(password);
            loginButton.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains(home_page_url));

            Thread.sleep(1000);
            return isAdminLoggedIn(email);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAdminLoggedIn(String email){
        try {
            nagigateToPortalHomePage();
            return adminEmail.getText().equals(email);
        } catch (Exception e) {
        }
        return false;
    }


    // public boolean userLogin(String email, String password){
    //     try {
            
    //         return true;
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         return false;
    //     }
    // }

}
