package utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class EcommerceLibrary {
	
	private static WebDriver driver;
	
	
	public void OpenSite(String URL)
	{
		driver.get(URL);	
		
	}	
	
	public void SearchProduct(WebElement ele,String product)
	{
		ele.sendKeys(product);
	}
	
	public  void HoveronProduct( WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		}
	
	public  void HoverAndProductClick(WebDriver driver,WebElement elementToHover,WebElement ProductName) {
		Actions action = new Actions(driver);
		action.moveToElement(elementToHover).click(ProductName).build().perform();
	}
	
	public void ClickonProductCheckboxes(WebElement CheckboxElement)
	{
		CheckboxElement.click();
	}
	
	public void Select_The_ProductCheckBox_from_List(WebElement element, String valueToSelect) {
		List<WebElement> allOptions = element.findElements(By.tagName("input"));
		for (WebElement option : allOptions) {
			   System.out.println("Option value "+option.getText());
			        if (valueToSelect.equals(option.getText())) {
			            option.click();
			            break;
			        }
			    }
	}
	
	public void ClickonProduct(WebElement productdetails)
	{
		productdetails.click();
	}
	
	
	public void Select_The_Size_ProductLink(WebElement element, String SizeToSelect) {
		List<WebElement> allOptions = element.findElements(By.tagName("input"));
		for (WebElement option : allOptions) {
			   System.out.println("Option value "+option.getText());
			        if (SizeToSelect.equals(option.getText())) {
			            option.click();
			            break;
			        }
			    }
	}
	
		public void AddtoProductToCart(WebElement Producttocart)
		{
			
			Producttocart.click();
		}
		
		
	
public void ClickonProductQuantity(int quanity,WebElement PlusQuantity,WebElement Minus_quanity)
{

	for(int i=1;i<quanity-1;i++)
	{
		PlusQuantity.click();
	}
		
	
}

















}