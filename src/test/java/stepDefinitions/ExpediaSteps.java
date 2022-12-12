package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
            Assert.assertEquals(expectedGoingTo, currentGoingTo);



        }




    }

    @And("the time schedule below")
    public void the_time_schedule_below(DataTable departingDates) {
        List<String> departures = departingDates.asList();

        //Departing date

        for(String departure : departures){
            WebElement currentDepartingButton = departingList(driver).get(departures.indexOf(departure));
            currentDepartingButton.click();
            System.out.println(departure);
            LocalDate date = LocalDate.parse(departure);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd, yyyy");
            String formattedDate = date.format(myFormatObj);
            System.out.println(formattedDate);
            selectDate(driver, formattedDate).click();
            doneButton(driver).click();

            DateTimeFormatter expectedDepartingFormat = DateTimeFormatter.ofPattern("MMM dd");
            String expectedDeparting = date.format(expectedDepartingFormat);

            System.out.println(currentDepartingButton.getText());
            Assert.assertEquals(currentDepartingButton.getText(), expectedDeparting);

        }
    }
}
