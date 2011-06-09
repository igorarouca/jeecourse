package br.com.objective.training.hugeinteger;

public interface UnaryOperations<T> {

	public abstract int compareTo(T value);

	public abstract T add(T value);

	public abstract T subtract(T value);

}
