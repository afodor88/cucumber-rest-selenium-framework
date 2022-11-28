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

public class expediaSteps {

    WebDriver driver;

    @Before
    public void setup() {

        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        System.setProperty("webdriver.chrome.driver", projectPath + "\\src\\test\\resources\\drivers\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }

    @After
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


//        List<Map<String, String>> myFlights = flights.asMaps();
//        for(Map<String, String> flightMap : myFlights){
//            for(String flight : flightMap.keySet()){
//
//            }
//        }

        List<List<String>> allFlights = flights.asLists();

        int flightNr = 0;
        for (List<String> flight : allFlights) {
            String expectedLeavingFrom = flight.get(0);
            String expectedGoingTo = flight.get(1);
            System.out.println("Flight number: " + (flightNr + 1));


            //LEAVING FROM
            //clicking Leaving from, adding flight then pressing Enter
            if (flightNr == 1) {
                String currentLeavingFrom = leavingFromList(driver).get(flightNr).getText();
                String[] splitedLeavingFrom = currentLeavingFrom.split("\n");
                currentLeavingFrom = splitedLeavingFrom[1];
                Assert.assertEquals(expectedLeavingFrom, currentLeavingFrom);
            } else {
                leavingFromList(driver).get(flightNr).click();
                whereAreYouLeavingFrom(driver).sendKeys(expectedLeavingFrom);
                whereAreYouLeavingFrom(driver).sendKeys(Keys.ENTER);
                System.out.println("Leaving from: " + expectedLeavingFrom);

                //removing 'Leaving from' text
                String currentLeavingFrom = leavingFromList(driver).get(flightNr).getText();
                String[] splitedLeavingFrom = currentLeavingFrom.split("\n");
                currentLeavingFrom = splitedLeavingFrom[1];

                Assert.assertEquals(expectedLeavingFrom, currentLeavingFrom);
            }


            //GOING TO
            //clicking Going to, adding flight then pressing Enter
            //TODO FIX for flight 2
            goingToList(driver).get(flightNr).click();
            whereAreYouGoingTo(driver).sendKeys(expectedGoingTo);
            whereAreYouGoingTo(driver).sendKeys(Keys.ENTER);
            System.out.println("Going to: " + expectedGoingTo);


            //removing 'Going to' text
            String currentGoingTo = goingToList(driver).get(flightNr).getText();
            String[] splittedGoingTo = currentGoingTo.split("\n");
            currentGoingTo = splittedGoingTo[1];
            System.out.println(currentGoingTo);
            Assert.assertEquals(expectedGoingTo, currentGoingTo);

            flightNr++;
        }


//        for(int i = 0; i < numberOfFlights;i++){
//            System.out.println("Flight number: " + i + 1);
//            leavingFromList(driver).get(i).click();
//
//            String leavingFromExpected = flights.get(i);
//            System.out.println("Leaving from: " + leavingFromExpected);
//            whereAreYouLeavingFrom(driver).sendKeys(leavingFromExpected);
//            whereAreYouLeavingFrom(driver).sendKeys(Keys.ENTER);
//
//            //removing 'Leaving from' text
//            String leavingFromFound = leavingFromList(driver).get(i).getText();
//            String[] splitedLeavingFrom = leavingFromFound.split("\n");
//            leavingFromFound = splitedLeavingFrom[1];
//
//            Assert.assertEquals(leavingFromExpected, leavingFromFound);
//
//        }

//        List<List<String>> dataList = dataTable.asLists(String.class);

//        System.out.println("Number of flights: " + numberOfFlights);

//        for(int i = 0; i < numberOfFlights; i++){
//            //Flight 1: clicking Leaving from
//            leavingFromList(driver).get(i).click();
//            String leavingFromF1 = dataList.get(i).get(i);
//            System.out.println("Searching for: " + leavingFromF1);
//            whereAreYouLeavingFrom(driver).sendKeys(leavingFromF1);
//            whereAreYouLeavingFrom(driver).sendKeys(Keys.ENTER);
//
//            //removing 'Leaving from' text F1
//            String leavingFromfoundTextF1 = leavingFromList(driver).get(i).getText();
//            String[] splitedLeavingFrom = leavingFromfoundTextF1.split("\n");
//            leavingFromfoundTextF1 = splitedLeavingFrom[1];
//
//            System.out.println(leavingFromfoundTextF1);
//
//            Assert.assertEquals(leavingFromF1, leavingFromfoundTextF1);
//
//            //Flight 1: clicking Going to
//            goingToList(driver).get(i).click();
//            String goingToF1 = dataList.get(i).get(i + 1);
//            System.out.println("Searching for: " + goingToF1);
//            whereAreYouGoingTo(driver).sendKeys(goingToF1);
//            whereAreYouGoingTo(driver).sendKeys(Keys.ENTER);
//
//            //removing 'Going to' text from F1
//            String goingToFoundTextF1 = goingToList(driver).get(i).getText();
//            String[] splitedGoingToF1 = goingToFoundTextF1.split("\n");
//            goingToFoundTextF1 = splitedGoingToF1[1];
//
//            System.out.println(goingToFoundTextF1);
//
//            Assert.assertEquals(goingToF1, goingToFoundTextF1);
//
//
//
//            //removing 'Leaving from' text from F2
//            String leavingFromfoundTextF2 = goingToList(driver).get(i).getText();
//            String[] splitedGoingToF2 = leavingFromfoundTextF2.split("\n");
//            leavingFromfoundTextF2 = splitedGoingToF2[1];
//            Assert.assertEquals(goingToF1, leavingFromfoundTextF2);


//        }


    }
}
