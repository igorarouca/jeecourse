package br.com.objective.training.foundation;

public interface ProducerX<T, X extends Throwable> {

	T produce() throws X;
	
}
