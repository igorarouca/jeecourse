package br.com.objective.training.hugeinteger.calc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

	/**
	 * @param calculatorFactory
	 */
	public CalculatorGUI(Calculator<String> calculator) {
		this.calculator = calculator;

		window = new JFrame(CALCULATOR_NAME);

		/* --- North Panel --- */
		GridLayout gridLayout = new GridLayout(3, 2);
		JPanel northPanel = new JPanel(gridLayout);

		leftOperandLabel = new JLabel("Left Operand:");
		leftOperandField = new JTextField();
		northPanel.add(leftOperandLabel);
		northPanel.add(leftOperandField);

		rightOperandLabel = new JLabel("Right Operand:");
		rightOperandField = new JTextField();
		northPanel.add(rightOperandLabel);
		northPanel.add(rightOperandField);

		resultLabel = new JLabel("Result:");
		resultField = new JTextField();
		northPanel.add(resultLabel);
		northPanel.add(resultField);

		window.add(northPanel, BorderLayout.NORTH);

		/* --- South Panel --- */
		JPanel southPanel = new JPanel(new FlowLayout());

		addButton = new JButton("+");
		addButton.addActionListener(
			new ActionListener() { @Override public void actionPerformed(ActionEvent event) { add(); }}
		);
		southPanel.add(addButton);

		subtractButton = new JButton("-");
		subtractButton.addActionListener(
			new ActionListener() { @Override public void actionPerformed(ActionEvent event) { subtract(); }}
		);
		southPanel.add(subtractButton);

		compareButton = new JButton("<>");
		compareButton.addActionListener(
			new ActionListener() { @Override public void actionPerformed(ActionEvent event) { compare(); }}
		);
		southPanel.add(compareButton);

		window.add(southPanel, BorderLayout.SOUTH);

		window.setSize(400, 120);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	private void add() {
		String left = leftOperandField.getText();
		String right = rightOperandField.getText();

		resultField.setText(calculator.add(left, right));
	}

	private void compare() {
		String left = leftOperandField.getText();
		String right = rightOperandField.getText();

		resultField.setText(calculator.compare(left, right));
	}

	private void subtract() {
		String left = leftOperandField.getText();
		String right = rightOperandField.getText();

		resultField.setText(calculator.subtract(left, right));
	}

	public static void main(String[] args) {
		new CalculatorGUI(RemoteCalculatorFactory.instance().newCalculator()); 
	}
}
