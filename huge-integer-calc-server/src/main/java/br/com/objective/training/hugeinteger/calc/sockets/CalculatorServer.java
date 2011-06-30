package br.com.objective.training.hugeinteger.calc.sockets;

import java.io.IOException;
import java.net.InetAddress;

import br.com.objective.training.hugeinteger.calc.Calculator;
import br.com.objective.training.hugeinteger.calc.CalculatorFactory;

public class CalculatorServer {

	public static void main(String[] args) throws IOException {
		Calculator<String> calculator = CalculatorFactory.DEFAULT.newCalculator();
		Service service = CalculatorServiceWrapper.wrap(calculator);

		System.out.println(">>> Starting calculator service...");
		service.startOn(InetAddress.getLocalHost(), 9876);
		System.out.println(">>> Calculator service running.");		
	}

}
