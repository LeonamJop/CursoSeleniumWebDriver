import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroComSucesso {
	
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
	public void deveConcluirCadastro() {
		String nome = "Leonam";
		String sobrenome = "Silva";
		String sexo = "Masculino";
		String comida = "Pizza";
		String escolaridade = "2o grau completo";
		String escolaridadeAbrev = "2graucomp";
		
		dsl.escreve("elementosForm:nome", nome);
		Assert.assertEquals(nome, dsl.obterValorCampo("elementosForm:nome"));
		
		dsl.escreve("elementosForm:sobrenome", sobrenome);
		Assert.assertEquals(sobrenome, dsl.obterValorCampo("elementosForm:sobrenome"));
		
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
		
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
		
		dsl.selecionarCombo("elementosForm:escolaridade", escolaridade);
		Assert.assertEquals(escolaridade, dsl.obterValorCombo("elementosForm:escolaridade"));
		
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		
		Assert.assertEquals(3, dsl.retornaTamanho("elementosForm:esportes"));

		dsl.clicaBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTextoById("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTextoById("descNome").endsWith(nome));
		Assert.assertTrue(dsl.obterTextoById("descSobrenome").endsWith(sobrenome));
		Assert.assertTrue(dsl.obterTextoById("descSexo").endsWith(sexo));
		Assert.assertTrue(dsl.obterTextoById("descComida").endsWith(comida));
		Assert.assertTrue(dsl.obterTextoById("descEscolaridade").endsWith(escolaridadeAbrev));
		Assert.assertEquals("Esportes: Natacao Corrida Karate", dsl.obterTextoById("descEsportes"));
		
	}
}
