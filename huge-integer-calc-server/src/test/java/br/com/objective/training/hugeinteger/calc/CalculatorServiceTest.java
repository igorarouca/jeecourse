package br.com.objective.training.hugeinteger.calc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.InetAddress;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import br.com.objective.training.hugeinteger.calc.sockets.CalculatorServiceWrapper;
import br.com.objective.training.hugeinteger.calc.sockets.Service;

public class CalculatorServiceTest {

	private static Service service;

	@BeforeClass
	public static void serviceAddress() throws IOException {
		service = CalculatorServiceWrapper.wrap(calculatorFactory().newCalculator());
	}

	@Ignore
	@Test
	public void startAndStop() throws IOException {
		service.startOn(InetAddress.getLocalHost(), 9876);
		assertTrue(service.isAlive());
		service.stop();
		assertFalse(service.isAlive());
	}

	private static CalculatorFactory calculatorFactory() {
		return CalculatorFactory.DEFAULT;
	}

}
