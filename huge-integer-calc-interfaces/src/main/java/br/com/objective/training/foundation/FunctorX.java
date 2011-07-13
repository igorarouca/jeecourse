package br.com.objective.training.foundation;

public interface FunctorX<A, B, X extends Throwable> {
	
	B evaluate(A value) throws X;

}
