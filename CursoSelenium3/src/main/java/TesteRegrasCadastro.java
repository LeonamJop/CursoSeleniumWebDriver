import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import br.ce.wcaquino.core.DSL;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro {
	
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobreNome;
	@Parameter(value=2)
	public Object sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String errorText;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			{"Leonam", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			{"Leonam", "Silva", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Leonam", "Silva", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Leonam", "Silva", "Masculino", Arrays.asList("Carne"), new String[]{"Corrida", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void deveValidarRegras(){
		page.setNome(nome);
		page.setSobrenome(sobreNome);
		
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		}
		
		if(sexo.equals("Feminino")) {
			page.setSexoFeminino();
		}

		if(comidas.contains("Carne")) page.setComidaCarne();
		if(comidas.contains("Pizza")) page.setComidaPizza();
		if(comidas.contains("Vegetariano")) page.setComidaVegano();
		
		page.setEsporte(esportes);
		
		page.cadastrar();
		System.out.println(errorText);
		Assert.assertEquals(errorText, dsl.alertaObterTextoEAceita());
	}
}
