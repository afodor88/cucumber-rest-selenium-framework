package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SauceDemoLoginPage {
    private static WebElement element = null;

    public static WebElement usernameField(WebDriver driver) {
        element = driver.findElement(By.name("user-name"));
        return element;
    }

    public static WebElement passwordField(WebDriver driver) {
        element = driver.findElement(By.name("password"));
        return element;
    }

    public static WebElement loginButton(WebDriver driver) {
        element = driver.findElement(By.name("login-button"));
        return element;
    }

}
