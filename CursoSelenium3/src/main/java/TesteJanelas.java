import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import br.ce.wcaquino.core.DSL;

public class TesteJanelas {
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		killDriver();;
	}
	
	@Test
	public void deveInteragirComPopup(){
		dsl.clicaBotao("buttonPopUpEasy"); 
		dsl.trocarJanela("Popup");
		
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		
		getDriver().close();
		
		dsl.trocarJanela("");
		dsl.escreve(By.tagName("textarea"), "E agora?");
	}
	
	@Test
	public void deveInteragirComPopupSemTitulo(){
		getDriver().findElement(By.id("buttonPopUpHard")).click();
		
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]);
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[0]);
		dsl.escreve(By.tagName("textarea"), "E agora?");
	}
}
