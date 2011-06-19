package br.com.objective.training.hugeinteger.calc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorServiceTest {

	private static InetSocketAddress DEFAULT_ADDRESS;
	private static CalculatorService service;

	@BeforeClass
	public static void serviceAddress() throws IOException {
		DEFAULT_ADDRESS = new InetSocketAddress(InetAddress.getLocalHost(), 9876);
		service = CalculatorService.load(calculatorFactory());
	}

	@Test
	public void startAndStop() throws IOException {
		service.startOn(DEFAULT_ADDRESS);
		assertTrue(service.isAlive());
		service.stop();
		assertFalse(service.isAlive());
	}

	private static CalculatorFactory calculatorFactory() {
		CalculatorFactory factory = CalculatorFactory.load(ProducerUsingHugeInteger.getInstance());
		return factory;
	}

}
