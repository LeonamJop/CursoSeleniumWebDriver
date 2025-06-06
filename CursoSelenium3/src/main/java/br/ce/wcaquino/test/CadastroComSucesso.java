package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.page.CampoTreinamentoPage;

public class CadastroComSucesso extends BaseTest {
	
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
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
		
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals(nome, page.obterNomeCadastro());
		Assert.assertEquals(sobrenome, page.obterSobrenomeCadastro());
		Assert.assertEquals(sexo, page.obterSexoCadastro());
		Assert.assertEquals(comida, page.obterComidaCadastro());
		Assert.assertEquals(escolaridadeAbrev, page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao Corrida Karate", page.obterEsportesCadastro());
	}
}
