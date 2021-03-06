package br.com.objective.training.calc;

import br.com.objective.training.foundation.Functor;
import br.com.objective.training.hugeinteger.UnaryOperations;

class CalculatorImpl<T extends UnaryOperations<? super T>> implements Calculator<String> {

	private final Functor<String, T> stringParser;

	CalculatorImpl(Functor<String, T> stringParser) {
		this.stringParser = stringParser;
	}

	@Override
	public String compare(String left, String right) {
		return String.valueOf(parse(left).compareTo(parse(right)));
	}

	@Override
	public String add(String left, String right) {
		return parse(left).add(parse(right)).toString();
	}

	@Override
	public String subtract(String left, String right) {
		return parse(left).subtract(parse(right)).toString();
	}

	private T parse(String value) {
		return stringParser.evaluate(value);
	}

}
