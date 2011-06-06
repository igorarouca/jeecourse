package br.com.objective.training.hugeinteger;

import br.com.objective.training.foundation.Functor;

public class HugeIntegerFactory {

	private static HugeIntegerFactory instance;

	private final Functor<String, HugeInteger> stringToHugeInteger;

	public HugeIntegerFactory(Functor<String, HugeInteger> functor) {
		this.stringToHugeInteger = functor;
	}

	public static void load(HugeIntegerFactory factory) {
		instance = factory;
	}

	public static HugeInteger newHugeInteger(String value) {
		return instance.stringToHugeInteger.evaluate(value);
	}

}
