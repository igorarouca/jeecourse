package br.com.objective.training.calc.protocol.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.com.objective.training.calc.CalculatorX;

public interface RemoteCalculator extends Remote, CalculatorX<String, RemoteException> {}
