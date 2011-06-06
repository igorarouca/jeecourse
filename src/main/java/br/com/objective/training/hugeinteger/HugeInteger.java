package br.com.objective.training.hugeinteger;

public interface HugeInteger {

	/**
	 *	other < this --> -1
	 *	other = this -->  0
	 *	other > this --> +1
	 */
	public abstract int compareTo(HugeInteger value);

	public abstract HugeInteger add(HugeInteger value);

	public abstract HugeInteger subtract(HugeInteger value);

	public abstract HugeInteger negate();

}
