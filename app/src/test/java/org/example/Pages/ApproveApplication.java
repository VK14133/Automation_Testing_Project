package org.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ApproveApplication {
    WebDriver driver;

    String activity_application_url = "https://siteone.chezuba-test.net/app/activity-application";

    public ApproveApplication(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
    }

    public void navigateToActivityApplicaitonPage() throws InterruptedException{
        if(!driver.getCurrentUrl().equals(this.activity_application_url)){
            driver.get(activity_application_url);
            Thread.sleep(2000);
        }
    }

    public boolean approveEventApplication(String eventId){
        try {
            WebElement eventIds = driver.findElement(By.xpath("//div[@class='result']//child::div[@class='list-row-container']//following::span[@class='level-item bold ellipsis']/a"));

            // for(WebElement id : eventIds){
            //     if(id.getText().equalsIgnoreCase(eventId)){
            //         id.click();
            //         Thread.sleep(2000);
            //         driver.getCurrentUrl();
            //     }
            // }

            eventIds.click();
            Thread.sleep(2000);

            System.out.println(driver.getCurrentUrl());

            WebElement actionButton = driver.findElement(By.xpath("//*[@id=\'page-Activity Application\']/div[1]/div/div/div[2]/div[3]/div[2]/button"));
            actionButton.click();

            Thread.sleep(2000);

            WebElement approveButton = driver.findElement(By.xpath("//*[@id=\"page-Activity Application\"]/div[1]/div/div/div[2]/div[3]/div[2]/ul/li[1]"));
            approveButton.click();

            Thread.sleep(3000);

            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }


}
