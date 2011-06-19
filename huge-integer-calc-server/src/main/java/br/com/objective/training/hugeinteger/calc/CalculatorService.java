package br.com.objective.training.hugeinteger.calc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import br.com.objective.training.foundation.IOUtils;

class CalculatorService implements Service, Runnable {

	private static final int SECONDS_TO_STOP = 10;

	private final Calculator<String> component;

	private final int capacity;
	private final ExecutorService threadPool;

	private ServerSocket serverSocket;

	private List<CalculatorServant> servants;

	CalculatorService(Calculator<String> component, int capacity) throws IOException {
		this.component = component;
		this.capacity = capacity;
		this.threadPool = Executors.newFixedThreadPool(capacity + 1);
		this.servants = new ArrayList<CalculatorServant>();
	}

	@Override
	public void startOn(InetAddress ip, int port) throws IOException {
		serverSocket = new ServerSocket(port, capacity, ip);
		threadPool.execute(this);
	}

	@Override
	public void run() {
		try {
			startListening();

		} catch (IOException ioe) {
			stop(); 
		}
	}

	private void startListening() throws IOException {
		while(true) {
			Runnable servant = createServant(component, serverSocket.accept());
			if (servant != null) threadPool.execute(servant);
		}
	}

	@Override
	public void stop() {
		closeServants();
		threadPool.shutdown();
		try {
			if (!threadPool.awaitTermination(SECONDS_TO_STOP, TimeUnit.SECONDS)) {
				threadPool.shutdownNow();
		       if (!threadPool.awaitTermination(SECONDS_TO_STOP, TimeUnit.SECONDS))
		           System.err.println(">>> Service did not terminate!");
		     }

		} catch (InterruptedException ie) {
			threadPool.shutdownNow();
			Thread.currentThread().interrupt();
	   }
	}

	private void closeServants() {
		for (CalculatorServant servant : servants) IOUtils.closeSilently(servant);
	}

	@Override
	public boolean isAlive() {
		return !threadPool.isTerminated();
	}

	private Runnable createServant(final Calculator<String> calculator, final Socket socket) {
		try {
			CalculatorServant servant = new CalculatorServant(calculator, socket);
			servants.add(servant);
			return servant;

		} catch (IOException ioe) {
			System.err.println(">>> Calculator servant terminated unexpectedly!");
			ioe.printStackTrace(System.err);
			return null;
		}
	}

}
