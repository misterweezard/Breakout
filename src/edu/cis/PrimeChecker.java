/*
 * File: PrimeChecker.java
 * -----------------------------
 * Checks whether a series of numbers are prime
 */

package edu.cis;

import acm.program.*;
import edu.cis.Breakout;

public class PrimeChecker extends ConsoleProgram {

	int[] testCases = {2, 3, 8, 37,  42, 87, 361, 382, 729, 1019};
	boolean[] answers = {true, true, false, true, false, false, false, false, false, true};

	/**
	 * Don't modify this method! You only need to edit the 
	 * isPrime method so that it works correctly. 
	 *
	 * The run method just tests your method against a variety
	 * of numbers to make sure that it's behaving as expected.
	 */
	public void run() {
		for (int i = 0; i < testCases.length; i++) {
			int testCase = testCases[i];
			boolean solution = answers[i];
			boolean returned = isPrime(testCase);

			if (solution == returned) {
				println("Your solution worked for n = " + testCase + ".");

			} else {
				println("Your method returned " + returned + " for n = " +
						testCase + ", but it should have returned " + solution + ".");
			}
		}
	}

	/**
	 *
	 * @param n a positive integer greater than 1
	 * @return true if n is prime and false otherwise
	 */
	public boolean isPrime(int n)
	{
		boolean prime = true;
		for(int count = 2; count <= n/2 ; count++)
		{
			if(n%count ==0)
				return false;
		}
		return (prime);
	}

	public static void main(String[] args) {
		new PrimeChecker().start();
	}

}
