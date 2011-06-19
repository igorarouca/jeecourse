package br.com.objective.training.hugeinteger.calc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;

public class CalculatorService implements Service, Calculator<String>, Runnable {

	private static final int DEFAULT_PAYLOAD = 10;

	private static CalculatorService instance;

	private boolean isAlive;
	private final int payloadLimit;
	private ServerSocket serverSocket;

	private final Calculator<String> calculator;

	private CalculatorService(Calculator<String> calculator, int payloadLimit) throws IOException {
		this.isAlive = false;
		this.payloadLimit = payloadLimit;
		this.calculator = calculator;
	}

	public static CalculatorService load(CalculatorFactory calculatorFactory) throws IOException {
		return load(calculatorFactory, DEFAULT_PAYLOAD);
	}

	public static CalculatorService load(CalculatorFactory calculatorFactory, int requestLimit) throws IOException {
		return instance = new CalculatorService(calculatorFactory.newCalculator(), requestLimit);
	}

	@Override
	public void startOn(InetSocketAddress address) throws IOException {
		serverSocket = new ServerSocket(address.getPort(), payloadLimit, address.getAddress());
		isAlive = true;
		start();
	}

	private void start() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			startListening();

		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new RuntimeException(ioe); 
		}
	}

	private void startListening() throws IOException {
		while(isAlive)
			try {
				serverSocket.accept();

			} catch(SocketException ioe) {
				throw new RuntimeException("Service Stopped!", ioe);
			}
	}

	@Override
	public void stop() {
		isAlive = false;
		try {
			serverSocket.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new RuntimeException(ioe);
		}
	}

	@Override
	public boolean isAlive() {
		return isAlive;
	}

	@Override
	public int compare(String left, String right) {
		return 0;
	}

	@Override
	public String add(String left, String right) {
		return null;
	}

	@Override
	public String subtract(String left, String right) {
		return null;
	}



}
