package dev.aziz;

import java.math.BigInteger;

public class ThirdTask {
//    Find the sum of the digits in the number 100! (i.e. 100 factorial)
//      {Correct answer: 648}
public static void main(String[] args) {
    // Step 1: Calculating 100!
    BigInteger factorial = calculateFactorial(100);

    // Step 2: Sum the digits of 100!
    int sumOfDigits = sumDigits(factorial);

    System.out.println("Sum of digits in 100! is: " + sumOfDigits);
}

    public static BigInteger calculateFactorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static int sumDigits(BigInteger number) {
        // Convert BigInteger to String
        String numStr = number.toString();
        int sum = 0;

        // Loop through each character in the string, convert it to an int, and add to sum
        for (int i = 0; i < numStr.length(); i++) {
            sum += Character.getNumericValue(numStr.charAt(i));
        }

        return sum;
    }
}
