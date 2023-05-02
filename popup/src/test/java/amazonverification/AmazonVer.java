package amazonverification;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AmazonVer {

	String commonAttributes = "//*[@class='a-color-secondary a-size-base prodDetSectionEntry' and contains(text(),'%s')]";

	String attributesValue = "//*[@class='a-color-secondary a-size-base prodDetSectionEntry' and contains(text(),'%s')]//following-sibling::td";

	@Test
	public void testCase1() throws InterruptedException {

		// Initializing WebDriver and setting Chromedriver path
		System.setProperty("webdriver.chrome.driver", "D:\\PopupTask\\popup\\dependencies\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		// Navigating to Amazon.in
		driver.navigate().to("https://www.amazon.in");
		driver.navigate().refresh();

		// Finding and clicking the "All" Menu
		WebElement allMenu = driver.findElement(By.xpath("//*[@aria-label='Open Menu']"));
		allMenu.click();
		Thread.sleep(500);

		// Selecting "Mobiles, Computers" from the Menu
		WebElement mobilesComputersOption = driver
				.findElement(By.xpath("//*[@class='hmenu-item']//div[text()='Mobiles, Computers']"));
		mobilesComputersOption.click();
		Thread.sleep(500);

		// Finding and clicking the "All Mobiles" option
		WebElement allMobilesLink = driver
				.findElement(By.xpath("//*[@class='hmenu-item' and text()='All Mobile Phones']"));
		allMobilesLink.click();
		Thread.sleep(1000);

		// Finding the Searching input field and enter "OnePlus 11 5G"
		WebElement searchInput = driver.findElement(By.id("twotabsearchtextbox"));
		searchInput.click();
		searchInput.sendKeys("OnePlus 11 5G");

		// Finding and clicking the Searchingbutton
		WebElement searchButton = driver.findElement(By.xpath("//*[@id='nav-search-submit-button']"));
		searchButton.click();
		Thread.sleep(1000);

		// Finding and clicking the first Searchingresult
		WebElement firstSearchResult = driver.findElement(By.xpath(
				"(//span[@class='a-size-medium a-color-base a-text-normal' and contains(text(),'OnePlus')])[1]|(//span[@class='a-size-medium a-color-base a-text-bold a-text-normal' and contains(text(),'OnePlus')])[1]"));
		firstSearchResult.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Getting window handles and switching handles to new tab
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

		// Verifying if Specifications are available
		WebElement prodDetailTable = driver.findElement(By.id("prodDetails"));
		boolean prodDetailsVisible = prodDetailTable.isDisplayed();
		assertTrue(prodDetailsVisible);

		// Verifying the added Attribute RAM in mobile phone detail page
		WebElement prodAttributeRAM = driver.findElement(By.xpath(String.format(attributesValue, "RAM")));
		boolean prodAttVisible = prodAttributeRAM.isDisplayed();
		assertTrue(prodAttVisible);

		// Closing the browser
		driver.quit();
	}
}
