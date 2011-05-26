package hugeinteger;

import org.junit.Test;

public class HugeIntegerTest {

	@Test (expected = NumberFormatException.class)
	public void tooLarge() {
		Long.parseLong("9223372036854775808");
	}

}
