package br.com.objective.training.calc.sockets;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.com.objective.training.calc.Calculator;
import br.com.objective.training.calc.protocol.sockets.Add;
import br.com.objective.training.calc.protocol.sockets.Compare;
import br.com.objective.training.calc.protocol.sockets.Operation;
import br.com.objective.training.calc.protocol.sockets.Subtract;
import br.com.objective.training.util.IOUtils;

class CalculatorSkeleton implements Runnable, Closeable {

	private final Calculator<String> servant;

	private final Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	private boolean isAcceptingRequests;

	CalculatorSkeleton(final Calculator<String> delegate, final Socket socket) throws IOException {
		this.servant = delegate;
		this.socket = socket;
		this.inputStream = new ObjectInputStream(socket.getInputStream());
		this.outputStream = new ObjectOutputStream(socket.getOutputStream());
		this.isAcceptingRequests = true;
		System.out.println("[Skeleton] >>> Connection established: " + socket);
	}

	@Override
	public void run() {
		try {
			while(isAcceptingRequests) {
				processRequest((Operation) inputStream.readObject());
			}

		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			System.out.println("[Skeleton] >>> Calculator Skeleton closed on socket: " + this.socket);
			IOUtils.closeSilently(this);
		}
	}

	@Override
	public void close() throws IOException {
		this.socket.close();
	}

	private void processRequest(Operation operation) {
		System.out.println("[Skeleton] >>> Incoming request: " + operation);
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
		System.out.println("[Skeleton] >>> Sending result: " + result);
		try {
			outputStream.writeObject(result);
			outputStream.flush();

		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	public void stop() {
		System.out.println("[Skeleton] >>> Calculator Skeleton stopped accepting requests...");
		this.isAcceptingRequests = false;
	}

}
