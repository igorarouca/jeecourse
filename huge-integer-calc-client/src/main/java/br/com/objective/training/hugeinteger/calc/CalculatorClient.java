package br.com.objective.training.hugeinteger.calc;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {

	public static void main(String args[]) {
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
			Registry registry = LocateRegistry.getRegistry(args[0]);

			@SuppressWarnings("unchecked")
			CalculatorX<String, RemoteException> calc = (CalculatorX<String, RemoteException>) registry.lookup("Calculator");

			System.out.println("1 + 1 = " + calc.add("1", "1"));

		} catch (Exception e) {
			System.err.println("ComputePi exception:");
			e.printStackTrace();
		}
	}

}
