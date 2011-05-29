package br.com.objective.training.hugeinteger.factory;

import br.com.objective.training.foundation.ReadOnly;
import br.com.objective.training.hugeinteger.HugeInteger;

class HugeIntegerImpl implements HugeInteger, ReadOnly {

	private final int[] value;

	HugeIntegerImpl(final String value) {
		this(parse(value));
	}

	private HugeIntegerImpl(final int[] value) {
		this.value = value;
	}

	private static int[] parse(String value) {
		int size = value.length();
		int[] result = new int[size];

		for (int i = 0; i < size; ++i)
			result[i] = value.charAt(size - i - 1) - '0';

		return result; 
	}

	public HugeInteger add(HugeInteger other) {
		int[] a = value;
		int[] b = ((HugeIntegerImpl) other).value;

		// Swaps 'a' and 'b', so 'a' always contains the bigger value
		if (a.length < b.length) {
			a = b;
			b = value;
		}

		int size = a.length + 1; // add 1 for the carry (example: 99 + 1 = 100)
		int[] result = new int[size];

		int carry = 0;
		for (int i = 0; i < a.length; ++i) {
			result[i] = a[i] + (i < b.length ? b[i] : 0) + carry;
			carry = result[i] < 10 ? 0 : 1; // Comparing is faster than using division: (result[i] / 10)
			result[i] = result[i] % 10;
		}

		if (carry != 0) {
			result[size - 1] = carry;			
		} else {
			int[] temp = new int[size - 1];
			System.arraycopy(result, 0, temp, 0, size - 1);
			result = temp;
		}

		return new HugeIntegerImpl(result);
	}

	@Override
	public HugeInteger subtract(HugeInteger other) {
		
		return null;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(value.length);

		for (int i = value.length - 1; i >= 0; --i)
			result.append(value[i]);

		return result.toString();
	}

}
