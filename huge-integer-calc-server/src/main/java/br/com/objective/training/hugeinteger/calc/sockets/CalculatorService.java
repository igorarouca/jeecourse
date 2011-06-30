package br.com.objective.training.hugeinteger.calc.sockets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import br.com.objective.training.hugeinteger.calc.Calculator;

class CalculatorService implements Service, Runnable {

	private static final int SECONDS_TO_STOP = 10;

	private final Calculator<String> component;

	private final int capacity;
	private final ExecutorService threadPool;

	private ServerSocket serverSocket;

	private List<CalculatorSkeleton> skeletons;

	CalculatorService(Calculator<String> component, int capacity) throws IOException {
		this.component = component;
		this.capacity = capacity;
		this.threadPool = Executors.newFixedThreadPool(capacity + 1);
		this.skeletons = new ArrayList<CalculatorSkeleton>();
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

		} catch (Exception e) {
			stop(); 
		}
	}

	private void startListening() throws IOException {
		while(true)
			threadPool.execute(
				createSkeleton(component, serverSocket.accept())
			);
	}

	@Override
	public void stop() {
		killSkeletons();
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

	private void killSkeletons() {
		for (CalculatorSkeleton skeleton : skeletons) skeleton.kill();
	}

	@Override
	public boolean isAlive() {
		return !threadPool.isTerminated();
	}

	private Runnable createSkeleton(final Calculator<String> calculator, final Socket socket) {
		try {
			CalculatorSkeleton skeleton = new CalculatorSkeleton(calculator, socket);
			skeletons.add(skeleton);
			return skeleton;

		} catch (IOException ioe) {
			throw new RuntimeException(">>> Calculator skeleton terminated unexpectedly!", ioe);
		}
	}

}
