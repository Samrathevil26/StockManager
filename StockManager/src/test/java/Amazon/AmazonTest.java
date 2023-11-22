package Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonTest {
	private WebDriver driver;

	@BeforeTest
	public void setup() {
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.amazon.in/");

		WebElement sign = driver.findElement(By.id("nav-link-accountList"));
		sign.click();

		WebElement email = driver.findElement(By.id("ap_email"));
		email.sendKeys("6306006966");

		WebElement continuee = driver.findElement(By.id("continue"));
		continuee.click();

		WebElement password = driver.findElement(By.id("ap_password"));
		password.sendKeys("samrat@26");

		WebElement signIn = driver.findElement(By.id("signInSubmit"));
		signIn.click();

		WebElement accountListAfterLogin = driver.findElement(By.id("nav-link-accountList"));
		boolean isUserSignedIn = accountListAfterLogin.getText().contains("Your Account");

		if (isUserSignedIn) {
			System.out.println("Successful Sign in");
		} else {
			System.out.println("Invelid entry");

		}

	}
	@Test
    public void searchProductAndCheckInventory() {
        // Locate the search input field and enter the product name
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Laptop");

        // Locate the search icon and click
        WebElement searchIcon = driver.findElement(By.id("nav-search-submit-button"));
        searchIcon.click();

        // Wait for the search results page to load (You might need to enhance this wait strategy)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assuming the first search result is the product page
        WebElement firstSearchResult = driver.findElement(By.cssSelector("div.s-main-slot div.s-asin:nth-child(1)"));
        firstSearchResult.click();

        // Wait for the product details page to load (You might need to enhance this wait strategy)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Simulate extracting the inventory information from the web page
        WebElement inventoryElement = driver.findElement(By.id("availability"));
        String inventoryText = inventoryElement.getText();
        System.out.println("Inventory: " + inventoryText);

        // Example: Assert that the inventory text contains the expected quantity information
        Assert.assertTrue(inventoryText.contains("In Stock"));

        // You can add more assertions based on your application's actual behavior
    }

    @AfterTest
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

}
