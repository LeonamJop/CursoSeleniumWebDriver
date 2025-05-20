package br.ce.wcaquino.suites;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.test.CadastroComSucesso;
import br.ce.wcaquino.test.DesafioRegrasDeNegocios;
import br.ce.wcaquino.test.TesteCampoTreinamento;

@RunWith(Suite.class)
@SuiteClasses({
	CadastroComSucesso.class,
	DesafioRegrasDeNegocios.class,
	TesteCampoTreinamento.class
})
public class SuiteTeste {

}
