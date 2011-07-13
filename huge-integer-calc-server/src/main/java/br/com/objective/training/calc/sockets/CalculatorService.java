package br.com.objective.training.calc.sockets;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import br.com.objective.training.calc.Calculator;
import br.com.objective.training.util.IOUtils;

class CalculatorService implements Service, Runnable, Closeable {

	private static final int SECONDS_TO_STOP = 10;

	private final Calculator<String> component;

	private final int capacity;
	private final ExecutorService threadPool;

	private CountDownLatch latch;

	private ServerSocket serverSocket;

	private List<CalculatorSkeleton> skeletons;

	private boolean isAcceptingRequests;

	CalculatorService(Calculator<String> component, int capacity) throws IOException {
		this.component = component;
		this.capacity = capacity;
		this.threadPool = Executors.newFixedThreadPool(capacity + 1);
		this.skeletons = new ArrayList<CalculatorSkeleton>();
	}

	@Override
	public void startOn(InetAddress ip, int port) throws IOException {
		serverSocket = new ServerSocket(port, capacity, ip);
		latch = new CountDownLatch(1);
		threadPool.execute(this);
		waitUntilServiceStartsAcceptingRequests();
		System.out.println("[Service] >>> Calculator Service Started");
	}

	private void waitUntilServiceStartsAcceptingRequests() {
		try {
			latch.await();

		} catch (InterruptedException ie) {
			System.err.println("[Service] >>> Service's main thread was interrupted!");
			throw new RuntimeException(ie);
		}
	}

	@Override
	public void run() {
		try {
			isAcceptingRequests = true;
			latch.countDown();
			startAcceptingRequests();

		} catch (Exception e) {
			stop();
		}
	}

	private void startAcceptingRequests() throws IOException {
		while(isAcceptingRequests) {
			System.out.println("[Service] >>> Waiting for connections on: " + serverSocket);
			Socket clientSocket = serverSocket.accept();
			threadPool.execute(createSkeleton(component, clientSocket));
		}
	}

	@Override
	public void stop() {
		if (!isAcceptingRequests) {
			System.out.println("[Service] Calculator Service wasn't running.");
			return;
		}

		stopAcceptingRequests();

		threadPool.shutdown();
		try {
			if (!threadPool.awaitTermination(SECONDS_TO_STOP, TimeUnit.SECONDS)) {
				threadPool.shutdownNow();
		       if (!threadPool.awaitTermination(SECONDS_TO_STOP, TimeUnit.SECONDS))
		           System.err.println("[Service] >>> Service did not terminate as expected!");
		     }

		} catch (InterruptedException ie) {
			threadPool.shutdownNow();
			Thread.currentThread().interrupt();
	   }
	}

	private void stopAcceptingRequests() {
		System.out.println("[Service] >>> Calculator Service will no longer accept requests...");
		isAcceptingRequests = false;
		IOUtils.closeSilently(this);
		makeSkeletonsStopAcceptingRequests();
	}

	@Override
	public void close() throws IOException {
		this.serverSocket.close();
	}

	private void makeSkeletonsStopAcceptingRequests() {
		for (CalculatorSkeleton skeleton : skeletons) skeleton.stop();
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
			throw new RuntimeException("[Service] >>> Calculator skeleton terminated unexpectedly!", ioe);
		}
	}

}
