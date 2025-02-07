import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteJanelas {
	private WebDriver driver;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void deveInteragirComPopup(){
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		Assert.assertEquals("Deu certo?", driver.findElement(By.tagName("textarea")).getAttribute("value"));
		driver.close();
		
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
		Assert.assertEquals("E agora?", driver.findElement(By.tagName("textarea")).getAttribute("value"));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void deveInteragirComPopupSemTitulo(){
		driver.findElement(By.id("buttonPopUpHard")).click();
		
//		System.out.println(driver.getWindowHandle());
//		System.out.println(driver.getWindowHandles());
		
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		Assert.assertEquals("Deu certo?", driver.findElement(By.tagName("textarea")).getAttribute("value"));
		
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
		Assert.assertEquals("E agora?", driver.findElement(By.tagName("textarea")).getAttribute("value"));
	}
}
