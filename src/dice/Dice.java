package dice;

import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

/**
 * 
 * @author Krishan Easparan
 *
 */
public class Dice {

	private int[] die; // declaration of instance variables
	private Random random;

	/**
	 * Constructs a new Dice with a pair of die.
	 */

	public Dice() {

		this(2); // uses 1 parameter constructor
	}

	/**
	 * Constructs a new Dice with a specified number of die and rolls them.
	 * 
	 * @param numDice An integer representing the number of dice to be rolled.
	 */

	public Dice(int numDice) {
		if (numDice < 1)
			throw new IllegalArgumentException("Invalid input."); 	//checks for valid number of die
																	//and initializes die values.
		die = new int[numDice];
		random = new Random(new Date().hashCode());
		roll();
	}

	/**
	 * Helper method for roll(). Returns random dice value between 1 and 6.
	 * 
	 * @return A random integer representing a dice value.
	 */
	private int rollDie() {

		return (random.nextInt(6) + 1); //rolls random value between 1-6

	}

	/**
	 * Rolls the die of the Dice object using the helper method rollDie(). Returns
	 * the total sum of all the die values.
	 * 
	 * @return The total sum of all the die values.
	 */
	public int roll() {
		int total = 0;
		for (int i = 0; i < die.length; i++) {
			die[i] = rollDie(); // iterates through array of die and rolls them
			total += die[i]; // and calculates their total sum
		}
		return total;
	}

	/**
	 * Gets the die values of the Die object.
	 * 
	 * @return An integer array containing the die values of the the Die object.
	 */

	public int[] getDieValues() {
		int[] defensiveCopy = die.clone(); 	// makes a clone of die array and prevents
		return defensiveCopy; 				// user from accessing die array directly.
	}

	/**
	 * Returns true if there any double values in the die and returns false if there
	 * are none.
	 * 
	 * @return A boolean representing whether there are any double values in the
	 *         die.
	 */

	public boolean hasDoubles() {
		Dictionary<Integer, Integer> numDieValues = new Hashtable<Integer, Integer>();
		for (int i = 0; i < die.length; i++) { 		// iterates through die array and determines
			if (numDieValues.get(die[i]) == null) { // whether a value appears more than once.
				numDieValues.put(die[i], 1);
			} 
			else {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a string representation of the die values in the Dice object.
	 * 
	 * @return A String containing the die values of the Dice object.
	 */

	public String toString() {
		String output = ""; 					// Makes a string by iterating though die array
		for (int i = 0; i < die.length; i++) { 	// and gives die values.
			output += (die[i] + " ");
		}
		return output;
	}

}
