package br.com.objective.training.calc.sockets;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Properties;

import br.com.objective.training.calc.Calculator;

public class ServiceLocator {

	public static final String PROPERTIES_FILE = "service.properties"; 

	private static final ServiceLocator instance = new ServiceLocator();

	private Properties properties = new Properties();

	public static ServiceLocator instance() {
		return instance;
	}

	private ServiceLocator() {
		reloadProperties();
	}

	public void reloadProperties() {
		loadProperties(PROPERTIES_FILE);
	}

	private void loadProperties(String fileName) {
		try {
			properties.load(new FileInputStream(getFile(fileName)));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private File getFile(String fileName) throws URISyntaxException {
		return new File(getClass().getClassLoader().getResource(fileName).toURI());
	}

	public Calculator<String> getCalculator() {
		return new CalculatorStub(host(), port());
	}

	private InetAddress host() {
		try {
			return InetAddress.getByName(
				properties.getProperty("calculator.service.host")
			);
		} catch (UnknownHostException uhe) {
			throw new RuntimeException(uhe);
		}
	}

	private int port() {
		return Integer.parseInt(
			properties.getProperty("calculator.service.port")
		);
	}

	public static void main(String[] args) {
		ServiceLocator locator = ServiceLocator.instance();
		System.out.println("Service Address --> " + locator.host() + ":" + locator.port());
		locator.reloadProperties();
		System.out.println("Service Address --> " + locator.host() + ":" + locator.port());
	}

}
