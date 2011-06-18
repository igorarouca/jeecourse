package br.com.objective.training.hugeinteger.calc.protocol;

import java.io.Serializable;

public abstract class BinaryOperation implements Serializable {

	private static final long serialVersionUID = -8657406388700558807L;

	private String leftOperand;
	private String rightOperand;

	public BinaryOperation() {
		super();
	}

	public BinaryOperation(String leftOperand, String rightOperand) {
		this();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public String getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(String leftOperand) {
		this.leftOperand = leftOperand;
	}

	public String getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(String rightOperand) {
		this.rightOperand = rightOperand;
	}

}
