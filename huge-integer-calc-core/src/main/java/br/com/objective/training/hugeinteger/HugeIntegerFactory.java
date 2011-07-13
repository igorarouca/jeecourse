package br.com.objective.training.hugeinteger;

public abstract class HugeIntegerFactory {

	private HugeIntegerFactory() {};

	public static HugeInteger newHugeInteger(String value) {
		return new HugeIntegerImpl(value);
	}

}
