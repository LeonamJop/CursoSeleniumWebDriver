import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteJanelas {
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveInteragirComPopup(){
		dsl.clicaBotao("buttonPopUpEasy"); 
		dsl.trocarJanela("Popup");
		
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		
		driver.close();
		
		dsl.trocarJanela("");
		dsl.escreve(By.tagName("textarea"), "E agora?");
	}
	
	@Test
	public void deveInteragirComPopupSemTitulo(){
		driver.findElement(By.id("buttonPopUpHard")).click();
		
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
		dsl.escreve(By.tagName("textarea"), "E agora?");
	}
}
