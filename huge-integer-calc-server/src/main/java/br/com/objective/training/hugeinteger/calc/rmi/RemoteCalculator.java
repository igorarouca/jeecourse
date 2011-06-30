package br.com.objective.training.hugeinteger.calc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.com.objective.training.hugeinteger.calc.CalculatorX;

public interface RemoteCalculator extends Remote, CalculatorX<String, RemoteException> {}
