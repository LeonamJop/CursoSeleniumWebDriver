import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DesafioRegrasDeNegocios {
	
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
	public void deveRetornarAvisoQuandoNomeForVazio(){		
		Assert.assertEquals("",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		String errorText = "Nome eh obrigatorio";
		
		Assert.assertEquals(errorText, alertText);
		
		alert.accept();
	}
	
	@Test
	public void deveRetornarAvisoQuandoSobrenomeForVazio(){
		String nome = "Leonam";
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(nome);
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		String errorText = "Sobrenome eh obrigatorio";
		
		Assert.assertEquals(errorText, alertText);
		
		alert.accept();
	}
	
	@Test
	public void deveRetornarAvisoQuandoSexoNaoForSelecionado(){
		String nome = "Leonam";
		String sobreNome = "Silva";
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(nome);
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys(sobreNome);
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		String errorText = "Sexo eh obrigatorio";
		
		Assert.assertEquals(errorText, alertText);
		
		alert.accept();
	}
	
	@Test
	public void deveRetornarAvisoQuandoCarneEVegetarianoForemSelecionados(){
		String nome = "Leonam";
		String sobreNome = "Silva";
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(nome);
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys(sobreNome);
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		String errorText = "Tem certeza que voce eh vegetariano?";
		
		Assert.assertEquals(errorText, alertText);
		
		alert.accept();
	}
	
	@Test
	public void deveRetornarAvisoQuandoEsporteENaoEsporteForSelecionado(){
		String nome = "Leonam";
		String sobreNome = "Silva";
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(nome);
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys(sobreNome);
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		
		Select combo = new Select(element);
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		String errorText = "Voce faz esporte ou nao?";
		
		Assert.assertEquals(errorText, alertText);
		
		alert.accept();
	}

}
