package br.com.objective.training.calc.sockets;

import java.io.IOException;
import java.net.InetAddress;

import br.com.objective.training.calc.Calculator;
import br.com.objective.training.calc.CalculatorFactory;

public class CalculatorServer {

	public static void main(String[] args) throws IOException {
		Calculator<String> calculator = CalculatorFactory.load().newCalculator();
		Service service = CalculatorServiceWrapper.wrap(calculator);

		System.out.println("[Server] >>> Starting Server...");
		service.startOn(InetAddress.getLocalHost(), 9876);		
	}

}
