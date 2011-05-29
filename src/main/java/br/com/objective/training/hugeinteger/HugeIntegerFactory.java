package br.com.objective.training.hugeinteger;

public class HugeIntegerFactory {

	public static HugeInteger newInstance(String value) {
		return new HugeIntegerImpl(value);
	}

}
