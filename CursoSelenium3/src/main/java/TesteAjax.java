import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteAjax {

	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void testAjaxEsperaExplicita() {
		dsl.escreve("j_idt248:name", "Teste");
		dsl.clicaBotao("j_idt248:j_idt252");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.textToBe(By.id("j_idt248:display"), "Teste"));
		
		Assert.assertEquals("Teste", dsl.obterTextoById("j_idt248:display"));
	}
	
	@Test
	public void testAjaxEsperaFinalizacaoDeLoadingEmTela() {
		dsl.escreve("j_idt248:name", "Teste");
		dsl.clicaBotao("j_idt248:j_idt252");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt300_start")));
		
		Assert.assertEquals("Teste", dsl.obterTextoById("j_idt248:display"));
	}
}
