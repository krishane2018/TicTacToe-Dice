package dice;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * 
 * @author Krishan Easparan
 *
 */

public class DiceClient {

	/**
	 * Determines the average value and standard deviation of a pair of die being
	 * rolled 2000 times. Also displays a histogram representing every 10 times a
	 * value was rolled.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		Dice myDie = new Dice(); // initialization of variables
		double sum = 0;
		double sqSum = 0;
		double mean = 0;
		double variance = 0;
		double stdVar = 0;
		int totalRolled = 0;
		int everyTen = 0;
		Dictionary<Integer, Integer> numDieValues = new Hashtable<Integer, Integer>() {
			{ 				
				// dictionary created to hold number of times rolled
				put(1, 0);
				put(2, 0);
				put(3, 0);
				put(4, 0);
				put(5, 0);
				put(6, 0);
			}
		};
		for (int i = 0; i < 2000; i++) {	// rolls a pair of die 2000 times
			myDie.roll(); 					// and stores the number of times the value is rolled.
			for (int j = 0; j < myDie.getDieValues().length; j++) { 
				sum += myDie.getDieValues()[j];
				sqSum += myDie.getDieValues()[j] * myDie.getDieValues()[j];
				numDieValues.put(myDie.getDieValues()[j], 
						numDieValues.get(myDie.getDieValues()[j]) + 1);
			}
		}
		mean = sum / 4000; 							// calculate and print out average roll
		variance = (sqSum / 4000) - (mean * mean); // and standard deviation.
		stdVar = Math.sqrt(variance);
		System.out.println("The average roll was " + mean);
		System.out.println("The standard deviation of the rolls was " + stdVar);
		System.out.println("The histogram of the rolls is:");
		for (int i = 1; i < 7; i++) { 			// prints histogram by printing out an
			totalRolled = numDieValues.get(i); 	// asterisk for every 10 times a value was rolled.
			everyTen = totalRolled / 10; 
			System.out.format("%d(%d) :", i, totalRolled);
			for (int j = 0; j < everyTen; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}

	}

}
