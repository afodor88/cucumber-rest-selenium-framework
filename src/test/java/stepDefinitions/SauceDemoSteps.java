package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import static pages.SauceDemoLoginPage.*;

public class SauceDemoSteps {

    WebDriver driver;
    @Before
    public void setup(){

        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        System.setProperty("webdriver.chrome.driver", projectPath + "\\src\\test\\resources\\drivers\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws InterruptedException{
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
        this.driver = null;
    }



    @Given("user is on login page")
    public void user_is_on_login_page() throws InterruptedException {

        driver.get("https://www.saucedemo.com/");
        Assert.assertEquals("Swag Labs", driver.getTitle());

    }
    @And("user enters {string} and {string} and clicks login")
    public void user_enters_and(String username, String password) {
        usernameField(driver).sendKeys(username);
        passwordField(driver).sendKeys(password);
        loginButton(driver).click();
        System.out.println(driver.findElement(By.cssSelector("div.error-message-container.error")).getText());


    }

    @Then("user is redirected to the homepage")
    public void user_is_redirected_to_the_homepage() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user should receive the error {string}")
    public void the_user_should_receive_the_error(String errorMessage) {
        String errorReceived = driver.findElement(By.cssSelector("div.error-message-container.error")).getText();
        Assert.assertEquals(errorMessage, errorReceived);
    }
}
