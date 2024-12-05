package dev.aziz;

import java.util.Scanner;

public class FirstTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n (number of parentheses pairs): ");
        int n = scanner.nextInt();

        System.out.println("Number of valid parentheses expressions: " + countValidParentheses(n));
    }

    public static int countValidParentheses(int n) {
        // DP array to store the number of valid parentheses sequences for each number of pairs
        int[] intArray = new int[n + 1];
        intArray[0] = 1;  // Base case: There is one valid sequence with 0 pairs (empty sequence)

        // Calculate the number of valid sequences for each number of pairs up to N
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                intArray[i] += intArray[j] * intArray[i - 1 - j];
            }
        }
        return intArray[n];
    }
}