package br.com.objective.training.hugeinteger.calc;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CalculatorConsole {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Scanner in = new Scanner(System.in);
		String[] input;
		String left;
		String operator;
		String right;
		Calculator<String> calculator = CalculatorFactory.load(ProducerUsingHugeInteger.getInstance()).newCalculator();

		while (true) {
			input = in.nextLine().split(" ");
			if (input[0].equals("exit")) {
				System.out.println("Programa terminado");
				break;
			}
			if (input.length != 3) {
				System.out.println("Erro - Comando Inválido");
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
			System.out.println("Erro - Operador " + operator + " Inválido");
		}
		in.close();
	}

}
