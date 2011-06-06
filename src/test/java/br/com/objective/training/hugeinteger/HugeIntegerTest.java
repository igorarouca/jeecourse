package br.com.objective.training.hugeinteger;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HugeIntegerTest {

	private static final String TOO_LARGE_FOR_LONG = "9223372036854775808";

	@Test(expected = NumberFormatException.class)
	public void valueTooLargeToFitInALong() {
		Long.parseLong(TOO_LARGE_FOR_LONG);
	}

	@Test
	public void instantiation() {
		assertEquals(TOO_LARGE_FOR_LONG, hugeInt(TOO_LARGE_FOR_LONG).toString());
	}

	private HugeInteger hugeInt(String value) {
		return HugeIntegerFactory.newHugeInteger(value);
	}

}
