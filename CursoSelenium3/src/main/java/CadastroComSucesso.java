import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroComSucesso {
	
	private WebDriver driver;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage(driver);
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
		
		page.setNome(nome);
		Assert.assertEquals(nome, page.getNome());
		
		page.setSobrenome(sobrenome);
		Assert.assertEquals(sobrenome, page.getSobrenome());
		
		page.setSexoMasculino();
		Assert.assertTrue(page.getSexoMasculino());
		
		page.setComidaPizza();
		Assert.assertTrue(page.getComidaPizza());
		
		page.setEscolaridade(escolaridade);
		Assert.assertEquals(escolaridadeAbrev, page.getEscolaridade());
		
		page.setEsporte("Natacao");
		page.setEsporte("Corrida");
		page.setEsporte("Karate");
		
		Assert.assertEquals(3, page.getQuantidadeEsportesSelecionados());

		page.cadastrar();
		
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith(nome));
		Assert.assertTrue(page.obterSobrenomeCadastro().endsWith(sobrenome));
		Assert.assertTrue(page.obterSexoCadastro().endsWith(sexo));
		Assert.assertTrue(page.obterComidaCadastro().endsWith(comida));
		Assert.assertTrue(page.obterEscolaridadeCadastro().endsWith(escolaridadeAbrev));
		Assert.assertEquals("Esportes: Natacao Corrida Karate", page.obterEsportesCadastro());
		
	}
}
