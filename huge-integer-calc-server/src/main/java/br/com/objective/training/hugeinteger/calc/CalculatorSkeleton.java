package br.com.objective.training.hugeinteger.calc;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import br.com.objective.training.foundation.IOUtils;
import br.com.objective.training.hugeinteger.calc.protocol.Add;
import br.com.objective.training.hugeinteger.calc.protocol.Operation;
import br.com.objective.training.hugeinteger.calc.protocol.Compare;
import br.com.objective.training.hugeinteger.calc.protocol.Subtract;

class CalculatorSkeleton implements Runnable, Closeable {

	private final Calculator<String> servant;

	private final Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	private boolean isAlive;

	CalculatorSkeleton(final Calculator<String> delegate, final Socket socket) throws IOException {
		this.servant = delegate;
		this.socket = socket;
		this.inputStream = new ObjectInputStream(socket.getInputStream());
		this.outputStream = new ObjectOutputStream(socket.getOutputStream());
		this.isAlive = true;
	}

	@Override
	public void run() {
		try {
			while(isAlive) {
				processRequest((Operation) inputStream.readObject());
			}

		} catch(SocketException se) {
			System.out.println(">>> Calculator Skeleton closed.");
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeSilently(this);
		}
	}

	@Override
	public void close() throws IOException {
		this.socket.close();
	}

	private void processRequest(Operation operation) {
		System.out.println("Incoming request: " + operation);
		String result = "Invalid Operation";

		if (operation instanceof Add)
			result = servant.add(operation.getLeftOperand(), operation.getRightOperand());

		if (operation instanceof Subtract)
			result = servant.subtract(operation.getLeftOperand(), operation.getRightOperand());

		if (operation instanceof Compare)
			result = servant.compare(operation.getLeftOperand(), operation.getRightOperand());

		sendResponse(result);
	}

	private void sendResponse(String result) {
		System.out.println("Sending result: " + result);
		try {
			outputStream.writeObject(result);
			outputStream.flush();

		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	public void kill() {
		this.isAlive = false;
	}

}
