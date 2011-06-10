package br.com.objective.training.foundation;

public interface Functor<A, B> extends FunctorX<A, B, RuntimeException> {

	@Override
	B evaluate(A value);

}
