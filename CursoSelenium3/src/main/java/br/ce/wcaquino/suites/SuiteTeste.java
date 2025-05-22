package br.ce.wcaquino.suites;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.test.CadastroComSucesso;
import br.ce.wcaquino.test.DesafioRegrasDeNegocios;
import br.ce.wcaquino.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	CadastroComSucesso.class,
	DesafioRegrasDeNegocios.class,
	TesteRegrasCadastro.class
})
public class SuiteTeste {
	
	@AfterClass
	public static void finalizaTudo() {
		killDriver();
	}
}
