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
	public void deveRetornarAvisoQuandoNomeForVazio(){	
		String errorText = "Nome eh obrigatorio";
		
		Assert.assertEquals("", dsl.obterTextoById("elementosForm:nome"));
		
		dsl.clicaBotao("elementosForm:cadastrar");
		
		Assert.assertEquals(errorText, dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveRetornarAvisoQuandoSobrenomeForVazio(){
		String nome = "Leonam";
		String errorText = "Sobrenome eh obrigatorio";
		
		dsl.escreve("elementosForm:nome", nome);
		dsl.clicaBotao("elementosForm:cadastrar");
		
		Assert.assertEquals(errorText, dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveRetornarAvisoQuandoSexoNaoForSelecionado(){
		String nome = "Leonam";
		String sobreNome = "Silva";
		String errorText = "Sexo eh obrigatorio";
		
		dsl.escreve("elementosForm:nome", nome);
		dsl.escreve("elementosForm:sobrenome", sobreNome);
		
		dsl.clicaBotao("elementosForm:cadastrar");
		
		Assert.assertEquals(errorText, dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveRetornarAvisoQuandoCarneEVegetarianoForemSelecionados(){
		String nome = "Leonam";
		String sobreNome = "Silva";
		String errorText = "Tem certeza que voce eh vegetariano?";

		dsl.escreve("elementosForm:nome", nome);
		dsl.escreve("elementosForm:sobrenome", sobreNome);
		
		dsl.clicaBotao("elementosForm:sexo:0");
		
		dsl.clicaBotao("elementosForm:comidaFavorita:0");
		dsl.clicaBotao("elementosForm:comidaFavorita:3");
		
		dsl.clicaBotao("elementosForm:cadastrar");

		Assert.assertEquals(errorText, dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveRetornarAvisoQuandoEsporteENaoEsporteForSelecionado(){
		String nome = "Leonam";
		String sobreNome = "Silva";
		String errorText = "Voce faz esporte ou nao?";
		
		dsl.escreve("elementosForm:nome", nome);
		dsl.escreve("elementosForm:sobrenome", sobreNome);
		dsl.clicaBotao("elementosForm:sexo:0");
		dsl.clicaBotao("elementosForm:comidaFavorita:0");
		
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		Assert.assertEquals(2, dsl.retornaTamanho("elementosForm:esportes"));
		
		dsl.clicaBotao("elementosForm:cadastrar");
		
		Assert.assertEquals(errorText, dsl.alertaObterTextoEAceita());
	}

}