import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}
	
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
		return dsl.obterTextoById("resultado");
	}
	
	public String obterNomeCadastro() {
		return dsl.obterTextoById("descNome");
	}
	
	public String obterSobrenomeCadastro() {
		return dsl.obterTextoById("descSobrenome");
	}
	
	public String obterSexoCadastro() {
		return dsl.obterTextoById("descSexo");
	}
	
	public String obterComidaCadastro() {
		return dsl.obterTextoById("descComida");
	}
	
	public String obterEscolaridadeCadastro() {
		return dsl.obterTextoById("descEscolaridade");
	}
	
	public String obterEsportesCadastro () {
		return dsl.obterTextoById("descEsportes");
	}
}
