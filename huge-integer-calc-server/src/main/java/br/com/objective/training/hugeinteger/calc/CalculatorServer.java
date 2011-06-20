package br.com.objective.training.hugeinteger.calc;

import java.io.IOException;
import java.net.InetAddress;

public class CalculatorServer {

	public static void main(String[] args) throws IOException {
		Calculator<String> calculator = CalculatorFactory.load(ProducerUsingHugeInteger.getInstance()).newCalculator();
		Service service = CalculatorServiceWrapper.wrap(calculator);

		System.out.println(">>> Starting calculator service...");
		service.startOn(InetAddress.getLocalHost(), 9876);
		System.out.println(">>> Calculator service running.");		
	}

}
