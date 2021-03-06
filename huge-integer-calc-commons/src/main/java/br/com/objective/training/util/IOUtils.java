package br.com.objective.training.util;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {

	private IOUtils() {};

	public static void closeSilently(Closeable closeable) {
		try {
			closeable.close();

		} catch (IOException ignored) {}
	}

}
