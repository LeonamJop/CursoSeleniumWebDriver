import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CadastroComSucesso {
	
	private WebDriver driver;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void deveConcluirCadastro() {
		String nome = "Leonam";
		String sobrenome = "Silva";
		String sexo = "Masculino";
		String comida = "Pizza";
		String escolaridade = "2o grau completo";
		String escolaridadeAbrev = "2graucomp";
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(nome);
		Assert.assertEquals(nome, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys(sobrenome);
		Assert.assertEquals(sobrenome, driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));		
		Select combo = new Select(element);
		combo.selectByVisibleText(escolaridade);
		Assert.assertEquals(escolaridade, combo.getFirstSelectedOption().getText());
		
		WebElement esporte = driver.findElement(By.id("elementosForm:esportes"));
		Select esportes = new Select(esporte);
		esportes.selectByVisibleText("Natacao");
		esportes.selectByVisibleText("Corrida");
		esportes.selectByVisibleText("Karate");
		
		List<WebElement> allSelectedOptions = esportes.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		WebElement cadastrar = driver.findElement(By.id("elementosForm:cadastrar"));
		cadastrar.click();
		
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith(nome));
		Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith(sobrenome));
		Assert.assertTrue(driver.findElement(By.id("descSexo")).getText().endsWith(sexo));
		Assert.assertTrue(driver.findElement(By.id("descComida")).getText().endsWith(comida));
		Assert.assertTrue(driver.findElement(By.id("descEscolaridade")).getText().endsWith(escolaridadeAbrev));
		Assert.assertEquals("Esportes: Natacao Corrida Karate" ,driver.findElement(By.id("descEsportes")).getText());
		
	}
}
