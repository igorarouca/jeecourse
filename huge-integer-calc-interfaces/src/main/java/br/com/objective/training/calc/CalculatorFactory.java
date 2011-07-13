package br.com.objective.training.calc;

public interface CalculatorFactory {

	CalculatorX<String, ? extends Throwable> newCalculator();

}
