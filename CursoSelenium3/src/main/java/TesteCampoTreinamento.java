import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCampoTreinamento {
	
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
	public void deveInteragirComTextField() {
		dsl.escreve("elementosForm:nome", "Teste de escrita");
		
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea() {
		dsl.escreve("elementosForm:sugestoes", "teste");
		
		Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void deveInteragirComCheckBox() {	
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void deveIntegarirComCombo(){		
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo(){
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo(){
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		Assert.assertEquals(3, dsl.retornaTamanho("elementosForm:esportes"));
		
		dsl.desselecionarCombo("elementosForm:esportes", "Corrida");
		
		Assert.assertEquals(2, dsl.retornaTamanho("elementosForm:esportes"));
	}
	
	@Test
	public void deveInteragitComBotoes(){
		dsl.clicaBotao("buttonSimple");

		Assert.assertEquals("Obrigado!", dsl.obterValorCampo("buttonSimple"));
	}
	
	@Test
	public void deveInteragitComLink(){
		dsl.clicaLink("Voltar");

		Assert.assertEquals("Voltou!", dsl.obterTextoById("resultado"));
	}
	
	@Test
	public void deveBuscarTextoNaPagina(){
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
		
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
}

