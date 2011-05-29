package br.com.objective.training.hugeinteger.factory;

import br.com.objective.training.hugeinteger.HugeInteger;

public class HugeIntegerFactory {

	public static HugeInteger newInstance(String value) {
		return new HugeIntegerImpl(value);
	}

}
