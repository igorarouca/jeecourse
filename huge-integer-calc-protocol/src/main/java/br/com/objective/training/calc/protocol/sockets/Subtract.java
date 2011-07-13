package br.com.objective.training.calc.protocol.sockets;

public class Subtract extends Operation {

	private static final long serialVersionUID = 8106485309082547603L;

	public Subtract() {
		super();
	}

	public Subtract(String leftOperand, String rightOperand) {
		super(leftOperand, rightOperand);
	}
	
	@Override
	public String toString() {
		return "subtract" + super.toString();
	}

}
