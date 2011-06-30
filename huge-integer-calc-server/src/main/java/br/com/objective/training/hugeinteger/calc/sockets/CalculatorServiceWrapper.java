package br.com.objective.training.hugeinteger.calc.sockets;

import java.io.IOException;

import br.com.objective.training.hugeinteger.calc.Calculator;

public class CalculatorServiceWrapper {

	private static final int SERVICE_DEFAULT_CAPACITY = 10;

	public static Service wrap(Calculator<String> component) throws IOException {
		return wrap(component, SERVICE_DEFAULT_CAPACITY);
	}

	public static Service wrap(Calculator<String> component, int serviceCapacity) throws IOException {
		return serviceFor(component, serviceCapacity);
	}

	private static Service serviceFor(Calculator<String> component, int serviceCapacity) throws IOException {
		return new CalculatorService(component, serviceCapacity);
	}

}
