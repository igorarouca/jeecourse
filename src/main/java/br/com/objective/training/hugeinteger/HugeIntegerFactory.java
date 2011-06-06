package br.com.objective.training.hugeinteger;

public class HugeIntegerFactory {

	private HugeIntegerFactory() {};

	public static HugeInteger newHugeInteger(String value) {
		return new HugeIntegerImpl(value);
	}

}
