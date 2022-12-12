package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static pages.ExpediaMainPage.*;

public class ExpediaSteps {

    WebDriver driver;

    @Before("@Expedia")
    public void setup() {

        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        System.setProperty("webdriver.chrome.driver", projectPath + "\\src\\test\\resources\\drivers\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }

    @After("@Expedia")
    public void tearDown() throws InterruptedException {
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
        this.driver = null;
    }

    @Given("the user navigates to expedia.com")
    public void the_user_navigates_to_expedia_com() {
        driver.get("https://www.expedia.com/");
        String currentTitle = driver.getTitle();
        Assert.assertEquals("Expedia Travel: Vacation Homes, Hotels, Car Rentals, Flights & More", currentTitle);
    }

    @Then("the user chooses a multi-city with the destinations below")
    public void the_user_chooses_a_multi_city_with_the_destinations_below(DataTable flights) {


        flightsButton(driver).click();
        multiCityButton(driver).click();

        Assert.assertTrue(addAnotherFlight(driver).isDisplayed());
        addAnotherFlight(driver).click();

        List<List<String>> allFlights = flights.asLists();
        Assert.assertEquals(allFlights.size(), 3);

        //Adding all flights
        for (List<String> flight : allFlights) {
            String expectedLeavingFrom = flight.get(0);
            String expectedGoingTo = flight.get(1);
            int flightRow = allFlights.indexOf(flight);

            leavingFromList(driver).get(flightRow).click();
            whereAreYouLeavingFrom(driver, flightRow + 1).sendKeys(expectedLeavingFrom);
            whereAreYouLeavingFrom(driver, flightRow + 1).sendKeys(Keys.ENTER);
            System.out.println("Leaving from: " + expectedLeavingFrom);

            //removing 'Leaving from' text
            String currentLeavingFrom = leavingFromList(driver).get(flightRow).getText();
            String[] splitedLeavingFrom = currentLeavingFrom.split("\n");
            currentLeavingFrom = splitedLeavingFrom[1];
            Assert.assertEquals(expectedLeavingFrom, currentLeavingFrom);

            goingToList(driver).get(flightRow).click();
            whereAreYouGoingTo(driver, flightRow + 1).sendKeys(expectedGoingTo);
            whereAreYouGoingTo(driver, flightRow + 1).sendKeys(Keys.ENTER);
            System.out.println("Going to: " + expectedGoingTo);


            //removing 'Going to' text
            String currentGoingTo = goingToList(driver).get(flightRow).getText();
            String[] splittedGoingTo = currentGoingTo.split("\n");
            currentGoingTo = splittedGoingTo[1];
            System.out.println(currentGoingTo);
            Assert.assertEquals(expectedGoingTo, currentGoingTo);
        }


    }
}
