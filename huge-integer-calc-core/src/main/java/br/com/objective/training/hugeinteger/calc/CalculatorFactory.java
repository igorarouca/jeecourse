package br.com.objective.training.hugeinteger.calc;

public interface CalculatorFactory {

	public final CalculatorFactory DEFAULT = new CalculatorFactory() { @Override public Calculator<String> newCalculator() {
		return CalculatorProducerUsingHugeInteger.getInstance().produce();
	}};

	Calculator<String> newCalculator();

}
