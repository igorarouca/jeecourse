package br.com.objective.training.hugeinteger.calc.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.com.objective.training.hugeinteger.calc.Calculator;
import br.com.objective.training.hugeinteger.calc.CalculatorFactory;

public class CalculatorGUI {

	private static final String CALCULATOR_NAME = "Igordão's Incredible Calculator";

	private final Calculator<String> calculator;

	private final JFrame window;

	private final JLabel leftOperandLabel;
	private final JTextField leftOperandField;

	private final JLabel rightOperandLabel;
	private final JTextField rightOperandField;

	private final JLabel resultLabel;
	private final JTextField resultField;

	private final JButton addButton;
	private final JButton subtractButton;
	private final JButton compareButton;


	public CalculatorGUI(CalculatorFactory calculatorFactory) {
		this.calculator = calculatorFactory.newCalculator();

		window = new JFrame(CALCULATOR_NAME);

		leftOperandLabel = new JLabel("Left Operand:");
		leftOperandField = new JTextField("Enter Number");
		window.add(leftOperandLabel);
		window.add(leftOperandField);

		rightOperandLabel = new JLabel("Right Operand:");
		rightOperandField = new JTextField("Enter Number");
		window.add(rightOperandLabel);
		window.add(rightOperandField);

		resultLabel = new JLabel("Result:");
		resultField = new JTextField();
		window.add(resultLabel);
		window.add(resultField);

		addButton = new JButton("+");
		addButton.addActionListener(
			new ActionListener() { @Override public void actionPerformed(ActionEvent event) { add(); }}
		);
		window.add(addButton);

		subtractButton = new JButton("-");
		subtractButton.addActionListener(
			new ActionListener() { @Override public void actionPerformed(ActionEvent event) { subtract(); }}
		);
		window.add(subtractButton);

		compareButton = new JButton("<>");
		compareButton.addActionListener(
			new ActionListener() { @Override public void actionPerformed(ActionEvent event) { compare(); }}
		);
		window.add(compareButton);

		window.setSize(400, 120);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void add() {
		// setResult();
	}

	private void compare() {
		
	}

	private void subtract() {
		
	}

}
