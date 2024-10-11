package org.example.Pages;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EventForm {
    
    WebDriver driver;
    String new_event_url = "https://siteone.chezuba-test.net/app/activity-event/new-activity-event-1";

    public String eventId;

    @FindBy(id="navbar-search") WebElement searchBar;

    @FindBy(xpath="//input[@data-fieldname='activity_name']") WebElement titleElement;
    @FindBy(xpath="//div[@class='ql-editor ql-blank']//child::p") WebElement descriptionElement;
    @FindBy(xpath="//input[@data-fieldname='start_date']") WebElement startDateElement;
    @FindBy(xpath="//input[@data-fieldname='end_date']") WebElement endDateElement;
    @FindBy(xpath="//input[@data-fieldname='time_commitment']") WebElement timeCommitmenElement;
    @FindBy(xpath="//input[@data-fieldname='contact_name']") WebElement contactNameElement;
    @FindBy(xpath="//input[@data-fieldname='contact_number']") WebElement contactNumberElement;
    @FindBy(xpath="//input[@data-fieldname='contact_email']") WebElement contactEmailElement;
    @FindBy(xpath="//input[@data-fieldname='venue']") WebElement venueElement;
    @FindBy(xpath="//input[@data-fieldname='maximum_applications']") WebElement numberOfVolunteerElement;
    @FindBy(xpath="//input[@data-fieldname='time_zone']") WebElement timezoneElement;
    @FindBy(xpath="//input[@data-fieldname='company_wide']") WebElement companyElement;
    @FindBy(xpath="//input[@data-fieldname='all_departments']") WebElement departmentElement;
    @FindBy(xpath = "//button[@data-label='Save']") WebElement SaveButton;



    

    public EventForm(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
    }

    public void searchNewEvent(String eventName){
        try {
            searchBar.sendKeys(eventName);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Actions actions = new Actions(driver);
            // actions.sendKeys(Keys.ENTER).perform();

            // get the options and click on it

            Thread.sleep(2000);


            WebElement new_activity_option = driver.findElement(By.xpath("//ul[@role='listbox']//child::mark[text()='Activity Event']"));

            wait.until(ExpectedConditions.visibilityOf(new_activity_option));
            new_activity_option.click();

            wait.until(ExpectedConditions.urlMatches(new_event_url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createEvent(String title,String description,String timeCommitment,String contactName,String contactNumber,String contactEmail ,String venue,String numberOfVolunteer,String timezone){
        try {
            titleElement.sendKeys(title);
            descriptionElement.sendKeys(description);
            // select all the offices
            // companyElement.click();

            // click on start date
            startDateElement.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement calander = driver.findElement(By.xpath("//div[@class='datepicker -bottom-left- -from-bottom- active']"));
            // wait.until(ExpectedConditions.visibilityOf(calander));

            Thread.sleep(1000);

            WebElement startDate = driver.findElement(By.xpath("//*[@id=\"datepickers-container\"]/div[1]/div[1]/div/div[2]/div[14]"));
            startDate.click();

            Thread.sleep(1000);

            
            timeCommitmenElement.sendKeys(timeCommitment);
            contactNameElement.sendKeys(contactName);
            contactNumberElement.sendKeys(contactNumber);
            Thread.sleep(1000);
            contactEmailElement.sendKeys(contactEmail);
            venueElement.sendKeys(venue);
            numberOfVolunteerElement.sendKeys(numberOfVolunteer);

            // click on end date
            endDateElement.click();

            Thread.sleep(2000);
            // select all the department
            // departmentElement.click();
            // wait.until(ExpectedConditions.visibilityOf(calander));

            WebElement endDate = driver.findElement(By.xpath("//*[@id=\"datepickers-container\"]/div[2]/div[1]/div/div[2]/div[21]"));

            endDate.click();

            Thread.sleep(1000);

            timezoneElement.clear();

            timezoneElement.sendKeys(timezone);


            if(SaveButton.isDisplayed()){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", SaveButton);
                SaveButton.click();
            }
            // SaveButton.click();

            Thread.sleep(1500);

            // WebElement actionButton = driver.findElement(By.xpath("(//button[@class='btn btn-primary btn-sm'])[3]")) ;

            // actionButton.click();
            // Thread.sleep(5000);

            String currentURL = driver.getCurrentUrl();
            @SuppressWarnings("deprecation")
            URL url = new URL(currentURL);
            String paths = url.getPath();

            String[] path = paths.split("/");

            this.eventId = path[path.length-1];
            
            System.out.println(eventId);

            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public boolean isEventCreated(){
        try {
            WebElement actionButton = driver.findElement(By.xpath("(//button[@class='btn btn-primary btn-sm'])[3]")) ;
            if(actionButton.isDisplayed()){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
