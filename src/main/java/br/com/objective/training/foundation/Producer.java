package br.com.objective.training.foundation;

public interface Producer<T> extends ProducerX<T, RuntimeException> {

	@Override
	T produce();

}
