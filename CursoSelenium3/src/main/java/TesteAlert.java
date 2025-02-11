import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TesteAlert {
	
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
