package br.com.objective.training.hugeinteger.calc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import br.com.objective.training.foundation.Producer;
import br.com.objective.training.hugeinteger.calc.protocol.Add;
import br.com.objective.training.hugeinteger.calc.protocol.Compare;
import br.com.objective.training.hugeinteger.calc.protocol.Operation;
import br.com.objective.training.hugeinteger.calc.protocol.Subtract;

public class CalculatorServiceLocator {

	private static final CalculatorFactory calculatorFactory;

	static {
		calculatorFactory = CalculatorFactory.load(new Producer<Calculator<String>>() { @Override public Calculator<String> produce() {
			return new CalculatorStub(defaultIp(), defaultPort());
		}});
	}

	private CalculatorServiceLocator() {}

	public static Calculator<String> newCalculator() {
		return calculatorFactory.newCalculator();
	}

	private static InetAddress defaultIp() {
		InetAddress addressIp = null;
		try {
			addressIp = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addressIp;
	}

	private static int defaultPort() {
		return 9876;
	}

	private static class CalculatorStub implements Calculator<String> {

		private final Socket socket;
		private final ObjectInputStream inputStream;
		private final ObjectOutputStream outputStream;

		private CalculatorStub(InetAddress ip, int port) {
			try {
				this.socket = new Socket(ip, port);
				this.inputStream = new ObjectInputStream(socket.getInputStream());
				this.outputStream = new ObjectOutputStream(socket.getOutputStream());

			} catch (IOException ioe) {
				throw new RuntimeException("Error trying to initialize calculator");
			}
		}
		
		@Override
		public String compare(String left, String right) {
			return sendRequest(new Compare(left, right));
		}

		@Override
		public String add(String left, String right) {
			return sendRequest(new Add(left, right));
		}

		@Override
		public String subtract(String left, String right) {
			return sendRequest(new Subtract(left, right));
		}

		private String sendRequest(Operation operation) {
			String result = null;
			System.out.println("Sending request: " + operation);
			try {
				outputStream.writeObject(operation);
				result = inputStream.readObject().toString();

			} catch (Exception e) {
				e.printStackTrace(System.err);
				throw new RuntimeException("Error computing operation");
			}

			return result;
		}

	}

}
