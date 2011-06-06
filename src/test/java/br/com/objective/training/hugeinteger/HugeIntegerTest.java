package br.com.objective.training.hugeinteger;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.objective.training.foundation.Functor;

public class HugeIntegerTest {

	private static final String TOO_LARGE_FOR_LONG = "9223372036854775808";

	@BeforeClass
	public static void configureHugeIntegerFactory() {
		HugeIntegerFactory.load(new HugeIntegerFactory(
			new Functor<String, HugeInteger>() { @Override public HugeInteger evaluate(String value) {
				return new HugeIntegerImpl(value);
			}}
		));
	}

	@Test(expected = NumberFormatException.class)
	public void valueTooLargeToFitInALong() {
		Long.parseLong(TOO_LARGE_FOR_LONG);
	}

	@Test
	public void instantiation() {
		assertEquals(TOO_LARGE_FOR_LONG, hugeInt(TOO_LARGE_FOR_LONG).toString());
	}

	@Test
	public void compare() {
		assertEquals(-1, compare("1", "2"));
		assertEquals( 1, compare("2", "1"));
		assertEquals( 0, compare("1", "1"));
		assertEquals( 0, compare("0", "0"));
		assertEquals( 1, compare("1", "0"));
		assertEquals(-1, compare("0", "1"));
		assertEquals(-1, compare("-1", "1"));
		assertEquals( 1, compare("1", "-1"));
		assertEquals( 1, compare("-3", "-4"));
		assertEquals(-1, compare("-4", "-3"));
		assertEquals( 0, compare(TOO_LARGE_FOR_LONG, TOO_LARGE_FOR_LONG));
		assertEquals( 1, compare(TOO_LARGE_FOR_LONG + 1, TOO_LARGE_FOR_LONG));
		assertEquals(-1, compare(TOO_LARGE_FOR_LONG, 1 + TOO_LARGE_FOR_LONG));
		assertEquals( 1, compare(TOO_LARGE_FOR_LONG, negate(TOO_LARGE_FOR_LONG)));
		assertEquals(-1, compare(negate(TOO_LARGE_FOR_LONG), TOO_LARGE_FOR_LONG));
		assertEquals( 0, compare(negate(TOO_LARGE_FOR_LONG), negate(TOO_LARGE_FOR_LONG)));
	}

	private int compare(String left, String right) {
		return hugeInt(left).compareTo(hugeInt(right));
	}

	@Test
	public void add() {
		assertEquals("7", add("3", "4"));
		assertEquals("7", add("4", "3"));
		assertEquals("1", add("0", "1"));
		assertEquals("1", add("1", "0"));
		assertEquals("0", add("0", "0"));
		assertEquals("100", add("99", "1"));
		assertEquals("100", add("1", "99"));
		assertEquals("100", add("01", "0099"));
		assertEquals("-98", add("-99", "1"));
		assertEquals("-100", add("-99", "-1"));
		assertEquals("-100", add("-000099", "-01"));
		assertEquals(TOO_LARGE_FOR_LONG, add(TOO_LARGE_FOR_LONG, "0"));
		assertEquals("0", add(TOO_LARGE_FOR_LONG, negate(TOO_LARGE_FOR_LONG)));
		assertEquals("1" + TOO_LARGE_FOR_LONG, add(TOO_LARGE_FOR_LONG, "10000000000000000000"));
		assertEquals("-1", add(TOO_LARGE_FOR_LONG, TOO_LARGE_FOR_LONG, negate(TOO_LARGE_FOR_LONG), negate(TOO_LARGE_FOR_LONG), "1", "-2"));
	}

	private String add(String... operands) {
		HugeInteger result = hugeInt("0");
		for (String operand : operands)
			result = result.add(hugeInt(operand));

		return result.toString();
	}

	@Test
	public void subtract() {
		assertEquals("1", subtract("120", "119"));
		assertEquals("119", subtract("120", "1"));
		assertEquals("-119", subtract("-120", "-1"));
		assertEquals("121", subtract("120", "-1"));
		assertEquals("-121", subtract("-120", "1"));
		assertEquals("0", subtract(TOO_LARGE_FOR_LONG, TOO_LARGE_FOR_LONG));
		assertEquals("0", subtract(TOO_LARGE_FOR_LONG, negate(TOO_LARGE_FOR_LONG), TOO_LARGE_FOR_LONG, TOO_LARGE_FOR_LONG));
	}

	private String subtract(String... operands) {
		HugeInteger result = hugeInt(operands[0]);
		for (int i = 1; i < operands.length; ++i)
			result = result.subtract(hugeInt(operands[i]));

		return result.toString();
	}

	private HugeInteger hugeInt(String value) {
		return HugeIntegerFactory.newHugeInteger(value);
	}

	private String negate(String value) {
		return "-" + value;
	}

}
