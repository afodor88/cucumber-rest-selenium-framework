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


    public static WebElement addAnotherFlight(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@type='button' and contains(., 'Add another flight')]"));
        return element;
    }

//    public static List<WebElement> leavingFromList(WebDriver driver) {
//        elements = driver.findElements(By.cssSelector("[aria-label=\"Leaving from\"]"));
//        return elements;
//    }

    public static List<WebElement> leavingFromList(WebDriver driver) {
        elements = driver.findElements(By.xpath("//button[contains(@aria-label,'Leaving from')]"));
        return elements;
    }


//    public static List<WebElement> goingToList(WebDriver driver) {
//        elements = driver.findElements(By.cssSelector("[aria-label=\"Going to\"]"));
//        return elements;
//    }

    public static List<WebElement> goingToList(WebDriver driver) {
        elements = driver.findElements(By.xpath("//button[contains(@aria-label,'Going to')]"));
        return elements;
    }

    public static WebElement whereAreYouLeavingFrom(WebDriver driver) {
        element = driver.findElement(By.cssSelector("[placeholder=\"Where are you leaving from?\"]"));
        return element;
    }

    public static WebElement whereAreYouGoingTo(WebDriver driver) {
        element = driver.findElement(By.cssSelector("[placeholder=\"Where are you going to?\"]"));
        return element;
    }
    public static WebElement whereAreYouGoingToEmpty(WebDriver driver) {
        element = driver.findElement(By.cssSelector("[placeholder=\"Where are you going to?'][value=\"\"]"));
        return element;
    }


    public static WebElement firstResultFound(WebDriver driver) {
        element = driver.findElement(By.cssSelector(".uitk-typeahead-results strong"));
        return element;
    }

    public static WebElement searchButton(WebDriver driver) {
        element = driver.findElement(By.cssSelector("[type='submit']"));
        return element;
    }

}
