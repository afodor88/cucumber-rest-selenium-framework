package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ExpediaMainPage {
    private static WebElement element = null;
    private static List<WebElement> elements = null;

    public static WebElement flightsButton(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[contains(@class, 'uitk-tab-text')] [ text() = 'Flights' ]"));
        return element;
    }

    public static WebElement multiCityButton(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[contains(@class, 'uitk-tab-text')] [ text() = 'Multi-city' ]"));
        return element;
    }


    public static List<WebElement> leavingFromList(WebDriver driver) {
        elements = driver.findElements(By.xpath("//button[contains(@aria-label,'Leaving from')]"));
        return elements;
    }

    public static List<WebElement> departingList(WebDriver driver) {
        elements = driver.findElements(By.xpath("//button[contains(@aria-label,'Departing')]"));
        return elements;
    }


    public static List<WebElement> goingToList(WebDriver driver) {
        elements = driver.findElements(By.xpath("//button[contains(@aria-label,'Going to')]"));
        return elements;
    }


    public static WebElement whereAreYouLeavingFrom(WebDriver driver, int row) {
        element = driver.findElement(By.id("location-field-leg" + row + "-origin"));
        return element;
    }


    public static WebElement whereAreYouGoingTo(WebDriver driver, int row) {
        element = driver.findElement(By.id("location-field-leg" + row + "-destination"));
        return element;
    }


    public static WebElement searchButton(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[text()='Search']"));
        return element;
    }

    public static WebElement addAnotherFlight(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[text()='Add another flight']"));
        return element;
    }

    public static WebElement selectDate(WebDriver driver, String date) {
        element = driver.findElement(By.cssSelector("[aria-label='" + date + "']"));
        return element;
    }

    public static WebElement doneButtonDate(WebDriver driver) {
        element = driver.findElement(By.cssSelector("[data-stid='apply-date-picker']"));
        return element;
    }

    public static WebElement doneButtonGuests(WebDriver driver) {
        element = driver.findElement(By.cssSelector("[data-testid=\"guests-done-button\"]"));
        return element;
    }

    public static List<WebElement> priceList(WebDriver driver) {
        elements = driver.findElements(By.cssSelector("[data-test-id=\"offer-listing\"]"));
        return elements;
    }

    public static WebElement flightPrice(WebDriver driver) {
        element = driver.findElement(By.cssSelector("[class=\"uitk-price-lockup align-start\"] [class=\"uitk-lockup-price\"]"));
        return element;
    }

    public static WebElement travelers(WebDriver driver) {
        element = driver.findElement(By.cssSelector("button[data-testid='travelers-field']"));
        return element;
    }

    public static WebElement lessAdults(WebDriver driver) {
        element = driver.findElement(By.cssSelector("[aria-label='Decrease adults in room 1']"));
        return element;
    }

    public static WebElement moreAdults(WebDriver driver) {
        element = driver.findElement(By.cssSelector("[aria-label=\"Increase adults\"]"));
        return element;
    }

}
