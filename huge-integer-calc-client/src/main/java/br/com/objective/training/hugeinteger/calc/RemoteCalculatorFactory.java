package br.com.objective.training.hugeinteger.calc;

import java.net.InetAddress;
import java.net.UnknownHostException;

class RemoteCalculatorFactory implements CalculatorFactory {

	private static CalculatorFactory instance = new RemoteCalculatorFactory();

	public static CalculatorFactory instance() {
		return instance;
	}

	private RemoteCalculatorFactory() {}

	@Override
	public Calculator<String> newCalculator() {
		return new CalculatorStub(defaultIp(), defaultPort());
	}

	private static int defaultPort() {
		return 9876;
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

}
