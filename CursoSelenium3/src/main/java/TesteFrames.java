import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import br.ce.wcaquino.core.DSL;

public class TesteFrames {
	
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
	public void deveInteragitComFrame(){		
		dsl.entrarFrame("frame1");
		
		dsl.clicaBotao("frameButton");
		
		String mensagem =  dsl.alertaObterTextoEAceita();
		String txt = "Frame OK!";
		
		Assert.assertEquals(txt, mensagem);
		
		dsl.sairFrame();
		dsl.escreve("elementosForm:nome", txt);
	}
	
	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		
		dsl.entrarFrame("frame2");

		
		dsl.clicaBotao("frameButton");
		
		String mensagem =  dsl.alertaObterTextoEAceita();
		String txt = "Frame OK!";
		
		Assert.assertEquals(txt, mensagem);
	}
}
