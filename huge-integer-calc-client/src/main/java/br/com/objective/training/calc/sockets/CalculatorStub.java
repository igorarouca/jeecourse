package br.com.objective.training.calc.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import br.com.objective.training.calc.Calculator;
import br.com.objective.training.calc.protocol.sockets.Add;
import br.com.objective.training.calc.protocol.sockets.Compare;
import br.com.objective.training.calc.protocol.sockets.Operation;
import br.com.objective.training.calc.protocol.sockets.Subtract;

class CalculatorStub implements Calculator<String> {

	private final Socket socket;
	private final ObjectInputStream inputStream;
	private final ObjectOutputStream outputStream;

	CalculatorStub(InetAddress ip, int port) {
		try {
			System.out.println("[Stub] >>> Trying to connect to: " + ip + ":" + port);
			this.socket = new Socket(ip, port);
			System.out.println("[Stub] >>> Connection Established");
			this.outputStream = new ObjectOutputStream(socket.getOutputStream());
			this.inputStream = new ObjectInputStream(socket.getInputStream());

		} catch (IOException ioe) {
			throw new RuntimeException("[Stub] >>> Error trying to initialize calculator", ioe);
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
		System.out.println("[Stub] >>> Sending request: " + operation);
		try {
			outputStream.writeObject(operation);
			outputStream.flush();
			result = inputStream.readObject().toString();

		} catch(SocketException so) {
			System.err.println("[Stub] >>> Calculator servant terminated unexpectedly!");
		} catch (Exception e) {
			e.printStackTrace(System.err);
			throw new RuntimeException("[Stub] >>> Error computing operation");
		}

		System.out.println("[Stub] >>> Result received: " + result);
		return result;
	}

}
