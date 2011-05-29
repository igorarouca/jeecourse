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
		assertEquals(TOO_LARGE_FOR_LONG, hugeInteger(TOO_LARGE_FOR_LONG).toString());
	}

	@Test
	public void add() {
		assertEquals("7", hugeInteger("3").add(hugeInteger("4")).toString());
		assertEquals("7", hugeInteger("4").add(hugeInteger("3")).toString());
		assertEquals("100", hugeInteger("99").add(hugeInteger("1")).toString());
		assertEquals(TOO_LARGE_FOR_LONG, hugeInteger(TOO_LARGE_FOR_LONG).add(hugeInteger("0")).toString());
		assertEquals("9223372036854775809", hugeInteger(TOO_LARGE_FOR_LONG).add(hugeInteger("1")).toString());
	}

	private HugeInteger hugeInteger(String value) {
		return HugeIntegerFactory.newInstance(value);
	}

}
