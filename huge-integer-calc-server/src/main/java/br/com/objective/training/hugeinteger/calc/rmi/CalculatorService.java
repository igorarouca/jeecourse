package br.com.objective.training.hugeinteger.calc.rmi;

import java.rmi.RemoteException;

import br.com.objective.training.hugeinteger.calc.Calculator;

class CalculatorService implements RemoteCalculator {

	private final Calculator<String> servant;

	CalculatorService(Calculator<String> servant) {
		super();
		this.servant = servant;
	}

	@Override
	public String compare(String left, String right) throws RemoteException {
		return servant.compare(left, right);
	}

	@Override
	public String add(String left, String right) throws RemoteException {
		return servant.add(left, right);
	}

	@Override
	public String subtract(String left, String right) throws RemoteException {
		return servant.subtract(left, right);
	}

}
