package org.example.Pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EventApply {
    WebDriver driver;

    String eventCalanderPageUrl = "https://siteone.chezuba-test.net/activities/calendar";

    @FindBy(xpath="//div[@class='event-box']//child::div") List<WebElement> eventList;
    
    

    public EventApply(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
    }

    public void navigateToActivityCalanderPage(){
        if(!driver.getCurrentUrl().equals(this.eventCalanderPageUrl)){
            driver.get(eventCalanderPageUrl);
        }
    }

    public boolean applyActivityEvent(String eventName){
        try {
            // EventForm eventForm = new EventForm(driver);
            // String eventId = eventForm.eventId;
            // System.out.println(eventId);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            

            if(eventList.size()>0){
                for(WebElement names : eventList){
                    if(names.getText().equalsIgnoreCase(eventName)){
                        names.click();
                        Thread.sleep(2000);
                        System.out.println(driver.getCurrentUrl());
    
                        Set<String> handles = driver.getWindowHandles();
                        driver.switchTo().window(handles.toArray(new String[handles.size()])[1]);

                        System.out.println(driver.getCurrentUrl());
                        
                        WebElement applyButton = driver.findElement(By.xpath("//*[@id=\"apply-button\"]"));
                        applyButton.click();
                        Thread.sleep(2000);
    
                        WebElement yesButton =  driver.findElement(By.xpath("//div[@class='modal-content']//following-sibling::div[@class='modal-footer']//following::button[text()='Yes']"));
                        yesButton.click();
                        Thread.sleep(5000);
                        return true;
                    }
                }
            }else{
                System.out.println("No Event for you Thank you for visit");
                return true;
            }
            
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
