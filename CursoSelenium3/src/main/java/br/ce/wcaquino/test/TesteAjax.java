package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.core.DSL;

public class TesteAjax {

	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void testAjaxEsperaExplicita() {
		dsl.escreve("j_idt248:name", "Teste");
		dsl.clicaBotao("j_idt248:j_idt252");
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.textToBe(By.id("j_idt248:display"), "Teste"));
		
		Assert.assertEquals("Teste", dsl.obterTextoById("j_idt248:display"));
	}
	
	@Test
	public void testAjaxEsperaFinalizacaoDeLoadingEmTela() {
		dsl.escreve("j_idt248:name", "Teste");
		dsl.clicaBotao("j_idt248:j_idt252");
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt300_start")));
		
		Assert.assertEquals("Teste", dsl.obterTextoById("j_idt248:display"));
	}
}
