package br.com.objective.training.hugeinteger.calc;

import br.com.objective.training.foundation.Functor;
import br.com.objective.training.foundation.Producer;
import br.com.objective.training.hugeinteger.HugeInteger;
import br.com.objective.training.hugeinteger.HugeIntegerFactory;

class ProducerUsingHugeInteger implements Producer<Calculator<String>> {

	private static final ProducerUsingHugeInteger instance = new ProducerUsingHugeInteger();

	private ProducerUsingHugeInteger() {};

	static ProducerUsingHugeInteger getInstance() {
		return instance;
	}

	@Override
	public Calculator<String> produce() {
		return new CalculatorImpl<HugeInteger>(
			new Functor<String, HugeInteger>() { @Override public HugeInteger evaluate(String value) {
				return HugeIntegerFactory.newHugeInteger(value);
			}}
		);
	}

}