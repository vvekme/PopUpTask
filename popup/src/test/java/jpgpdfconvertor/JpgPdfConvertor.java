package jpgpdfconvertor;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class JpgPdfConvertor {

	String imageURL = "https://cdn.popup-cloud.com/account/a7147d72-a1d7-4cfd-82af-344dafbe14fa/account_files/8390949d-74f3-4372-a3ca-91e4157ce12b/asdf3.jpg";

	@Test
	public void ImageConvertor() throws InterruptedException, AWTException {

		// Initializing WebDriver and setting Chromedriver path
		System.setProperty("webdriver.chrome.driver", "D:\\PopupTask\\popup\\dependencies\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Creating Actions Object
		Actions actions = new Actions(driver);
		ChromeOptions options = new ChromeOptions();
		Robot robot = new Robot();

		driver.manage().window().maximize();

		// Open the webpage with the image
		driver.get(imageURL);

		// Find the image elementD:\User management SSO\popup\downloads
		
		WebElement imageElement = driver
				.findElement(By.xpath("//body[@style='margin: 0px; background: #0e0e0e; height: 100%']/img"));

		//Right clicking on the Image
		actions.contextClick(imageElement).build().perform();
		Thread.sleep(500);
		
		//Clicking Save image as
		robot.keyPress(KeyEvent.VK_V);
		Thread.sleep(1000);
		
		//Setting pathto download
		String path = "D:\\PopupTask\\popup\\downloads\\image.jpg";
		StringSelection strSelection = new StringSelection(path);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(strSelection, null);
		
		//Saving the image at the path mentioned
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(500);
		robot.keyRelease(KeyEvent.VK_V);
		robot.delay(500);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(500);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(500);
		
		
		
		// Close the WebDriver
		driver.quit();
	}
}
