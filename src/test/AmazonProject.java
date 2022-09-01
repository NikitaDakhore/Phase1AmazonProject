package test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        System.setProperty("webdriver.chrome.driver", "chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		//Launch Amazon.in

		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
		
		//Getting parent window handler
		
		String ParentWin = driver.getWindowHandle();
		
		//Search for samsung
		
		WebElement SearchInput = driver.findElement(By.id("twotabsearchtextbox"));
		SearchInput.sendKeys("Samsung");
		
		//Click search button 
		
		WebElement SearchButton = driver.findElement(By.id("nav-search-submit-button"));
		SearchButton.click();
		
		//Print product details and price
		//div[@data-component-type='s-search-result']//h2 or //div[@class='a-section']//span[starts-with(test(),'Samsung')]
		
		List<WebElement> ProductDesc = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		System.out.println("Total num of links are " + ProductDesc.size());
		List<WebElement> ProductPrice = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));
		
		for(int index=0;index<ProductDesc.size();index++) {
			
			System.out.println(ProductDesc.get(index).getText() + "Price : Rupees ");
			System.out.println(ProductPrice.get(index).getText());
			
		}
        //Fetch string to validate on parent window
		
		String Validate = ProductDesc.get(0).getText();
		System.out.println( Validate);
		
		//Click on first product link
		
		ProductDesc.get(0).click();
		
		//Validation of header string parent and child windows
		
		Set<String> allWinHan = driver.getWindowHandles();
		System.out.println("Before clicking tab button the win is " + ParentWin);
		
		//Switching window handlers
		
		for(String win : allWinHan) {
			if(!win .equals(ParentWin)) {
				driver.switchTo().window(win);
			}
		}
		//Fetch header string on child window
		
		WebElement HeadingOnNewTab = driver.findElement(By.xpath("//div[@id='title_feature_div']//span"));
		String headerString = HeadingOnNewTab.getText();
		System.out.println(headerString);
		
		//Validating
		
		if(headerString.equals(Validate)) {
			System.out.println("TC Passed");
		}else {
			System.out.println("TC Failed");
		}
		
 	

}

	}


