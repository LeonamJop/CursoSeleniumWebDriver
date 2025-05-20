package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteGoogle {
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void DeveTrazerTituloCorreto() {
		Assert.assertEquals("Google", getDriver().getTitle());
	}
}
