package br.com.objective.training.hugeinteger.calc.client;

import br.com.objective.training.hugeinteger.calc.Calculator;
import br.com.objective.training.hugeinteger.calc.CalculatorFactory;
import br.com.objective.training.hugeinteger.calc.ProducerUsingHugeInteger;


public class CalculatorConsole {

	public static void main(String[] args) {
		CalculatorFactory factory = CalculatorFactory.load(ProducerUsingHugeInteger.getInstance());
		Calculator calculator = factory.newCalculator();

	}

}
