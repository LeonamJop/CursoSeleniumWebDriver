package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.page.CampoTreinamentoPage;

public class DesafioRegrasDeNegocios {

	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
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