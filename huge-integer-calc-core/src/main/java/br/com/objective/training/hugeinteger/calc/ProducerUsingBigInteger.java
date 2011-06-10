package br.com.objective.training.hugeinteger.calc;

import java.math.BigInteger;

import br.com.objective.training.foundation.Functor;
import br.com.objective.training.foundation.Producer;
import br.com.objective.training.hugeinteger.UnaryOperations;

public class ProducerUsingBigInteger implements Producer<Calculator<String>> {

	private static final ProducerUsingBigInteger instance = new ProducerUsingBigInteger();

	private ProducerUsingBigInteger() {}

	public static ProducerUsingBigInteger getInstance() {
		return instance;
	}

	@Override
	public Calculator<String> produce() {
		return new CalculatorImpl<BigIntegerAdapter>(
			new Functor<String, BigIntegerAdapter>() { @Override public BigIntegerAdapter evaluate(String value) {
				return new BigIntegerAdapter(value);
			}}
		);
	}

	private static class BigIntegerAdapter extends BigInteger implements UnaryOperations<BigInteger> {

		private static final long serialVersionUID = 1346997383310648995L;

		private BigIntegerAdapter(String value) { super(value); }

		@Override public int compareTo(BigInteger value) { return super.compareTo(value); }
		@Override public BigInteger add(BigInteger value) { return super.add(value); }
		@Override public BigInteger subtract(BigInteger value) { return super.subtract(value); }

	}

}
