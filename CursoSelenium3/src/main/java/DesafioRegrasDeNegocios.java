import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DesafioRegrasDeNegocios {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveRetornarAvisoQuandoNomeForVazio(){	
		String errorText = "Nome eh obrigatorio";
		
		Assert.assertEquals("", page.getNome());
		
		page.cadastrar();
		
		Assert.assertEquals(errorText, dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveRetornarAvisoQuandoSobrenomeForVazio(){
		String nome = "Leonam";
		String errorText = "Sobrenome eh obrigatorio";
		
		page.setNome(nome);
		page.cadastrar();
		
		Assert.assertEquals(errorText, dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveRetornarAvisoQuandoSexoNaoForSelecionado(){
		String nome = "Leonam";
		String sobreNome = "Silva";
		String errorText = "Sexo eh obrigatorio";
		
		page.setNome(nome);
		page.setSobrenome(sobreNome);
		
		page.cadastrar();
		
		Assert.assertEquals(errorText, dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveRetornarAvisoQuandoCarneEVegetarianoForemSelecionados(){
		String nome = "Leonam";
		String sobreNome = "Silva";
		String errorText = "Tem certeza que voce eh vegetariano?";

		page.setNome(nome);
		page.setSobrenome(sobreNome);
		page.setSexoMasculino();
		
		page.setComidaCarne();
		page.setComidaVegano();
		
		page.cadastrar();

		Assert.assertEquals(errorText, dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveRetornarAvisoQuandoEsporteENaoEsporteForSelecionado(){
		String nome = "Leonam";
		String sobreNome = "Silva";
		String esporte = "Corrida";
		String naoEhEsporte = "O que eh esporte?";
		String errorText = "Voce faz esporte ou nao?";

		page.setNome(nome);
		page.setSobrenome(sobreNome);
		page.setSexoMasculino();
		page.setComidaPizza();
		
		page.setEsporte(esporte, naoEhEsporte);
		
		Assert.assertEquals(2, page.getQuantidadeEsportesSelecionados());
		
		page.cadastrar();
		
		Assert.assertEquals(errorText, dsl.alertaObterTextoEAceita());
	}

}