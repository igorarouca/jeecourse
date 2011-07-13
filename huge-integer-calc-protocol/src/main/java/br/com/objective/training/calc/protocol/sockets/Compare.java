package br.com.objective.training.calc.protocol.sockets;

public class Compare extends Operation {

	private static final long serialVersionUID = 8106485309082547603L;

	public Compare() {
		super();
	}

	public Compare(String leftOperand, String rightOperand) {
		super(leftOperand, rightOperand);
	}
	
	@Override
	public String toString() {
		return "compare" + super.toString();
	}

}
