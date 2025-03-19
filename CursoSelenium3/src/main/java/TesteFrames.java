import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrames {
	
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
		WebElement frame = driver.findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		
		dsl.entrarFrame("frame2");

		
		dsl.clicaBotao("frameButton");
		
		String mensagem =  dsl.alertaObterTextoEAceita();
		String txt = "Frame OK!";
		
		Assert.assertEquals(txt, mensagem);
	}
}
