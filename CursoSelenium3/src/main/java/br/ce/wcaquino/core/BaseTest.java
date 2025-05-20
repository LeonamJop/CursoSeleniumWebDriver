package br.ce.wcaquino.core;

import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;

public class BaseTest {
	
	@After
	public void finaliza() {
		killDriver();
	}

}
