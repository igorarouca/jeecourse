package br.com.objective.training.hugeinteger.calc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.InetAddress;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.objective.training.calc.Calculator;
import br.com.objective.training.calc.CalculatorFactory;
import br.com.objective.training.calc.sockets.CalculatorServiceWrapper;
import br.com.objective.training.calc.sockets.Service;

public class CalculatorServiceTest {

	private static Service service;

	@BeforeClass
	public static void configureService() throws IOException {
		Calculator<String> component = CalculatorFactory.load().newCalculator();
		service = CalculatorServiceWrapper.wrap(component);
	}

	@Test
	public void startAndStop() throws IOException {
		service.startOn(InetAddress.getLocalHost(), 9876);
		assertTrue(service.isAlive());
		service.stop();
		assertFalse(service.isAlive());
	}

}
