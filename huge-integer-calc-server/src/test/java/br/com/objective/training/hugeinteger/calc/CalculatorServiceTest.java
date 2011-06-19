package br.com.objective.training.hugeinteger.calc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.InetAddress;

import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorServiceTest {

	private static Service service;

	@BeforeClass
	public static void serviceAddress() throws IOException {
		service = CalculatorServiceWrapper.wrap(calculatorFactory().newCalculator());
	}

	@Test
	public void startAndStop() throws IOException {
		service.startOn(InetAddress.getLocalHost(), 9876);
		assertTrue(service.isAlive());
		service.stop();
		assertFalse(service.isAlive());
	}

	private static CalculatorFactory calculatorFactory() {
		CalculatorFactory factory = CalculatorFactory.load(ProducerUsingHugeInteger.getInstance());
		return factory;
	}

}
