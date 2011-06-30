package br.com.objective.training.hugeinteger.calc.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import br.com.objective.training.hugeinteger.calc.CalculatorFactory;

public class CalculatorServer {

	public static void main(String[] args) {

		if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

		try {
            RemoteCalculator calculator = new CalculatorService(CalculatorFactory.DEFAULT.newCalculator());
            RemoteCalculator stub = (RemoteCalculator) UnicastRemoteObject.exportObject(calculator, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Calculator", stub);
            System.out.println("Calculator bound");

		} catch (Exception e) {
            e.printStackTrace();
        }

	}

}
