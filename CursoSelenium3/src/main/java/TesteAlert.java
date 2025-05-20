import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.ce.wcaquino.core.DSL;



public class TesteAlert {
	
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
	public void deveInteragirComAlertSimples() {
		dsl.clicaBotao("alert");
		
		String texto = dsl.alertaObterTextoEAceita();
		
		Assert.assertEquals("Alert Simples", texto);
		
		dsl.escreve("elementosForm:nome", texto);
	}
	
	@Test
	public void deveConfirmarAlertConfirm() {
		dsl.clicaBotao("confirm");

		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveNegarAlertConfirm() {
		dsl.clicaBotao("confirm");
		
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
	}
	
	@Test
	public void deveConfirmarPrompt() {
		dsl.clicaBotao("prompt");
		
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("12");
		
		Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
		
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
	}
}
