package br.com.objective.training.calc;


public interface Calculator<T> extends CalculatorX<T, RuntimeException> {

	@Override
	T compare(T left, T right);

	@Override
	T add(T left, T right);

	@Override
	T subtract(T left, T right);

}
