package br.com.objective.training.hugeinteger.calc;

import java.io.IOException;
import java.net.InetSocketAddress;

public interface Service {

	void startOn(InetSocketAddress address) throws IOException;

	void stop();

	boolean isAlive();

}
