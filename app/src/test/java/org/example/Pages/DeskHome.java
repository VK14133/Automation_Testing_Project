package org.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class DeskHome {
    WebDriver driver;
    String desk_home_page_url = "https://siteone.chezuba-test.net/app";

    @FindBy(xpath="//li[@class=\"nav-item dropdown dropdown-navbar-user dropdown-mobile\"]") WebElement avatarElement;
    @FindBy(xpath = "//a[contains(text(), 'Logout')]") WebElement logoutButton;


    public DeskHome(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
    }

    public void navigateToDeskHomePage(){
        if(!driver.getCurrentUrl().equals(this.desk_home_page_url)){
            driver.get(this.desk_home_page_url);
        }
    }

    public void deskLogout(){
        try {
            avatarElement.click();
            Thread.sleep(1000);
            logoutButton.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
