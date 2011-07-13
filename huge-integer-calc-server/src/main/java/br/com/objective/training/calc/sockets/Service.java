package br.com.objective.training.calc.sockets;

import java.io.IOException;
import java.net.InetAddress;

public interface Service {

	void startOn(InetAddress ip, int port) throws IOException;

	void stop();

	boolean isAlive();

}
