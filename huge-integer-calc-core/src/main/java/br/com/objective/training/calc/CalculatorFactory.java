package br.com.objective.training.calc;

import br.com.objective.training.foundation.Producer;

public class CalculatorFactory {

	public static final Producer<Calculator<String>> DEFAULT_PRODUCER = CalculatorProducerUsingHugeInteger.getInstance();

	private static CalculatorFactory instance;

	private final Producer<Calculator<String>> producer;

	private CalculatorFactory(Producer<Calculator<String>> producer) {
		this.producer = producer;
	}

	public static CalculatorFactory load() {
		return load(DEFAULT_PRODUCER);
	}

	public static CalculatorFactory load(Producer<Calculator<String>> producer) {
		return instance = new CalculatorFactory(producer);
	}

	public Calculator<String> newCalculator() {
		return instance.producer.produce();
	}

}
