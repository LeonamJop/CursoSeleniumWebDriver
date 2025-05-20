package br.ce.wcaquino.page;
import br.ce.wcaquino.core.BasePage;
import org.openqa.selenium.By;

public class CampoTreinamentoPage extends BasePage {

	public void setNome(String nome) {		
		dsl.escreve("elementosForm:nome", nome);
	}
	
	public String getNome() {
		return dsl.obterValorCampo("elementosForm:nome");
	}
	
	public void setSobrenome(String sobrenome) {		
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public String getSobrenome() {
		return dsl.obterValorCampo("elementosForm:sobrenome");
	}
	
	public void setSexoMasculino() {
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	
	public boolean getSexoMasculino() {
		return dsl.isRadioMarcado("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino() {
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public boolean getSexoFeminino() {
		return dsl.isRadioMarcado("elementosForm:sexo:1");
	}
	
	public void setComidaPizza() {
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
	}
	
	public boolean getComidaPizza() {
		return dsl.isRadioMarcado("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaCarne() {
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
	}
	
	public boolean getComidaCarne() {
		return dsl.isRadioMarcado("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaVegano() {
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
	}
	
	public boolean getComidaVegano() {
		return dsl.isRadioMarcado("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridade", valor);
	}
	
	public String getEscolaridade() {
		return dsl.obterValorCampo("elementosForm:escolaridade");
	}
	
	public void setEsporte(String... esportes) {
		for(String esporte: esportes) {
			dsl.selecionarCombo("elementosForm:esportes", esporte);
		}
	}
	
	public Number getQuantidadeEsportesSelecionados() {
		return dsl.retornaTamanho("elementosForm:esportes");
	}
	
	public void cadastrar() {
		dsl.clicaBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro() {
		//return dsl.obterTextoById("resultado");
		return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
	}
	
	public String obterNomeCadastro() {
		//return dsl.obterTextoById("descNome");
		return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
	}
	
	public String obterSobrenomeCadastro() {
		//return dsl.obterTextoById("descSobrenome");
		return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
	}
	
	public String obterSexoCadastro() {
		//return dsl.obterTextoById("descSexo");
		return dsl.obterTexto(By.xpath("//*[@id='descSexo']/span"));
	}
	
	public String obterComidaCadastro() {
		//return dsl.obterTextoById("descComida");
		return dsl.obterTexto(By.xpath("//*[@id='descComida']/span"));
	}
	
	public String obterEscolaridadeCadastro() {
		//return dsl.obterTextoById("descEscolaridade");
		return dsl.obterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
	}
	
	public String obterEsportesCadastro () {
		//return dsl.obterTextoById("descEsportes");
		return dsl.obterTexto(By.xpath("//*[@id='descEsportes']/span"));
	}
}
