import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        
        while (true) {
            int randomNumber = rand.nextInt(100) + 1;
            final int MAX_ATTEMPTS = 5;
            int lowerBound = 1;
            int upperBound = 100;

            System.out.println("\n---------------------------------------");
            System.out.println("NEW GAME");
            System.out.println("---------------------------------------");
            System.out.println("Pick a number between 1 and 100! You will get " + MAX_ATTEMPTS + " turns.");

            for (int attemptsLeft = MAX_ATTEMPTS; attemptsLeft > 0; attemptsLeft--) {
                System.out.print("\nPick a number between " + lowerBound + "-" + upperBound + " and turns left " + attemptsLeft + ": ");
                int playerGuess = scan.nextInt();

                System.out.println("Player guesses " + playerGuess);

                if (playerGuess == randomNumber) {
                    System.out.println("YOU WIN! The SECRET number was " + randomNumber);
                    break;
                }

                if (playerGuess < randomNumber) {
                    System.out.println("  Sorry, that is too low.");
                    lowerBound = Math.max(lowerBound, playerGuess + 1);
                } else {
                    System.out.println("  Sorry, that number is too high.");
                    upperBound = Math.min(upperBound, playerGuess - 1);
                }

                if (attemptsLeft == 1) {
                    System.out.println("Game Over! You've used all your turns.");
                    System.out.println("The secret number was " + randomNumber);
                }
            }
        }
    }
}
