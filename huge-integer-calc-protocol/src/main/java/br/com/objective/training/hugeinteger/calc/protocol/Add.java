package br.com.objective.training.hugeinteger.calc.protocol;

public class Add extends Operation {

	private static final long serialVersionUID = 8106485309082547603L;

	public Add() {
		super();
	}

	public Add(String leftOperand, String rightOperand) {
		super(leftOperand, rightOperand);
	}
	
	@Override
	public String toString() {
		return "add" + super.toString();
	}

}
