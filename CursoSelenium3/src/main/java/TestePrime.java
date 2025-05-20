import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import br.ce.wcaquino.core.DSL;

public class TestePrime {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void deveInteragirComRadioPrimeViaIdDinamico() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl.clicarRadio(By.xpath("//input[@id='j_idt249:line:0']/../..//span"));
		
		Assert.assertTrue(dsl.isRadioMarcado("j_idt249:line:0"));
	}
	
	@Test
	public void deveInteragirComRadioPrimeTexto() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl.clicarRadio(By.xpath("//label[.='Option2']/..//span"));
		
		Assert.assertTrue(dsl.isRadioMarcado("j_idt249:line:1"));
	}
	
	///Combo\\\
	@Test
	public void deveInteragirComSelectPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		
		dsl.selecionarComboPrime("j_idt248:option", "Option3");
		
		Assert.assertEquals("Option3", dsl.obterTextoById("j_idt248:option_label"));
	}
}
