import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TesteSincronismo {
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		dsl.clicaBotao("buttonDelay");
		
		Thread.sleep(5000);
		
		dsl.escreve("novoCampo", "Deu certo?");
		
		Assert.assertEquals("Deu certo?", dsl.obterValorCampo("novoCampo"));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		dsl.clicaBotao("buttonDelay");
		
		dsl.escreve("novoCampo", "Deu certo?");
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		Assert.assertEquals("Deu certo?", dsl.obterValorCampo("novoCampo"));
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException {
		dsl.clicaBotao("buttonDelay");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		
		dsl.escreve("novoCampo", "Deu certo?");
		
		Assert.assertEquals("Deu certo?", dsl.obterValorCampo("novoCampo"));
	}
}
