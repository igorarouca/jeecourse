package br.com.objective.training.foundation;

public interface Functor<A, B> extends FunctorX<A, B, RuntimeException> {
	
	public static final Functor<Object, Object> IDENTITY = new Functor<Object, Object>() { @Override public Object evaluate(Object obj) {
		return obj;
	}};

	@Override
	B evaluate(A value);

}
