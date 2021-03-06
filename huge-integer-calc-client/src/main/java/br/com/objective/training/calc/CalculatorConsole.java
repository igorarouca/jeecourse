package br.com.objective.training.calc;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import br.com.objective.training.calc.sockets.ServiceLocator;

public class CalculatorConsole {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Calculator<String> calculator = ServiceLocator.instance().getCalculator();

		Scanner in = new Scanner(System.in);
		String[] input;
		String left;
		String operator;
		String right;

		while (true) {
			input = in.nextLine().split(" ");
			if (input[0].equals("exit")) {
				System.out.println("Programa terminado");
				break;
			}
			if (input.length != 3) {
				System.out.println("Erro - Comando Inv�lido");
				continue;
			}
			left = input[0];
			operator = input[1];
			right = input[2];
			if (operator.equals("+")) {
				System.out.println("resultado = " + calculator.add(left, right));
				continue;
			}
			if (operator.equals("-")) {
				System.out.println("resultado = " + calculator.subtract(left, right));
				continue;
			}
			if (operator.equals("=")) {
				System.out.println("resultado = " + calculator.compare(left, right));
				continue;
			}
			System.out.println("Erro - Operador " + operator + " Inv�lido");
		}
		in.close();
	}

}
