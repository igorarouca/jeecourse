package br.com.objective.training.hugeinteger.calc;

public interface Calculator<T> {
	/**
	 *	left < right --> -1
	 *	left = right -->  0
	 *	left > right --> +1
	 */
	public abstract T compare(T left, T right);

	public abstract T add(T left, T right);

	public abstract T subtract(T left, T right);

}
