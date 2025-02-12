import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	
	public DSL(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void escreve(By by, String texto) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}
	
	public void escreve(String id_campo, String texto) {
		escreve(By.id(id_campo), texto);
	}
	
	public void apaga(String id_campo) {
		driver.findElement(By.id(id_campo)).clear();
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getDomProperty("value");
	}
	
	public void clicarRadio(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isRadioMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void selecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public void desselecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		
		return combo.getFirstSelectedOption().getText();
	}
	
	public void clicaBotao(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public void clicaLink(String id) {
		driver.findElement(By.linkText(id)).click();
	}
	
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	
	public String obterTextoById(String id) {
		return obterTexto(By.id(id));
	}
	
	public Number retornaTamanho(String id) {
		WebElement esporte = driver.findElement(By.id(id));
		Select esportes = new Select(esporte);
		List<WebElement> allSelectedOptions = esportes.getAllSelectedOptions();
		return allSelectedOptions.size();
	}
	
	public int obterQuantidadeOpcoesCombo(String id){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verificarOpcaoCombo(String id, String opcao){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;
	}
	
	public String alertaObterTextoEAceita(){
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
	}
	
	public String alertaObterTextoENega(){
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
	}
	
	public String alertaObterTexto() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	public void alertaEscrever(String valor) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
	public void entrarFrame(String id) {
		driver.switchTo().frame(id);
	}
	
	public void sairFrame(){
		driver.switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		driver.switchTo().window(id);
	}
}
