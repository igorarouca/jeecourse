package br.com.objective.training.hugeinteger.calc;

import java.net.InetAddress;
import java.net.UnknownHostException;

import br.com.objective.training.foundation.Producer;

public class CalculatorServiceLocator {

	private static final CalculatorFactory calculatorFactory;

	static {
		calculatorFactory = CalculatorFactory.load(new Producer<Calculator<String>>() { @Override public Calculator<String> produce() {
			return new CalculatorStub(defaultIp(), defaultPort());
		}});
	}

	private CalculatorServiceLocator() {}

	public static Calculator<String> newCalculator() {
		return calculatorFactory.newCalculator();
	}

	private static InetAddress defaultIp() {
		InetAddress addressIp = null;
		try {
			addressIp = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addressIp;
	}

	private static int defaultPort() {
		return 9876;
	}

}
