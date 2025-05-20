package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.time.Duration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.core.DSL;


public class TesteSincronismo {
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		dsl.clicaBotao("buttonDelay");
		
		Thread.sleep(5000);
		
		dsl.escreve("novoCampo", "Deu certo?");
		
		Assert.assertEquals("Deu certo?", dsl.obterValorCampo("novoCampo"));
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		dsl.clicaBotao("buttonDelay");
		
		dsl.escreve("novoCampo", "Deu certo?");
		
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		
		Assert.assertEquals("Deu certo?", dsl.obterValorCampo("novoCampo"));
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException {
		dsl.clicaBotao("buttonDelay");
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		
		dsl.escreve("novoCampo", "Deu certo?");
		
		Assert.assertEquals("Deu certo?", dsl.obterValorCampo("novoCampo"));
	}
}
