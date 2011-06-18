package br.com.objective.training.hugeinteger.calc;

import br.com.objective.training.foundation.Producer;

public class CalculatorFactory {

	private final Producer<Calculator<String>> producer;

	private CalculatorFactory(Producer<Calculator<String>> producer) {
		this.producer = producer;
	}

	public static CalculatorFactory load(Producer<Calculator<String>> producer) {
		return new CalculatorFactory(producer);
	}

	public Calculator<String> newCalculator() {
		return producer.produce();
	}

}
